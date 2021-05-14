package cn.edu.zucc.controller;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.vo.DoctorVO;
import cn.edu.zucc.vo.VisitDoctorPlanVO;
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
import java.util.Date;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 15.04.2021
 */
@Slf4j
@RestController
@Api(value = "医生模块", tags = "医生信息接口")
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @Resource
    private VisitPlanService visitPlanService;

    @ApiOperation(value = "根据医生id查找医生信息")
    @GetMapping("{id}")
    public ResponseVO<DoctorVO> findDoctorVOById(@PathVariable Long id) {

        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.findDoctorVOById(id));
    }

    @ApiOperation(value = "按照科室编号查找医生列表")
    @GetMapping("/getDoctorsByOfficeId/{officeId}")
    public ResponsePageVO<DoctorVO> getDoctorsByOfficeId(@PathVariable Long officeId,
                                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize) {

        if (null == officeId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorList(officeId, pageNum, pageSize)));
    }

    @ApiOperation(value = "按照医院编号查找医生列表")
    @GetMapping("/getDoctorsByHosId/{hospitalId}")
    public ResponsePageVO<DoctorVO> getDoctorsByHosId(@PathVariable Long hospitalId,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {

        if (null == hospitalId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorListByHosId(hospitalId, pageNum, pageSize)));
    }

    @ApiOperation(value = "分页搜索医生信息 按医生名字模糊查询")
    @GetMapping("/getDoctorList")
    public ResponsePageVO<DoctorVO> getDoctorList(@RequestParam String doctorName,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        if (StringUtils.isEmpty(doctorName))
            throw new FormException();

        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorList(doctorName, pageNum, pageSize)));
    }

    @ApiOperation(value = "根据医生编号获取一段时间内出诊计划信息")
    @GetMapping("/getDoctorPlan/{doctorId}")
    public ResponseVO<VisitDoctorPlanVO> getDoctorPlan(@PathVariable Long doctorId,
                                                       @RequestParam Date start,
                                                       @RequestParam Date end) {
        if (null == doctorId || null == start || null == end) {
            throw new FormException();
        }
        return ResponseBuilder.success(visitPlanService.getDoctorPlan(doctorId, start, end));
    }

    @ApiOperation(value = "添加医生")
    @PostMapping("/insert")
    public ResponseVO<Boolean> insertDoctor(@RequestBody DoctorInfoDTO doctorInfoDTO) {
        if (null == doctorInfoDTO
                || StringUtils.isEmpty(doctorInfoDTO.getDoctorName())
                || StringUtils.isEmpty(doctorInfoDTO.getPosition())
                || null == doctorInfoDTO.getSex()
                || null == doctorInfoDTO.getHospitalId()
                || null == doctorInfoDTO.getOfficeId()) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.insert(doctorInfoDTO));
    }

    @ApiOperation(value = "删除医生")
    @DeleteMapping("/delete/{id}")
    public ResponseVO<Boolean> deleteDoctor(@PathVariable Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.delete(id));
    }

    @ApiOperation(value = "修改医生")
    @PostMapping("/update/{id}")
    public ResponseVO<Boolean> updateDoctor(@PathVariable Long id, @RequestBody DoctorInfoDTO doctorInfoDTO) {
        if (null == doctorInfoDTO || null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.update(id, doctorInfoDTO));
    }
}
