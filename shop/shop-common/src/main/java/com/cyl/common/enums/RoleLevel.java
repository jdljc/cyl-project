package com.cyl.common.enums;
/**
*@author cyl
*@date 2019年7月17日
*@time 下午1:58:42
*/
public enum RoleLevel {
	
	SUPERADMIN(0,"superadmin"),
	ADMIN(1,"admin"),
	
	M_YEAR(5,"m_year"),
	M_QUARTER(6,"m_quarter"),
	M_MONTH(7,"m_month"),
	
	V9(11,"V9"),
	V8(12,"V8"),
	V7(13,"V7"),
	V6(14,"V6"),
	V5(15,"V5"),
	V4(16,"V4"),
	V3(17,"V3"),
	V2(18,"V2"),
	V1(19,"V1"),
	
	ANNO(20,"anno");
	
	private int level;
	
	private String name;
	
	public int level() {
		return this.level;
	}
	
	public String role() {
		return this.name;
	}
	
	private RoleLevel(int level,String name) {
		this.level = level;
	}
}
