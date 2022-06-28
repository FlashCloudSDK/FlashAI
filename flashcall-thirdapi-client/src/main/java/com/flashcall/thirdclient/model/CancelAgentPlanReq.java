package com.flashcall.thirdclient.model;

import lombok.Data;

@Data
public class CancelAgentPlanReq {
	private String batchName;
	//可以多个 | 隔开
	private String mobileList;
}
