<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>限定词信息表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			 //$('table tr:not(:first)').remove();
	        var len = $('table tr').length;
	        for(var i = 1;i<len;i++){
	            $('table tr:eq('+i+') td:first').text(i);
	        }			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/determiner/determiner/">限定词信息表列表</a></li>
		<shiro:hasPermission name="determiner:determiner:edit"><li><a href="${ctx}/determiner/determiner/form">限定词信息表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="determiner" action="${ctx}/determiner/determiner/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内部标识符：</label>
				<form:input path="internalIdentifier" htmlEscape="false" maxlength="36" class="input-medium"/>
			</li>
			<li><label>标识符：</label>
				<form:input path="identifier" htmlEscape="false" maxlength="36" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="ccname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:checkboxes path="statue" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>提交日期：</label>
				<input name="submissionDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${determiner.submissionDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>录入单位：</label>
				<form:input path="entryUnit" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>数据来源：</label>
				<form:input path="origin" htmlEscape="false" maxlength="200" list="originList" class="input-medium"/>
				<datalist id="originList">
				    <option>上饶市公安局</option>
				    <option>南京市公安局</option>
				    <option>公安数据元管理系统</option>
				</datalist> 
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			    <th>序号</th>
				<th>内部标识符</th>
				<th>标识符</th>
				<th>中文名称</th>
				<th>说明</th>
				<shiro:hasPermission name="determiner:determiner:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="determiner">
			<tr>
		 	    <td>

				</td>
				<td>
					${determiner.internalIdentifier}
				</td>
				<td>
					${determiner.identifier}
				</td>
				<td>
					${determiner.ccname}
				</td>
				<td>
					${determiner.remark}
				</td>
				<shiro:hasPermission name="determiner:determiner:edit"><td>
				    <a href="${ctx}/determiner/determiner/view?id=${determiner.id}">详情</a>
    				<a href="${ctx}/determiner/determiner/edit?id=${determiner.id}">修改</a>
					<a href="${ctx}/determiner/determiner/delete?id=${determiner.id}" onclick="return confirmx('确认要删除该限定词信息表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>