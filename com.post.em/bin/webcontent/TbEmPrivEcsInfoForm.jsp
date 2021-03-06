<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%--
- Author(s): zhaohui
- Date: 2019-01-12 15:05:34
- Description:
    --%>
    <head>
        <title>
            私有云资源服务器信息录入
        </title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
        <script src="<%= request.getContextPath() %>/common/nui/nui.js" type="text/javascript">
        </script>
    </head>
    <body>
        <!-- 标识页面是查看(query)、修改(edit)、新增(add) -->
        <input name="pageType" class="nui-hidden"/>
        <div id="dataform1" style="padding-top:5px;">
            <!-- hidden域 -->
            <input class="nui-hidden" name="tbemprivecsinfo.recId"/>
            <table style="width:100%;height:100%;table-layout:fixed;" class="nui-form-table">
                <tr>
                    <td class="form_label">
                        私有云IP地址:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.ecsIp"/>
                    </td>
                    <td class="form_label">
                        ECS名称:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.ecsNm"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        EDAS服务名称:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.edasNm"/>
                    </td>
                    <td class="form_label">
                        Cpu信息:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.cpuInfo"/>
                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        内存信息:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.memoInfo"/>
                    </td>
                  
                
                
                    <td class="form_label">
                        所属环境:
                    </td>
                    <td colspan="1">
                       
                        <select name="tbemprivecsinfo.evmentType" class="nui-combobox">
						  <option value ="01">开发环境</option>
						  <option value ="02">测试环境</option>
						  <option value="03">生产模拟环境</option>
						  <option value="04">培训环境</option>
						  <option value="05">新技术环境</option>
						</select>

                    </td>
                </tr>
                <tr>
                    <td class="form_label">
                        管理员用户:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.adminNm"/>
                    </td>
                    <td class="form_label">
                        管理员密码:
                    </td>
                    <td colspan="1">
                        <input class="nui-textbox" name="tbemprivecsinfo.adminPass"/>
                    </td>
                </tr>
             
            </table>
            <div class="nui-toolbar" style="padding:0px;" borderStyle="border:0;">
                <table width="100%">
                    <tr>
                        <td style="text-align:center;" colspan="4">
                            <a class="nui-button" iconCls="icon-save" onclick="onOk()">
                                保存
                            </a>
                            <span style="display:inline-block;width:25px;">
                            </span>
                            <a class="nui-button" iconCls="icon-cancel" onclick="onCancel()">
                                取消
                            </a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <script type="text/javascript">
            nui.parse();

            function saveData(){

                var form = new nui.Form("#dataform1");
                form.setChanged(false);
                //保存
                var urlStr = "com.post.em.tbemprivecsinfobiz.addTbEmPrivEcsInfo.biz.ext";
                var pageType = nui.getbyName("pageType").getValue();//获取当前页面是编辑还是新增状态
                //编辑
                if(pageType=="edit"){
                    urlStr = "com.post.em.tbemprivecsinfobiz.updateTbEmPrivEcsInfo.biz.ext";
                }
                form.validate();
                if(form.isValid()==false) return;

                var data = form.getData(false,true);
                var json = nui.encode(data);

                $.ajax({
                    url:urlStr,
                    type:'POST',
                    data:json,
                    cache:false,
                    contentType:'text/json',
                    success:function(text){
                        var returnJson = nui.decode(text);
                        if(returnJson.exception == null){
                            CloseWindow("saveSuccess");
                        }else{
                            nui.alert("保存失败", "系统提示", function(action){
                                if(action == "ok" || action == "close"){
                                    //CloseWindow("saveFailed");
                                }
                                });
                            }
                        }
                        });
                    }

                    //页面间传输json数据
                    function setFormData(data){
                        //跨页面传递的数据对象，克隆后才可以安全使用
                        var infos = nui.clone(data);

                        //保存list页面传递过来的页面类型：add表示新增、edit表示编辑
                        nui.getbyName("pageType").setValue(infos.pageType);

                        //如果是点击编辑类型页面
                        if (infos.pageType == "edit") {
                            var json = infos.record;

                            var form = new nui.Form("#dataform1");//将普通form转为nui的form
                            form.setData(json);
                            form.setChanged(false);
                        }
                    }

                    //关闭窗口
                    function CloseWindow(action) {
                        if (action == "close" && form.isChanged()) {
                            if (confirm("数据被修改了，是否先保存？")) {
                                saveData();
                            }
                        }
                        if (window.CloseOwnerWindow)
                        return window.CloseOwnerWindow(action);
                        else window.close();
                    }

                    //确定保存或更新
                    function onOk() {
                        saveData();
                    }

                    //取消
                    function onCancel() {
                        CloseWindow("cancel");
                    }
                </script>
            </body>
        </html>
