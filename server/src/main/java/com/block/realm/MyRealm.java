package com.block.realm;

import com.block.entity.User;
import com.block.service.MenuService;
import com.block.service.RoleService;
import com.block.service.UserRoleService;
import com.block.service.UserService;
import com.block.utils.ShiroUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;



public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权入口。。。。");
        if(principals == null){
            throw new AuthorizationException("principals should not be null");
        }
        User user = (User) principals.getPrimaryPrincipal();
//        List<String> perms= Lists.newLinkedList();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(roleService.findRoleByUserId(user.getId()));
        //系统超级管理员拥有最高的权限，不需要发出sql的查询，直接拥有所有权限；否则，则需要根据当前用户id去查询权限列表
//        if (user.getId().equals(ConstantUtils.SUPER_ADMIN)){
//            List<Menu> list=menuService.list();
//            if (list!=null && !list.isEmpty()){
//                perms=list.stream().map(menuService::getPerms).collect(Collectors.toList());
//            }
//        }else{
//            perms=sysUserDao.queryAllPerms(userId);
//        }
//
//        info.setStringPermissions(permissionService.findPermsByUserId(user.getUserId()));
//        return info;
        return null;
    }


    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", token.getUsername());
        User user = (User) userService.findOne(wrapper);

        //账户不存在
        if (user==null){
            throw new UnknownAccountException("账户不存在!");
        }

        //第二种验证逻辑-交给shiro的密钥匹配器去实现
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());

    }

    /**
     * 密码验证器~匹配逻辑 ~ 第二种验证逻辑
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher shaCredentialsMatcher = new HashedCredentialsMatcher();
        shaCredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        shaCredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(shaCredentialsMatcher);
    }
}
