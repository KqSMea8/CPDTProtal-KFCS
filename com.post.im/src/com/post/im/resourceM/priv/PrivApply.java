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
public class PrivApply {
	public DataObject dataParseUpdate(DataObject dataObject){
		DataObject tbimapplyappr = DataObjectUtil.createDataObject("com.post.im.resourceM.priv.priv.TbImPrivApply");
		tbimapplyappr.set("recId", dataObject.get("recId"));
		tbimapplyappr.set("operTime", new Date());
		tbimapplyappr.set("userId", dataObject.get("userId"));
		tbimapplyappr.set("operType", "01");
		tbimapplyappr.set("sourceType", "02");
		tbimapplyappr.set("sourceId", dataObject.get("recId"));
		return tbimapplyappr;
	}
	
	public DataObject dataParseAdd(DataObject tbimprivapply){
		//DataObject tbimprivapply = DataObjectUtil.createDataObject("com.post.im.resourceM.priv.priv.TbImPrivApply");
		
		tbimprivapply.set("applyDate", new Date());
		tbimprivapply.set("openStat", "01");//操作类别
		tbimprivapply.set("crTime", new Date());
		return tbimprivapply;
	}
	
	public DataObject dataParseDele(DataObject dataObject){
		DataObject tbimapplyappr = DataObjectUtil.createDataObject("com.post.im.resourceM.priv.priv.TbImPrivApply");
		tbimapplyappr.set("operTime", new Date());
		tbimapplyappr.set("userId", dataObject.get("userId"));
		tbimapplyappr.set("operType", "01");//操作类别
		tbimapplyappr.set("sourceType", "02");//资源类别
		tbimapplyappr.set("sourceId", dataObject.get("recId"));
		tbimapplyappr.set("crTime", new Date());
		return tbimapplyappr;
	}
	public DataObject getTbImPrivApply(String resourceId){
		DataObject tbImPrivApply = DataObjectUtil.createDataObject("com.post.im.resourceM.priv.priv.TbImPrivApply");
		Object[] tbImPrivApply1 = DatabaseExt.queryByNamedSql("default", "com.post.im.resourceM.priv.priv.select_id", resourceId);
		tbImPrivApply = (DataObject)tbImPrivApply1[0];
		tbImPrivApply.set("recId", tbImPrivApply.get("rec_id"));
		tbImPrivApply.set("itemsCd", tbImPrivApply.get("items_cd"));
		tbImPrivApply.set("userId", tbImPrivApply.get("user_id"));
		tbImPrivApply.set("applyDate", tbImPrivApply.get("apply_date"));
		tbImPrivApply.set("applySpec", tbImPrivApply.get("apply_spec"));
		tbImPrivApply.set("applyDesc", tbImPrivApply.get("apply_desc"));
		tbImPrivApply.set("applyCount", tbImPrivApply.get("apply_count"));
		
		tbImPrivApply.set("openStat", tbImPrivApply.get("open_stat"));
		tbImPrivApply.set("crTime", tbImPrivApply.get("cr_time"));
		tbImPrivApply.set("edTime", tbImPrivApply.get("ed_time"));
		return tbImPrivApply;
	}
}
