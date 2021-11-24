package com.hj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.common.dto.ChangePasswordDto;
import com.hj.common.dto.FindCodeDto;
import com.hj.common.dto.GetCodeDto;
import com.hj.common.dto.RegisterDto;
import com.hj.common.lang.Result;
import com.hj.config.zhenziSMS;
import com.hj.entity.User;
import com.hj.entity.UserInfo;
import com.hj.service.UserInfoService;
import com.hj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hzy
 * @since 2021-11-16
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private zhenziSMS sms;

    @Autowired
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @CrossOrigin
    //@RequiresAuthentication
    //修改密码接口
    @PostMapping("/change")
    public Result change(@Validated @RequestBody ChangePasswordDto changePasswordDto) {

        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", changePasswordDto.getUserId()));
        Assert.notNull(user, "用户不存在");
        if (!user.getUserPassword().equals(changePasswordDto.getUserPassword())) {//(SecureUtil.md5(loginDto.getUserPassword()))) {
            return Result.fail("密码不正确");
        }
        user.setUserPassword(changePasswordDto.getUserNewPassword());
        userService.updateById(user);
        return Result.success("密码修改成功");
    }

    //注册用户接口
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {
        if(userService.getOne(new QueryWrapper<User>().eq("user_id", registerDto.getUserId()))!=null) {
            return Result.fail("用户已注册");
        }
        if((new Date().getTime()-GetCodeDto.date.getTime())>180000) {
            return Result.fail("验证期限已过，请重新获取验证码");
        }
        if(!registerDto.getCode().equals(GetCodeDto.code)) {
            return Result.fail("验证码错误");
        }
        User user = new User();
        user.setUserId(registerDto.getUserId());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(registerDto.getUserId());
        userInfo.setUserNickname(registerDto.getUserId());
        user.setUserPassword(registerDto.getUserPassword());
        userService.saveOrUpdate(user);
        userInfoService.saveOrUpdate(userInfo);
        return Result.success("注册成功");
    }

    //找回密码接口
    @PostMapping("/findcode")
    public Result getCode(@Validated @RequestBody FindCodeDto findCodeDto){
        if(userService.getOne(new QueryWrapper<User>().eq("user_id", findCodeDto.getUserId()))==null) {
            return Result.fail("用户不存在");
        }
        //防止用户第一次输入自己的手机号，第二次输入他人的手机号，通过自己收到的验证码来获得他人的账户密码
        if(!findCodeDto.getUserId().equals(FindCodeDto.checkId)) {
            return Result.fail("请输入收到验证码的用户手机号");
        }
        if((new Date().getTime()-GetCodeDto.date.getTime())>180000) {
            return Result.fail("验证期限已过，请重新获取验证码");
        }
        if(!findCodeDto.getCode().equals(GetCodeDto.code)) {
            return Result.fail("验证码错误");
        }
        User user = userService.getById(findCodeDto.getUserId());
        return Result.success(user.getUserPassword());
    }

    //获取验证码接口
    @PostMapping("/getcode")
    public Result getCode(@Validated @RequestBody GetCodeDto getCodeDto){
        Assert.notNull(getCodeDto.getUserId(), "手机号不存在");
        try {
            Map<String, Object> params = sms.sendMessage(getCodeDto.getUserId(), null);
            if((boolean) params.get("success")) {
                String[] templateParams = (String[]) params.get("templateParams");
                GetCodeDto.code=templateParams[0];
                GetCodeDto.date=new Date();
                FindCodeDto.checkId=getCodeDto.getUserId();
                return Result.success(templateParams[0]);
            }else {
                return Result.fail("短信发送失败");
            }
        } catch (Exception e) {
            Result.fail("短信发送失败");
        }
        return Result.fail("短信发送失败");
    }

}
