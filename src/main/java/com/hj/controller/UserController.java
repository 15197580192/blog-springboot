package com.hj.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.common.dto.ChangePasswordDto;
import com.hj.common.dto.RegisterDto;
import com.hj.common.lang.Result;
import com.hj.entity.User;
import com.hj.entity.UserInfo;
import com.hj.service.UserInfoService;
import com.hj.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    UserService userService;

    @Autowired
    UserInfoService userInfoService;

    @CrossOrigin
    //@RequiresAuthentication
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

    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterDto registerDto) {

        User user = new User();
        user.setUserId(registerDto.getUserId());
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(registerDto.getUserId());
        user.setUserPassword(registerDto.getUserPassword());
        userService.saveOrUpdate(user);
        userInfoService.saveOrUpdate(userInfo);
        return Result.success("注册成功");
    }

}
