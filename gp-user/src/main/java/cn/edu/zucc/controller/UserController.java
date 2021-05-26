package cn.edu.zucc.controller;

import cn.edu.zucc.PatientInfoService;
import cn.edu.zucc.UserAccountInfoService;
import cn.edu.zucc.commonVO.ResponsePageVO;
import cn.edu.zucc.commonVO.ResponseVO;
import cn.edu.zucc.dto.AdminLoginDTO;
import cn.edu.zucc.dto.UpdatePasswordDTO;
import cn.edu.zucc.dto.UserAccountInfoDTO;
import cn.edu.zucc.dto.UserAccountInfoUpdateDTO;
import cn.edu.zucc.dto.UserLoginDTO;
import cn.edu.zucc.dto.UserRegisterDTO;
import cn.edu.zucc.exception.FormException;
import cn.edu.zucc.exception.SourceNotFoundException;
import cn.edu.zucc.po.UserAccountInfo;
import cn.edu.zucc.utils.PageUtils;
import cn.edu.zucc.utils.ResponseBuilder;
import cn.edu.zucc.utils.TokenProviderUtils;
import cn.edu.zucc.utils.TokenUtils;
import cn.edu.zucc.vo.MyAppointmentListVO;
import cn.edu.zucc.vo.MyPatientVO;
import cn.edu.zucc.vo.UserAccountInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Resource
    private PatientInfoService patientInfoService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseVO<UserAccountInfoVO> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        if (null == userLoginDTO) {
            if (StringUtils.isBlank(userLoginDTO.getOpenCode())) {
                throw new SourceNotFoundException("账号为空！");
            }
            if (StringUtils.isBlank(userLoginDTO.getPassword())) {
                throw new SourceNotFoundException("密码为空！");
            }
            throw new FormException();
        }
        UserAccountInfo user = userAccountInfoService.login(userLoginDTO);

        //为当前用户签名
        log.info(tokenSecret);
        response.setHeader("Authorization", TokenProviderUtils.sign(user.getId(), tokenSecret));
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        UserAccountInfoVO userVO = new UserAccountInfoVO();
        BeanUtils.copyProperties(user, userVO);

        return ResponseBuilder.success(userVO);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseVO<Void> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        //帐号、密码、用户名均不能为空
        if (null == userRegisterDTO) {
            if (StringUtils.isBlank(userRegisterDTO.getOpenCode())) {
                throw new SourceNotFoundException("账号为空！");
            }
            if (StringUtils.isBlank(userRegisterDTO.getPassword())) {
                throw new SourceNotFoundException("密码为空！");
            }
            if (StringUtils.isBlank(userRegisterDTO.getUserName())) {
                throw new SourceNotFoundException("用户名为空！");
            }
            throw new FormException();
        }
        userAccountInfoService.register(userRegisterDTO);
        return ResponseBuilder.success();
    }

    @ApiOperation(value = "获取用户基本资料")
    @GetMapping("/userInfo")
    //必须传入token（用户必须登录），才能继续操作
    public ResponseVO<UserAccountInfoVO> getUserInfo(@RequestHeader("Authorization") String token) {

        UserAccountInfoVO userVO = new UserAccountInfoVO();
        //通过TokenUtils对token解码得到的userId进行操作
        BeanUtils.copyProperties(userAccountInfoService.findUserById(TokenUtils.getUserId(token, tokenSecret)), userVO);

        return ResponseBuilder.success(userVO);
    }

    @ApiOperation(value = "更新用户基本资料")
    @PostMapping("/updateUserInfo")
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

    @ApiOperation(value = "获取我的预约集合")
    @GetMapping("/getMyAppointments")
    //必须传入token（用户必须登录），才能继续操作
    public ResponseVO<List<MyAppointmentListVO>> getMyAppointments(@RequestHeader("Authorization") String token) {
        return ResponseBuilder.success(userAccountInfoService.getMyAppointments(TokenUtils.getUserId(token, tokenSecret)));
    }

    @ApiOperation(value = "获取我的患者集合")
    @GetMapping("/getMyPatients")
    public ResponseVO<List<MyPatientVO>> getMyPatients(@RequestHeader("Authorization") String token) {
        return ResponseBuilder.success(patientInfoService.findPatientList(TokenUtils.getUserId(token, tokenSecret)));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/insert")
    public ResponseVO<Boolean> insertUser(@RequestBody UserAccountInfoDTO userAccountInfoDTO) {
        if (StringUtils.isEmpty(userAccountInfoDTO.getUserName()) || StringUtils.isEmpty(userAccountInfoDTO.getPassword())) {
            throw new FormException();
        }
        return ResponseBuilder.success(userAccountInfoService.insert(userAccountInfoDTO));
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    public ResponseVO<Boolean> deleteUser(@RequestParam Long id) {
        if (null == id) {
            throw new FormException();
        }
        return ResponseBuilder.success(userAccountInfoService.delete(id));
    }

    @ApiOperation(value = "修改用户")
    @PostMapping("/update")
    public ResponseVO<Boolean> updateUser(@RequestBody UserAccountInfoDTO userAccountInfoDTO) {
        if (null == userAccountInfoDTO) {
            throw new FormException();
        }
        return ResponseBuilder.success(userAccountInfoService.update(userAccountInfoDTO.getId(), userAccountInfoDTO));
    }

    @ApiOperation(value = "禁/启用用户")
    @GetMapping("/disable")
    public ResponseVO<Boolean> disableAndEnableUser(@RequestParam Long id, @RequestParam("status") Integer status) {
        if (null == id || null == status) {
            throw new FormException();
        }
        return ResponseBuilder.success(userAccountInfoService.disableAndEnableUser(id, status));
    }

    @ApiOperation(value = "管理员登录")
    @PostMapping("/adminLogin")
    public ResponseVO<Boolean> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        if (null == adminLoginDTO) {
            if (StringUtils.isBlank(adminLoginDTO.getUserName())) {
                throw new SourceNotFoundException("用户名为空！");
            }
            if (StringUtils.isBlank(adminLoginDTO.getPassword())) {
                throw new SourceNotFoundException("密码为空！");
            }
            throw new FormException();
        }

        return ResponseBuilder.success(userAccountInfoService.adminLogin(adminLoginDTO));
    }

    @ApiOperation(value = "获取所有用户列表（可按照用户名查询）")
    @GetMapping("/getAllUsers")
    public ResponsePageVO<UserAccountInfo> getAllUsers(@RequestParam(required = false) String userName,
                                                       @RequestParam(defaultValue = "1") Integer pageNum,
                                                       @RequestParam(defaultValue = "10") Integer pageSize) {

        return ResponseBuilder.successPageable(PageUtils.restPage(userAccountInfoService.getAllUsers(userName, pageNum, pageSize)));
    }
}
