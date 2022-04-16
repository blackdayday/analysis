package tgc.edu.ljc.stu.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tgc.edu.ljc.stu.custom.AjaxResult;
import tgc.edu.ljc.stu.custom.JwtTokenUtils;
import tgc.edu.ljc.stu.entity.SysUser;
import tgc.edu.ljc.stu.security.UserUtils;
import tgc.edu.ljc.stu.service.SysUserService;

@RestController
public class MainController {
	@Autowired
	private UserUtils userUtils;
	@Autowired
	private SysUserService userService;
	
	@RequestMapping(value = "/loginSucc")
	public Object loginSucc() {
		String username = userUtils.getUsername();
	    String roles = userUtils.getRoles();
	    String name=userUtils.getName();
	    String token = JwtTokenUtils.createToken(username,name,roles);
	    token=JwtTokenUtils.TOKEN_PREFIX+token;  //加上token前缀
	    HashMap<String, Object> result=new HashMap<String, Object>();
	    result.put("token",token);
	    result.put("name", name);
	    result.put("roles", roles);
	    return new AjaxResult("登录成功", result);		
	}
	
	@RequestMapping(value = "/logoutSucc")
	public Object logoutSucc() {
		return new AjaxResult("注销成功");
	}
	
	@RequestMapping(value = "/loginFailure")
	public Object loginFailure() {
		return new AjaxResult(false,"用户名或密码错误");
	}
	
	@RequestMapping(value = "/changePwd")
	public Object changePwd(String oldPwd,String newPwd) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		SysUser user = userUtils.getUser();
		String password = user.getPassword();
		if(!encoder.matches(oldPwd, password)) {
			return new AjaxResult(false, "旧密码不正确");
		}
		user.setPassword(encoder.encode(newPwd));
		userService.save(user);
		return new AjaxResult("密码修改成功");
	}
	
}
