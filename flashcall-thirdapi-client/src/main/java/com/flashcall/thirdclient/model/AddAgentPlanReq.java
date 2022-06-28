package com.flashcall.thirdclient.model;

import lombok.Data;

@Data
public class AddAgentPlanReq {
	private String mobileList;
	private String batchName;
	private String agentGroupId;
	private String addType;
	private String callType;
	private String agentId;
	//回调地址
    private String notifyUrl;
}
