package com.cyl.common.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
*@author 25280
*@date 2019年5月27日
*@time 下午10:48:47
*/
public class PageItem {

	private int page;
	private int size;
	private boolean isAsc;
	private String[] properties;
	
	public PageItem() {}
	
	public PageItem(int page, int size, boolean isAsc, String[] properties) {
		super();
		this.page = page;
		this.size = size;
		this.isAsc = isAsc;
		this.properties = properties;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isAsc() {
		return isAsc;
	}

	public void setAsc(boolean isAsc) {
		this.isAsc = isAsc;
	}

	public String[] getProperties() {
		return properties;
	}

	public void setProperties(String[] properties) {
		this.properties = properties;
	}

	public PageRequest of() {
		Direction direction = null;
		if(this.isAsc()) direction = Direction.ASC;
		else direction = Direction.DESC;
		return PageRequest.of(this.getPage(), this.getSize(), direction, this.getProperties());
	}
}
