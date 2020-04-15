package com.java.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author djin
 *   员工部门实体类
 */
public class EmpAndDept implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//id
	private Integer empno;
	//姓名
	private String ename;
	//工作
	private String job;
	//上司
	private Integer mgr;
	//工资
	private Double sal;
	//入职时间
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date hiredate;
	//奖金
	private Double comm;
	//部门id
	private Integer deptno;
	//名字
	private String dname;
	//地址
	private String loc;
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getMgr() {
		return mgr;
	}
	public void setMgr(Integer mgr) {
		this.mgr = mgr;
	}
	public Double getSal() {
		return sal;
	}
	public void setSal(Double sal) {
		this.sal = sal;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Double getComm() {
		return comm;
	}
	public void setComm(Double comm) {
		this.comm = comm;
	}
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
		return "EmpAndDept [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", sal=" + sal
				+ ", hiredate=" + hiredate + ", comm=" + comm + ", deptno=" + deptno + ", dname=" + dname + ", loc="
				+ loc + "]";
	}
	
	
	
}
