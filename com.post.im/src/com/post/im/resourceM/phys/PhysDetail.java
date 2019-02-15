/**
 * 
 */
package com.post.im.resourceM.phys;

import java.util.Date;

import com.eos.foundation.data.DataObjectUtil;
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
public class PhysDetail {

	/**
	 * 
	 */
	public PhysDetail() {
		// TODO 自动生成的构造函数存根
	}
	
	public DataObject dataParseUpdate(DataObject dataObject){
		DataObject tbimapplyappr = DataObjectUtil.createDataObject("com.post.im.resourceM.appr.appr.TbImApplyAppr");
		tbimapplyappr.set("operTime", new Date());
		tbimapplyappr.set("userId", dataObject.get("userId"));
		tbimapplyappr.set("operType", "01");//操作类别
		tbimapplyappr.set("sourceType", "01");//资源类别
		tbimapplyappr.set("sourceId", dataObject.get("recId"));
		tbimapplyappr.set("crTime", new Date());
		return tbimapplyappr;
	}
	
	public DataObject dataParseAdd(DataObject dataObject){
		DataObject tbimapplyappr = DataObjectUtil.createDataObject("com.post.im.resourceM.appr.appr.TbImApplyAppr");
		tbimapplyappr.set("operTime", new Date());
		tbimapplyappr.set("userId", dataObject.get("userId"));
		tbimapplyappr.set("operType", "01");//操作类别
		tbimapplyappr.set("sourceType", "01");//资源类别
		tbimapplyappr.set("sourceId", dataObject.get("recId"));
		tbimapplyappr.set("crTime", new Date());
		return tbimapplyappr;
	}
	
	public DataObject dataParseDele(DataObject dataObject){
		DataObject tbimapplyappr = DataObjectUtil.createDataObject("com.post.im.resourceM.appr.appr.TbImApplyAppr");
		tbimapplyappr.set("operTime", new Date());
		tbimapplyappr.set("userId", dataObject.get("userId"));
		tbimapplyappr.set("operType", "01");//操作类别
		tbimapplyappr.set("sourceType", "01");//资源类别
		tbimapplyappr.set("sourceId", dataObject.get("recId"));
		tbimapplyappr.set("crTime", new Date());
		return tbimapplyappr;
	}
}
