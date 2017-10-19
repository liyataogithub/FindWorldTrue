package com.findWorld.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.findWorld.bean.User;
import com.findWorld.service.UserService;
import com.findWorld.util.DateUtil;
import com.findWorld.util.MD5Util;

import org.apache.log4j.Logger;
@Controller 
public class UserController {  
 
    @Resource 
    private UserService userService;  
	static Logger logger = Logger.getLogger(UserController.class);
    @RequestMapping("/login.do")    
    public ModelAndView getIndex(){      
        ModelAndView mav = new ModelAndView("index");   
        User user = userService.selectUserById(1);  
        mav.addObject("user", user);   
        return mav;    
    }
    
    // 注册，登录查询
 	@RequestMapping(value = "registerUser.do", method = RequestMethod.POST)
 	@ResponseBody
 	public String checkRegisterUser(HttpServletRequest request, HttpServletResponse response) {
 		String registerName = request.getParameter("registerName");
 		if (userService.getManageUserCount(registerName) > 0) 
 			return "true";
 		else 
 			return "false";
 	}
 	
	// 登录
	@RequestMapping(value = "LoginUser.do", method = RequestMethod.POST)
	@ResponseBody
	public void LoginUser(HttpServletRequest request, HttpServletResponse response) {
		String registerName = request.getParameter("registerName");
		String registerpwd = request.getParameter("registerpwd");
		String pwd = MD5Util.string2MD5(MD5Util.string2MD5(registerpwd) + registerName);
		User user = userService.login(registerName, registerpwd);
		HttpSession session = request.getSession();
		// 存储用户信息
		session.setAttribute("user", user);
		// 将用户信息存到session中
		if (null != user) {
			// 更新登录时间
			int sum = userService.updateLoginTime(DateUtil.getCurrentTime(), user.getUserName());
			if (sum > 0) {
				response.setCharacterEncoding("utf-8");
				PrintWriter pw = null;
				JSONObject jsobject = new JSONObject(user);
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					logger.error(e);
					e.printStackTrace();
				}
				pw.print(jsobject);
			}
		}
	}

	// 1.修改密码与原密码核对
	@RequestMapping(value = "findPwd.do", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwdUser(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		// 原密码
		String pwd = request.getParameter("pwd");
		// 原密码加密
		String md5pwd = MD5Util.string2MD5(MD5Util.string2MD5(pwd) + userName);
		// 新密码
		String newpwd = request.getParameter("newpwd");
		// 新密码加密
		String newMD5pwd = MD5Util.string2MD5(MD5Util.string2MD5(newpwd) + userName);
		// 原密码核对
		User user = userService.login(userName, pwd);
		if (null == user) {
			return "false";
		} else {
			// 原密码正确，进行修改
			int sum = userService.updatePwd(userName, newpwd);
			if (sum > 0)
				return "true";
			else
				return "false";
		}
	}
	
	//登陆成功后跳转到主页面
	@RequestMapping(value = "/toHome.do")
	public String loginPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		//存储用户信息
		session.getAttribute("user");
        //session超时跳转到登录页
		if(session.getAttribute("user")==""||session.getAttribute("user")==null){
			 return "redirect:/login.do"; 
		}
		User userinfo = (User) session.getAttribute("user");
		request.getSession().setAttribute("userinfo", userinfo);
		return "home";
	}
}