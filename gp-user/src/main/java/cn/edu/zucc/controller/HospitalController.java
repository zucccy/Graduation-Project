package cn.edu.zucc.controller;

import cn.edu.zucc.HospitalService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.po.Hospital;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseVO<Hospital> findHospitalById(@PathVariable Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(hospitalService.findHospitalById(id));
    }

    @ApiOperation(value = "分页搜索医院信息")
    @GetMapping(value = "/list")
    public ResponsePageVO<Hospital> findHospitalList(@RequestParam(required = false) String hospitalName,
                                                     @RequestParam(required = false) String address,
                                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) {

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
}
