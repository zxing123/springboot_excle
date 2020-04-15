<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
String path = request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="lib/layui/css/layui.css"  media="all">
<title>员工显示页面</title>
</head>
<body>
   <h1 align="center">员工显示页面</h1>
   <p align="center" style="margin-top: 20px;">
   <button class="layui-btn layui-btn-normal layui-btn-radius">添加员工</button>
   <button class="layui-btn layui-btn-normal layui-btn-radius"><a href="emp/downLoadExcel"><font color="white">导出Excel文件</a></font></button>
   <button class="layui-btn layui-btn-normal layui-btn-radius"><a href="emp/toAddEmp"><font color="white">Excel批量新增</a></font></button>
	<button class="layui-btn layui-btn-normal layui-btn-radius" id="outPageData">由页面导出</button>
   </p>
 <div style="display: none;margin-top: 20px;" id="updEmpDiv">
  <form class="layui-form" action="">
	  <div class="layui-form-item">
	    <label class="layui-form-label">员工姓名</label>
	    <div class="layui-input-block">
	      <input type="text" name="ename" lay-verify="ename" autocomplete="off" placeholder="请输入员工名字" class="layui-input" id="ename">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">工作</label>
	    <div class="layui-input-block">
	      <input type="text" name="job" lay-verify="job" placeholder="请输入工作" autocomplete="off" class="layui-input" id="job">
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">上司</label>
	      <div class="layui-input-inline">
	        <input type="text" name="mgr" lay-verify="required" autocomplete="off" placeholder="请输入上司编号"  class="layui-input" id="mgr">
	      </div>
	    </div>
	    <div class="layui-inline">
	      <label class="layui-form-label">工资</label>
	      <div class="layui-input-inline">
	        <input type="text" name="sal" lay-verify="number|sal|required" autocomplete="off" placeholder="请输入工资" class="layui-input" id="sal">
	      </div>
	    </div>
	  </div>
	  
	  <div class="layui-form-item">
	  <div class="layui-inline">
	      <label class="layui-form-label">入职时间</label>
	      <div class="layui-input-inline">
	        <input type="text" name="hiredate" id="hiredate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	      </div>
	    </div>
	    </div>
	 <div class="layui-form-item">
	    <div class="layui-inline">
	      <label class="layui-form-label">奖金</label>
	      <div class="layui-input-inline">
	        <input type="text" name="comm" lay-verify="required|number|sal" autocomplete="off" placeholder="请输入奖金" class="layui-input" id="comm">
	      </div>
	    </div>
	    
	   </div>
	   <div class="layui-form-item">
	    <label class="layui-form-label">部门：</label>
	    <div class="layui-input-inline">
	      <select name="dept" id="dept">
	        <option value="" selected="" id="selDept">--请选择省部门--</option>
	         <c:forEach items="${depts }" var="dept">
	             <option value="${dept.deptno },${dept.dname },${dept.loc }">${dept.dname }</option>
	         </c:forEach>
	      </select>
        </div>
       </div>
	   <div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit="" lay-filter="submitUpdEmp">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
      </div>
    </form>
   </div>
   
   <div align="center" style="margin-top: 20px;">
      <table class="layui-hide" id="LAY_table_emp" lay-filter="emp"></table>
      <div id="pageEmp"></div>
   </div>
   
   <script type="text/html" id="barDemo1">
     <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
     <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
     <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
   </script>
   
   <script type="text/html" id="empSal">
     <a class="layui-btn layui-btn-primary layui-btn-xs"><i class="layui-icon" lay-event="updSal">&#xe642;</i></a>
   </script>
    
   <script src="lib/layui/layui.js" charset="utf-8"></script>
   <script src="js/showTableEmp.js" charset="utf-8"></script>
</body>
</html>