<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>限定词信息表管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/determiner/determiner/">限定词信息表列表</a></li>
		<li class="active"><a href="${ctx}/determiner/determiner/form?id=${determiner.id}">限定词信息表<shiro:hasPermission name="determiner:determiner:edit">${not empty determiner.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="determiner:determiner:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="determiner" action="${ctx}/determiner/determiner/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<fieldset>	
		<legend>限定词信息</legend>
		<div class="control-group">
			<label class="control-label">内部标识符：</label>
			<div class="controls">
				<form:input path="internalIdentifier" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标识符：</label>
			<div class="controls">
				<form:input path="identifier" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="ccname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:checkboxes path="statue" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交日期：</label>
			<div class="controls">
				<input name="submissionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${determiner.submissionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
		<legend>录入信息</legend>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="entryMan" htmlEscape="false" maxlength="32" class="input-xlarge " value="${fns:getUser().name}" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="entryUnit" htmlEscape="false" maxlength="64" class="input-xlarge " value="${fns:getUser().company}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input name="entryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${determiner.entryDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="contactInformation" htmlEscape="false" maxlength="32" class="input-xlarge " value="${fns:getUser().mobile}"/>
			</div>
		</div>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="determiner:determiner:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>