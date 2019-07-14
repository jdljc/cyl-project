package com.cyl.common.vo;
/**
*@author 25280
*@date 2019年6月18日
*@time 上午10:50:27
*/

import java.util.HashMap;
import java.util.Map;

public class Error {
	
	private String error;
	
	private Map<String, String> detail;
	
	public Error() {
		this.detail = new HashMap<>();
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Map<String, String> getDetail() {
		return detail;
	}

	public void setDetail(Map<String, String> detail) {
		this.detail = detail;
	}
	
	public String get(String key) {
		return detail.get(key);
	}
	
	public void put(String key,String value) {
		if(detail!=null) detail.put(key, value);
	}
}
