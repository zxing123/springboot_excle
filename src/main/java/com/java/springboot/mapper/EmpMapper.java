package com.java.springboot.mapper;


import com.java.springboot.entity.Emp;
import com.java.springboot.entity.EmpAndDept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 
 * @author djin
 *   员工dao层
 */
public interface EmpMapper {

	//查询所有员工
	@Select("SELECT empno,ename,job,mgr,hiredate,sal,comm,dept.deptno,dname,loc from emp LEFT JOIN dept ON emp.deptno=dept.deptno ORDER BY empno DESC LIMIT #{currentRecord},#{pageSize}")
	List<EmpAndDept> selPageEmp(@Param("currentRecord") Integer currentRecord, @Param("pageSize") Integer pageSize) throws Exception;
	
	//获取员工的数据条数
	@Select("select count(*) from emp")
	Long getEmpCounts() throws Exception;
	
	//根据id删除员工
	@Delete("delete from emp where empno=#{empno}")
	void delEmpByEmpno(Integer empno) throws Exception;
	
	//修改员工工资
	@Update("update emp set sal=#{sal} where empno=#{empno}")
	void updEmpSal(Emp emp) throws Exception;
	
	//修改员工信息
	@Update("update emp set ename=#{ename},job=#{job},mgr=#{mgr},sal=#{sal},hiredate=#{hiredate},comm=#{comm},deptno=#{deptno} where empno=#{empno}")
	void updEmp(EmpAndDept empAndDept) throws Exception;
	
	//添加员工
	@Insert("insert into emp(empno,ename,job,mgr,hiredate,sal,comm,deptno) values(null,#{ename},#{job},#{mgr},#{hiredate},#{sal},#{comm},#{deptno})")
	Integer insEmp(EmpAndDept empAndDept) throws Exception;
}
