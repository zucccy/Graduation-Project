package cn.edu.zucc.controller;

import cn.edu.zucc.DoctorService;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.vo.DoctorVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "医生模块", tags = "医生信息接口")
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @ApiOperation(value = "根据医生id查找医生信息")
    @GetMapping("{id}")
    public ResponseVO<DoctorVO> findDoctorVOById(@PathVariable Long id) {

        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(doctorService.findDoctorVOById(id));
    }
}
