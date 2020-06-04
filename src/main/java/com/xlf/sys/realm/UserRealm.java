package com.xlf.sys.realm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xlf.sys.common.ActiverUser;
import com.xlf.sys.domain.User;
import com.xlf.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName(){
        return this.getClass().getSimpleName();
    }

    /**
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        QueryWrapper<User> querWrapper = new QueryWrapper<>();
        querWrapper.eq("loginname",authenticationToken.getPrincipal().toString());
        User user = userService.getOne(querWrapper);
        if (null!=user){
            ActiverUser activerUser = new ActiverUser();

            activerUser.setUser(user);

            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activerUser,user.getPwd(),credentialsSalt,this.getName());
            return info;
        }
        return null;
    }

    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


}
