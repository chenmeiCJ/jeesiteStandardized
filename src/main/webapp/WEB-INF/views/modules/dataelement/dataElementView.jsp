<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据元信息录入管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
		    $("form[id='inputForm'] :text").attr("readonly",true); 
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dataelement/dataElement/">数据元信息录入列表</a></li>
		<li class="active"><a href="${ctx}/dataelement/dataElement/form?id=${dataElement.id}">数据元信息查看<shiro:lacksPermission name="dataelement:dataElement:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dataElement" action="${ctx}/dataelement/dataElement/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
				<fieldset>
		<legend>标识类属性</legend>		
		<div class="control-group">
			<label class="control-label">内部标识符：</label>
			<div class="controls">
				<form:input path="nbbsf" htmlEscape="false" maxlength="100" class="input-xlarge required" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="zwmc" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称：</label>
			<div class="controls">
				<form:input path="ywmc" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文全拼：</label>
			<div class="controls">
				<form:input path="zwqp" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标识符：</label>
			<div class="controls">
				<form:input path="bsf" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">语境：</label>
			<div class="controls">
				<form:input path="yj" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:input path="bb" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">同义名称：</label>
			<div class="controls">
				<form:input path="tycmc" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		
	    <fieldset>
		<legend>定义类属性</legend>	
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:textarea path="sm" htmlEscape="false" maxlength="100" class="input-xlarge required" readonly="true" rows="3"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对象类词：</label>
			<div class="controls">
				<form:input path="dxlx" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">特性词：</label>
			<div class="controls">
				<form:input path="txc" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">应用约束：</label>
			<div class="controls">
				<form:input path="yyys" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		<fieldset>
		<legend>关系类属性</legend>	
		<div class="control-group">
			<label class="control-label">分类方案：</label>
			<div class="controls">
				<form:input path="flfa" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类方案值：</label>
			<div class="controls">
				<form:input path="flfaz" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关系：</label>
			<div class="controls">
				<form:input path="gx" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		
		<fieldset>
		<legend>表示类属性</legend>	
		<div class="control-group">
			<label class="control-label">表示词：</label>
			<div class="controls">
				<form:input path="bsc" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据类型：</label>
			<div class="controls">
				<form:input path="sjlx" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">表示格式：</label>
			<div class="controls">
				<form:input path="bsgs" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">值域：</label>
			<div class="controls">
				<form:textarea path="zy" htmlEscape="false" maxlength="500" class="input-xlarge required"  readonly="true" rows="3"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计量单位：</label>
			<div class="controls">
				<form:input path="jldw" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
        </fieldset>
        
        <fieldset>
		<legend>管理类属性</legend>	
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
               <form:radiobutton path="zt" value="原始" checked="true"/>原始  
               <form:radiobutton path="zt" value="标准" />标准 
               <form:radiobutton path="zt" value="非标准"/>非标准   
			   <form:radiobutton path="zt" value="废止"/>废止   
			   <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交机构：</label>
			<div class="controls">
				<form:input path="tjjg" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">注册机构：</label>
			<div class="controls">
				<form:input path="zcjg" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主要起草人：</label>
			<div class="controls">
				<form:textarea path="zyqcr" htmlEscape="false" class="input-xlarge "  readonly="true" rows="3"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批准时间：</label>
			<div class="controls">
				<input name="pzsj" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dataElement.pzsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		
		</fieldset>
		
		<fieldset>
		<legend>附加类属性</legend>	
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" class="input-xlarge "  readonly="true" rows="4"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来源：</label>
			<div class="controls">
				<form:input path="origin" htmlEscape="false" class="input-xlarge "/>
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
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="lrdw" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="lrr" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="lxfs" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input name="lrsj" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dataElement.lrsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>