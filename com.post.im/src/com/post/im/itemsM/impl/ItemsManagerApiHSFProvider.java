package com.post.im.itemsM.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chinapost.common.modle.PaginationResult;
import com.chinapost.common.msg.TableResultResponse;
import com.eos.foundation.database.DatabaseExt;
import com.eos.runtime.core.TraceLoggerFactory;
import com.eos.system.logging.Logger;
import com.pfpj.common.exception.PTPRuntimeException;
import com.post.kfcs.im.itemsM.api.ItemsManagerApi;
import com.post.kfcs.im.itemsM.api.model.ItemsInfo;
import com.post.kfcs.im.itemsM.api.model.ItemsInfos;
import commonj.sdo.DataObject;


public class ItemsManagerApiHSFProvider implements ItemsManagerApi {
	private static Logger logger = TraceLoggerFactory
			.getLogger(ItemsManagerApiHSFProvider.class);

	public TableResultResponse<ItemsInfo> queryItemsInfo(ItemsInfo itemsInfo)
			throws PTPRuntimeException {
		// TODO 自动生成的方法存根
		return null;
	}

	/**
	 * 通过用户编码获取用户所属项目信息
	 */
	public TableResultResponse<ItemsInfos> queryItemsInfoAndUser(
			ItemsInfos itemsInfos) throws PTPRuntimeException {
		TableResultResponse<ItemsInfos> ret = new TableResultResponse<ItemsInfos>();
		PaginationResult<ItemsInfos> pageRet = new PaginationResult<ItemsInfos>();
		if (itemsInfos == null) {
			pageRet.setTotal(0);
			pageRet.setData(null);
			ret.setData(pageRet);
			logger.info("=========queryItemsInfoAndUser  itemsInfos is null=======");
			return ret;
		}
		String userId = itemsInfos.getUserId();// 用户ID tlrno
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);
		// 通过用户ID 获取用户对应的项目信息
		Object[] obj = DatabaseExt.queryByNamedSql("default",
				"com.post.im.itemsM.itemsTeamManager.queryItemsInfos", param);
		
		if (obj != null && obj.length > 0) {
			Map resultMap = new HashMap();
			List<ItemsInfos> list = new ArrayList<ItemsInfos>();
			for (int i = 0; i < obj.length ; i++) {
				resultMap = (Map) obj[i];
				ItemsInfos itemsInfo = new ItemsInfos();
				itemsInfo.setItemsCd( Integer.valueOf(resultMap.get("itemsCd").toString()));
				itemsInfo.setItemsNm((String) resultMap.get("itemsNm"));
				itemsInfo.setUserId((String) resultMap.get("userId"));
				itemsInfo.setRoleCode((String) resultMap.get("roleCode"));
				list.add(itemsInfo);
			}
			pageRet.setTotal((int) list.size());
			pageRet.setData(list);
			ret.setData(pageRet);
			return ret;
		}else {
			pageRet.setTotal(0);
			pageRet.setData(null);
			ret.setData(pageRet);
			logger.info("=========queryItemsInfoAndUser  queryItemsInfos sql resultMap is null=======");
			return ret;
		}
		
		
//		if (obj != null && obj.length > 0) {
//			List<ItemsInfos> list = new ArrayList<ItemsInfos>();
//			Map[] resultMap = (Map[]) obj;
//			for (int i = 0; i < resultMap.length; i++) {
//				Map map = resultMap[i];
//				ItemsInfos itemsInfo = new ItemsInfos();
//				itemsInfo.setItemsCd((String) map.get("itemsCd"));
//				itemsInfo.setItemsNm((String) map.get("itemsNm"));
//				itemsInfo.setUserId((String) map.get("userId"));
//				itemsInfo.setRoleCode((String) map.get("roleCode"));
//				list.add(itemsInfo);
//			}
//			pageRet.setTotal((int) list.size());
//			pageRet.setData(list);
//			return ret;
//		} else {
//			pageRet.setTotal(0);
//			pageRet.setData(null);
//			ret.setData(pageRet);
//			logger.info("=========queryItemsInfoAndUser  queryItemsInfos sql resultMap is null=======");
//			return ret;
//		}
	}

}
