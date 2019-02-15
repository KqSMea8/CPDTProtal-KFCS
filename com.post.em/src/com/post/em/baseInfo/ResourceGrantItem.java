/**
 * 
 */
package com.post.em.baseInfo;

import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

/**
 * @author zhaohui
 * @date 2019-01-17 10:00:03
 *
 */
@Bizlet("私有云资源下发")
public class ResourceGrantItem {
	
	@Bizlet("reGrant")
	public String reGrant(DataObject[] dataObject ) {
		
		if(dataObject.length>0){
			for (int i = 0; i < dataObject.length; i++) {
				DatabaseUtil.updateEntity("default", dataObject[i]);
			}
			
			if(null !=dataObject[0].get("itemsCd")){ 
			DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
			DatabaseExt.getPrimaryKey(tbImBacklogInfo);
			tbImBacklogInfo.set("todoType","02");//待办
			tbImBacklogInfo.set("resourceId","0");
			tbImBacklogInfo.set("todoType","02");
			tbImBacklogInfo.set("todoTitle", "资源下发已经完成");
			tbImBacklogInfo.set("itemsCd", dataObject[0].get("itemsCd"));
			tbImBacklogInfo.set("applyDate", new Date());
			tbImBacklogInfo.set("detailUrl", "/kfcs/im/priv/TbImPrivApplyApproval.jsp");
			tbImBacklogInfo.set("userId", "user");
			tbImBacklogInfo.set("backlogUser", "");
			tbImBacklogInfo.set("backlogRole", "MANAGER");
			tbImBacklogInfo.set("handleTime", new Date());
			tbImBacklogInfo.set("openStat", "05");//申请中
			tbImBacklogInfo.set("edTime", new Date());
			DatabaseUtil.insertEntity("default", tbImBacklogInfo);
		}
		}
		

		return "success";
	}
	
	@Bizlet("reGrantPys")
	public String reGrantPys(DataObject[] dataObject ) {
		
		if(dataObject.length>0){
			for (int i = 0; i < dataObject.length; i++) {
				DatabaseUtil.updateEntity("default", dataObject[i]);
			}
			
			if(null !=dataObject[0].get("itemsCd")){ 
				DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
				DatabaseExt.getPrimaryKey(tbImBacklogInfo);
				tbImBacklogInfo.set("todoType","02");//待办
				tbImBacklogInfo.set("resourceId","0");
				tbImBacklogInfo.set("todoType","02");
				tbImBacklogInfo.set("todoTitle", "资源下发已经完成");
				tbImBacklogInfo.set("itemsCd", dataObject[0].get("itemsCd"));
				tbImBacklogInfo.set("applyDate", new Date());
				tbImBacklogInfo.set("detailUrl", "/kfcs/im/priv/TbImPrivApplyApproval.jsp");
				tbImBacklogInfo.set("userId", "user");
				tbImBacklogInfo.set("backlogUser", "");
				tbImBacklogInfo.set("backlogRole", "MANAGER");
				tbImBacklogInfo.set("handleTime", new Date());
				tbImBacklogInfo.set("openStat", "05");//申请中
				tbImBacklogInfo.set("edTime", new Date());
				DatabaseUtil.insertEntity("default", tbImBacklogInfo);
			}
		}
	
		return "success";
	}
	
	@Bizlet("reGrantMQ")
	public String reGrantMQ(DataObject[] dataObject ) {
		
		if(dataObject.length>0){
			for (int i = 0; i < dataObject.length; i++) {
				DatabaseUtil.updateEntity("default", dataObject[i]);
			}
			
			if(null !=dataObject[0].get("itemsCd")){ 
				DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
				DatabaseExt.getPrimaryKey(tbImBacklogInfo);
				tbImBacklogInfo.set("todoType","02");//待办
				tbImBacklogInfo.set("resourceId","0");
				tbImBacklogInfo.set("todoType","02");
				tbImBacklogInfo.set("todoTitle", "资源下发已经完成");
				tbImBacklogInfo.set("itemsCd", dataObject[0].get("itemsCd"));
				tbImBacklogInfo.set("applyDate", new Date());
				tbImBacklogInfo.set("detailUrl", "/kfcs/im/priv/TbImPrivApplyApproval.jsp");
				tbImBacklogInfo.set("userId", "user");
				tbImBacklogInfo.set("backlogUser", "");
				tbImBacklogInfo.set("backlogRole", "MANAGER");
				tbImBacklogInfo.set("handleTime", new Date());
				tbImBacklogInfo.set("openStat", "05");//申请中
				tbImBacklogInfo.set("edTime", new Date());
				DatabaseUtil.insertEntity("default", tbImBacklogInfo);
			}
		}
	
		return "success";
	}
	
	@Bizlet("reGrantKvs")
	public String reGrantKvs(DataObject[] dataObject ) {
		
		if(dataObject.length>0){
			for (int i = 0; i < dataObject.length; i++) {
				DatabaseUtil.updateEntity("default", dataObject[i]);
			}
			
			if(null !=dataObject[0].get("itemsCd")){ 
				DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
				DatabaseExt.getPrimaryKey(tbImBacklogInfo);
				tbImBacklogInfo.set("todoType","02");//待办
				tbImBacklogInfo.set("resourceId","0");
				tbImBacklogInfo.set("todoType","02");
				tbImBacklogInfo.set("todoTitle", "资源下发已经完成");
				tbImBacklogInfo.set("itemsCd", dataObject[0].get("itemsCd"));
				tbImBacklogInfo.set("applyDate", new Date());
				tbImBacklogInfo.set("detailUrl", "/kfcs/im/priv/TbImPrivApplyApproval.jsp");
				tbImBacklogInfo.set("userId", "user");
				tbImBacklogInfo.set("backlogUser", "");
				tbImBacklogInfo.set("backlogRole", "MANAGER");
				tbImBacklogInfo.set("handleTime", new Date());
				tbImBacklogInfo.set("openStat", "05");//申请中
				tbImBacklogInfo.set("edTime", new Date());
				DatabaseUtil.insertEntity("default", tbImBacklogInfo);
			}
		}
	
		return "success";
	}
	
	@Bizlet("reGrantRds")
	public String reGrantRds(DataObject[] dataObject ) {
		
		if(dataObject.length>0){
			for (int i = 0; i < dataObject.length; i++) {
				DatabaseUtil.updateEntity("default", dataObject[i]);
			}
			
			if(null !=dataObject[0].get("itemsCd")){ 
				DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
				DatabaseExt.getPrimaryKey(tbImBacklogInfo);
				tbImBacklogInfo.set("todoType","02");//待办
				tbImBacklogInfo.set("resourceId","0");
				tbImBacklogInfo.set("todoType","02");
				tbImBacklogInfo.set("todoTitle", "资源下发已经完成");
				tbImBacklogInfo.set("itemsCd", dataObject[0].get("itemsCd"));
				tbImBacklogInfo.set("applyDate", new Date());
				tbImBacklogInfo.set("detailUrl", "/kfcs/im/priv/TbImPrivApplyApproval.jsp");
				tbImBacklogInfo.set("userId", "user");
				tbImBacklogInfo.set("backlogUser", "");
				tbImBacklogInfo.set("backlogRole", "MANAGER");
				tbImBacklogInfo.set("handleTime", new Date());
				tbImBacklogInfo.set("openStat", "05");//申请中
				tbImBacklogInfo.set("edTime", new Date());
				DatabaseUtil.insertEntity("default", tbImBacklogInfo);
			}
		}
	
		return "success";
	}

}
