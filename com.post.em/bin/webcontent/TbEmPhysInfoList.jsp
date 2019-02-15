<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): zhaohui
- Date: 2019-01-12 15:13:45
- Description:
    --%>
    <head>
        <title>
            物理机资源信息查询
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body style="width:98%;height:95%;">
        <div class="nui-panel" title="物理机资源信息查询" iconCls="icon-add" style="width:100%;height:15%;" showToolbar="false" showFooter="true">
            <div id="form1" class="nui-form" align="center" style="height:100%">
                <!-- 数据实体的名称 -->
                <input class="nui-hidden" name="criteria/_entity" value="com.post.em.baseInfo.phys.TbEmPhysInfo">
                <!-- 排序字段 -->
                <input class="nui-hidden" name="criteria/_orderby[1]/_property" value="edTime">
                <input class="nui-hidden" name="criteria/_orderby[1]/_sort" value="asc">
                <table id="table1" class="table" style="height:100%">
                    <tr>
                        <td class="form_label">
                            物理机IP地址:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="criteria/_expr[1]/physIp"/>
                            <input class="nui-hidden" name="criteria/_expr[1]/_op" value="=">
                        </td>
                        <td class="form_label">
                            项目编码:
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="criteria/_expr[2]/itemsCd"/>
                            <input class="nui-hidden" name="criteria/_expr[2]/_op" value="=">
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <!--footer-->
        <div property="footer" align="center">
            <a class="nui-button" onclick="search()">
                查询
            </a>
            <a class="nui-button" onclick="reset()">
                重置
            </a>
        </div>
        <div class="nui-panel" title="物理机资源信息列表" iconCls="icon-add" style="width:100%;height:85%;" showToolbar="false" showFooter="false" >
            <div class="nui-toolbar" style="border-bottom:0;padding:0px;">
                <table style="width:100%;">
                    <tr>
                        <td style="width:100%;">
                            <a class="nui-button" iconCls="icon-add" onclick="add()">
                                增加
                            </a>
                            <a id="update" class="nui-button" iconCls="icon-edit" onclick="edit()">
                                编辑
                            </a>
                                              <a id="grant" class="nui-button" iconCls="icon-add" onclick="grant()">
                                下发
                            </a>
                             
                               <a id="grant" class="nui-button" iconCls="icon-add" onclick="recovery()">
                回收                
                            </a>
                            <a class="nui-button" iconCls="icon-remove" onclick="remove()">
                                删除
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="nui-fit">
                <div id="datagrid1" dataField="tbemphysinfos" class="nui-datagrid" style="width:100%;height:100%;" url="com.post.em.tbemphysinfobiz.queryTbEmPhysInfos.biz.ext" pageSize="10" showPageInfo="true" multiSelect="true" onselectionchanged="selectionChanged" allowSortColumn="false">
                    <div property="columns">
                        <div type="indexcolumn">
                        </div>
                        <div type="checkcolumn">
                        </div>
                        <div field="recId" headerAlign="center" allowSort="true" visible="false">
                            recId
                        </div>
                        <div field="physIp" headerAlign="center" allowSort="true" >
                            物理机ip地址
                        </div>
                        <div field="cpuInfo" headerAlign="center" allowSort="true" >
                           Cpu信息
                        </div>
                        <div field="memoInfo" headerAlign="center" allowSort="true" >
                            内存信息
                        </div>
                      
                        <div field="itemsCd" headerAlign="center" allowSort="true" >
                            项目组编码
                        </div>
                        <div field="itemsNm" headerAlign="center" allowSort="true" >
                           所属项目组
                        </div>
                        <div field="evmentType" headerAlign="center" allowSort="true" >
                            所属环境
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
                    url: "/kfcs/em/TbEmPhysInfoForm.jsp",
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
                        url: "/kfcs/em/TbEmPhysInfoForm.jsp",
                        title: "编辑数据",
                        width: 600,
                        height: 300,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = {pageType:"edit",record:{tbemphysinfo:row}};
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
                                    var json = nui.encode({tbemphysinfos:rows});
                                    grid.loading("正在删除中,请稍等...");
                                    $.ajax({
                                        url:"com.post.em.tbemphysinfobiz.deleteTbEmPhysInfos.biz.ext",
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
                                
                                
                                 //下发
            function grant() {
                var rows = grid.getSelecteds();
                if (rows.length>0) {
                     nui.open({
                    url: "/kfcs/em/TbImItemsInfoList.jsp",
                    title: "项目组信息", width: 600, height: 300,
                    onload: function () {//弹出页面加载完成
                    
                    },
                    ondestroy: function (action) {//弹出页面关闭前
                    if(action.length>0){
                    for (var i=0;i <rows.length;i++){
                       rows[i].itemsCd = action[0].itemsCd;
                       rows[i].itemsNm = action[0].itemsNm;
                    }
                    
                     var json = nui.encode({tbemphysinfos:rows});
                   // var json = nui.encode(rows);
                     $.ajax({
                                        url:"com.post.em.baseInfo.regrant.physregrant.biz.ext",
                                        
                                        type:'POST',
                                        data:json,
                                        cache: false,
                                        contentType:'text/json',
                                        success:function(text){
                                            var returnJson = nui.decode(text);
                                            if(returnJson.exception == null){
                                                grid.reload();
                                                nui.alert("下发成功", "系统提示", function(action){
                                                    });
                                                }else{
                                                    grid.unmask();
                                                    nui.alert("下发失败", "系统提示");
                                                }
                                            }
                                            });
                                            
                                            
                }}
                });
                } else {
                            nui.alert("请选中一条记录","提示");
                        }
            }
            
            
            //回收
            function recovery() {
                var rows = grid.getSelecteds();
                if (rows.length>0) {
                    if(rows.length>0){
                    for (var i=0;i <rows.length;i++){
                       rows[i].itemsCd = null;
                       rows[i].itemsNm = null;
                    }
                     var json = nui.encode({tbemphysinfos:rows});
                      $.ajax({
                                        url:"com.post.em.baseInfo.regrant.physrecovery.biz.ext",
                                        
                                        type:'POST',
                                        data:json,
                                        cache: false,
                                        contentType:'text/json',
                                        success:function(text){
                                            var returnJson = nui.decode(text);
                                            if(returnJson.exception == null){
                                                grid.reload();
                                                nui.alert("回收成功", "系统提示", function(action){
                                                    });
                                                }else{
                                                    grid.unmask();
                                                    nui.alert("回收失败", "系统提示");
                                                }
                                            }
                                            });
                } else {
                            nui.alert("请选中一条记录","提示");
                        }
            }}

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
                            </script>
                        </body>
                    </html>
