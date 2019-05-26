package com.cyl.goods.service;
/**
*@author chen
*@date 2019年4月28日
*@time 下午10:15:04
*/

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public List<Goods> getAll(int providerId){
		return null;
	}
	
	public Goods add(Goods goods){
		return dao.save(goods);
	}
	
	public Goods update(Goods goods){
		return dao.save(goods);
	}
}
