package com.cyl.goods.service;
/**
*@author chen
*@date 2019年4月28日
*@time 下午10:15:04
*/

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cyl.common.exception.BadRequestException;
import com.cyl.common.exception.UnknowException;
import com.cyl.common.vo.PageItem;
import com.cyl.goods.dao.GoodsDao;
import com.cyl.goods.entity.Goods;

@Service
public class GoodsService {

	@Autowired
	private GoodsDao dao;
	
	public Goods get(int goodsId){
		Optional<Goods> optional = dao.findById(goodsId);
		return optional.isPresent()?optional.get():null;
	}
	
	public List<Goods> getAll(){
		return dao.findAll();
	}
	
	public Page<Goods> getGoodies(PageItem pageItem){
		return dao.findAll(pageItem.of());
	}
	
	public Goods add(Goods goods){
		goods.setRegistry(new Date(System.currentTimeMillis()));
		return dao.save(goods);
	}
	
	public Goods update(Integer id,Goods goods){
		if(id==null||id.intValue()==0)
			throw new BadRequestException().put("id", "this field could not be null");
		return dao.save(goods);
	}
	
	public boolean delete(Integer id,Goods goods){
		if(id==null||id.intValue()==0)
			throw new BadRequestException().put("id", "this field could not be null");
		try {
			goods.setId(id);
		    dao.delete(goods);
		    return true;
		} catch (Exception e) {
		    throw new UnknowException("error occured while delete operation!").put(goods.getId()+"", "failure to delete this goods!");
		}
	}
}
