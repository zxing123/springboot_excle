layui.use(['table','laypage','form','laydate'], function(){
  var table = layui.table
      ,laypage = layui.laypage
      ,form = layui.form
      ,laydate = layui.laydate
      ,$ = layui.$;

  //页面中每一页的数据
  var empData;
  
  //日期
  laydate.render({
    elem: '#hiredate',
    format:'yyyy/MM/dd'
  });
  
  //方法级渲染
  table.render({
    elem: '#LAY_table_emp'
    ,url: 'emp/loadEmpAndDept'
    ,page:true
    ,limit:5
    ,cols: [[
      {checkbox: true, fixed: true}
      ,{field:'empno', title: 'ID',align:'center', width:80, sort: true, fixed: true}
      ,{field:'ename', title: '姓名',align:'center', width:130}
      ,{field:'job', title: '工作',align:'center', width:150, sort: true}
      ,{field:'mgr', title: '上司',align:'center', width:120}
      ,{field:'sal', title: '工资', align:'center',width:120}
      ,{fixed: 'right',title: '修改', width:80, align:'center', toolbar: '#empSal'}
     /* ,{field:'hiredate', title: '入职时间', sort: true,align:'center', width:150}
      ,{field:'comm', title: '奖金', sort: true,align:'center', width:120}
      ,{field:'deptno', title: '部门编号', sort: true,align:'center', width:120}
      ,{field:'dname', title: '部门名称',align:'center', width:140}
      ,{field:'loc', title: '地址', sort: true,align:'center', width:150}*/
      ,{fixed: 'right',title: '操作', width:178, align:'center', toolbar: '#barDemo1'}
    ]],done: function(res, curr, count){
		 $("[data-field='deptno']").css('display','none');
	//	 console.log(res.data);
           empData = res.data;
           console.log(empData);
	 }
    ,width:1500
    ,height: 380
   });

   //导出页面数据
  $("#outPageData").click(function () {
      //将上述表格示例导出为 csv 文件
      table.exportFile("LAY_table_emp",empData,'csv'/*'xls'*/); //data 为该实例中的任意数量的数据
  });

  //表格监听
  table.on('tool(emp)', function(obj){
    var data = obj.data;
    if(obj.event === 'detail'){
    	layer.msg("编号为："+data.empno+"用户信息被查看了。。。");
    }
    if(obj.event === 'del'){
      layer.confirm('真的删除行么', function(index){
    	var jsonEmp = {};
    	jsonEmp.empno = data.empno;
    	czEmp(jsonEmp,"emp/delEmpByEmpno");
        obj.del();
        layer.close(index);
      });
    } else if(obj.event === 'updSal'){
    	layer.prompt({
	        formType: 2
	        ,value: data.sal
	      }, function(value, index){
	    	if(value>0){
	    		 var jsonEmp = {};
	    		 jsonEmp.empno = data.empno;
	    		 jsonEmp.sal = value;
	    		 czEmp(jsonEmp,"emp/updEmpSal");
	    		 obj.update({
	   	          sal: value
	   	        });
	    	}else{
	    	   layer.msg("请输入正确的工资！！！",{icon:2,time:2000});	
	    	}
	        layer.close(index);
	      });
    }else if(obj.event === 'edit'){
    	layer.open({
    		type:1,
    		title:"员工信息编辑",
    		area:['600px','500px'],
    		content:$("#updEmpDiv"),
    		success:function(){
    		  $("#ename").val(data.ename);
    		  $("#job").val(data.job);
    		  $("#mgr").val(data.mgr);
    		  $("#sal").val(data.sal);
    		  $("#hiredate").val(data.hiredate);
    		  $("#comm").val(data.comm);
    		  $("#selDept").replaceWith('<option value="'+data.deptno,data.dname,data.loc+'" selected="" id="selDept">'+data.dname+'</option>');
    		  form.render('select'); //刷新select选择框渲染
    		  //监听提交
			  form.on('submit(submitUpdEmp)', function(){
			    var jsonEmp = {};
			    var emp = $("form").serializeArray()
			    $.each(emp,function(){
			    	jsonEmp[this.name] = this.value;
                 });
			    var depts = jsonEmp.dept.split(",");
			    jsonEmp.empno = data.empno;
			    jsonEmp.deptno = depts[0];
			    console.log("jsonEmp"+jsonEmp.toString());
			    czEmp(jsonEmp,"emp/updEmp");
			    obj.update({
			    	  ename:jsonEmp.ename,
			    	  job:jsonEmp.job,
			    	  mgr:jsonEmp.mgr,
			    	  sal:jsonEmp.sal,
			    	  hiredate:jsonEmp.hiredate,
		   	          comm: jsonEmp.comm,
		   	          dname:depts[1],
		   	          loc:depts[2]
		   	        });
			    layer.closeAll();
			    return false;
			  });
    		}
    	});
    }
  
  });  
  
  //自定义表单验证
  form.verify({
	ename: function(value){
      if(value.length < 2){
        return '姓名至少得2个字符啊';
      }
    }
    ,job: function(value){
        if(value.length < 3||value.length > 9){
            return '工作必须3到9位';
          }
        }
    ,sal: function(value){
    	 if(value < 0){
             return '工资要大于0';
          }
    }
  });
  
  //操作员工
  function czEmp(jsonEmp,url){
	  $.ajax({
	   	type:"post",
	   	url:url,
	   	data:jsonEmp,
	   	success:function(data){
	   		if(data=="success"){
	   			layer.msg("操作成功。。。")
	   		}else{
	   			layer.msg("操作失败！！！")
	   		}	
	   	},
	   	error:function(){
	   		layer.msg("操作异常！！！");
	   	}
	   });
  }
  
  
});