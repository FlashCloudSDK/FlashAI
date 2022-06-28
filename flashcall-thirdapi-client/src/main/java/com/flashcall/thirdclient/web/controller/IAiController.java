
package com.flashcall.thirdclient.web.controller;

import java.util.List;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flashcall.thirdclient.model.AddAgentPlanReqVo;
import com.flashcall.thirdclient.model.AddPlanReq;
import com.flashcall.thirdclient.model.AddPlanReqVo;
import com.flashcall.thirdclient.model.AddPlanRes;
import com.flashcall.thirdclient.model.BotenceRsp;
import com.flashcall.thirdclient.model.CallDetailToHJ;
import com.flashcall.thirdclient.model.CallLineReq;
import com.flashcall.thirdclient.model.CallLineRsp;
import com.flashcall.thirdclient.model.CallPlanDetailRecordVO;
import com.flashcall.thirdclient.model.CancelAgentPlanReq;
import com.flashcall.thirdclient.model.KmicallSendRequest;
import com.flashcall.thirdclient.model.KmicallSendResponse;
import com.flashcall.thirdclient.model.QueryAgentGroupRes;
import com.flashcall.thirdclient.model.ServerResult;
import com.flashcall.thirdclient.model.phoneDetailVo;

import lombok.extern.slf4j.Slf4j;




public interface IAiController {
	
	
	/*
	 * 查询当前用户的座席组详情
	 */
	@RequestMapping(value = "/queryAgentGroup", method = RequestMethod.POST)
	public ServerResult<QueryAgentGroupRes> queryAgentGroup();
	
	/*
	 * 取消某个批次的某些号码的拨打
	 */
	@RequestMapping(value = "/cancelAgentPlan", method = RequestMethod.POST)
	public ServerResult<AddPlanRes> cancelAgentPlan(@RequestBody(required = false) CancelAgentPlanReq cancelPlan);
	
	
	/*
	 * 添加人工座席
	 */
	@RequestMapping(value = "/addAgentPlan", method = RequestMethod.POST)
	public ServerResult<AddPlanRes> addAgentPlan(@RequestBody(required = false) AddAgentPlanReqVo addPlanVo) ;
	
	
	/**
	 * 查询用户拥有的线路
	 * @param callLineReq
	 * @return
	 */
	@RequestMapping(value="/getUserCallLine", method=RequestMethod.POST)
	public ServerResult<List<CallLineRsp>> getUserCallLine(@RequestBody CallLineReq callLineReq);
	
	/**
	 * 查询用户拥有的话术模板
	 * @return
	 */
	@RequestMapping(value="/getUserBotence", method=RequestMethod.POST)
	public ServerResult<List<BotenceRsp>> getUserBotence();
	
	
	
	/**
	 * 添加拨打任务
	 * @param addPlanReq
	 * @return
	 */
	@RequestMapping(value="/addPlan", method=RequestMethod.POST)
	public ServerResult<AddPlanRes> addPlan(@RequestBody AddPlanReqVo addPlanReq);

	@RequestMapping(value="/batchPlanPause", method=RequestMethod.POST)
	public ServerResult batchPlanPause(@RequestParam(value="batchName",required=true)String batchName) ;
	
	

	@RequestMapping(value="/batchPlanStop", method=RequestMethod.POST)
	public ServerResult batchPlanStop(@RequestParam(value="batchName",required=true)String batchName) ;
	
	@RequestMapping(value="/batchPlanRestart", method=RequestMethod.POST)
	public ServerResult batchPlanRestart(@RequestParam(value="batchName",required=true)String batchName);

	@RequestMapping(value="/queryCallDetail", method=RequestMethod.POST)
	public ServerResult<CallPlanDetailRecordVO> queryCallDetail(@RequestParam(value="callId",required=true)String callId);

	/**
	 * 查询用户拥有的线路
	 * @param callLineReq
	 * @return
	 */
	@RequestMapping(value="/getCallRecordUrl", method=RequestMethod.POST)
	public ServerResult<String> getCallRecordUrl(@RequestParam(value="callId",required=true)String callId );

	/**
	 * 查询用户拥有的线路
	 * @param callLineReq
	 * @return
	 */
	@RequestMapping(value = "/recall", method = RequestMethod.POST)
	public ServerResult<List<CallLineRsp>> recall(@RequestParam(value="batchName",required=true)String batchName,
			@RequestParam(value="planUUid",required=true)String planUUid,
			@RequestParam(value="url",required=true)String url);
	
	
	@RequestMapping(value = "/getPhoneDetail", method = RequestMethod.POST)
	public ServerResult<CallDetailToHJ> getPhoneDetail(@RequestBody(required = false) phoneDetailVo phoneDetal);
	
	
	@RequestMapping(value = "/sendKmi", method = RequestMethod.POST)
	public ServerResult<KmicallSendResponse> sendKmi(@RequestBody(required = false) KmicallSendRequest kmiRequest);
	
}
