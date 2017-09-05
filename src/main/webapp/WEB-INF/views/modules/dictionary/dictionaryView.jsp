<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代码集表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("form[id='inputForm'] :text").attr("readonly",true);
	        var len = $('table tr').length;
	        for(var i = 1;i<len;i++){
	            $('table tr:eq('+i+') td:first').text(i);
	        }
		});

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dictionary/dictionary/">代码集表列表</a></li>
		<li class="active"><a href="${ctx}/dictionary/dictionary/form?id=${dictionary.id}">代码集表查看</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dictionary" action="${ctx}/dictionary/dictionary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<fieldset>
		<legend>代码集属性</legend>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="ccname" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input path="remark" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内部标识符：</label>
			<div class="controls">
				<form:input path="internalIdentifier" htmlEscape="false" maxlength="36" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="instruction" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:checkboxes path="state" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规范性引用文件：</label>
			<div class="controls">
				<form:input path="normativeReferenceFile" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">术语和定义：</label>
			<div class="controls">
				<form:input path="termsAndDefinitions" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">分类原则和方法：</label>
			<div class="controls">
				<form:input path="classificationPrinciple" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编码方法：</label>
			<div class="controls">
				<form:input path="codingMethod" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主要起草人：</label>
			<div class="controls">
				<form:input path="draftman" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准号：</label>
			<div class="controls">
				<form:input path="standardNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">提交机构：</label>
			<div class="controls">
				<form:input path="submittingBody" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布日期：</label>
			<div class="controls">
				<input name="releaseDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dictionary.releaseDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
		<legend>录入属性</legend>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="entryMan" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="entryUnit" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="contactInformation" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input name="entryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dictionary.entryTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
		<legend>代码集列表</legend>
	    <div class="control-group">
	    <div hidden="true">
	    <form:form id="searchForm" modelAttribute="dictionary" action="${ctx}/dictionary/dictionary/view" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium" value="${id}"/>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		</ul>
		</div>
	    </form:form>
	        <table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				   <tr>
					 <th class="hide"></th>
					    <th>序号</th>
					    <th>名称</th>
						<th>代码</th>
						<th>说明</th>
					 </tr>
				</thead>
				<tbody id="dictionaryCodeList">
			   <c:forEach items="${page.list}" var="dictionaryCode">
			    <tr>
			    <td>   </td>
				<td>
					${dictionaryCode.name}
				</td>
				<td>
					${dictionaryCode.code}
				</td>
				<td>
					${dictionaryCode.remark}
				</td>
			</tr>
		    </c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
			<script type="text/javascript">
				function page(n,s){
				   $("#pageNo").val(n);
				   $("#pageSize").val(s);
				   $("#searchForm").submit();
			       return false;
			     }
			</script>
	   </div>
</body>
</html>