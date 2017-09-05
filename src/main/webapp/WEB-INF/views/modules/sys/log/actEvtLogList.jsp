<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>日志管理</title>
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
		<li class="active"><a href="${ctx}/sys/log/actEvtLog/">日志列表</a></li>
		<shiro:hasPermission name="sys:log:actEvtLog:edit"><li><a href="${ctx}/sys/log/actEvtLog/form">日志添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="actEvtLog" action="${ctx}/sys/log/actEvtLog/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>log_nr_：</label>
				<form:input path="logNr" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>type_：</label>
				<form:input path="type" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>type_</th>
				<th>proc_def_id_</th>
				<th>proc_inst_id_</th>
				<th>execution_id_</th>
				<th>task_id_</th>
				<th>time_stamp_</th>
				<th>user_id_</th>
				<th>data_</th>
				<th>lock_owner_</th>
				<shiro:hasPermission name="sys:log:actEvtLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="actEvtLog">
			<tr>
				<td><a href="${ctx}/sys/log/actEvtLog/form?id=${actEvtLog.id}">
					${actEvtLog.type}
				</a></td>
				<td>
					${actEvtLog.procDefId}
				</td>
				<td>
					${actEvtLog.procInstId}
				</td>
				<td>
					${actEvtLog.executionId}
				</td>
				<td>
					${actEvtLog.taskId}
				</td>
				<td>
					<fmt:formatDate value="${actEvtLog.timeStamp}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${actEvtLog.user.name}
				</td>
				<td>
					${actEvtLog.data}
				</td>
				<td>
					${actEvtLog.lockOwner}
				</td>
				<shiro:hasPermission name="sys:log:actEvtLog:edit"><td>
    				<a href="${ctx}/sys/log/actEvtLog/form?id=${actEvtLog.id}">修改</a>
					<a href="${ctx}/sys/log/actEvtLog/delete?id=${actEvtLog.id}" onclick="return confirmx('确认要删除该日志吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>