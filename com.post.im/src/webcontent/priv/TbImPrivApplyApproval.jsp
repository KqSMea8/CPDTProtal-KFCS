<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): Administrator
- Date: 2019-01-14 16:48:05
- Description:
    --%>
    <head>
        <title>
            私有云资源列表查询
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body style="width:98%;height:95%;">
        <div class="nui-panel" title="私有云资源列表查询" iconCls="icon-add" style="width:100%;height:15%;" showToolbar="false" showFooter="true">
            <div id="form1" class="nui-form" align="center" style="height:100%">
                <!-- 数据实体的名称 -->
                <input class="nui-hidden" name="criteria/_entity" value="com.post.im.resourceM.priv.priv.TbImPrivApply">
                <!-- 排序字段 -->
                <table id="table1" class="table" style="height:100%">
                    <tr>
                        <td class="form_label">
                            项目编码:
                        </td>
                        <td>
                            <input class="nui-textbox" name="criteria/_expr[1]/itemsCd"/>
                            <input class="nui-hidden" name="criteria/_expr[1]/_op" value="=">
                        </td>
                        <td class="form_label">
                            申请状态:
                        </td>
                        <td>
                            <input class="nui-textbox" name="criteria/_expr[2]/openStat"/>
                            <input class="nui-hidden" name="criteria/_expr[2]/_op" value="=">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <!--footer-->
        <div property="footer" align="center">
            <a id="search" class="nui-button" onclick="search()">
                查询
            </a>
            <a class="nui-button" onclick="reset()">
                重置
            </a>
        </div>
        <div class="nui-panel" title=" 私有云资源列表" iconCls="icon-add" style="width:100%;height:85%;" showToolbar="false" showFooter="false" >
            <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
<!--                             <a class="nui-button" iconCls="icon-add" onclick="add()"> -->
<!--                                 资源申请 -->
<!--                             </a> -->
                            <a id="update" class="nui-button" iconCls="icon-edit" onclick="edit()">
                               资源申请审批
                            </a>
<!--                             <a class="nui-button" iconCls="icon-remove" onclick="remove()"> -->
<!--                                 资源申请删除 -->
<!--                             </a> -->
                        </td>
                    </tr>
                </table>
            </div>
            <div class="nui-fit">
                <div id="datagrid1" dataField="tbimprivapplys" class="nui-datagrid" style="width:100%;height:100%;" url="com.post.im.tbimprivapplybiz.queryTbImPrivApplys.biz.ext" pageSize="10" showPageInfo="true" multiSelect="true" onselectionchanged="selectionChanged" allowSortColumn="false">
                    <div property="columns">
                        <div type="indexcolumn">
                        </div>
                        <div type="checkcolumn">
                        </div>
                        <div field="recId" headerAlign="center" allowSort="true" visible="false">
                            编码
                        </div>
                        <div field="itemsCd" headerAlign="center" allowSort="true" >
                            项目编码
                        </div>
                        <div field="userId" headerAlign="center" allowSort="true" >
                            申请人
                        </div>
                        <div field="applyDate" headerAlign="center" allowSort="true" >
                            申请时间
                        </div>
                        <div field="applySpec" headerAlign="center" allowSort="true" >
                            规格
                        </div>
                        <div field="applyDesc" headerAlign="center" allowSort="true" >
                            用途说明
                        </div>
                        <div field="applyCount" headerAlign="center" allowSort="true" >
                            数量
                        </div>
                        <div field="openStat" headerAlign="center" allowSort="true" >
                            申请状态
                        </div>
                        <div field="remark" headerAlign="center" allowSort="true" >
                            审批备注
                        </div>
