package com.flashcall.thirdclient.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.flashcall.thirdclient.constant.AiConstants;
import com.flashcall.thirdclient.model.AddAgentPlanReqVo;
import com.flashcall.thirdclient.model.AddPlanReq;
import com.flashcall.thirdclient.model.AddPlanReqVo;
import com.flashcall.thirdclient.model.AddPlanRes;
import com.flashcall.thirdclient.model.BatchPlanCallSumary;
import com.flashcall.thirdclient.model.BatchReCallParamRo;
import com.flashcall.thirdclient.model.BotenceRsp;
import com.flashcall.thirdclient.model.CallDetailToHJ;
import com.flashcall.thirdclient.model.CallLineReq;
import com.flashcall.thirdclient.model.CallLineRsp;
import com.flashcall.thirdclient.model.CallPlanDetailRecordVO;
import com.flashcall.thirdclient.model.CancelAgentPlanReq;
import com.flashcall.thirdclient.model.KmicallSendRequest;
import com.flashcall.thirdclient.model.KmicallSendResponse;
import com.flashcall.thirdclient.model.KmicallSmsUtil;
import com.flashcall.thirdclient.model.MobileInfo;
import com.flashcall.thirdclient.model.QueryAgentGroupRes;
import com.flashcall.thirdclient.model.RecallParams;
import com.flashcall.thirdclient.model.ServerResult;
import com.flashcall.thirdclient.model.TtsParamVO;
import com.flashcall.thirdclient.model.phoneDetailVo;
import com.flashcall.thirdclient.service.FlashcallAccountService;
import com.flashcall.thirdclient.service.FlashCallService;
import com.flashcall.thirdclient.util.DateUtil;
import com.flashcall.thirdclient.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: FlashcallController
 * @Description: Flashcall服务
 * @auth weiyunbo
 * @date 2019年6月24日 上午9:50:29
 * @version V1.0
 */
@Slf4j
@RestController
public class FlashcallController implements IAiController {

	@Autowired
	FlashcallAccountService flashcallAccountService;
	@Autowired
	FlashCallService flashCallService;

	private Logger logger = LoggerFactory.getLogger(FlashcallController.class);

	/**
	 * 根据batchName and phone 查询回调明细
	 * 
	 * @param batchName
	 * @param phone
	 */

	@RequestMapping(value = "/getPhoneDetail", method = RequestMethod.POST)
	@Override
	public ServerResult<CallDetailToHJ> getPhoneDetail(@RequestBody(required = false) phoneDetailVo phoneDetal) {
		if (phoneDetal == null) {
			logger.info("参数为空！");
			return null;
		}
		if (phoneDetal.getBatchName() == null || phoneDetal.getPhone() == null) {
			logger.info("参数为空");
			return null;
		}

		logger.info("getPhoneDetail data : " + phoneDetal.toString());

		CallDetailToHJ hj = flashCallService.getPhoneDetail(phoneDetal);
		logger.info("return hj : " + hj.toString());
		return ServerResult.createBySuccess(hj);
	}

	/*
	 * 添加人工座席拨打计划
	 */
	@RequestMapping(value = "/addAgentPlan", method = RequestMethod.POST)
	@Override
	public ServerResult<AddPlanRes> addAgentPlan(@RequestBody(required = false) AddAgentPlanReqVo addAgentPlanVo) {
		logger.info("addPlanVo data : " + addAgentPlanVo.toString());
		AddPlanRes addPlanRes = flashCallService.addAgentPlan(addAgentPlanVo);
		if (addPlanRes != null) {
			System.out.println("返回参数 ： " + addPlanRes.toString());
		} else {
			System.out.println("返回参数 ： " + null);
		}
		return ServerResult.createBySuccess(addPlanRes);
	}

	/*
	 * 取消某个批次的某些号码拨打
	 */
	@RequestMapping(value = "/cancelAgentPlan", method = RequestMethod.POST)
	@Override
	public ServerResult<AddPlanRes> cancelAgentPlan(@RequestBody(required = false) CancelAgentPlanReq cancelPlan) {
		logger.info("addPlanVo data : " + cancelPlan.toString());
		AddPlanRes addPlanRes = flashCallService.cancelAgentPlan(cancelPlan);
		System.out.println("返回参数 ： " + addPlanRes.toString());
		return ServerResult.createBySuccess(addPlanRes);
	}

