<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据项信息表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("form[id='inputForm'] :text").attr("readonly",true); 
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dataitem/dataItem/">数据项信息表列表</a></li>
		<li class="active"><a href="${ctx}/dataitem/dataItem/form?id=${dataItem.id}">数据项信息表<shiro:hasPermission name="dataitem:dataItem:edit">${not empty dataItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataitem:dataItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dataItem" action="${ctx}/dataitem/dataItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<fieldset>
		<legend>数据项信息</legend>
		<div class="control-group">
			<label class="control-label">数据项名称：</label>
			<div class="controls">
				<form:input path="dataItemName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据项标识符：</label>
			<div class="controls">
				<form:input path="dataItemIdentifier" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据元内部标识符：</label>
			<div class="controls">
				<form:input path="dataElementInternalIdentifier" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数据元中文名称：</label>
			<div class="controls">
				<form:input path="dataElementCcname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限定词内部标识符：</label>
			<div class="controls">
				<form:input path="qualifierInternalIdentifier" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">限定词中文名称：</label>
			<div class="controls">
				<form:input path="qualifierCcname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
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
		<legend>录入信息</legend>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="entryMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input name="entryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dataItem.entryDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="entryUnit" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="contactInformation" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>