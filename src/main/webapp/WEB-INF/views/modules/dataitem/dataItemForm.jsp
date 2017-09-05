<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据项信息表管理</title>
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
		<legend>数据项信息</legend>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="entryMan" htmlEscape="false" maxlength="32" class="input-xlarge " value="${fns:getUser().name}"/>
			</div>
		</div>
		<div class="control-group">
		    <script>
			    $(document).ready(function() {
		            $("#entryDate").val(new Date().Format("yyyy-MM-dd"));
			    });
	        </script>
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
				<form:input path="entryUnit" htmlEscape="false" maxlength="64" class="input-xlarge " value="${fns:getUser().company}" />
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
			<shiro:hasPermission name="dataitem:dataItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>