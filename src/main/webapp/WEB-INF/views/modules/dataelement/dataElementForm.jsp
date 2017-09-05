<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据元信息录入管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	    function checkIsExist(value,type) {
	    	type = $.trim(type);
	        value = $.trim(value);
	        if(value==""){
	        	if(type=="nbbsf"){
	        	   $("#nbbsfShowResult").html("");  
	        	}else if(type=="bsf"){
	        	   $("#bsfShowResult").html("");  
	        	}
	        	return;
	        } 
	        $.ajax({  
	            type:"POST",   //http请求方式  
	            url:"${ctx}/dataelement/dataElement/IsExist", //发送给服务器的url  
	            data:"value="+value+"&type="+type, //发送给服务器的参数  
	            dataType:"json",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)  
	            complete : function(msg) {  
	              var result = eval("(" + msg.responseText + ")"); 
	              if(type=="bsf"){
		              if(result.success) {  
		                  $("#bsfShowResult").html(result.message);  
		                } else {  
		                  $("#bsfShowResult").html(result.message);  
		               } 
	              }else if(type=="nbbsf"){
		              if(result.success) {  
		                  $("#nbbsfShowResult").html(result.message);  
		                } else {  
		                  $("#nbbsfShowResult").html(result.message);  
		               }   
	              }  
	           }   
	        });  
	    }
	    Date.prototype.Format = function (fmt) { //author: meizz   
	        var o = {  
	            "M+": this.getMonth() + 1, //月份   
	            "d+": this.getDate(), //日   
	            "H+": this.getHours(), //小时   
	            "m+": this.getMinutes(), //分   
	            "s+": this.getSeconds(), //秒   
	            "q+": Math.floor((this.getMonth() + 3) / 3), //季度   
	            "S": this.getMilliseconds() //毫秒   
	        };  
	        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	        for (var k in o)  
	        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	        return fmt;  
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dataelement/dataElement/">数据元信息录入列表</a></li>
		<li class="active"><a href="${ctx}/dataelement/dataElement/form?id=${dataElement.id}">数据元信息录入<shiro:hasPermission name="dataelement:dataElement:edit">${not empty dataElement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataelement:dataElement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dataElement" action="${ctx}/dataelement/dataElement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
				<fieldset>
		<legend>一、标识类属性</legend>		
		<div class="control-group">
			<label class="control-label">1、内部标识符：</label>
			<div class="controls">
				<form:input path="nbbsf" htmlEscape="false" maxlength="100" class="input-xlarge required"  onblur="checkIsExist(this.value,'nbbsf');" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:DE00001</span>
				<span id="nbbsfShowResult"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">2、中文名称：</label>
			<div class="controls">
				<form:input path="zwmc" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:公民身份号码</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">3、英文名称：</label>
			<div class="controls">
				<form:input path="ywmc" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">4、中文全拼：</label>
			<div class="controls">
				<form:input path="zwqp" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:gong-min-shen-fen-hao-ma</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">5、标识符：</label>
			<div class="controls">
				<form:input path="bsf" htmlEscape="false" maxlength="100" class="input-xlarge required" onblur="checkIsExist(this.value,'bsf');" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:GMSFHM</span>
				<span id="bsfShowResult"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">6、语境：</label>
			<div class="controls">
				<form:input path="yj" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">7、版本：</label>
			<div class="controls">
				<form:input path="bb" htmlEscape="false" maxlength="100" value="1.0"  class="input-xlarge required" style="border-color: red;"
				onfocus='if(this.value=="1.0"){this.value="";}; '   
                onblur='if(this.value==""){this.value="1.0";};'
				/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:1.0</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">8、同义名称：</label>
			<div class="controls">
				<form:input path="tycmc" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		
	    <fieldset>
		<legend>二、定义类属性</legend>	
		<div class="control-group">
			<label class="control-label">9、说明：</label>
			<div class="controls">
				<form:input path="sm" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:赋码机关为每个公民给出的唯一的、终身不变的法定标识号码</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">10、对象类词：</label>
			<div class="controls">
				<form:input path="dxlx" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:公民</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">11、特性词：</label>
			<div class="controls">
				<form:input path="txc" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:身份</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">12、应用约束：</label>
			<div class="controls">
				<form:input path="yyys" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		<fieldset>
		<legend>三、关系类属性</legend>	
		<div class="control-group">
			<label class="control-label">13、分类方案：</label>
			<div class="controls">
				<form:input path="flfa" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">14、分类方案值：</label>
			<div class="controls">
				<form:input path="flfaz" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">15、关系：</label>
			<div class="controls">
				<form:input path="gx" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		
		<fieldset>
		<legend>四、表示类属性</legend>	
		<div class="control-group">
			<label class="control-label">16、表示词：</label>
			<div class="controls">
				 <form:input path="bsc" htmlEscape="false" maxlength="100" type="text" list="bscitemlist" class="input-xlarge required" style="border-color: red;"/>
				 <datalist id="bscitemlist">
				    <c:forEach  items="${fns:getDictList('expression_word')}" var = "item" >			    
				    <option>${item.label}</option>
				  </c:forEach>
				</datalist> 
<%-- 				<form:select path="bsc" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('expression_word')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:号码</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">17、数据类型：</label>
			<div class="controls">
				<form:input path="sjlx" htmlEscape="false" maxlength="100" type="text" list="sjlxitemlist" class="input-xlarge required" style="border-color: red;"/>
				<datalist id="sjlxitemlist">
				    <c:forEach  items="${fns:getDictList('data_type')}" var = "item" >			    
				    <option>${item.label}</option>
				  </c:forEach>
				</datalist> 
<%-- 				<form:select path="sjlx" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('data_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:字符型(string)</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">18、表示格式：</label>
			<div class="controls">
				<form:input path="bsgs" htmlEscape="false" maxlength="100" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:c18</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">19、值域：</label>
			<div class="controls">
				<form:input path="zy" htmlEscape="false" maxlength="500" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:符合GB 11643《公民身份号码》</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">20、计量单位：</label>
			<div class="controls">
				<form:input path="jldw" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
        </fieldset>
        
        <fieldset>
		<legend>五、管理类属性</legend>	
		<div class="control-group">
			<label class="control-label">21、状态：</label>
			<div class="controls">
               <form:radiobutton path="zt" value="原始" checked="true"/>原始  
               <form:radiobutton path="zt" value="标准" />标准 
               <form:radiobutton path="zt" value="非标准"/>非标准   
			   <form:radiobutton path="zt" value="废止"/>废止   
			   <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">22、提交机构：</label>
			<div class="controls" id="tjjgselect">
			<form:input path="tjjg" htmlEscape="false" maxlength="200"  type="text" list="itemlist" class="input-xlarge required" style="border-color: red;" autocomplet= "off"/>
				<datalist id="itemlist">
				    <c:forEach  items="${fns:getDictList('sub_organization')}" var = "item" >			    
				    <option>${item.label}</option>
				  </c:forEach>
				</datalist>  
			<%-- <form:input path="tjjg" htmlEscape="false" maxlength="200" class="input-xlarge required" style="border-color: red;"/> --%>
				<%--  <form:select path="tjjg" class="Winstar-input120">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('sub_organization')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select> --%>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:公安部科技信息化局</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">23、注册机构：</label>
			<div class="controls">
				<form:input path="zcjg" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">24、主要起草人：</label>
			<div class="controls">
				<form:input path="zyqcr" htmlEscape="false" class="input-xlarge " value="${fns:getUser().name}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">25、批准时间：</label>
			<div class="controls">
				<input name="pzsj" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dataElement.pzsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		
		</fieldset>
		
		<fieldset>
		<legend>六、附加类属性</legend>	
		<div class="control-group">
			<label class="control-label">26、备注：</label>
			<div class="controls">
				<form:input path="bz" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">27、来源：</label>
			<div class="controls">
				<form:input path="origin" htmlEscape="false" class="input-xlarge " type="text" list="itemlist"/>
				<datalist id="originlist">
	                <option>上饶市公安局</option>
				    <option>南京市公安局</option>
				</datalist> 
			</div>
		</div>
		</fieldset>
		
		<fieldset>
		<legend>数据元录入信息</legend>	
		<div class="control-group">
			<label class="control-label">申请类型：</label>
			<div class="controls">
				<form:input path="sqlx" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请说明：</label>
			<div class="controls">
				<form:input path="sqsm" htmlEscape="false" maxlength="500" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="lrr" htmlEscape="false" maxlength="100" class="input-xlarge " value="${fns:getUser().name}" readonly="true"/>
			</div>
		</div>
	    <div class="control-group">
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="lrdw" htmlEscape="false" maxlength="100" class="input-xlarge " value="${fns:getUser().company}" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="lxfs" htmlEscape="false" maxlength="100" class="input-xlarge " value="${fns:getUser().mobile}"/>
			</div>
		</div>
		<div class="control-group">
		    <script>
		    $(document).ready(function() {
	            $("#lrsj").val(new Date().Format("yyyy-MM-dd"));
		    });
	        </script>
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input id="lrsj" name="lrsj" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dataElement.lrsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="dataelement:dataElement:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>