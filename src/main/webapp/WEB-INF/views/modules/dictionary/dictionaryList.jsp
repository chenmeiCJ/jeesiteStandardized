<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代码集表管理</title>
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
		<li class="active"><a href="${ctx}/dictionary/dictionary/">代码集表列表</a></li>
		<shiro:hasPermission name="dictionary:dictionary:edit"><li><a href="${ctx}/dictionary/dictionary/form">代码集表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dictionary" action="${ctx}/dictionary/dictionary/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>中文名称：</label>
				<form:input path="ccname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>备注：</label>
				<form:input path="remark" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:checkboxes path="state" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>主要起草人：</label>
				<form:input path="draftman" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>提交机构：</label>
				<form:input path="submittingBody" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>录入时间：</label>
				<input name="entryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dictionary.entryTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>中文名称</th>
				<th>内部标识符</th>
				<th>说明</th>
				<th>状态</th>
				<th>发布日期</th>
				<shiro:hasPermission name="dictionary:dictionary:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dictionary">
			<tr>
		 	    <td>

				</td>
				<td>
					${dictionary.ccname}
				</td>
				<td>
					${dictionary.internalIdentifier}
				</td>
				<td>
					${dictionary.instruction}
				</td>
				<td>
					${fns:getDictLabel(dictionary.state, '', '')}
				</td>
				<td>
					<fmt:formatDate value="${dictionary.releaseDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dictionary:dictionary:edit"><td>
				    <a href="${ctx}/dictionary/dictionary/view?pageSize=10&id=${dictionary.id}">详情</a>
    				<a href="${ctx}/dictionary/dictionary/edit?id=${dictionary.id}">修改</a>
					<a href="${ctx}/dictionary/dictionary/delete?id=${dictionary.id}" onclick="return confirmx('确认要删除该代码集表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>