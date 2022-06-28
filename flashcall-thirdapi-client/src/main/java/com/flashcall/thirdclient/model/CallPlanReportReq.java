
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: CallPlanReportReq 
* @Description: 呼叫计划报表返回
* @auth weiyunbo
* @date 2019年7月5日 上午10:37:09 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class CallPlanReportReq {
	//开始时间（yyyyMMdd）
	private String startTime;
	//结束时间（yyyyMMdd）
	private String endTime;
}