	/*
	 * 查询当前用户的座席组详情
	 */
	@RequestMapping(value = "/queryAgentGroup", method = RequestMethod.POST)
	@Override
	public ServerResult<QueryAgentGroupRes> queryAgentGroup() {
		QueryAgentGroupRes queryAgentGroupVo = flashCallService.queryAgentGroup();
		System.out.println("返回参数 ： " + queryAgentGroupVo.toString());
		return ServerResult.createBySuccess(queryAgentGroupVo);
	}

	/**
	 * 查询用户拥有的线路
	 * 
	 * @param callLineReq
	 * @return
	 */
	@RequestMapping(value = "/getUserCallLine", method = RequestMethod.POST)
	@Override
	public ServerResult<List<CallLineRsp>> getUserCallLine(@RequestBody(required = false) CallLineReq callLineReq) {

		List<CallLineRsp> list = flashcallAccountService.getUserCallLine();
		System.out.println("返回参数 ： " + list.toString());
		return ServerResult.createBySuccess(list);

	}

	/**
	 * 查询用户拥有的线路
	 * 
	 * @param callLineReq
	 * @return
	 */
	@RequestMapping(value = "/getCallRecordUrl", method = RequestMethod.POST)
	@Override
	public ServerResult<String> getCallRecordUrl(@RequestParam(value = "callId", required = true) String callId) {

		System.out.println("返回参数 ： " + flashcallAccountService.getCallRecordUrl(callId));
		return ServerResult.createBySuccess("");

	}

	/**
	 * 查询用户拥有的话术模板
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getUserBotence", method = RequestMethod.POST)
	@Override
	public ServerResult<List<BotenceRsp>> getUserBotence() {
		List<BotenceRsp> list = flashcallAccountService.getUserBotence();
		System.out.println("返回参数 ： " + list.toString());
		return ServerResult.createBySuccess(list);
	}

	/**
	 * 查询对应botenceId的变量
	 */
	@RequestMapping(value = "/queryPlanParams", method = RequestMethod.POST)
	public ServerResult<List<TtsParamVO>> queryPlanParams(
			@RequestParam(value = "botenceId", required = true) String botenceId) {
		List<TtsParamVO> vo = flashcallAccountService.queryPlanParams(botenceId);
		System.out.println("返回参数 ： " + vo.toString());
		return ServerResult.createBySuccess(vo);
	}

	/**
	 * 添加拨打任务
	 * 
	 * @param addPlanReq
	 * @return
	 */
	@Override
	@RequestMapping(value = "/addPlan", method = RequestMethod.POST)
	public ServerResult<AddPlanRes> addPlan(@RequestBody AddPlanReqVo addPlanReq) {
		System.out.println("接收参数 ： " + addPlanReq.toString());
		AddPlanRes addPlanRes = flashCallService.addPlan(addPlanReq);
		System.out.println("返回参数 ： " + addPlanRes.toString());
		return ServerResult.createBySuccess(addPlanRes);
	}

	/**
	 * 批量计划暂停
	 */
	@Override
	@RequestMapping(value = "/batchPlanPause", method = RequestMethod.POST)
	public ServerResult batchPlanPause(@RequestParam(value = "batchName", required = true) String batchName) {
		flashCallService.batchPlanChanged(batchName, AiConstants.PARROT_PAUSE_PLAN);
		return ServerResult.createBySuccess();
	}

	/**
	 * 批量计划停止
	 */
	@Override
	@RequestMapping(value = "/batchPlanStop", method = RequestMethod.POST)
	public ServerResult batchPlanStop(@RequestParam(value = "batchName", required = true) String batchName) {
		flashCallService.batchPlanChanged(batchName, AiConstants.PARROT_STOP_PLAN);
		return ServerResult.createBySuccess();
	}

	/**
	 * 批量计划恢复（重拨）
	 */
	@Override
	@RequestMapping(value = "/batchPlanRestart", method = RequestMethod.POST)
	public ServerResult batchPlanRestart(@RequestParam(value = "batchName", required = true) String batchName) {
		flashCallService.batchPlanChanged(batchName, AiConstants.PARROT_RESTART_PLAN);
		return ServerResult.createBySuccess();
	}

	/**
	 * 通过批次号查询该批次的拨打汇总
	 * 
	 * @param batchName
	 * @return
	 */
	@RequestMapping(value = "/getBatchPlanCallSummary", method = RequestMethod.POST)
	public ServerResult<BatchPlanCallSumary> getBatchPlanCallSummary(
			@RequestParam(value = "batchName", required = true) String batchName) {
		BatchPlanCallSumary batchPlanCallSumary = flashCallService.getBatchPlanCallSummary(batchName);
		System.out.println("返回参数 ： " + batchPlanCallSumary.toString());
		return ServerResult.createBySuccess(batchPlanCallSumary);
	}

