<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- 
  - Author(s): Administrator
  - Date: 2019-01-15 16:32:35
  - Description:
-->
<head>
<title>删除项目成员</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="<%=request.getContextPath()%>/common/nui/nui.js"
	type="text/javascript"></script>
</head>
<body>
<body style="width: 98%; height: 95%;">
	<div id="form1" class="nui-form" align="center" style="height: 0%">
		<!-- 数据实体的名称 -->
		<input class="nui-hidden" name="criteria/_entity"
			value="com.post.im.itemsM.itemsM.ItemsUserInfo"> <input
			class="nui-textbox" name="criteria/_expr[1]/itemsCd" />
			<a id="search" class="nui-button"
						onclick="search()"> 查询 </a> 
	</div>
	<div class="nui-panel" title="项目成员列表" iconCls="icon-add"
		style="width: 100%; height: 85%;" showToolbar="false"
		showFooter="false">
		<div class="nui-toolbar" style="border-bottom: 0; padding: 0px;">
			<table style="width: 100%;">
				<tr>
					<td style="width: 100%;">
						<!--                             <a class="nui-button" iconCls="icon-add" onclick="add()"> -->
						<!--                                 增加 --> <!--                             </a> -->
						<a id="update" class="nui-button" iconCls="icon-edit"
						onclick="edit()"> 确定 </a> <!--                             <a class="nui-button" iconCls="icon-remove" onclick="remove()"> -->
						<!--                                 删除 --> <!--                             </a> -->
					</td>
				</tr>
			</table>
		</div>
		<div class="nui-fit">
			<div id="datagrid1" dataField="ItemsUserInfos" class="nui-datagrid"
				style="width: 100%; height: 100%;"
				url="com.post.im.itemsM.itemsTellerManager.queryItemsUser.biz.ext"
				pageSize="10" showPageInfo="true" multiSelect="true"
				allowSortColumn="false">

				<div property="columns">
					<div type="indexcolumn"></div>
					<div type="checkcolumn"></div>
					<div field="tlrNo" headerAlign="center" allowSort="true">
						人员编码</div>
					<div field="tlrName" headerAlign="center" allowSort="true">
						人员名称</div>
					<div field="itemsCd" headerAlign="center" allowSort="true">
						项目编码</div>
					<div field="itemsNm" headerAlign="center" allowSort="true">
						项目名称</div>

				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("datagrid1");
		function setFormData(data) {
			//跨页面传递的数据对象，克隆后才可以安全使用
			var infos = nui.clone(data);
			//保存list页面传递过来的页面类型：add表示新增、edit表示编辑
			nui.getbyName("criteria/_expr[1]/itemsCd").setValue(
					infos.itemsCdFrom);
		}
		function click() {
			document.getElementById("search").click();
		}

		var formData = new nui.Form("#form1").getData(false, false);
		grid.load(formData);

		//删除--修改关系表的状态
		function edit() {
			var rows = grid.getSelecteds();
			if (rows.length > 0) {
				nui
						.confirm(
								"确定删除选中记录？",
								"系统提示",
								function(action) {
									if (action == "ok") {
										var json = nui.encode({
											ItemsUserInfos : rows
										});
										grid.loading("正在删除中,请稍等...");
										$
												.ajax({
													url : "com.post.im.itemsM.itemsTellerManager.editItemsUser.biz.ext",
													type : 'POST',
													data : json,
													cache : false,
													contentType : 'text/json',
													success : function(text) {
														var returnJson = nui
																.decode(text);
														if (returnJson.exception == null) {
															grid.reload();
															nui
																	.alert(
																			"删除成功",
																			"系统提示",
																			function(
																					action) {
																					search();
																			});
														} else {
															grid.unmask();
															nui.alert("删除失败",
																	"系统提示");
														}
													}
												});
									}
								});
			} else {
				nui.alert("请选中一条记录！");
			}
		}

		//重新刷新页面
		function refresh() {
			var form = new nui.Form("#form1");
			var json = form.getData(false, false);
			grid.load(json);//grid查询
			nui.get("update").enable();
		}

		//查询
		function search() {
			var form = new nui.Form("#form1");
			var json = form.getData(false, false);
			grid.load(json);//grid查询
		}

		//重置查询条件
		function reset() {
			var form = new nui.Form("#form1");//将普通form转为nui的form
			form.reset();
		}

		//enter键触发查询
		function onKeyEnter(e) {
			search();
		}

// 		//当选择列时
// 		function selectionChanged() {
// 			var rows = grid.getSelecteds();
// 			if (rows.length > 1) {
// 				nui.get("update").disable();
// 			} else {
// 				nui.get("update").enable();
// 			}
// 		}
		//页面间传输json数据
	</script>
</body>


</html>