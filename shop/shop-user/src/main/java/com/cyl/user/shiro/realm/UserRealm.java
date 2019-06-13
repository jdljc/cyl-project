package com.cyl.user.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyl.user.entity.Permission;
import com.cyl.user.entity.Role;
import com.cyl.user.entity.User;
import com.cyl.user.service.PermissionService;
import com.cyl.user.service.UserService;

/**
 *@author 25280
 *@date 2019年5月31日
 *@time 下午3:42:35
 */
@Component
public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	@Autowired
	private PermissionService permissionService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("-----get user roles and permission info-----");
		String name = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		User user = userService.findByName(name);

		Set<String> roleNames = new HashSet<>();
		for (Role role : user.getRoles()) roleNames.add(role.getName());
		authorizationInfo.setRoles(roleNames);

		Set<String> permissionNames = new HashSet<>();
		for (Permission permission: permissionService.findPermissions(name)) {
			permissionNames.add(permission.getName());
		}
		authorizationInfo.setStringPermissions(permissionNames);
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("-----return authentication info-----");
		if(token==null) return null;
		String name = (String) token.getPrincipal();
		User user = userService.findByName(name);
		if(user==null) throw new UnknownAccountException();
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(),ByteSource.Util.bytes(user.getSalty()),getName());
		return authenticationInfo;
	}
}
