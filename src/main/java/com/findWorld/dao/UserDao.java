package com.findWorld.dao;

import org.apache.ibatis.annotations.Param;

import com.findWorld.bean.User;

/** 
 * ���ܸ�Ҫ��User��DAO�� 
 *  
 * @author liyt 
 */  
public interface UserDao {
	
	 /** 
     *  根据ID查询用户
     * @author liyt 
     * @param userId 
     * @return 
     */  
    public User selectUserById(Integer userId);  
    
    /**
	 * 登录
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	User login(@Param("userName")String userName, @Param("userPassword")String userPassword);
	
	/**
	 * 修改登录时间
	 * 
	 * @param loginTimeint
	 * @param manageUserId
	 * @author liyt
	 * @return
	 */
	public int updateLoginTime(@Param("loginTime")String loginTime, @Param("userName")String userName);
	
	/**
	 * 修改密码
	 * 
	 * @param userName
	 * @param userPassword
	 * @author liyt
	 * @return
	 */
	public int updatePwd(@Param("userName")String userName, @Param("userPassword")String userPassword);

	/**
	 * 查询用户名是否已注册，登录校验是否注册过
	 * 
	 * @param userName
	 * @author liyt
	 * @return
	 */
	public int getManageUserCount(@Param("userName")String userName);
}
