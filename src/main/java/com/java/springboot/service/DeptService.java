package com.java.springboot.service;



import com.java.springboot.entity.Dept;

import java.util.List;

public interface DeptService {

	List<Dept> findAllDept() throws Exception;
}
