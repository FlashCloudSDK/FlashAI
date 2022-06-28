
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: PlanTimeReq 
* @Description: 外呼情况统计请求
* @auth weiyunbo
* @date 2019年6月27日 下午1:52:37 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class PlanTimeReq {
	//查询开始时间（yyyy-MM-dd hh:mm:ss）
	private String startTime;
	//结束时间
	private String endTime;
}
