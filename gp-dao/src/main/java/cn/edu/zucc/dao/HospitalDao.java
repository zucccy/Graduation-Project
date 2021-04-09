package cn.edu.zucc.dao;

import cn.edu.zucc.mapper.HospitalMapper;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.po.HospitalExample;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 医院相关dao类，调用mapper接口
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Repository
public class HospitalDao {

    @Resource
    private HospitalMapper hospitalMapper;

    public Hospital findHospitalById(Long id) {
        HospitalExample example = new HospitalExample();
        example.createCriteria().andIdEqualTo(id);
        return hospitalMapper.selectOneByExample(example);
    }

    public List<Hospital> findHospitalList(String hospitalName, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        HospitalExample example = new HospitalExample();
        if (!StringUtils.isEmpty(hospitalName)) {
            example.createCriteria().andHospitalNameLike("%" + hospitalName + "%");
        }

        return hospitalMapper.selectByExample(example);
    }
}
