package com.flashcall.thirdclient.model;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AddPlanReq 
* @Description: 添加拨打任务请求
* @auth weiyunbo
* @date 2019年6月24日 上午9:08:12 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class AddPlanReq {
	//批次名称
	private String batchName;

	
	private String mobileList;
	
	
	//呼叫开始日期（yyyyMMdd）
	private String callDate;
	//话术模板
	private String botenceId;
	//线路id(多线路逗号分隔)
	private String lineIds;
	//是否清空
	private int clear;
	//可呼叫的时段
	private String callHour;
	
	//批次回调地址
	private String notifyBatchUrl;
	//单笔回调地址
	private String notifyUrl;
	
	private int recall;
	private String recallParams;
}
