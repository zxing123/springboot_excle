package com.java.springboot.controller;


import com.java.springboot.entity.Emp;
import com.java.springboot.entity.EmpAndDept;
import com.java.springboot.service.DeptService;
import com.java.springboot.service.EmpService;
import com.java.springboot.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * 
 * @author djin
 *   员工控制器层
 */
@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@Autowired
	private DeptService deptService;
	
	Integer currentPage,pageSize;

	@RequestMapping("/showTableEmp")
	public String showTableEmp(Model model){
		try {
			model.addAttribute("depts", deptService.findAllDept());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showTableEmp";
		
	}
	
	//获取数据记录条数
	@RequestMapping("/getEmpCounts")
	@ResponseBody
	public Integer getEmpCounts(){
		try {
			return empService.getEmpCounts();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	//加载员工部门信息
	@RequestMapping("/loadEmpAndDept")
	@ResponseBody
	public Map<String,Object> loadEmpAndDept(Integer page,Integer limit){
		currentPage = page;
		pageSize = limit;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 0);
		try {
			map.put("count", empService.getEmpCounts());
			map.put("data", empService.findPageEmp(page, limit));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;		
	}

	//下载数据到Excel文档
	@RequestMapping("/downLoadExcel")
	public void downLoadExcel(HttpServletResponse response,Model model) {
		try {
			//获取数据源//可以不用取查询数据库，直接取到页面数据
			List<EmpAndDept> emps= empService.findPageEmp(currentPage, pageSize);

			//指定excel列和属性映射关系   fieldMap的设置和uploadExcel当中相反
			LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
			fieldMap.put("empno", "编号");
			fieldMap.put("ename", "姓名");
			fieldMap.put("job", "工作");
			fieldMap.put("mgr", "上司编号");
			fieldMap.put("sal", "工资");
			fieldMap.put("hiredate", "入职时间");
			fieldMap.put("comm", "奖金");
			fieldMap.put("deptno", "部门编号");
			fieldMap.put("dname", "部门名称");
			fieldMap.put("loc", "地址");
			//指定导出表的名字（不是文件名）
			String sheetName="用户信息";
			ExcelUtil.listToExcel(emps, fieldMap, sheetName, response);
			model.addAttribute("res", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//去上传
	@RequestMapping("/toAddEmp")
	public String toAddEmp(){
		return "addBatchUser";
		
	}
	
	//Excel批量新增用户
	@RequestMapping("/uploadExcel")
	public String uploadExcel(MultipartFile file,Model model){
		try {
			//单个实例类型导入
			/*InputStream is =file.getInputStream();
			ReadExcel readExcel=new ReadExcel();
			List<User> userList=readExcel.readIs(is);*/
			
			//泛型通用工具导入
			InputStream in =file.getInputStream();
//				String sheetName=file.getOriginalFilename();
			LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
			//指定excel列和对象属性映射关系
			//fieldMap.put("编号","empno");
			fieldMap.put("姓名","ename");
			fieldMap.put("工作","job");
			fieldMap.put("上司编号","mgr");
			fieldMap.put("工资","sal");
			fieldMap.put("入职时间","hiredate");
			fieldMap.put("奖金","comm");
			fieldMap.put("部门编号","deptno");
			fieldMap.put("部门名称","dname");
			fieldMap.put("地址","loc");
			//指定要检查的唯一列
			String[] uniqueFields= {"编号"};
			List<EmpAndDept> empAndDeptList = ExcelUtil.excelToList(in, "用户信息", EmpAndDept.class, fieldMap, uniqueFields);
			if(empAndDeptList.size()>0) {
				//存入数据库
				int i=0;
				List<String> failList=new ArrayList<String>();
				for (EmpAndDept empAndDept : empAndDeptList) {
					i = empService.saveEmp(empAndDept);
					if(i<=0) {
						failList.add(empAndDept.getEname());
					}
				}
				if(failList.size() >0 && failList.size()<empAndDeptList.size()) {
					model.addAttribute("res", "成功创建部分用户,未成功创建的用户姓名如下："+failList.toString());
				}else {
					model.addAttribute("res", "成功创建全部用户");
				}
			}else {
				model.addAttribute("res", "Excel文件中无用户数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:showTableEmp";
	}
	
	//根据id删除员工
	@RequestMapping("/delEmpByEmpno")
	@ResponseBody
	public String delEmpByEmpno(Integer empno){
		System.out.println("------delEmpByEmpno----------"+"empno="+empno);
		try {
			empService.removeEmpByEmpno(empno);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//修改员工工资
	@RequestMapping("/updEmpSal")
	@ResponseBody
	public String updEmpSal(Emp emp){
		System.out.println("-----updEmpSal-----------"+emp);
		try {
			empService.updEmpSal(emp);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	
	//修改员工信息
	@RequestMapping("/updEmp")
	@ResponseBody
	public String updEmp(EmpAndDept empAndDept){
		System.out.println("empAndDept="+empAndDept);
		try {
			empService.updEmp(empAndDept);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}	
	}
}
