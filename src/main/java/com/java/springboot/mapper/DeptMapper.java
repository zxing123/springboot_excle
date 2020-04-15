package com.java.springboot.mapper;


import com.java.springboot.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeptMapper {
	
	//查询所有部门
	@Select("select * from dept")
	List<Dept> selAllDept() throws Exception;
	

}
