package cn.edu.zucc.controller;

import cn.edu.zucc.AppointmentInfoService;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.AppointmentInfoDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.utils.TokenUtils;
import cn.edu.zucc.vo.MyAppointmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 14.04.2021
 */
@Slf4j
@RestController
@Api(value = "预约模块", tags = "用户预约接口")
@RequestMapping("/appointment")
public class AppointmentController {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Resource
    private AppointmentInfoService appointmentInfoService;

    @ApiOperation(value = "创建预约信息")
    @PostMapping(value = "/createAppointment")
    public ResponseVO<Void> createAppointment(@RequestHeader("Authorization") String token,
                                              @RequestBody AppointmentInfoDTO appointmentInfoDTO) {
        //帐号、密码、用户名均不能为空
        if (null == appointmentInfoDTO
                || null == appointmentInfoDTO.getDoctorId()
                || null == appointmentInfoDTO.getVisitId()
                || null == appointmentInfoDTO.getPatientId()) {
            throw new FormException();
        }
        appointmentInfoService.insert(TokenUtils.getUserId(token, tokenSecret), appointmentInfoDTO);
        return ResponseBuilder.success();
    }

    @ApiOperation(value = "查看我的预约详情")
    @GetMapping(value = "/getMyAppointmentInfo/{id}")
    public ResponseVO<MyAppointmentVO> getMyAppointmentInfo(@RequestHeader("Authorization") String token,
                                                            @PathVariable Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(appointmentInfoService.convertMyAppointmentVO(id));
    }

    @ApiOperation(value = "修改我的预约状态")
    @PostMapping(value = "/updateVisitStatus/{id}")
    public ResponseVO<Void> updateVisitStatus(@RequestHeader("Authorization") String token,
                                              @PathVariable Long id,
                                              @RequestParam Byte visitStatus) {
        if (null == id || null == visitStatus) {
            throw new FormException();
        }
        appointmentInfoService.update(TokenUtils.getUserId(token, tokenSecret), id, visitStatus);
        return ResponseBuilder.success();
    }

}
