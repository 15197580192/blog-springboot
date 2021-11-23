package com.hj.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.common.dto.ChangePasswordDto;
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

    //获取验证码接口
    @PostMapping("/getcode")
    public Result getCode(@Validated @RequestBody GetCodeDto getCodeDto){
        Assert.notNull(getCodeDto.getUserId(), "手机号不存在");
        try {
            Map<String, Object> params = sms.sendMessage(getCodeDto.getUserId(), null);
            if((boolean) params.get("success")) {
                String[] templateParams = (String[]) params.get("templateParams");
                return Result.success(templateParams[0]);
            }else {
                return Result.fail("短信发送失败");
            }
        } catch (Exception e) {
            Result.fail("短信发送失败");
        }
        return Result.fail("短信发送失败");
    }

    /*//获取验证码接口
    @PostMapping("/getcode")
    public Result getCode(@Validated @RequestBody GetCodeDto getCodeDto ){
        Assert.notNull(getCodeDto.getUserId(), "手机号不存在");
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "110151", "7a312ef9-3aa1-466c-8b90-a661ac56f4a9");
        Random rd = new Random();
        int rd1 = rd.nextInt(10);
        int rd2 = rd.nextInt(10);
        int rd3 = rd.nextInt(10);
        int rd4 = rd.nextInt(10);
        String code = "" + rd1 + rd2 + rd3 + rd4;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("number", getCodeDto.getUserId());
        params.put("templateId", "6993");
        String[] templateParams = new String[2];
        templateParams[0] = code;
        templateParams[1] = "5分钟";
        params.put("templateParams", templateParams);
        String result = null;
        try {
            result = client.send(params);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if(result.length()==30) {
            return Result.fail("手机号码格式错误");
        }
        return Result.success(200,"发送成功",code);
    }*/

}
