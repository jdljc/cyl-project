package com.cyl.common.enums;
/**
*@author cyl
*@date 2019年7月17日
*@time 下午3:43:11
*/
public enum PermissionLevel {

	NORMAL(20),
	MIDDLE(19),
	HIGH(18),
	
	MANAGE(11),
	
	SERIOUS(5),
	WARN(4),
	DANGER(3);
	
	private PermissionLevel(int level) {
		this.level = level;
	}
	
	private int level;
	
	public int level() {
		return level;
	}
}
