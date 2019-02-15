<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): zhoujl
  - Date: 2019-01-14 18:51:13
  - Description:
-->
<head>
<title>资源申请审批明细表</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript"></script>
    
</head>
<body style="width:98%;height:95%;">
	<div class="nui-panel" title="资源申请审批信息查询" iconCls="icon-add"
		style="width: 100%; height: 15%;" showToolbar="false"
		showFooter="true">
		<div id="form1" class="nui-form" align="center" style="height: 100%">
			<!-- 数据实体的名称 -->
			<input class="nui-hidden" name="criteria/_entity"
				value="com.post.im.resoAplyAppr.TbImApplyAppr">
			<!-- 排序字段 -->
			<input class="nui-hidden" name="criteria/_orderby[1]/_property"
				value="operTime"> <input class="nui-hidden"
				name="criteria/_orderby[1]/_sort" value="desc">
			<table id="table1" class="table" style="height: 100%">
				<tr>
					<td class="form_label">操作人ID:</td>
					<td colspan="1">
						<input class="nui-textbox" name="criteria/_expr[1]/userId" /> 
						<input class="nui-hidden" name="criteria/_expr[1]/_op" value="like"> 
						<input class="nui-hidden" name="criteria/_expr[1]/_likeRule" value="all">
					</td>
					<td class="form_label">操作类别:</td>
					<td colspan="1">
						<input class="nui-textbox" name="criteria/_expr[2]/operType" /> 
						<input class="nui-hidden" name="criteria/_expr[2]/_op" value="="> 
					</td>
					<td class="form_label">资源类别:</td>
					<td colspan="1">
						<input class="nui-textbox" name="criteria/_expr[3]/operType" /> 
						<input class="nui-hidden" name="criteria/_expr[3]/_op" value="="> 
					</td>
				</tr>
			</table>
		</div>
	</div>
	<!--footer-->
	<div property="footer" align="center">
		<a class="nui-button" onclick="search()"> 查询 </a> <a
			class="nui-button" onclick="reset()"> 重置 </a>
	</div>
	<div class="nui-panel" title="查询信息" iconCls="icon-add"
		style="width: 100%; height: 85%;" showToolbar="false"
		showFooter="false">
		<div class="nui-toolbar" style="border-bottom: 0; padding: 0px;">
			<table style="width: 100%;">
				<tr>
					
				</tr>
			</table>
		</div>
		<div class="nui-fit">
			<div id="datagrid1" dataField="tblmApplyApprInfo" class="nui-datagrid"
				style="width: 100%; height: 100%;"
				url="com.post.im.itemsM.resoAplyAppr.resoApluApprQuery.biz.ext"
				pageSize="10" showPageInfo="true" multiSelect="true"
				onselectionchanged="selectionChanged" allowSortColumn="false">
				<div property="columns">
					<div type="indexcolumn"></div>
					<div type="checkcolumn"></div>
					<div field="recId" headerAlign="center" allowSort="true"
						visible="false">编码</div>
					<div field="operTime" headerAlign="center" allowSort="true">
						操作时间</div>
					<div field="userId" headerAlign="center" allowSort="true">
						操作人ID</div>
					<div field="operType" headerAlign="center" allowSort="true">
						操作类别</div>
					<div field="sourceType" headerAlign="center" allowSort="true">
						资源类别</div>
					<div field="sourceId" headerAlign="center" allowSort="true">
						资源ID</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
    	nui.parse();
    	var grid = nui.get("datagrid1");
		var formData = new nui.Form("#form1").getData(false, false);
		console.log(formData);
		grid.load(formData);
    	function search() {
			var form = new nui.Form("#form1");
			var json = form.getData(false, false);
			console.log(json);
			grid.load(json);//grid查询
		}
		function refresh() {
			var form = new nui.Form("#form1");
			var json = form.getData(false, false);
			console.log(json);
			grid.load(json);//grid查询
			nui.get("update").enable();
		}
		
		function reset(){
        	var form = new nui.Form("#form1");//将普通form转为nui的form
         	form.reset();
        }
    </script>
</body>
</html>