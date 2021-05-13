package cn.edu.zucc.controller;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.HospitalInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.vo.HospitalInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 08.04.2021
 */
@Slf4j
@RestController
@Api(value = "医院模块", tags = "医院信息接口")
@RequestMapping("/hospital")
public class HospitalController {

    @Resource
    private HospitalService hospitalService;

    @ApiOperation(value = "根据id查找医院信息")
    @GetMapping(value = "{id}")
    public ResponseVO<HospitalInfoVO> findHospitalById(@PathVariable Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.findHospitalVOById(id));
    }

    @ApiOperation(value = "分页搜索医院信息 根据医院名、地址")
    @GetMapping(value = "/list")
    public ResponsePageVO<Hospital> findHospitalList(@RequestParam(required = false) String hospitalName,
                                                     @RequestParam(required = false) String address,
                                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        if (StringUtils.isEmpty(hospitalName) || StringUtils.isEmpty(address))
            throw new FormException();

        return ResponseBuilder.successPageable(PageUtils.restPage(hospitalService.findHospitalList(hospitalName, address, pageNum, pageSize)));
    }

    @ApiOperation(value = "根据科室编号查找医院列表")
    @GetMapping(value = "/getHospitals/{officeId}")
    public ResponsePageVO<Hospital> getHospitalsByOfficeId(@PathVariable Long officeId,
                                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                                           @RequestParam(defaultValue = "10") Integer pageSize) {
        if (null == officeId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(hospitalService.findHospitalList(officeId, pageNum, pageSize)));
    }

    @ApiOperation(value = "添加医院")
    @PostMapping("/insert")
    public ResponseVO<Boolean> insertHospital(@RequestBody HospitalInfoDTO hospitalInfoDTO) {
        if (StringUtils.isEmpty(hospitalInfoDTO.getHospitalName())
                || StringUtils.isEmpty(hospitalInfoDTO.getAddress())
                || StringUtils.isEmpty(hospitalInfoDTO.getPhone())
                || null == hospitalInfoDTO.getLatitudes()
                || null == hospitalInfoDTO.getLongitudes()) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.insert(hospitalInfoDTO));
    }

    @ApiOperation(value = "删除医院")
    @DeleteMapping("/delete/{id}")
    public ResponseVO<Boolean> deleteHospital(@PathVariable Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.delete(id));
    }

    @ApiOperation(value = "修改医院")
    @PostMapping("/update/{id}")
    public ResponseVO<Boolean> updateHospital(@PathVariable Long id, @RequestBody HospitalInfoDTO hospitalInfoDTO) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.update(id, hospitalInfoDTO));
    }

    @ApiOperation(value = "删除医院下的指定科室")
    @DeleteMapping("{hospitalId}/deleteOffice/{officeId}")
    public ResponseVO<Boolean> deleteOfficeInHospital(@PathVariable Long hospitalId, @PathVariable Long officeId) {
        if (null == hospitalId || null == officeId) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.deleteOfficeRelation(hospitalId, officeId));
    }

    @ApiOperation(value = "医院中添加指定科室")
    @PostMapping("{hospitalId}/addOffice/{officeId}")
    public ResponseVO<Boolean> addOfficeToHospital(@PathVariable Long hospitalId, @PathVariable Long officeId) {
        if (null == hospitalId || null == officeId) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.insertOfficeRelation(hospitalId, officeId));
    }
}
