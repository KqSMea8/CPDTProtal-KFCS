<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@include file="/common/common.jsp"%>
<%@ include file="/common/skins/skin0/component.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): Administrator
- Date: 2019-01-12 16:02:48
- Description:
    --%>
<head>
<title>项目信息查询</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<script src="<%=request.getContextPath()%>/common/nui/nui.js"
	type="text/javascript">
	
</script>
</head>
<body style="width: 98%; height: 95%;">
	<div class="nui-panel" title="项目信息查询" iconCls="icon-add"
		style="width: 100%; height: 15%;" showToolbar="false"
		showFooter="true">
		<div id="form1" class="nui-form" align="center" style="height: 100%">
			<!-- 数据实体的名称 -->
			<input class="nui-hidden" name="criteria/_entity"
				value="com.post.im.itemsM.itemsM.TbImItemsInfo">
			<!-- 排序字段 -->
			<input class="nui-hidden" name="criteria/_orderby[1]/_property"
				value="itemsCd"> <input class="nui-hidden"
				name="criteria/_orderby[1]/_sort" value="asc">
			<table id="table1" class="table" style="height: 100%">
				<tr>
					<td class="form_label">项目编码:</td>
					<td colspan="1"><input class="nui-textbox"
						name="criteria/_expr[1]/itemsCd" /> <input class="nui-hidden"
						name="criteria/_expr[1]/_op" value="="></td>
					<td class="form_label">项目名称:</td>
					<td colspan="1"><input class="nui-textbox"
						name="criteria/_expr[2]/itemsNm" /> <input class="nui-hidden"
						name="criteria/_expr[2]/_op" value="like"> <input
						class="nui-hidden" name="criteria/_expr[2]/_likeRule" value="all">
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
	<div class="nui-panel" title="项目信息列表" iconCls="icon-add"
		style="width: 100%; height: 85%;" showToolbar="false"
		showFooter="false">
		<div class="nui-toolbar" style="border-bottom: 0; padding: 0px;">
			<table style="width: 100%;">
				<tr>
					<td style="width: 100%;">
						<!--                             <a class="nui-button" iconCls="icon-add" onclick="add()"> -->
						<!--                                 增加 --> <!--                             </a> -->
						<a id="addItemsUser" class="nui-button" iconCls="icon-edit"
						onclick="addItemsUser()"> 添加项目成员 </a> 
						<a id="editItemsUser" class="nui-button" iconCls="icon-edit"
						onclick="editItemsUser()"> 删除项目成员 </a><!--                             <a class="nui-button" iconCls="icon-remove" onclick="remove()"> -->
						<!--                                 删除 --> <!--                             </a> -->
					</td>
				</tr>
			</table>
		</div>
		<div class="nui-fit">
			<div id="datagrid1" dataField="tbimitemsinfos" class="nui-datagrid"
				style="width: 100%; height: 100%;"
				url="com.post.im.itemsM.tbimitemsinfobiz.queryTbImItemsInfos.biz.ext"
				pageSize="10" showPageInfo="true" multiSelect="true"
				onselectionchanged="selectionChanged" allowSortColumn="false">

				<div property="columns">
					<div type="indexcolumn"></div>
					<div type="checkcolumn"></div>
					<div field="itemsCd" headerAlign="center" allowSort="true"
						visible="false">项目编码</div>
					<div field="itemsNm" headerAlign="center" allowSort="true">
						项目名称</div>
					<div field="itemsType" headerAlign="center" allowSort="true">
						项目类别</div>
					<div field="itemsSource" headerAlign="center" allowSort="true">
						数据来源</div>
					<div field="startTime" headerAlign="center" allowSort="true">
						开始时间</div>
					<div field="endTime" headerAlign="center" allowSort="true">
						结束时间</div>
					<div field="avalWorkDay" headerAlign="center" allowSort="true">
						可用工作日</div>
					<div field="itemsDesc" headerAlign="center" allowSort="true">
						项目描述</div>
					<div field="crTime" headerAlign="center" allowSort="true">
						创建时间</div>
					<div field="edTime" headerAlign="center" allowSort="true">
						修改时间</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		nui.parse();
		var grid = nui.get("datagrid1");

		var formData = new nui.Form("#form1").getData(false, false);
		grid.load(formData);

		//新增
		function add() {
			nui.open({
				url : "TbImItemsInfoForm.jsp",
				title : "新增记录",
				width : 600,
				height : 300,
				onload : function() {//弹出页面加载完成
					var iframe = this.getIFrameEl();
					var data = {
						pageType : "add"
					};//传入页面的json数据
					iframe.contentWindow.setFormData(data);
				},
				ondestroy : function(action) {//弹出页面关闭前
					grid.reload();
				}
			});
		}

		//添加项目成员
		function addItemsUser() {
			var row = grid.getSelected();
			if (row) {
				//                 var strUrl="/teller/teller-managerMH.html";

				//                 showModalCenter(strUrl, null, callBack, "800px", "400px", "人员查询");
				nui
						.open({
							//url : "/teller/teller-managerMH.html",
							url : "/kfcs/im/teller-managerMH.html",
							title : "编辑数据",
							width : 800,
							height : 400,
							onload : function() {
								//                             var iframe = this.getIFrameEl();
								//                             var data = {pageType:"edit",record:{tbimitemsinfo:row}};
								//                             //直接从页面获取，不用去后台获取
								//                             iframe.contentWindow.setFormData(data);
							},

							ondestroy : function(action) {
								var data1 = {
									user : action,
									tbimitemsinfo : row
								};
								urlStr = " com.post.im.itemsM.itemsTellerManager.insertItemUser.biz.ext";
								var json = nui.encode(data1);
								alert(json);
								$.ajax({
									url : urlStr,
									type : 'POST',
									data : json,
									cache : false,
									contentType : 'text/json',
									success : function(text) {
										var returnJson = nui.decode(text);
										if (returnJson.exception == null) {
											grid.reload();
											nui.alert("添加成功", "系统提示", function(
													action) {
											});
										} else {
											grid.unmask();
											nui.alert("添加失败", "系统提示");
										}
									}
								});

							}
						});
			} else {
				nui.alert("请选中一条记录", "提示");
			}
		}

	//修改项目成员
		function editItemsUser() {
			var row = grid.getSelected();
			if (row) {
				
				nui.open({
							url : "/kfcs/im/EditItemsUser.jsp",
							width : 800,
							height : 400,
							onload : function() {
								                             var iframe = this.getIFrameEl();
								                             //var data = {pageType:"delete",record:{tbimitemsinfo:row}};
								                             //直接从页面获取，不用去后台获取
								                              var data = {itemsCdFrom:row.itemsCd};//传入页面的json数据
								                             iframe.contentWindow.setFormData(data);
								                             iframe.contentWindow.click();
// 							,

// 							ondestroy : function(action) {
// 								var t = eval("(" + action + ")");
// 								var data1 = {
// 									user : action,
// 									tbimitemsinfo : row
// 								};
// 								urlStr = " com.post.im.itemsM.itemsTellerManager.editItemsUser.biz.ext";
// 								var json = nui.encode(data1);
// 								alert(json);
// 								$.ajax({
// 									url : urlStr,
// 									type : 'POST',
// 									data : json,
// 									cache : false,
// 									contentType : 'text/json',
// 									success : function(text) {
// 										var returnJson = nui.decode(text);
// 										if (returnJson.exception == null) {
// 											grid.reload();
// 											nui.alert("添加成功", "系统提示", function(
// 													action) {
// 											});
// 										} else {
// 											grid.unmask();
// 											nui.alert("添加失败", "系统提示");
// 										}
// 									}
// 								});

							}
						});
			} else {
				nui.alert("请选中一条记录", "提示");
			}
		}
		function callBack(value) {
			alert(value)
		}


		//删除
		function remove() {
			var rows = grid.getSelecteds();
			if (rows.length > 0) {
				nui
						.confirm(
								"确定删除选中记录？",
								"系统提示",
								function(action) {
									if (action == "ok") {
										var json = nui.encode({
											tbimitemsinfos : rows
										});
										grid.loading("正在删除中,请稍等...");
										$
												.ajax({
													url : "com.post.im.itemsM.tbimitemsinfobiz.deleteTbImItemsInfos.biz.ext",
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

		//当选择列时
		function selectionChanged() {
			var rows = grid.getSelecteds();
			if (rows.length > 1) {
				nui.get("addItemsUser").disable();
				nui.get("editItemsUser").disable();
			} else {
				nui.get("addItemsUser").enable();
				nui.get("editItemsUser").enable();
			}
		}
	</script>
</body>
</html>
