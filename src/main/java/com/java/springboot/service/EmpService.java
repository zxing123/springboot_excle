package com.java.springboot.service;



import com.java.springboot.entity.Emp;
import com.java.springboot.entity.EmpAndDept;

import java.util.List;

/**
 * 
 * @author djin
 *   员工业务层接口
 */
public interface EmpService {

	List<EmpAndDept> findPageEmp(Integer currentPage, Integer pageSize) throws Exception;
	
	Integer getEmpCounts() throws Exception;
	
	void removeEmpByEmpno(Integer empno) throws Exception;
	
	void updEmpSal(Emp emp) throws Exception;
	
	void updEmp(EmpAndDept empAndDept) throws Exception;
	
	Integer saveEmp(EmpAndDept empAndDept) throws Exception;
}
