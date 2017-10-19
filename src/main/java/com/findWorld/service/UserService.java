package com.findWorld.service;

import com.findWorld.bean.User;

/**
 * ���ܸ�Ҫ��UserService�ӿ���
 * 
 * @author liyt
 */
public interface UserService {

	/**
	 * 根据ID查询用户
	 * 
	 * @param userId
	 * @author liyt
	 * @return
	 */
	User selectUserById(Integer userId);

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	User login(String userName,String userPassword);
	
	/**
	 * 修改登录时间
	 * 
	 * @param loginTimeint
	 * @param manageUserId
	 * @author liyt
	 * @return
	 */
	public int updateLoginTime(String loginTime,String userName);
	
	/**
	 * 修改密码
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	public int updatePwd(String userName,String userPassword);
	
	/**
	 * 查询用户名是否已注册，登录校验是否注册过
	 * 
	 * @param userName
	 * @author liyt
	 * @return
	 */
	public int getManageUserCount(String userName);
}
