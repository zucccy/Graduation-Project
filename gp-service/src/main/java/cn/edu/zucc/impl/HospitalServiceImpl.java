package cn.edu.zucc.impl;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.HospitalMapper;
import cn.edu.zucc.mapper.HospitalRelOfficeMapper;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import cn.edu.zucc.vo.AddressVO;
import cn.edu.zucc.vo.DoctorVO;
import cn.edu.zucc.vo.EssayVO;
import cn.edu.zucc.vo.HospitalInfoVO;
import cn.edu.zucc.vo.HospitalLocalationVO;
import cn.edu.zucc.vo.HospitalNewsVO;
import cn.edu.zucc.vo.OfficeVO;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description: 医院相关接口实现类
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Service
public class HospitalServiceImpl implements HospitalService {

    @Resource
    private HospitalMapper hospitalMapper;

    @Resource
    private HospitalRelOfficeMapper hospitalRelOfficeMapper;

    @Resource
    private DoctorService doctorService;

    @Resource
    private OfficeService officeService;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public Hospital findHospitalById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("医院不存在");
        }
        return hospitalMapper.selectByPrimaryKey(id);
    }

    @Override
    public HospitalInfoVO findHospitalVOById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("医院不存在");
        }
        HospitalInfoVO hospitalInfoVO = new HospitalInfoVO();
        BeanUtils.copyProperties(findHospitalById(id), hospitalInfoVO);
        //医生列表
        List<DoctorVO> doctorVOList = doctorService.findDoctorListByHosId(id, null, null);
        if (CollectionUtil.isNotEmpty(doctorVOList)) {
            hospitalInfoVO.setDoctorVOList(doctorVOList);
        } else {
            hospitalInfoVO.setDoctorVOList(new ArrayList<>());
        }
        //科室列表
        List<OfficeVO> officeVOList = officeService.findOfficeVOList(id);
        if (CollectionUtil.isNotEmpty(officeVOList)) {
            hospitalInfoVO.setOfficeVOList(officeVOList);
        } else {
            hospitalInfoVO.setOfficeVOList(new ArrayList<>());
        }
        return hospitalInfoVO;
    }

    @Override
    public List<Hospital> findHospitalList(String hospitalName, Integer pageNum, Integer pageSize) {
        if (null != pageNum && null != pageSize) {
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Hospital> hospitalList = hospitalMapper.findHospitalList(hospitalName);
        return hospitalList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(HospitalInfoDTO hospitalInfoDTO) {

        Hospital hospital = new Hospital();

        //将DTO的值复制到hospital实体中
        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setCreateTime(new Date());
        hospital.setUpdateTime(new Date());

        return hospitalMapper.insertSelective(hospital) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, HospitalInfoDTO hospitalInfoDTO) {
        Hospital hospital = new Hospital();

        BeanUtils.copyProperties(hospitalInfoDTO, hospital);

        hospital.setId(id);
        hospital.setUpdateTime(new Date());

        return hospitalMapper.updateByPrimaryKeySelective(hospital) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("医院不存在");
        }
        //若删除时，医院含有科室信息
        if (countOfficeRelation(id, null)) {
            //删除该医院下所有科室
            deleteOfficeRelation(id, null);
        }
        return hospitalMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean count(Long id) {
        HospitalExample example = new HospitalExample();

        example.createCriteria().andIdEqualTo(id);

        return hospitalMapper.selectCountByExample(example) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOfficeRelation(Long hospitalId, Long officeId) {

        HospitalRelOffice hospitalRelOffice = new HospitalRelOffice();
        //该医院存在
        if (!count(hospitalId)) {
            throw new SourceNotFoundException("医院不存在");
        }
        //该科室存在
        if (officeService.count(officeId)) {

            hospitalRelOffice.setHospitalId(hospitalId);
            hospitalRelOffice.setOfficeId(officeId);
            hospitalRelOffice.setCreateTime(new Date());
            hospitalRelOffice.setUpdateTime(new Date());
            hospitalRelOfficeMapper.insertSelective(hospitalRelOffice);
            //若该科室有父科室，要把父科室也加入到关系中
            Long parentId = officeService.findOfficeById(officeId).getParentId();
            if (officeService.count(parentId)) {
                //父科室不存在
                if (!countOfficeRelation(hospitalId, parentId)) {
                    hospitalRelOffice.setOfficeId(parentId);
                    hospitalRelOfficeMapper.insertSelective(hospitalRelOffice);
                }
            }
        } else {
            throw new SourceNotFoundException("科室不存在");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteOfficeRelation(Long hospitalId, Long officeId) {

        if (!countOfficeRelation(hospitalId, officeId)) {
            throw new SourceNotFoundException("该医院不存在该科室");
        }

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();

        HospitalRelOfficeExample.Criteria criteria = example.createCriteria();

        //若医院id不为空
        if (null != hospitalId) {
            criteria.andHospitalIdEqualTo(hospitalId);
        }
        //若科室id不为空
        if (null != officeId) {
            criteria.andOfficeIdEqualTo(officeId);
        }
        return hospitalRelOfficeMapper.deleteByExample(example) > 0;
    }

    @Override
    public boolean countOfficeRelation(Long hospitalId, Long officeId) {
        HospitalRelOfficeExample example = new HospitalRelOfficeExample();

        HospitalRelOfficeExample.Criteria criteria = example.createCriteria();
        //若医院id不为空
        if (null != hospitalId) {
            criteria.andHospitalIdEqualTo(hospitalId);
        }
        //若科室id不为空
        if (null != officeId) {
            criteria.andOfficeIdEqualTo(officeId);
        }
        return hospitalRelOfficeMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<Hospital> findHospitalList(Long officeId, Integer pageNum, Integer pageSize) {

        if (!officeService.count(officeId)) {
            throw new SourceNotFoundException("科室不存在");
        }
        HospitalRelOfficeExample example = new HospitalRelOfficeExample();
        example.createCriteria().andOfficeIdEqualTo(officeId);

        //得到医院编号列表
        List<Long> hospitalIdList = hospitalRelOfficeMapper.selectByExample(example)
                .stream().map(HospitalRelOffice::getHospitalId).collect(Collectors.toList());
        //得到包含该科室以及该父科室的医院信息列表

        //分页查询放在最后一个select语句前
        PageHelper.startPage(pageNum, pageSize);

        return findHospitalList(hospitalIdList);
    }

    @Override
    public List<Hospital> getAllHospitals(String address, String hospitalName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        HospitalExample hospitalExample = new HospitalExample();
        HospitalExample.Criteria criteria = hospitalExample.createCriteria();
        if (null != address) {
            criteria.andAddressLike("%" + address + "%");
        }
        if (null != hospitalName) {
            criteria.andHospitalNameLike("%" + hospitalName + "%");
        }
        return hospitalMapper.selectByExample(hospitalExample);
    }

    @Override
    public List<Hospital> findHospitalList(List<Long> hospitalIdList) {
        HospitalExample example = new HospitalExample();
        HospitalExample.Criteria criteria = example.createCriteria();

        if (CollectionUtil.isNotEmpty(hospitalIdList)) {
            criteria.andIdIn(hospitalIdList);
            return hospitalMapper.selectByExample(example);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<HospitalNewsVO> findHospitalNewsList() {

        List<HospitalNewsVO> hospitalNewsVOList = new ArrayList<>();

        Set<String> newsListStr = redisTemplate.opsForZSet().reverseRange("hospitalNews", 0, 8);
        if (CollectionUtil.isNotEmpty(newsListStr)) {
            newsListStr.forEach(newsStr -> {
                String newsTitle = StringUtils.substringBefore(newsStr, ":");
                String newsUrl = StringUtils.substringAfter(newsStr, ":");
                HospitalNewsVO hospitalNewsVO = new HospitalNewsVO();
                hospitalNewsVO.setNewsTitle(newsTitle);
                hospitalNewsVO.setNewsUrl(newsUrl);
                hospitalNewsVOList.add(hospitalNewsVO);
            });
        }
        return hospitalNewsVOList;
    }

    @Override
    public boolean addHospitalNews(HospitalNewsVO hospitalNewsVO) {
        //需要拼接字符串为title:url的形式
        String newsStr = null;
        if (!StringUtils.isEmpty(hospitalNewsVO.getNewsTitle()) && !StringUtils.isEmpty(hospitalNewsVO.getNewsUrl())) {
            newsStr = hospitalNewsVO.getNewsTitle() + ":" + hospitalNewsVO.getNewsUrl();
            redisTemplate.opsForZSet().add("hospitalNews", newsStr, 1);
        } else {
            throw new FormException();
        }
        return true;
    }

    @Override
    public boolean newsIncrementScore(HospitalNewsVO hospitalNewsVO) {
        //需要拼接字符串为title:url的形式
        String newsStr = null;
        if (!StringUtils.isEmpty(hospitalNewsVO.getNewsTitle()) && !StringUtils.isEmpty(hospitalNewsVO.getNewsUrl())) {
            newsStr = hospitalNewsVO.getNewsTitle() + ":" + hospitalNewsVO.getNewsUrl();
            redisTemplate.opsForZSet().incrementScore("hospitalNews", newsStr, 1);
        } else {
            throw new FormException();
        }
        return true;
    }

    @Override
    public boolean addHospitalLocation(HospitalLocalationVO hospitalLocalationVO) {
        Point point = new Point(hospitalLocalationVO.getLongitudes().doubleValue(), hospitalLocalationVO.getLatitudes().doubleValue());
        return redisTemplate.opsForGeo().add("HospitalLocation", point, String.valueOf(hospitalLocalationVO.getId())) > 0;
    }

    @Override
    public List<HospitalInfoVO> findAdviceHospitalList(AddressVO addressVO) {
        Double distance = addressVO.getDistance();
        List<Long> hospitalIdList = new ArrayList<>();
        //Point(经度, 纬度) Distance(距离量, 距离单位)
        Circle circle = new Circle(new Point(addressVO.getLongitudes().doubleValue(), addressVO.getLatitudes().doubleValue()), new Distance(distance, Metrics.KILOMETERS));
        //params: key, Circle 获取存储到redis中的distance范围内的所有医院位置信息
        GeoResults radius = redisTemplate.opsForGeo().radius("HospitalLocation", circle);
        List<GeoResult> contentList = radius.getContent();
        if (CollectionUtil.isNotEmpty(contentList)) {
            contentList.forEach(item -> {
                RedisGeoCommands.GeoLocation content = (RedisGeoCommands.GeoLocation) item.getContent();
                hospitalIdList.add(Long.valueOf((String) content.getName()));
            });
        }
        List<HospitalInfoVO> hospitalInfoVOList = new ArrayList<>();
        System.out.println(hospitalIdList);
        hospitalIdList.forEach(item -> {
            HospitalInfoVO hospitalInfoVO = findHospitalVOById(item);
            hospitalInfoVOList.add(hospitalInfoVO);
        });
        return CollectionUtil.isNotEmpty(hospitalInfoVOList) ? hospitalInfoVOList : new ArrayList<>();
    }

    @Override
    public List<EssayVO> findEssayList() {
        List<EssayVO> essayVOList = new ArrayList<>();

        Set<String> essayListStr = redisTemplate.opsForZSet().reverseRange("Essay", 0, 7);
        if (CollectionUtil.isNotEmpty(essayListStr)) {
            essayListStr.forEach(essayStr -> {
                String essayTitle = StringUtils.substringBefore(essayStr, ":");
                String essayUrl = StringUtils.substringAfter(essayStr, ":");
                essayUrl = StringUtils.substringBeforeLast(essayUrl, ":");
                String essayAuthor = StringUtils.substringAfterLast(essayStr, ":");
                EssayVO essayVO = new EssayVO();
                essayVO.setEssayTitle(essayTitle);
                essayVO.setEssayUrl(essayUrl);
                essayVO.setEssayAuthor(essayAuthor);
                essayVOList.add(essayVO);
            });
        }
        return essayVOList;
    }
}
