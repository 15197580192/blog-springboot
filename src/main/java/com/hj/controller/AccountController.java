package com.hj.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hj.entity.UserInfo;
import com.hj.service.UserInfoService;
import com.hj.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import com.hj.common.dto.LoginDto;
import com.hj.common.lang.Result;
import com.hj.entity.User;
import com.hj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AccountController {
    @Autowired
    JwtUtils jwtUtils;  // 生成jwt凭证

    @Autowired
    UserService userService;    //user表

    @Autowired
    UserInfoService userInfoService;    //user_info表


    @CrossOrigin        //跨域
    // 登陆接口
    @PostMapping("/login")  //url映射
    //loginDto前端传来的数据
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        //找到数据库中对应的user
        User user = userService.getOne(new QueryWrapper<User>().eq("user_id", loginDto.getUserId()));
        Assert.notNull(user, "用户不存在");

        //密码检验
        if (!user.getUserPassword().equals(loginDto.getUserPassword())) {   //(SecureUtil.md5(loginDto.getUserPassword()))) {
            return Result.fail("密码不正确");
        }

        //找到数据库中对应的user_info
        UserInfo userInfo = userInfoService.getOne(new QueryWrapper<UserInfo>().eq("user_id", loginDto.getUserId()));

        //生成jwt凭证
        String jwt = jwtUtils.generateToken(Long.parseLong(user.getUserId()));
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        //后端数据返回到前端
        return Result.success(MapUtil.builder()
                .put("userId", user.getUserId())
                .put("userPassword", user.getUserPassword())
                .put("userIp",user.getUserIp())
                .put("userName",user.getUserName())
                .put("registerTime",user.getRegisterTime())
                .put("userPhone",user.getUserPhone())
                .put("userStatus",user.getUserStatus())
                .put("userFreezeTime",user.getUserFreezeTime())
                .put("userBackup1",user.getUserBackup1())
                .put("userBackup2",user.getUserBackup2())
                .put("userBackup3",user.getUserBackup3())
                .put("userProfilePhoto",userInfo.getUserProfilePhoto())
                .put("userNickname",userInfo.getUserNickname())
                .map()
        );
    }

    // 退出接口
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}
