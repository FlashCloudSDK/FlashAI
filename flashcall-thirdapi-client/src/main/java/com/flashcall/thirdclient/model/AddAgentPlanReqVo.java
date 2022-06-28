package com.flashcall.thirdclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AddAgentPlanReqVo {
	//手机号（机号码^附加数据1-附加数据2-附加数据3^参数1-参数2-参数3）
	private List<MobileInfo> mobileListForString;
	private String mobileList;
	//批次名称
	private String batchName;
	//座席组编号
	private String agentGroupId;
	//任务添加类型 	
	//1：add&clean 2: add
	private String addType;
	//0-不直接外呼，1-直接外呼
	private String callType;
	//座席编号
	private String agentId;
	//回调地址
    private String notifyUrl;
	
}
