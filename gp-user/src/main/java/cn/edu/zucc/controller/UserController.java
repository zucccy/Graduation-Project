package cn.edu.zucc.controller;

import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.UpdatePasswordDTO;
import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.po.UserAccountInfo;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.utils.TokenProviderUtils;
import cn.edu.zucc.utils.TokenUtils;
import cn.edu.zucc.vo.MyAppointmentVO;
import cn.edu.zucc.vo.UserAccountInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Description: TODO
 *
 * @author chenyun
 * @since 13.04.2021
 */
@Slf4j
@RestController
@Api(value = "用户模块", tags = "用户信息接口")
@RequestMapping("/user")
public class UserController {

    @Value("${jwt.secret}")
    private String tokenSecret;

    @Resource
    private UserAccountInfoService userAccountInfoService;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResponseVO<UserAccountInfoVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        if (null == userLoginDTO || StringUtils.isBlank(userLoginDTO.getOpenCode()) || StringUtils.isBlank(userLoginDTO.getPassword())) {
            throw new FormException();
        }
        UserAccountInfo user = userAccountInfoService.login(userLoginDTO);

        //为当前用户签名
        System.out.println(tokenSecret);
        response.setHeader("Authorization", TokenProviderUtils.sign(user.getId(), tokenSecret));

        UserAccountInfoVO userVO = new UserAccountInfoVO();
        BeanUtils.copyProperties(user, userVO);

        return ResponseBuilder.success(userVO);
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public ResponseVO<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        //帐号、密码、用户名均不能为空
        if (null == userRegisterDTO
                || StringUtils.isBlank(userRegisterDTO.getOpenCode())
                || StringUtils.isBlank(userRegisterDTO.getPassword())
                || StringUtils.isBlank(userRegisterDTO.getUserName())) {
            throw new FormException();
        }
        userAccountInfoService.register(userRegisterDTO);
        return ResponseBuilder.success();
    }

    @ApiOperation(value = "获取用户基本资料")
    @GetMapping(value = "/userInfo")
    //必须传入token（用户必须登录），才能继续操作
    public ResponseVO<UserAccountInfoVO> getUserInfo(@RequestHeader("Authorization") String token) {

        UserAccountInfoVO userVO = new UserAccountInfoVO();
        //通过TokenUtils对token解码得到的userId进行操作
        BeanUtils.copyProperties(userAccountInfoService.findUserById(TokenUtils.getUserId(token, tokenSecret)), userVO);

        return ResponseBuilder.success(userVO);
    }

    @ApiOperation(value = "更新用户基本资料")
    @PostMapping(value = "/updateUserInfo")
    //必须传入token（用户必须登录），才能继续操作
    public ResponseVO<Void> updateUserInfo(@RequestHeader("Authorization") String token,
                                           @RequestBody UserAccountInfoUpdateDTO userAccountInfoUpdateDTO) {
        //用户名不能为空
        if (null == userAccountInfoUpdateDTO || StringUtils.isBlank(userAccountInfoUpdateDTO.getUserName())) {
            throw new FormException();
        }
        //更新
        userAccountInfoService.update(TokenUtils.getUserId(token, tokenSecret), userAccountInfoUpdateDTO);

        return ResponseBuilder.success();
    }

    @ApiOperation(value = "密码管理")
    @PostMapping("/password")
    public ResponseVO<Void> updatePassword(@RequestHeader("Authorization") String token,
                                           @RequestBody UpdatePasswordDTO updatePasswordDTO) {

        if (null == updatePasswordDTO) {
            throw new FormException();
        }
        //修改密码
        userAccountInfoService.updatePassword(TokenUtils.getUserId(token, tokenSecret), updatePasswordDTO);

        return ResponseBuilder.success();
    }

    @ApiOperation(value = "获取我的预约")
    @GetMapping(value = "/getMyAppointments")
    //必须传入token（用户必须登录），才能继续操作
    public ResponseVO<List<MyAppointmentVO>> getMyAppointments(@RequestHeader("Authorization") String token) {
        return ResponseBuilder.success(userAccountInfoService.getMyAppointments(TokenUtils.getUserId(token, tokenSecret)));
    }
}
