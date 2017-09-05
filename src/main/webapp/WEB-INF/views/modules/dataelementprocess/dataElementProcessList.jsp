<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据元审批管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/dataelementprocess/dataElementProcess/">数据元审批列表</a></li>
		<shiro:hasPermission name="dataelementprocess:dataElementProcess:edit"><li><a href="${ctx}/dataelementprocess/dataElementProcess/form">数据元审批添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dataElementProcess" action="${ctx}/dataelementprocess/dataElementProcess/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内部标识符：</label>
				<form:input path="nbbsf" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="zwmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>英文名称：</label>
				<form:input path="ywmc" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:checkboxes path="zt" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>提交机构：</label>
				<form:input path="tjjg" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li><label>录入人：</label>
				<form:input path="lrr" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="lxfs" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>录入时间：</label>
				<input name="lrsj" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dataElementProcess.lrsj}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>内部标识符</th>
				<th>中文名称</th>
				<th>版本</th>
				<th>录入时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="dataelementprocess:dataElementProcess:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dataElementProcess">
			<tr>
				<td><a href="${ctx}/dataelementprocess/dataElementProcess/form?id=${dataElementProcess.id}">
					${dataElementProcess.nbbsf}
				</a></td>
				<td>
					${dataElementProcess.zwmc}
				</td>
				<td>
					${dataElementProcess.bb}
				</td>
				<td>
					<fmt:formatDate value="${dataElementProcess.lrsj}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${dataElementProcess.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dataelementprocess:dataElementProcess:edit"><td>
    				<a href="${ctx}/dataelementprocess/dataElementProcess/form?id=${dataElementProcess.id}">修改</a>
					<a href="${ctx}/dataelementprocess/dataElementProcess/delete?id=${dataElementProcess.id}" onclick="return confirmx('确认要删除该数据元审批吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>