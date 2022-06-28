package com.flashcall.thirdclient.model;

import java.util.ArrayList;
import java.util.List;

import com.flashcall.thirdclient.exception.Result;
import com.flashcall.thirdclient.exception.Result.ReturnData;
import com.flashcall.thirdclient.util.JsonUtils;

import lombok.Data;

@Data
public class QueryAgentGroupRes {

	/*
	agentMembrVO.setQueueId(queueId);
    agentMembrVO.setQueueName(queueName);
    agentMembrVO.setLoginAccount(loginAccount);
    agentMembrVO.setCustomerName(customerName);
	 */
	//座席组编号，座席组名称，座席编号，座席名称

	private List<AgentGroupDetailsRes> records;

	
	
	public static void main(String[] args) {
		ReturnData<QueryAgentGroupRes> returnVo = Result.ok();
		QueryAgentGroupRes queryAgentGroupVo = new QueryAgentGroupRes();
		
		List<AgentGroupDetailsRes> vos = new ArrayList<AgentGroupDetailsRes>();
		AgentGroupDetailsRes agentGroupDetailsVo0= new AgentGroupDetailsRes();
		agentGroupDetailsVo0.setAgentGroupId("座席组id0");
		agentGroupDetailsVo0.setAgentGroupName("座席组名称0");
		agentGroupDetailsVo0.setAgentId("座席id");
		agentGroupDetailsVo0.setAgentName("座席名称");
		
		AgentGroupDetailsRes agentGroupDetailsVo1= new AgentGroupDetailsRes();
		agentGroupDetailsVo1.setAgentGroupId("座席组id1");
		agentGroupDetailsVo1.setAgentGroupName("座席组名称1");
		agentGroupDetailsVo1.setAgentId("座席id");
		agentGroupDetailsVo1.setAgentName("座席名称");
		
		vos.add(agentGroupDetailsVo0);
		vos.add(agentGroupDetailsVo1);
		queryAgentGroupVo.setRecords(vos);
		returnVo.setBody(queryAgentGroupVo);
		System.out.println(JsonUtils.bean2Json(returnVo));
		
	}
}
