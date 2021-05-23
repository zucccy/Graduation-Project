package cn.edu.zucc.controller;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.DoctorService;
import cn.edu.zucc.VisitPlanService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.DoctorInfoDTO;
import cn.edu.zucc.dto.UpdateAppointmentStatusDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.po.VisitPlan;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.vo.DoctorVO;
import cn.edu.zucc.vo.MyAppointmentListVO;
import cn.edu.zucc.vo.VisitDoctorPlanVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private AppointmentInfoService appointmentInfoService;

    @ApiOperation(value = "根据医生id查找医生信息")
    @GetMapping("/id")
    public ResponseVO<DoctorVO> findDoctorVOById(@RequestParam Long id) {

        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.findDoctorVOById(id));
    }

    @ApiOperation(value = "查找同医院同科室医生列表(除了该医生以外)")
    @GetMapping("/getDoctorsByOfficeId/")
    public ResponsePageVO<DoctorVO> getDoctorsByOfficeId(@RequestParam Long officeId,
                                                         @RequestParam Long id,
                                                         @RequestParam Long hospitalId,
                                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                                         @RequestParam(defaultValue = "10") Integer pageSize) {

        if (null == officeId || null == hospitalId || null == id) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorList(officeId, id, hospitalId, pageNum, pageSize)));
    }

    @ApiOperation(value = "按照科室编号查找医生列表")
    @GetMapping("/getDoctorListByOfficeId/")
    public ResponsePageVO<DoctorVO> getDoctorListByOfficeId(@RequestParam Long officeId,
                                                            @RequestParam(defaultValue = "1") Integer pageNum,
                                                            @RequestParam(defaultValue = "10") Integer pageSize) {

        if (null == officeId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorList(officeId, pageNum, pageSize)));
    }

    @ApiOperation(value = "按照医院编号查找医生列表")
    @GetMapping("/getDoctorsByHosId/")
    public ResponsePageVO<DoctorVO> getDoctorsByHosId(@RequestParam Long hospitalId,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize) {

        if (null == hospitalId) {
            throw new FormException();
        }
        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorListByHosId(hospitalId, pageNum, pageSize)));
    }

    @ApiOperation(value = "查找所有医生列表")
    @GetMapping("/getAllDoctors/")
    public ResponsePageVO<DoctorVO> getAllDoctors(@RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {

        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.getAllDoctors(pageNum, pageSize)));
    }

    @ApiOperation(value = "分页搜索医生信息 按医生名字/所属医院名字/科室名模糊查询")
    @GetMapping("/getDoctorList")
    public ResponsePageVO<DoctorVO> getDoctorList(@RequestParam String doctorName,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        if (StringUtils.isEmpty(doctorName))
            throw new FormException();

        return ResponseBuilder.successPageable(PageUtils.restPage(doctorService.findDoctorList(doctorName, pageNum, pageSize)));
    }

    @ApiOperation(value = "根据医生编号获取当前日期至7天后出诊计划信息")
    @GetMapping("/getDoctorPlan/")
    public ResponseVO<VisitDoctorPlanVO> getDoctorPlan(@RequestParam Long doctorId) {
        if (null == doctorId) {
            throw new FormException();
        }
        return ResponseBuilder.success(visitPlanService.getDoctorPlan(doctorId));
    }

    @ApiOperation(value = "根据出诊编号获取出诊计划信息")
    @GetMapping("/getVisitPlan/")
    public ResponseVO<VisitPlan> getVisitPlan(@RequestParam Long visitPlanId) {
        if (null == visitPlanId) {
            throw new FormException();
        }
        return ResponseBuilder.success(visitPlanService.findVisitPlanById(visitPlanId));
    }

    @ApiOperation(value = "根据医生编号获取预约集合")
    @GetMapping("/getAppointmentList/")
    public ResponseVO<List<MyAppointmentListVO>> getAppointmentList(@RequestParam Long doctorId) {
        if (null == doctorId) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.getAppointmentListByDoctorId(doctorId));
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
    @DeleteMapping("/delete/")
    public ResponseVO<Boolean> deleteDoctor(@RequestParam Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.delete(id));
    }

    @ApiOperation(value = "修改医生")
    @PostMapping("/update/")
    public ResponseVO<Boolean> updateDoctor(@RequestParam Long id, @RequestBody DoctorInfoDTO doctorInfoDTO) {
        if (null == doctorInfoDTO || null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.update(id, doctorInfoDTO));
    }

    @ApiOperation(value = "修改就诊人预约状态")
    @PostMapping("/updateAppointmentStatus/")
    public ResponseVO<Boolean> updateAppointmentStatus(@RequestBody UpdateAppointmentStatusDTO updateAppointmentStatusDTO) {
        if (null == updateAppointmentStatusDTO || null == updateAppointmentStatusDTO.getId()) {
            throw new FormException();
        }
        return ResponseBuilder.success(appointmentInfoService.updateByDoctorId(updateAppointmentStatusDTO.getId(),
                updateAppointmentStatusDTO.getVisitStatus()));
    }

}
