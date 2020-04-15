package com.java.springboot.service.impl;

import com.java.springboot.entity.Dept;
import com.java.springboot.mapper.DeptMapper;
import com.java.springboot.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public List<Dept> findAllDept() throws Exception {
		
		return deptMapper.selAllDept();
	}

}
