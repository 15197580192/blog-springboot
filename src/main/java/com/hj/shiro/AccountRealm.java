package com.hj.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.hj.entity.User;
import com.hj.service.UserService;
import com.hj.util.JwtUtils;
import io.jsonwebtoken.Jwt;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken jwt = (JwtToken) authenticationToken;

        //log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.parseLong(userId));//1L范围-9223372036854775808 ~ 9223372036854775807
        if(user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        /*if(user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }*/
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        //log.info("profile----------------->{}", profile.toString());
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

