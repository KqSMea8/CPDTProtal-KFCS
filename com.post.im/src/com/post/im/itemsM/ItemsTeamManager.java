package com.post.im.itemsM;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import com.eos.foundation.data.DataObjectUtil;
import com.eos.foundation.database.DatabaseExt;
import com.eos.foundation.database.DatabaseUtil;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

public class ItemsTeamManager {
	/**
	 * 对项目组指定项目成员
	 * 
	 * @param str
	 *            入参Json串
	 * @return
	 */
	@Bizlet("updateItemsManager")
	public String updateItemsManager(String str, DataObject date) {
		// DataObject[] obj = DatabaseExt.queryEntitiesByCriteriaEntityWithPage(
		// "default", criteriaType, pagecond);
		String itemsCd = date.getString("itemsCd");
		String itemsNm = date.getString("itemsNm");
		JSONArray json = JSONArray.parseArray(str);
		for (int i = 0; i < json.size(); i++) {
			JSONObject item = json.getJSONObject(i);
			String tlrno = item.getString("tlrno");
			String tlrname = item.getString("tlrname");
			DataObject tbImItemsUsersRela = DataObjectUtil
					.createDataObject("com.post.im.itemsM.itemsM.TbImItemsUsersRela");
			tbImItemsUsersRela.set("itemsCd", itemsCd);
			tbImItemsUsersRela.set("userId", tlrno);
			Object[] obj = DatabaseExt
					.queryByNamedSql(
							"default",
							"com.post.im.itemsM.itemsTeamManager.queryTbImItemsUsersRela",
							tbImItemsUsersRela);
			if (obj.length > 0) {
				tbImItemsUsersRela.set("reStat", "01");
				Map resultMap=(HashMap)obj[0];
				tbImItemsUsersRela.set("recId", resultMap.get("recId"));
				DatabaseUtil.updateEntity("default", tbImItemsUsersRela);
				continue;
			}
			tbImItemsUsersRela.set("itemsCd", itemsCd);
			tbImItemsUsersRela.set("userId", tlrno);
			tbImItemsUsersRela.set("reStat", "01");
			tbImItemsUsersRela.set("crTime", new Date());
			tbImItemsUsersRela.set("itemsCd", itemsCd);
			DatabaseExt.getPrimaryKey(tbImItemsUsersRela);
			DatabaseUtil.insertEntity("default", tbImItemsUsersRela);
		}

		return "success";
		// 解析json串str
	}

	/**
	 * 对项目组指定项目成员
	 * 
	 * @param str
	 *            入参Json串
	 * @return
	 */
	@Bizlet("updateItemsUser")
	public void updateItemsUser(DataObject date) {
		String itemsCd = date.getString("itemsCd");
		String tlrNo = date.getString("tlrNo");
		DataObject tbImItemsUsersRela = DataObjectUtil
				.createDataObject("com.post.im.itemsM.itemsM.TbImItemsUsersRela");
		tbImItemsUsersRela.set("itemsCd", itemsCd);
		tbImItemsUsersRela.set("userId", tlrNo);
		Object[] obj = DatabaseExt
				.queryByNamedSql(
						"default",
						"com.post.im.itemsM.itemsTeamManager.queryTbImItemsUsersRela",
						tbImItemsUsersRela);
		tbImItemsUsersRela.set("reStat", "02");
		Map resultMap=(HashMap)obj[0];
		tbImItemsUsersRela.set("recId", resultMap.get("recId"));
		DatabaseUtil.updateEntity("default", tbImItemsUsersRela);
	}

	/**
	 * 通过项目ID获取项目对应的人员信息
	 * 
	 * @param date
	 * @return
	 */
	@Bizlet("queryItemsUserList")
	public DataObject[] queryItemsUserList(String itemsCd, DataObject pageCond) {

		// String itemsCd = date.getString("itemsCd");//项目ID

		DataObject tbImItemsUsersRela = DataObjectUtil
				.createDataObject("com.post.im.itemsM.itemsM.TbImItemsUsersRela");
		tbImItemsUsersRela.set("itemsCd", itemsCd);

		Map<String, String> searchMap = new HashMap<String, String>();
		searchMap.put("itemsCd", itemsCd);
		searchMap.put("reStat", "01");

		Object[] obj = DatabaseExt.queryByNamedSqlWithPage("default",
				"com.post.im.itemsM.itemsTeamManager.queryItemsUserList",
				pageCond, searchMap);
		if (obj != null && obj.length > 0) {
			DataObject[] ret = new DataObject[obj.length];
			for (int i = 0; i < obj.length && obj[i] instanceof DataObject; i++) {
				ret[i] = (DataObject) obj[i];
			}
			return ret;
		}
		return new DataObject[0];
	}
	private DataObject[] getDataObject(Object[] obj){
		if (obj != null && obj.length > 0) {
			DataObject[] ret = new DataObject[obj.length];
			for (int i = 0; i < obj.length && obj[i] instanceof DataObject; i++) {
				ret[i] = (DataObject) obj[i];
			}
			return ret;
		}
		return new DataObject[0];
	}
}
