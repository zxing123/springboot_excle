package com.java.springboot.entity;

import java.io.Serializable;

/**
 * 
 * @author djin
 *   部门实体类
 */
public class Dept implements Serializable{

	private static final long serialVersionUID = 1L;

	//部门id
	private Integer deptno;
	//名字
	private String dname;
	//地址
	private String loc;
	
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
}
