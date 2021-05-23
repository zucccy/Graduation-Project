package cn.edu.zucc.controller;

import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.PatientInfoDTO;
import cn.edu.zucc.dto.PatientInfoUpdateDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
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
 * @since 15.04.2021
 */
@Slf4j
@RestController
@Api(value = "患者模块", tags = "患者处理接口")
@RequestMapping("/patient")
public class PatientController {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Resource
    private PatientInfoService patientInfoService;

    @ApiOperation(value = "创建患者信息")
    @PostMapping("/createPatient")
    public ResponseVO<Void> createPatient(@RequestHeader("Authorization") String token,
                                          @RequestBody PatientInfoDTO patientInfoDTO) {
        if (null == patientInfoDTO
                || null == patientInfoDTO.getSex()
                || StringUtils.isBlank(patientInfoDTO.getPatientName())
                || StringUtils.isBlank(patientInfoDTO.getIdCard())
                || StringUtils.isBlank(patientInfoDTO.getPhone())
                || StringUtils.isBlank(patientInfoDTO.getDescription())
                || null == patientInfoDTO.getBirthday()) {
            throw new FormException();
        }
        patientInfoService.insert(TokenUtils.getUserId(token, tokenSecret), patientInfoDTO);
        return ResponseBuilder.success();
    }

    @ApiOperation(value = "删除我的患者")
    @DeleteMapping("/deleteMyPatient/patientId/")
    public ResponseVO<Void> deleteMyPatient(@RequestHeader("Authorization") String token,
                                            @RequestParam Long patientId) {
        if (null == patientId) {
            throw new FormException();
        }
        patientInfoService.deleteRelation(TokenUtils.getUserId(token, tokenSecret), patientId);
        return ResponseBuilder.success();
    }

    @ApiOperation(value = "修改我的患者")
    @PostMapping("/updateMyPatient/patientId/")
    public ResponseVO<Void> updateMyPatient(@RequestHeader("Authorization") String token,
                                            @RequestBody PatientInfoUpdateDTO patientInfoUpdateDTO) {
        if (null == patientInfoUpdateDTO) {
            throw new FormException();
        }
        patientInfoService.update(TokenUtils.getUserId(token, tokenSecret), patientInfoUpdateDTO.getPatientId(), patientInfoUpdateDTO);
        return ResponseBuilder.success();
    }
}
