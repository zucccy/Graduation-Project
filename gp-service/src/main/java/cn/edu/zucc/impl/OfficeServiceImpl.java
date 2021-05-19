package cn.edu.zucc.impl;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.OfficeService;
import cn.edu.zucc.dto.OfficeInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.mapper.HospitalRelOfficeMapper;
import cn.edu.zucc.mapper.OfficeMapper;
import cn.edu.zucc.po.HospitalRelOffice;
import cn.edu.zucc.po.HospitalRelOfficeExample;
import cn.edu.zucc.po.Office;
import cn.edu.zucc.po.OfficeExample;
import cn.edu.zucc.utils.CopyUtils;
import cn.edu.zucc.vo.ChildOfficeVO;
import cn.edu.zucc.vo.OfficeVO;
import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: 科室相关接口实现类
 *
 * @author chenyun
 * @since 09.04.2021
 */
@Slf4j
@Service
public class OfficeServiceImpl implements OfficeService {

    @Resource
    private OfficeMapper officeMapper;

    @Resource
    private HospitalRelOfficeMapper hospitalRelOfficeMapper;

    @Resource
    private HospitalService hospitalService;

    @Override
    public Office findOfficeById(Long id) {
        if (!count(id)) {
            throw new SourceNotFoundException("科室不存在");
        }
        return officeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ChildOfficeVO> findChildOfficeList(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("该科室不存在");
        }

        OfficeExample example = new OfficeExample();
        example.createCriteria().andParentIdEqualTo(id);
        return CopyUtils.copyList(officeMapper.selectByExample(example), ChildOfficeVO.class);
    }

    @Override
    public List<OfficeVO> findOfficeVOList(Long hospitalId) {

        if (!hospitalService.count(hospitalId)) {
            throw new SourceNotFoundException("医院不存在");
        }

        List<Office> officeList = findOfficeListByHosId(hospitalId, null, null);
        List<OfficeVO> officeVOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(officeList)) {
            officeList.forEach(office -> {
                OfficeVO officeVO = new OfficeVO();
                BeanUtils.copyProperties(office, officeVO);
                if (officeVO.getId() < 8) {
                    officeVOList.add(officeVO);
                }
            });
            //给科室集合中的每个科室添加子科室集合
            officeVOList.forEach(officeVO -> {
                List<ChildOfficeVO> childOfficeVOList = findChildOfficeList(officeVO.getId());
                if (CollectionUtil.isNotEmpty(childOfficeVOList)) {
                    officeVO.setChildOfficeList(childOfficeVOList);
                }
            });
        }
        return officeVOList;
    }

    @Override
    public boolean countChildOffice(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("该科室不存在");
        }

        OfficeExample example = new OfficeExample();
        example.createCriteria().andParentIdEqualTo(id);

        return officeMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<Office> findOfficeList(String officeName) {

        OfficeExample example = new OfficeExample();
        OfficeExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(officeName)) {
            criteria.andOfficeNameLike("%" + officeName + "%");
        }

        return officeMapper.selectByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(OfficeInfoDTO officeInfoDTO) {
        Office office = new Office();

        BeanUtils.copyProperties(officeInfoDTO, office);

        office.setCreateTime(new Date());
        office.setUpdateTime(new Date());

        return officeMapper.insertSelective(office) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Long id, OfficeInfoDTO officeInfoDTO) {

        //若父科室不存在
        if (!count(officeInfoDTO.getParentId())) {
            throw new SourceNotFoundException("父科室不存在");
        }

        Office office = new Office();

        BeanUtils.copyProperties(officeInfoDTO, office);

        office.setId(id);
        office.setUpdateTime(new Date());

        return officeMapper.updateByPrimaryKeySelective(office) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long id) {
        //若医院有该科室信息，则不允许删除
        if (hospitalService.countOfficeRelation(null, id)) {
            throw new FormException();
        }
        //该科室有若干子科室，则级联删除子科室
        if (countChildOffice(id)) {
            deleteChild(id);
        }
        return officeMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean deleteChild(Long id) {

        if (!count(id)) {
            throw new SourceNotFoundException("该科室不存在");
        }

        OfficeExample example = new OfficeExample();
        example.createCriteria().andParentIdEqualTo(id);

        return officeMapper.deleteByExample(example) > 0;
    }

    @Override
    public boolean count(Long id) {
        OfficeExample example = new OfficeExample();
        example.createCriteria().andIdEqualTo(id);

        return officeMapper.selectCountByExample(example) > 0;
    }

    @Override
    public List<Office> findOfficeListByHosId(Long hospitalId, Integer pageNum, Integer pageSize) {

        if (!hospitalService.count(hospitalId)) {
            throw new SourceNotFoundException("医院不存在");
        }

        HospitalRelOfficeExample example = new HospitalRelOfficeExample();
        example.createCriteria().andHospitalIdEqualTo(hospitalId);

        List<Long> officeIdList = hospitalRelOfficeMapper.selectByExample(example)
                .stream().map(HospitalRelOffice::getOfficeId).collect(Collectors.toList());

        if (null != pageNum && null != pageSize) {
            //开启分页
            PageHelper.startPage(pageNum, pageSize);
        }

        return findOfficeList(officeIdList);
    }

    @Override
    public List<OfficeVO> findOfficeListAll() {
        List<Office> officeList = officeMapper.selectAll();
        List<OfficeVO> officeVOList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(officeList)) {
            officeList.forEach(office -> {
                OfficeVO officeVO = new OfficeVO();
                BeanUtils.copyProperties(office, officeVO);
                if (officeVO.getId() < 8) {
                    officeVOList.add(officeVO);
                }
            });
            //给科室集合中的每个科室添加子科室集合
            officeVOList.forEach(officeVO -> {
                List<ChildOfficeVO> childOfficeVOList = findChildOfficeList(officeVO.getId());
                if (CollectionUtil.isNotEmpty(childOfficeVOList)) {
                    officeVO.setChildOfficeList(childOfficeVOList);
                }
            });
        }
        return officeVOList;
    }

    @Override
    public List<Office> findOfficeList(List<Long> officeIdList) {

        //用科室编号列表获取科室信息列表
        OfficeExample example = new OfficeExample();
        OfficeExample.Criteria criteria = example.createCriteria();

        if (CollectionUtil.isNotEmpty(officeIdList)) {
            criteria.andIdIn(officeIdList);
        } else {
            return new ArrayList<>();
        }
        return officeMapper.selectByExample(example);
    }
}
