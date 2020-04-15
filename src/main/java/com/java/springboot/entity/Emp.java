package com.java.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author djin
 *    员工实体类
 */
public class Emp implements Serializable{

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
	private Date hiredate;
	//奖金
	private Double comm;
	
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
	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", mgr=" + mgr + ", sal=" + sal
				+ ", hiredate=" + hiredate + ", comm=" + comm + "]";
	}
	
	
}
