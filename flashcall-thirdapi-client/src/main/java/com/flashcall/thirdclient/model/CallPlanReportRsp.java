
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: CallPlanReportReq 
* @Description: 呼叫计划报表查询
* @auth weiyunbo
* @date 2019年7月5日 上午10:37:09 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class CallPlanReportRsp {
	//导入总数
	private String batchImportTime;
	//批次状态
	private String batchStatus;
	//话术模板编号
	private String batchBotsenId;
	//话术名称
	private String batchBotsenName;
	//批次名称
	private String batchName;
	//已拨打数量
	private int calledNum;
	//导入数量
	private int importNum;
	//意向数量
	private int intentionNum;
	//接通数量
	private int answerNum;
	
	
	//产品id
	private String productId;
	//产品名称
	private String productName;
}

