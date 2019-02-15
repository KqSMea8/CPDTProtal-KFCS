package com.post.trm;

import java.util.HashMap;
import java.util.Map;




import com.eos.foundation.database.DatabaseExt;
import com.eos.system.annotation.Bizlet;

import commonj.sdo.DataObject;

public class TrainScheManage {
	
	@Bizlet("queryTrainScheInfo")
	public DataObject[] queryTrainScheInfo(String beginDate,String endDate){
		Map<String,String> searchMap = new HashMap<String,String>(); 
		searchMap.put("beginDate",beginDate);
		searchMap.put("endDate",endDate);
		Object[] obj = DatabaseExt.queryByNamedSql("default", "com.post.trm.trainSche.queryTrainScheInfo", searchMap);
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
