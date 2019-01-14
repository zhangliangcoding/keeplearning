package com.learning.zuul.security.realm;



import com.learning.zuul.domain.entity.User;
import com.learning.zuul.security.JWTToken;
import com.learning.zuul.security.util.JWTUtil;
import com.learning.zuul.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class JWTRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = jwtUtil.getUsernameFromToken(principals.toString());
        User user = userService.findByUsername(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRole(user.getRole());
        Set<String> permission = user.getPermissions();
        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        //解析token
        String username = jwtUtil.getUsernameFromToken(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }

        User userBean = userService.findByUsername(username);
        if (userBean == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        if (! jwtUtil.validateToken(token, userBean)) {
            throw new AuthenticationException("Username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
