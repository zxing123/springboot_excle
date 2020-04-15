package com.java.springboot.service.impl;

import com.java.springboot.entity.Emp;
import com.java.springboot.entity.EmpAndDept;
import com.java.springboot.mapper.EmpMapper;
import com.java.springboot.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	private EmpMapper empMappper;

	@Override
	public List<EmpAndDept> findPageEmp(Integer currentPage, Integer pageSize) throws Exception {
		
		return empMappper.selPageEmp((currentPage-1)*pageSize, pageSize);
	}

	@Override
	public Integer getEmpCounts() throws Exception {
		
		return empMappper.getEmpCounts().intValue();
	}

	@Override
	public void removeEmpByEmpno(Integer empno) throws Exception {
		empMappper.delEmpByEmpno(empno);
		
	}

	@Override
	public void updEmpSal(Emp emp) throws Exception {
		empMappper.updEmpSal(emp);
		
	}

	@Override
	public void updEmp(EmpAndDept empAndDept) throws Exception {
		empMappper.updEmp(empAndDept);
		
	}

	@Override
	public Integer saveEmp(EmpAndDept empAndDept) throws Exception {
		return empMappper.insEmp(empAndDept);
		
	}

	

}
