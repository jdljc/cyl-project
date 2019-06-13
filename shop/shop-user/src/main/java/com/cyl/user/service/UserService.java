package com.cyl.user.service;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.common.util.MD5Util;
import com.cyl.user.dao.UserDao;
import com.cyl.user.entity.User;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:20:11
*/
@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	public User get(long userId){
		Optional<User> optional = dao.findById(userId);
		return optional.isPresent()?optional.get():null;
	}
	
	public User findByName(String name) {
		return dao.findByName(name);
	}
	
	public User save(User user){
		if(dao.findByName(user.getName())!=null)
			return null;
		user.setRegistry(new Date(System.currentTimeMillis()));
		String salty = UUID.randomUUID().toString();
		String password = MD5Util.getInstance().getMD5(user.getPassword()+salty);
		user.setSalty(salty);
		user.setPassword(password);
		return dao.save(user);
	}
	
	public User update(User user){
		User old = dao.getOne(user.getId());
		if(old!=null) {
			if(user.getPassword()!=null) {
				String salty = old.getSalty();
				String password = MD5Util.getInstance().getMD5(user.getPassword()+salty);
				user.setPassword(password);
			}
			return dao.save(user);
		}
		return null;
	}
	
	public boolean delete(User user){
		try {
			dao.delete(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
