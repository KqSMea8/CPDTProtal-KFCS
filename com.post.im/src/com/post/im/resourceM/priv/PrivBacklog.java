/**
 * 
 */
package com.post.im.resourceM.priv;

import java.util.Date;

import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;

import commonj.sdo.DataObject;

/**
 * <pre>
 * Title: 程序的中文名称
 * Description: 程序功能的描述
 * </pre>
 * @author Administrator
 * @version 1.00.00
 * 
 */
/*
 * 修改历史
 * $log$
 */
public class PrivBacklog {

	/**
	 * 
	 */
	public PrivBacklog() {
		// TODO 自动生成的构造函数存根
	}
	public DataObject dataParseToBacklogUpdate(DataObject dataObject){
		DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
		int resourceId = (Integer) dataObject.get("recId");
		Object[] tbImBacklog = DatabaseExt.queryByNamedSql("default", "com.post.im.backlog.backlog.selectRecIdByResourceId", resourceId);
		int recId = (Integer)tbImBacklog[0];
		String openStat = (String) dataObject.get("openStat");
		if("04".equals(openStat) || "06".equals(openStat)){
			tbImBacklogInfo.set("todoType","02");//待阅
		}else{
			tbImBacklogInfo.set("todoType","01");//待办
		}
		tbImBacklogInfo.set("recId", recId);
		tbImBacklogInfo.set("resourceId", resourceId);
		
		tbImBacklogInfo.set("todoTitle", "私有云资源申请");
		tbImBacklogInfo.set("itemsCd", dataObject.get("itemsCd"));
		tbImBacklogInfo.set("applyDate", dataObject.get("applyDate"));
		tbImBacklogInfo.set("detailUrl", "../priv/BacklogToPrivApproval.html");
		tbImBacklogInfo.set("userId", dataObject.get("userId"));
		tbImBacklogInfo.set("backlogUser", "");
		tbImBacklogInfo.set("backlogRole", "MANAGER");
		tbImBacklogInfo.set("handleTime", new Date());
		tbImBacklogInfo.set("openStat", dataObject.get("openStat"));
		tbImBacklogInfo.set("edTime", new Date());
		return tbImBacklogInfo;
	}
	/**
	 * 新增私有云资源申请时调用，组装待办任务信息表数据
	 * 
	 * */
	public DataObject dataParseToBacklogAdd(DataObject dataObject){
		DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
		tbImBacklogInfo.set("resourceId", dataObject.get("recId"));
		tbImBacklogInfo.set("todoType","01");//待办
		tbImBacklogInfo.set("todoTitle", "私有云资源申请");
		tbImBacklogInfo.set("itemsCd", dataObject.get("itemsCd"));
		tbImBacklogInfo.set("applyDate", dataObject.get("applyDate"));
		tbImBacklogInfo.set("detailUrl", "../priv/BacklogToPrivApproval.html");
		tbImBacklogInfo.set("userId", dataObject.get("userId"));
		tbImBacklogInfo.set("backlogUser", "");
		tbImBacklogInfo.set("backlogRole", "MANAGER");
		tbImBacklogInfo.set("handleTime", new Date());
		tbImBacklogInfo.set("openStat", dataObject.get("openStat"));//申请中
		tbImBacklogInfo.set("edTime", new Date());
		return tbImBacklogInfo;
	}
	
	public DataObject dataParseToBacklogDele(DataObject dataObject){
		DataObject tbImBacklogInfo = DataObjectUtil.createDataObject("com.post.im.backlog.backlog.TbImBacklogInfo");
		tbImBacklogInfo.set("operTime", new Date());
		tbImBacklogInfo.set("userId", dataObject.get("userId"));
		tbImBacklogInfo.set("operType", "01");//操作类别
		tbImBacklogInfo.set("sourceType", "02");//资源类别
		tbImBacklogInfo.set("sourceId", dataObject.get("recId"));
		tbImBacklogInfo.set("crTime", new Date());
		return tbImBacklogInfo;
	}

}
