<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>代码集表管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	    function checkIsExist(value,type) {
	    	type = $.trim(type);
	        value = $.trim(value);
	        if(value==""){
	        	if(type=="internalIdentifier"){
	        	   $("#nbbsfShowResult").html("");  
	        	}else if(type=="ccname"){
	        	   $("#ccnamefShowResult").html("");  
	        	}
	        	return;
	        } 
	        $.ajax({  
	            type:"POST",   //http请求方式  
	            url:"${ctx}/dictionary/dictionary/IsExist", //发送给服务器的url  
	            data:"value="+value+"&type="+type, //发送给服务器的参数  
	            dataType:"json",  //告诉JQUERY返回的数据格式(注意此处数据格式一定要与提交的controller返回的数据格式一致,不然不会调用回调函数complete)  
	            complete : function(msg) {  
	              var result = eval("(" + msg.responseText + ")"); 
	              if(type=="ccname"){
		              if(result.success) {  
		                  $("#ccnamefShowResult").html(result.message);  
		                } else {  
		                  $("#ccnamefShowResult").html(result.message);  
		               } 
	              }else if(type=="internalIdentifier"){
		              if(result.success) {  
		                  $("#nbbsfShowResult").html(result.message);  
		                } else {  
		                  $("#nbbsfShowResult").html(result.message);  
		               }   
	              }  
	           }   
	        });  
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dictionary/dictionary/">代码集表列表</a></li>
		<li class="active"><a href="${ctx}/dictionary/dictionary/form?id=${dictionary.id}">代码集表<shiro:hasPermission name="dictionary:dictionary:edit">${not empty dictionary.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dictionary:dictionary:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dictionary" action="${ctx}/dictionary/dictionary/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
			
		<fieldset>
		<legend>代码集属性</legend>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="ccname" htmlEscape="false" maxlength="64" class="input-xlarge required" onblur="checkIsExist(this.value,'ccname');" style="border-color: red;"/>
			    <span class="help-inline"><font color="red">*</font> </span>
			    <span>例如:公安机关职务序列代码</span>
				<span id="ccnamefShowResult"></span>
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
				<form:input path="internalIdentifier" htmlEscape="false" maxlength="36" class="input-xlarge " onblur="checkIsExist(this.value,'internalIdentifier');"/>
				<span id="nbbsfShowResult"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">说明：</label>
			<div class="controls">
				<form:input path="instruction" htmlEscape="false" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:人民警察分类管理，实施公安机关民警套改晋升工作所划分的职务序列</span>
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
			<div class="checkbox-group required">
				<form:checkboxes path="state" items="${fns:getDictList('dataelement_del_flag_code')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				<span class="help-inline"><font color="red">*</font> </span>
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
				<form:input path="codingMethod" htmlEscape="false" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:代码采用数字顺序码，用2位数字表示，不足2位则首补0。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主要起草人：</label>
			<div class="controls">
				<form:input path="draftman" htmlEscape="false" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:窦金豹、王存远、苏雳钧、陈伟、伍思畑、李绪华</span>
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
				<form:input path="submittingBody" htmlEscape="false" maxlength="32" class="input-xlarge required" style="border-color: red;"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<span>例如:广东省珠海市公安局科技信息化处</span>
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
		<legend>录入属性</legend>
		<div class="control-group">
			<label class="control-label">录入人：</label>
			<div class="controls">
				<form:input path="entryMan" htmlEscape="false" maxlength="16" class="input-xlarge" value="${fns:getUser().name}" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入单位：</label>
			<div class="controls">
				<form:input path="entryUnit" htmlEscape="false" maxlength="32" class="input-xlarge " value="${fns:getUser().company}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系方式：</label>
			<div class="controls">
				<form:input path="contactInformation" htmlEscape="false" maxlength="32" class="input-xlarge " value="${fns:getUser().mobile}"/>
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
		
		<fieldset>
		<legend>代码集列表</legend>
			<div class="control-group">
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>名称</th>
								<th>代码</th>
								<th>说明</th>
								<shiro:hasPermission name="dictionary:dictionary:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="dictionaryCodeList">
						</tbody>
						<shiro:hasPermission name="dictionary:dictionary:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#dictionaryCodeList', dictionaryCodeRowIdx, dictionaryCodeTpl);dictionaryCodeRowIdx = dictionaryCodeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="dictionaryCodeTpl">
						<tr id="dictionaryCodeList{{idx}}">
							<td class="hide">
								<input id="dictionaryCodeList{{idx}}_id" name="dictionaryCodeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dictionaryCodeList{{idx}}_delFlag" name="dictionaryCodeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="dictionaryCodeList{{idx}}_name" name="dictionaryCodeList[{{idx}}].name" type="text" value="{{row.name}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="dictionaryCodeList{{idx}}_code" name="dictionaryCodeList[{{idx}}].code" type="text" value="{{row.code}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="dictionaryCodeList{{idx}}_remark" name="dictionaryCodeList[{{idx}}].remark" type="text" value="{{row.remark}}" class="input-small "/>
							</td>
							<shiro:hasPermission name="dictionary:dictionary:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dictionaryCodeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
					</script>
					<script type="text/javascript">
						var dictionaryCodeRowIdx = 0, dictionaryCodeTpl = $("#dictionaryCodeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dictionary.dictionaryCodeList)};
							for (var i=0; i<data.length; i++){
								addRow('#dictionaryCodeList', dictionaryCodeRowIdx, dictionaryCodeTpl, data[i]);
								dictionaryCodeRowIdx = dictionaryCodeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="dictionary:dictionary:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>