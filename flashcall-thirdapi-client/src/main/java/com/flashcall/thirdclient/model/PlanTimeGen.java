package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: PlanTimeGen 
* @Description: 按时间段统计的外呼情况
* @auth weiyunbo
* @date 2019年6月27日 下午1:55:14 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class PlanTimeGen {
	//导入总数
	private int importNum;
	//已拨打数
	private int calledNum;
	//客户数数
	private int intentionNum;
	//导入批次数
	private int batchNameNum;
}
