<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): zhaohui
- Date: 2019-01-14 14:44:51
- Description:
    --%>
    <head>
        <title>
            项目组信息查询
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body style="width:98%;height:95%;">
        <div class="nui-panel" title="项目组信息查询" iconCls="icon-add" style="width:100%;height:15%;" showToolbar="false" showFooter="true">
            <div id="form1" class="nui-form" align="center" style="height:100%">
                <!-- 数据实体的名称 -->
                <input class="nui-hidden" name="criteria/_entity" value="com.post.em.baseInfo.item.TbImItemsInfo">
                <!-- 排序字段 -->
                <input class="nui-hidden" name="criteria/_orderby[1]/_property" value="crTime">
                <input class="nui-hidden" name="criteria/_orderby[1]/_sort" value="asc">
                 <table id="table1" class="table" style="height:100%">
                    <tr>
                        <td class="form_label">
                                                         项目名称    :
                        </td>
                        <td colspan="1">
                            <input class="nui-textbox" name="criteria/_expr[1]/itemsNm"/>
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
                  <a class="nui-button" onclick="ok()">
                确定
            </a>
            
        
        </div>
        <div class="nui-panel" title="项目组信息列表" iconCls="icon-add" style="width:100%;height:85%;" showToolbar="false" showFooter="false" >
           
            <div class="nui-fit">
                <div id="datagrid1" dataField="tbimitemsinfos" class="nui-datagrid" style="width:100%;height:100%;" url="com.post.em.tbimitemsinfobiz.queryTbImItemsInfos.biz.ext" pageSize="10" showPageInfo="true" multiSelect="true" onselectionchanged="selectionChanged" allowSortColumn="false">
                    <div property="columns">
                        <div type="indexcolumn">
                        </div>
                        <div type="checkcolumn">
                        </div>
                        <div field="itemsCd" headerAlign="center" allowSort="true" visible="false">
                            项目编码
                        </div>
                        <div field="itemsNm" headerAlign="center" allowSort="true" >
                            项目名称
                        </div>
                        <div field="itemsType" headerAlign="center" allowSort="true" >
                            项目类别
                        </div>
                        <div field="itemsSource" headerAlign="center" allowSort="true" >
                            数据来源
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
          
                    //下发
                    function save(){
                        var rows = grid.getSelecteds();
                        if(rows.length > 0){
                            nui.confirm("确定下发到选中记录？","系统提示",
                            function(action){
                                if(action=="ok"){
                                    var json = nui.encode({tbemprivecsinfo:row});
                                    grid.loading("正在下发中,请稍等...");
                                    $.ajax({
                                        url:"com.post.em.tbemprivecsinfobiz.updateTbEmPrivEcsInfo.biz.ext",
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
                                    grid.load(json);//grid查询
                                }

                         
                                //enter键触发查询
                                function onKeyEnter(e) {
                                    search();
                                }
                                function close(){
                                  window.close();
                                
                                }
                                 //当选择列时
                                function selectionChanged(){
                                    var rows = grid.getSelecteds();
                                    if(rows.length>1){
                                        nui.alert("请选择一条记录");
                                        search();
                                    }
                                    
                                }
                     function ok(){
                   
                     var row = grid.getSelecteds();
                     if (row.length=0){
                     nui.alert("请选择一");
                     }
                     CloseWindow("cancel");
                    }           
                                  //关闭窗口
                    function CloseWindow(action) {
                       var row = grid.getSelecteds();
                        if (window.CloseOwnerWindow)
                        return window.CloseOwnerWindow(row);
                        else window.close();
                    }
                            </script>
                        </body>
                    </html>