	/**
	 * 通过批次号查询该批次线路拨打汇总
	 * 
	 * @param batchName
	 * @return
	 */
	@RequestMapping(value = "/getBatchSummaryGroupByLine", method = RequestMethod.POST)
	public ServerResult<BatchPlanCallSumary> getBatchSummaryGroupByLine(
			@RequestParam(value = "batchName", required = true) String batchName) {
		BatchPlanCallSumary batchPlanCallSumary = flashCallService.getBatchSummaryGroupByLine(batchName);
		System.out.println("返回参数 ： " + batchPlanCallSumary.toString());
		return ServerResult.createBySuccess(batchPlanCallSumary);
	}

	/**
	 * 获取计划号码列表
	 * 
	 * @param batchName
	 * @param phoneStatus
	 * @param page
	 * @param pageNum
	 * @param notifyUrl
	 * @return
	 */
	@RequestMapping(value = "/getBatchPlanList", method = RequestMethod.POST)
	public ServerResult getBatchPlanList(@RequestParam(value = "batchName", required = true) String batchName,
			@RequestParam(value = "phoneStatus", required = true) String phoneStatus,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "pageNum", required = true) Integer pageNum) {

		flashCallService.getBatchPlanList(batchName, page, pageNum, phoneStatus);
		return ServerResult.createBySuccess();

	}

	/**
	 * 获取计划通话列表
	 * 
	 * @param batchName
	 * @param phoneStatus
	 * @param page
	 * @param pageNum
	 * @param notifyUrl
	 * @return
	 */
	@RequestMapping(value = "/getBatchPlanCallList", method = RequestMethod.POST)
	public ServerResult getBatchPlanList(@RequestParam(value = "batchName", required = true) String batchName,
			@RequestParam(value = "page", required = true) Integer page,
			@RequestParam(value = "pageNum", required = true) Integer pageNum,
			@RequestParam(value = "notifyUrl") String notifyUrl) {

		flashCallService.getBatchPlanCallList(batchName, page, pageNum, notifyUrl);
		return ServerResult.createBySuccess();

	}

	/**
	 * 根据callId查询对应的通话详情
	 */
	@RequestMapping(value = "/queryCallDetail", method = RequestMethod.POST)
	public ServerResult<CallPlanDetailRecordVO> queryCallDetail(
			@RequestParam(value = "callId", required = true) String callId) {
		CallPlanDetailRecordVO vo = flashCallService.queryCallDetailById(callId);
		System.out.println("返回参数 ： " + vo.toString());
		return ServerResult.createBySuccess(vo);
	}

	@GetMapping(value = "/simpleTest_dev")
	public ServerResult<AddPlanRes> simpleTest_dev(@RequestParam(value = "phone", required = false) String phone) {
		ServerResult serverResult = ServerResult.createBySuccess();

		AddPlanReqVo addPlanReq = new AddPlanReqVo();
		addPlanReq.setBatchName("" + new Date().getTime());
		addPlanReq.setBotenceId("20210604283993174376433185_en"); // 提供
		addPlanReq.setLineIds("5"); // 提供
		addPlanReq.setCallDate(DateUtil.getCurrentTimOfymd2());
		addPlanReq.setCallHour("9,10,11,12,13,14,15,16,17,18,19");
		addPlanReq.setClear(0);
		// addPlanReq.setRecall(recall);
		List<MobileInfo> list = new ArrayList<MobileInfo>();
		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setPhone(phone);
		mobileInfo.setParams("18/06/2021-luokai-flashcall"); // 提供
		mobileInfo.setAttach("date-name-platform"); // 提供
		list.add(mobileInfo);
		addPlanReq.setMobileListForString(list);

		// 添加计划
		AddPlanRes addPlanRes = flashCallService.addPlan(addPlanReq);
		return serverResult.createBySuccess(addPlanRes);
	}

	@GetMapping(value = "/simpleTest_saas")
	public ServerResult<AddPlanRes> simpleTest_saas(@RequestParam(value = "phone", required = false) String phone) {
		ServerResult serverResult = ServerResult.createBySuccess();

		AddPlanReqVo addPlanReq = new AddPlanReqVo();
		addPlanReq.setBatchName("" + new Date().getTime());
		// 20210609097401677717479291_en 20210518532405434517422280_en
		addPlanReq.setBotenceId("20210712456057543042444598_en"); // 提供
		// 53 50
		addPlanReq.setLineIds("50"); // 提供 DateUtil.getCurrentTimOfymd2() 65
		addPlanReq.setCallDate("20210716");
		addPlanReq.setCallHour("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20");
		addPlanReq.setClear(0);
		// addPlanReq.setRecall(recall);
		List<MobileInfo> list = new ArrayList<MobileInfo>();
		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setPhone(phone);
		mobileInfo.setParams("1000-18/06/2021-luokai-flashcall"); // 提供
		mobileInfo.setAttach("amount-date-name-platform"); // 提供
		list.add(mobileInfo);
		addPlanReq.setMobileListForString(list);

		// 添加计划
		AddPlanRes addPlanRes = flashCallService.addPlan(addPlanReq);
		return serverResult.createBySuccess(addPlanRes);
	}

	@GetMapping(value = "mockAddPlan")
	public ServerResult<AddPlanRes> mockAddPlan() {
		ServerResult serverResult = ServerResult.createBySuccess();
		AddPlanReqVo addPlanReq = new AddPlanReqVo();
		addPlanReq.setMobileList("18016491916^areaName-name^吴泾街道-andy|18016491917^areaName-name^吴泾街道-andy");
		addPlanReq.setBatchName("" + new Date().getTime());
		addPlanReq.setBotenceId("20210331240163851654426299_en");
		addPlanReq.setLineIds("165");
		addPlanReq.setRecall(1);
		addPlanReq.setCallDate(DateUtil.getCurrentTimOfymd2());
		addPlanReq.setCallHour("9,10,11,12,13,14,15,16,17,18,19");
		addPlanReq.setNotifyUrl("http://ip:port/xxx/singleCallbackUrl");
		addPlanReq.setNotifyBatchUrl("http://ip:port/xxx/batchCallBack");
		String recallParams = "[{\"recallIntervalTime\":\"5\",\"recallTag\":\"F\",\"recallTimes\":\"1\",\"ruleType\":\"0\"},{\"recallIntervalTime\":\"5\",\"recallTag\":\"W\",\"recallTimes\":\"1\",\"ruleType\":\"0\"}]";
		addPlanReq.setRecallParams(recallParams);
		addPlanReq.setClear(1);

		AddPlanRes addPlanRes = flashCallService.addPlanToTest(addPlanReq);
		return serverResult.createBySuccess(addPlanRes);

	}

	// 50
	// 20210518532405434517422280_en

	@GetMapping(value = "/mockSimpleAddPlan")
	public ServerResult<AddPlanRes> mockSimpleAddPlan(@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "lineName", required = false) String lineName,
			@RequestParam(value = "tempName", required = false) String tempName,
			@RequestParam(value = "params", required = false) String params,
			@RequestParam(value = "recall", required = false) int recall,
			@RequestBody(required = false) List<BatchReCallParamRo> recallParams) {
		ServerResult serverResult = ServerResult.createBySuccess();
		log.info("phone {} , lineName {} ,tempName {} ,params {}", phone, lineName, tempName, params);
		if (StringUtils.isBlank(phone)) {
			serverResult = ServerResult.createByErrorMessage("the  phone is null ");
			return serverResult;
		}
		// 获取用户线路
		List<CallLineRsp> listCallLineRsp = flashcallAccountService.getUserCallLine();
		CallLineRsp callLineRsp = null;
		if (listCallLineRsp != null && listCallLineRsp.size() != 0) {
			if (StringUtils.isBlank(lineName)) {
				callLineRsp = listCallLineRsp.get(0);
			} else {
				for (CallLineRsp tempCallLineRsp : listCallLineRsp) {
					if (tempCallLineRsp != null && tempCallLineRsp.getLineName() != null
							&& tempCallLineRsp.getLineName().equals(lineName)) {
						callLineRsp = tempCallLineRsp;
						log.info("the lineName {} is matched ", lineName);
						break;
					}
				}
				if (callLineRsp == null) {
					serverResult = ServerResult
							.createByErrorMessage("the  lineName {" + lineName + "} is not existed , pls check  ");
					return serverResult;
				}
			}
		} else {
			serverResult = ServerResult.createByErrorMessage("the  line is null ");
			return serverResult;
		}
		BotenceRsp botenceRsp = null;
		// 获取用户话术
		List<BotenceRsp> listBotenceRsp = flashcallAccountService.getUserBotence();
		if (listBotenceRsp != null && listBotenceRsp.size() != 0) {
			if (StringUtils.isBlank(tempName)) {
				botenceRsp = listBotenceRsp.get(0);
			} else {
				for (BotenceRsp tempBotenceRsp : listBotenceRsp) {
					if (tempBotenceRsp != null && tempBotenceRsp.getTemplateName() != null
							&& tempBotenceRsp.getTemplateName().equals(tempName)) {
						botenceRsp = tempBotenceRsp;
						log.info("the tempName {} is matched ", tempName);
						break;
					}
				}
				if (botenceRsp == null) {
					serverResult = ServerResult
							.createByErrorMessage("the  tempName {" + tempName + "} is not existed , pls check  ");
					return serverResult;
				}
			}
		} else {
			serverResult = ServerResult.createByErrorMessage("the script is null ");
			return serverResult;
		}

		AddPlanReqVo addPlanReq = new AddPlanReqVo();
		addPlanReq.setBatchName("" + new Date().getTime());
		addPlanReq.setBotenceId(botenceRsp.getTemplateId());
		addPlanReq.setLineIds("" + callLineRsp.getLineId());
		addPlanReq.setCallDate(DateUtil.getCurrentTimOfymd2());
		addPlanReq.setCallHour("9,10,11,12,13,14,15,16,17,18,19");
		addPlanReq.setClear(1);
		addPlanReq.setRecall(recall);
		List<MobileInfo> list = new ArrayList<MobileInfo>();
		MobileInfo mobileInfo = new MobileInfo();
		mobileInfo.setPhone(phone);
		mobileInfo.setParams(params);
		List<BatchReCallParamRo> recalls = null;

		if (recallParams != null) {
			addPlanReq.setRecallParamsList(recallParams);
		}

		// 根据话术模板Id获取计划参数
		List<TtsParamVO> vo = flashcallAccountService.queryPlanParams("20210604283993174376433185_en");
		StringBuffer attach = new StringBuffer("");
		if (vo != null && vo.size() != 0) {
			if (StringUtils.isBlank(params)) {
				serverResult = ServerResult
						.createByErrorMessage("the script template need params, pls add the params in the url ");
				return serverResult;
			} else {
				String[] argList = params.split("-");
				if (vo.size() != argList.length) {
					serverResult = ServerResult.createByErrorMessage(
							"the params form must be spilt by '-' and the params num is not match {" + vo.size() + "}");
					return serverResult;
				}
				int i = 0;
				for (TtsParamVO ttsParamVO : vo) {
					attach.append(ttsParamVO.getParamName());
					if (i < vo.size() - 1) {
						attach.append("-");
					}
					i++;
				}
			}
		} else {
			if (StringUtils.isNotBlank(params)) {
				serverResult = ServerResult.createByErrorMessage(
						"the script template is not need params, pls clear the params in the url ");
				return serverResult;
			}
		}
		mobileInfo.setAttach(attach.toString());
		list.add(mobileInfo);
		addPlanReq.setMobileListForString(list);
		// 添加计划
		AddPlanRes addPlanRes = flashCallService.addPlan(addPlanReq);
		return serverResult.createBySuccess(addPlanRes);
	}

	// 测试回调
	@Override
	public ServerResult<List<CallLineRsp>> recall(@RequestParam(value = "batchName", required = true) String batchName,
			@RequestParam(value = "planUUid", required = true) String planUUid,
			@RequestParam(value = "url", required = true) String url) {

		System.out.println("返回参数 ： " + flashcallAccountService.recall(batchName, planUUid, url));
		return ServerResult.createBySuccess(null);
	}

	@Override
	public ServerResult<KmicallSendResponse> sendKmi(@RequestBody(required = false) KmicallSendRequest kmiRequest) {
		String path ="http://api.kmicloud.com/sms/send/v1/notify";
		String requestJson = JSON.toJSONString(kmiRequest);
		log.info("requestJson  toString is :" + requestJson);
		String response = KmicallSmsUtil.sendSmsByPost(path, requestJson); // 发送请求
		log.info("response  toString is :" + response);
		KmicallSendResponse smsSingleResponse = JsonUtils.json2Bean(response, KmicallSendResponse.class);
		logger.info("response : " + smsSingleResponse.toString());
		return ServerResult.createBySuccess(smsSingleResponse);
	}

}
