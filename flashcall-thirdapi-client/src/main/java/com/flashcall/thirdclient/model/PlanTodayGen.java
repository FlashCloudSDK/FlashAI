
package com.flashcall.thirdclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: PlanTodayGen 
* @Description: 当天拨打情况概况
* @auth weiyunbo
* @date 2019年6月27日 上午11:54:46 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class PlanTodayGen {
	//当日导入总数
	private int importNum;
	//当日已拨打
	private int calledNum;
	//意向客户数
	private int intentionNum;
	//导入批次数
	private int batchNameNum;
	
	/**
	 * 正在拨打批次概述
	 */
	//当天正在拨打批次名称
	private String runBatchName;
	//话术编号
	private String runBatchBotsenId;
	//话术名称
	private String runBatchBotsenName;
	//当前批次导入数量
	private int runBatchNum;
	//已拨打数量
	private int runBatchCalledNum;
	//意图项统计
	private List<ItemCount> intentionList;
}
