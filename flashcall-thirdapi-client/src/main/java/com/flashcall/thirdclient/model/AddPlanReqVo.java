
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
public class AddPlanReqVo {
	//批次名称
	private String batchName;  //自定义
	//手机号（机号码^附加数据^参数1-参数2-参数3）
	private List<MobileInfo> mobileListForString;
	
	private String mobileList;
	
	
	//呼叫开始日期（yyyyMMdd）
	private String callDate;  //20200518
	//话术模板
	private String botenceId; //话术id
	//线路id(多线路逗号分隔)
	private String lineIds; //线路id
	//是否清空
	private int clear; //
	//可呼叫的时段
	private String callHour;
	
	//批次回调地址
	private String notifyBatchUrl;
	//单笔回调地址
	private String notifyUrl;
	
	
	/**
	 * @author lhq
	 */
	//重播 0不重播 1重播
	private int recall;
	//重播条件参数
	private List<BatchReCallParamRo> recallParamsList;
	
	private String recallParams;
	
}