<!--                         <div field="field1" headerAlign="center" allowSort="true" > -->
<!--                             备用1 -->
<!--                         </div> -->
<!--                         <div field="field2" headerAlign="center" allowSort="true" > -->
<!--                             备用2 -->
<!--                         </div> -->
                        <div field="crTime" headerAlign="center" allowSort="true" >
                            插入时间
                        </div>
                        <div field="edTime" headerAlign="center" allowSort="true" >
                            修改时间
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            nui.parse();
            var grid = nui.get("datagrid1");

            var formData = new nui.Form("#form1").getData(false,false);
            grid.load(formData);

            //新增
            function add() {
                nui.open({
                    url: "kfcs/im/priv/TbImPrivApplyApprovalForm.jsp",
                    title: "新增记录", width: 600, height: 300,
                    onload: function () {//弹出页面加载完成
                    var iframe = this.getIFrameEl();
                    var data = {pageType:"add"};//传入页面的json数据
                    iframe.contentWindow.setFormData(data);
                    },
                    ondestroy: function (action) {//弹出页面关闭前
                    grid.reload();
                }
                });
            }

            //编辑
            function edit() {
                var row = grid.getSelected();
                if (row) {
                    nui.open({
                        url: "kfcs/im/priv/TbImPrivApplyApprovalForm.jsp",
                        title: "编辑数据",
                        width: 600,
                        height: 300,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = {pageType:"edit",record:{tbimprivapply:row}};
                            //直接从页面获取，不用去后台获取
                            iframe.contentWindow.setFormData(data);
                            },
                            ondestroy: function (action) {
                                grid.reload();
                            }
                            });
                        } else {
                            nui.alert("请选中一条记录","提示");
                        }
                    }

                    //删除
                    function remove(){
                        var rows = grid.getSelecteds();
                        if(rows.length > 0){
                            nui.confirm("确定删除选中记录？","系统提示",
                            function(action){
                                if(action=="ok"){
                                    var json = nui.encode({tbimprivapplys:rows});
                                    grid.loading("正在删除中,请稍等...");
                                    $.ajax({
                                        url:"com.post.im.tbimprivapplybiz.deleteTbImPrivApplys.biz.ext",
                                        type:'POST',
                                        data:json,
                                        cache: false,
                                        contentType:'text/json',
                                        success:function(text){
                                            var returnJson = nui.decode(text);
                                            if(returnJson.exception == null){
                                                grid.reload();
                                                nui.alert("删除成功", "系统提示", function(action){
                                                    });
                                                }else{
                                                    grid.unmask();
                                                    nui.alert("删除失败", "系统提示");
                                                }
                                            }
                                            });
                                        }
                                        });
                                    }else{
                                        nui.alert("请选中一条记录！");
                                    }
                                }

                                //重新刷新页面
                                function refresh(){
                                    var form = new  nui.Form("#form1");
                                    var json = form.getData(false,false);
                                    grid.load(json);//grid查询
                                    nui.get("update").enable();
                                }

                                //查询
                                function search() {
                                    var form = new nui.Form("#form1");
                                    var json = form.getData(false,false);
                                    alert(JSON.stringify(json));
                                    grid.load(json);//grid查询
                                }

                                //重置查询条件
                                function reset(){
                                    var form = new nui.Form("#form1");//将普通form转为nui的form
                                    form.reset();
                                }

                                //enter键触发查询
                                function onKeyEnter(e) {
                                    search();
                                }

                                //当选择列时
                                function selectionChanged(){
                                    var rows = grid.getSelecteds();
                                    if(rows.length>1){
                                        nui.get("update").disable();
                                    }else{
                                        nui.get("update").enable();
                                    }
                                }
		function setFormData(data){
		//跨页面传递的数据对象，克隆后才可以安全使用
		var infos = nui.clone(data);
		//保存list页面传递过来的页面类型：add表示新增、edit表示编辑
		//nui.getbyName("pageType").setValue(infos.pageType);
		alert(JSON.stringify(infos));
		alert(infos.record.tbimprivapply.resourceId);
		nui.getbyName("criteria/_expr[1]/itemsCd").setValue(infos.record.tbimprivapply.resourceId);
		document.getElementById("search").click();
		//如果是点击编辑类型页面
// 		if (infos.pageType == "edit") {
// 			var json = infos.record;
// 			var form = new nui.Form("#dataform1");//将普通form转为nui的form
// 			form.setData(json);
// 			form.setChanged(false);
// 			}
		}
                            </script>
                        </body>
                    </html>
