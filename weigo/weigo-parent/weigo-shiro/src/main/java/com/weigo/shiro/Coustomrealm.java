package com.weigo.shiro;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.weigo.commons.utils.JsonUtils;
import com.weigo.dubbo.user.service.TbPermissionDubboService;
import com.weigo.dubbo.user.service.TbRoleDubboService;
import com.weigo.dubbo.user.service.TbUserDubboService;
import com.weigo.pojo.TbPermission;
import com.weigo.pojo.TbRole;
import com.weigo.pojo.TbUser;


public class Coustomrealm extends AuthorizingRealm{
  
	@Autowired
   private TbUserDubboService tbUserDubboService;
	@Autowired
	private TbRoleDubboService tbRoleDubboService;
	@Autowired
	private TbPermissionDubboService tbPermissionDubboService;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Value("${permission.key}")
	private String permissionKey; 
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();	
		TbUser user = (TbUser) principals.getPrimaryPrincipal();
		Long id = user.getId();
		String userPermissionKey = permissionKey+id;
		
		if(redisTemplate.hasKey(userPermissionKey)) {
			
			//List<String> range = redisTemplate.opsForList().range(userPermissionKey, 0, -1);
			String string = this.redisTemplate.opsForValue().get(userPermissionKey);
			List<String> range = JsonUtils.jsonToList(string, String.class);
			authorizationInfo.addStringPermissions(range);
			return authorizationInfo;
	}
		
//		
		TbRole role = tbRoleDubboService.selectTbRolePermissionByTbRoleId(user.getRoleId());
//		
		String permissionIds = role.getPermissionIds();
//		
		String[] permissionIdsArr = permissionIds.split(",");
	
		List<Long> permissionIdsList = new ArrayList<Long>();
		
		for (String permission : permissionIdsArr) {
		    permissionIdsList.add(Long.valueOf(permission));
		}		
	List<TbPermission> permissions = tbPermissionDubboService.selectTbPermissionExpressionByIds(permissionIdsList);		
			
	
	  List<String> valueList = new ArrayList<String>();
		
		for (TbPermission permission : permissions) {			
		     String expression = permission.getExpression();
			if(StringUtils.isNotBlank(expression)) {
				//this.redisTemplate.setValueSerializer(new StringRedisSerializer());
			//	redisTemplate.opsForList().rightPush(userPermissionKey, expression);
				valueList.add(expression);
				authorizationInfo.addStringPermission(expression);
			}
			
		}
		
		String objectToJson = JsonUtils.objectToJson(valueList);
		this.redisTemplate.opsForValue().set(userPermissionKey, objectToJson);
		redisTemplate.expire(userPermissionKey, 1, TimeUnit.DAYS);
	return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)  throws AuthenticationException {
		String username = (String) token.getPrincipal();
//		try {
//			username = new String(username.getBytes("ISO-8859-1"), "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		 List<TbUser> selectUserByEmail = tbUserDubboService.selectUserByEmail(username);
		
		if(selectUserByEmail==null||selectUserByEmail.size()==0) {
           throw new UnknownAccountException();		
		}
		TbUser user = selectUserByEmail.get(0);
		if(user.getStatus()==0) {
			throw new DisabledAccountException();
		}
		String loginKey = "login:"+username;
		
		this.redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.opsForValue().increment(loginKey, 1);
		redisTemplate.expire(loginKey, 1,TimeUnit.HOURS);
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info=null;
	    info = new SimpleAuthenticationInfo(user, user.getPassword(),credentialsSalt, this.getName());
		return info;
	}

}
