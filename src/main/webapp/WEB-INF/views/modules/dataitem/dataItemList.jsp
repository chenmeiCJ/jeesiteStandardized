<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据项信息表管理</title>
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
		<li class="active"><a href="${ctx}/dataitem/dataItem/">数据项信息表列表</a></li>
		<shiro:hasPermission name="dataitem:dataItem:edit"><li><a href="${ctx}/dataitem/dataItem/form">数据项信息表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dataItem" action="${ctx}/dataitem/dataItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>数据项名称：</label>
				<form:input path="dataItemName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>数据项标识符：</label>
				<form:input path="dataItemIdentifier" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>数据元内部标识符：</label>
				<form:input path="dataElementInternalIdentifier" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>数据元中文名称：</label>
				<form:input path="dataElementCcname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>限定词内部标识符：</label>
				<form:input path="qualifierInternalIdentifier" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>限定词中文名称：</label>
				<form:input path="qualifierCcname" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>录入时间：</label>
				<input name="entryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dataItem.entryDate}" pattern="yyyy-MM-dd"/>"
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
				<th>数据项名称</th>
				<th>数据项标识符</th>
				<th>数据元内部标识符</th>
				<th>数据元中文名称</th>
				<th>限定词内部标识符</th>
				<th>限定词中文名称</th>
				<th>录入人</th>
				<th>录入单位</th>
				<shiro:hasPermission name="dataitem:dataItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dataItem">
			<tr>
		 	    <td>

				</td>
				<td>
					${dataItem.dataItemName}
				</td>
				<td>
					${dataItem.dataItemIdentifier}
				</td>
				<td>
					${dataItem.dataElementInternalIdentifier}
				</td>
				<td>
					${dataItem.dataElementCcname}
				</td>
				<td>
					${dataItem.qualifierInternalIdentifier}
				</td>
				<td>
					${dataItem.qualifierCcname}
				</td>
				<td>
					${dataItem.entryMan}
				</td>
				<td>
					${dataItem.entryUnit}
				</td>
				<shiro:hasPermission name="dataitem:dataItem:edit"><td>
				    <a href="${ctx}/dataitem/dataItem/view?id=${dataItem.id}">详情</a>
    				<a href="${ctx}/dataitem/dataItem/form?id=${dataItem.id}">修改</a>
					<a href="${ctx}/dataitem/dataItem/delete?id=${dataItem.id}" onclick="return confirmx('确认要删除该数据项信息表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>