package com.findWorld.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findWorld.bean.User;
import com.findWorld.dao.UserDao;
import com.findWorld.service.UserService;
/** 
 * ���ܸ�Ҫ��UserServiceʵ���� 
 *  
 * @author liyt 
 */  
@Service  
public class UserServiceImpl implements UserService  {
	@Autowired  
    private UserDao userDao;

	/** 
     *  根据ID查询用户
     * @author liyt 
     * @param userId 
     * @return 
     */ 
	public User selectUserById(Integer userId) {
		return userDao.selectUserById(userId); 
	}

	/**
	 * 登录
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	public User login(String userName, String userPassword) {
		return userDao.login(userName, userPassword);
	}
	
	/**
	 * 修改登录时间
	 * 
	 * @param loginTimeint
	 * @param manageUserId
	 * @author liyt
	 * @return
	 */
	public int updateLoginTime(String loginTimeint, String userId) {
		return userDao.updateLoginTime(loginTimeint, userId);
	}

	/**
	 * 修改密码
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	public int updatePwd(String userName, String userPassword) {
		return userDao.updatePwd(userName, userPassword);
	}
	/**
	 * 查询用户名是否已注册，登录校验是否注册过
	 * 
	 * @param userName
	 * @author liyt
	 * @return
	 */
	public int getManageUserCount(String userName) {
		return userDao.getManageUserCount(userName);
	}  
}