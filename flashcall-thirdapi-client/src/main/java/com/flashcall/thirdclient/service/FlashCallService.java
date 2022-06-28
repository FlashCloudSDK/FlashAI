package com.flashcall.thirdclient.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flashcall.thirdclient.config.FlashcallConf;
import com.flashcall.thirdclient.constant.AiConstants;
import com.flashcall.thirdclient.exception.Result;
import com.flashcall.thirdclient.exception.Result.ReturnData;
import com.flashcall.thirdclient.model.*;
import com.flashcall.thirdclient.util.JsonUtils;
import com.flashcall.thirdclient.util.ListUtil;
import com.flashcall.thirdclient.util.FlashcallClient;
import com.flashcall.thirdclient.util.FlashcallSignUtils;
import com.flashcall.thirdclient.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/** 
* @ClassName: FlashCallService 
* @Description: Flashcall电话服务（外呼、通话查询等）
* @auth weiyunbo
* @date 2019年6月23日 下午5:47:19 
* @version V1.0  
*/
@Slf4j
@Service
public class FlashCallService {
	@Autowired
	FlashcallClient flashcallUtil;
	@Autowired
	FlashcallConf flashcallConf;

	private Logger logger = LoggerFactory.getLogger(FlashCallService.class);
	
	public CallDetailToHJ getPhoneDetail(phoneDetailVo phoneDetal) {
		
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_PHONE_DETAIL, phoneDetal);
		if(StrUtils.isNotEmpty(resJson)) {
			CallDetailToHJ hj = JsonUtils.json2Bean(resJson, CallDetailToHJ.class);
			return hj;
		}
		return null;
	}
	
	
	public AddPlanRes addPlanToTest(AddPlanReqVo addPlanReqVo) {
		AddPlanReq addPlanReq= new AddPlanReq();			
		BeanUtils.copyProperties(addPlanReqVo, addPlanReq);
		String resJson = flashcallUtil.sendWithAuthTest(addPlanReq);
		if(StrUtils.isNotEmpty(resJson)) {
			AddPlanRes addPlanRes = new AddPlanRes();
			JSONObject jObj = JSON.parseObject(resJson);
			String rightCount = jObj.getString("rightCount");
			String failCount = jObj.getString("failCount");
			String failMobileInfo = jObj.getString("failMobileInfo");
			if(StrUtils.isNotEmpty(failMobileInfo)) {
				List<MobileInfo> failMobileList = new ArrayList<MobileInfo>();
				String[] failMobileInfoList = failMobileInfo.split("\\|");
	        	if(failMobileInfoList!=null && failMobileInfoList.length!=0) {
	        		for(String failMobile:failMobileInfoList) {
	        			MobileInfo mobileInfo = new MobileInfo();
	        			String[] temp1= failMobile.split("\\^");
	        			mobileInfo.setPhone(temp1[0]);
	        			mobileInfo.setAttach(temp1[1]);
	        			mobileInfo.setParams(temp1[2]);
	        			failMobileList.add(mobileInfo);
	        			
	        		}
	        		
	        	}
	        	addPlanRes.setFailMobileInfo(failMobileList);
			}
			addPlanRes.setFailCount(failCount!=null?Integer.valueOf(failCount):0);
			addPlanRes.setRightCount(rightCount!=null?Integer.valueOf(rightCount):0);
			return addPlanRes;
		}
		return null;
	}
	
	
	public QueryAgentGroupRes queryAgentGroup() {
		
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_AGENT_GROUP, null);
		if(StrUtils.isNotEmpty(resJson)) {
			
			QueryAgentGroupRes vo = JsonUtils.json2Bean(resJson, QueryAgentGroupRes.class);
			logger.info("ret vo : " + vo.toString());
			return vo;
		}
		return null;
	}
	
	/**
	 * 添加计划
	 * @param AddPlanReq
	 */
	public AddPlanRes cancelAgentPlan(CancelAgentPlanReq cancelPlan) {
		if(StringUtils.isNotBlank(cancelPlan.getMobileList()) && StringUtils.isNotBlank(cancelPlan.getBatchName())) {
			//授权校验
			String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_CANCEL_AGENT_PLAN, cancelPlan);
			if(StrUtils.isNotEmpty(resJson)) {
				AddPlanRes addPlanRes = new AddPlanRes();
				JSONObject jObj = JSON.parseObject(resJson);
				String rightCount = jObj.getString("rightCount");
				String failCount = jObj.getString("failCount");
				String failMobileInfo = jObj.getString("failMobileInfo");
				if(StrUtils.isNotEmpty(failMobileInfo)) {
					List<MobileInfo> failMobileList = new ArrayList<MobileInfo>();
					String[] failMobileInfoList = failMobileInfo.split("\\|");
		        	if(failMobileInfoList!=null && failMobileInfoList.length!=0) {
		        		for(String failMobile:failMobileInfoList) {
		        			MobileInfo mobileInfo = new MobileInfo();
		        			String[] temp1= failMobile.split("\\^");
		        			mobileInfo.setPhone(temp1[0]);
		        			mobileInfo.setAttach(temp1[1]);
		        			mobileInfo.setParams(temp1[2]);
		        			failMobileList.add(mobileInfo);
		        			
		        		}
		        		
		        	}
		        	addPlanRes.setFailMobileInfo(failMobileList);
				}
				addPlanRes.setFailCount(failCount!=null?Integer.valueOf(failCount):0);
				addPlanRes.setRightCount(rightCount!=null?Integer.valueOf(rightCount):0);
				return addPlanRes;
			}
			return null;
		}else {
			logger.info("getMobileList is null and getBatchName is null,pls check");
			return null;
		}
		
	}
	
	
	/**
	 * 添加人工座席计划
	 * @param AddPlanReq
	 */
	public AddPlanRes addAgentPlan(AddAgentPlanReqVo addAgentPlanReqVo) {
		if(StringUtils.isNotEmpty(addAgentPlanReqVo.getMobileList())) {
			addAgentPlanReqVo.setNotifyUrl(flashcallConf.getNotifyUrl()); //单笔回调地址
			/*
			 * StringBuffer mobileList = new StringBuffer(""); List<MobileInfo>
			 * mobileListForString = addAgentPlanReqVo.getMobileListForString();
			 * for(MobileInfo mobileInfo: mobileListForString) {
			 * mobileList.append(mobileInfo.getPhone());
			 * if(StrUtils.isNotEmpty(mobileInfo.getAttach())) {
			 * mobileList.append("^"+mobileInfo.getAttach()); }else {
			 * mobileList.append("^"); } if(StrUtils.isNotEmpty(mobileInfo.getParams())) {
			 * mobileList.append("^"+mobileInfo.getParams()); } mobileList.append("|"); }
			 * 
			 * addAgentPlanReqVo.setMobileList(mobileList.toString());
			 */

			AddAgentPlanReq addAgentPlanReq= new AddAgentPlanReq();			
			BeanUtils.copyProperties(addAgentPlanReqVo, addAgentPlanReq);
			//授权校验
			String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_ADD_AGENT_PLAN, addAgentPlanReq);
			if(StrUtils.isNotEmpty(resJson)) {
				AddPlanRes addPlanRes = new AddPlanRes();
				JSONObject jObj = JSON.parseObject(resJson);
				String rightCount = jObj.getString("rightCount");
				String failCount = jObj.getString("failCount");
				String failMobileInfo = jObj.getString("failMobileInfo");
				if(StrUtils.isNotEmpty(failMobileInfo)) {
					List<MobileInfo> failMobileList = new ArrayList<MobileInfo>();
					String[] failMobileInfoList = failMobileInfo.split("\\|");
		        	if(failMobileInfoList!=null && failMobileInfoList.length!=0) {
		        		for(String failMobile:failMobileInfoList) {
		        			MobileInfo mobileInfo = new MobileInfo();
		        			String[] temp1= failMobile.split("\\^");
		        			mobileInfo.setPhone(temp1[0]);
		        			mobileInfo.setAttach(temp1[1]);
		        			mobileInfo.setParams(temp1[2]);
		        			failMobileList.add(mobileInfo);
		        			
		        		}
		        		
		        	}
		        	addPlanRes.setFailMobileInfo(failMobileList);
				}
				addPlanRes.setFailCount(failCount!=null?Integer.valueOf(failCount):0);
				addPlanRes.setRightCount(rightCount!=null?Integer.valueOf(rightCount):0);
				return addPlanRes;
			}
			return null;
		}else {
			logger.info("getMobileListForString is null, pls check");
			return null;
		}
		
	}
	
	
	/**
	 * 添加计划
	 * @param AddPlanReq
	 */
	public AddPlanRes addPlan(AddPlanReqVo addPlanReqVo) {
		if(ListUtil.isNotEmpty(addPlanReqVo.getMobileListForString())) {
			addPlanReqVo.setNotifyBatchUrl(flashcallConf.getNotifyBatchUrl()); //批次回调地址
			addPlanReqVo.setNotifyUrl(flashcallConf.getNotifyUrl()); //单笔回调地址
			StringBuffer mobileList = new StringBuffer("");
			List<MobileInfo> mobileListForString = addPlanReqVo.getMobileListForString();
			for(MobileInfo mobileInfo: mobileListForString) {
				mobileList.append(mobileInfo.getPhone());
				if(StrUtils.isNotEmpty(mobileInfo.getAttach())) {
					mobileList.append("^"+mobileInfo.getAttach());
				}else {
					mobileList.append("^");
				}
				if(StrUtils.isNotEmpty(mobileInfo.getParams())) {
					mobileList.append("^"+mobileInfo.getParams());
				}
				mobileList.append("|");
			}
			
			
			addPlanReqVo.setMobileList(mobileList.toString());
			if(addPlanReqVo.getRecallParamsList() != null) {
				addPlanReqVo.setRecallParams(JsonUtils.bean2Json(addPlanReqVo.getRecallParamsList()));
			}
			AddPlanReq addPlanReq= new AddPlanReq();			
			BeanUtils.copyProperties(addPlanReqVo, addPlanReq);
			//授权校验
			String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_ADD_PLAN, addPlanReq);
			if(StrUtils.isNotEmpty(resJson)) {
				AddPlanRes addPlanRes = new AddPlanRes();
				JSONObject jObj = JSON.parseObject(resJson);
				String rightCount = jObj.getString("rightCount");
				String failCount = jObj.getString("failCount");
				String failMobileInfo = jObj.getString("failMobileInfo");
				if(StrUtils.isNotEmpty(failMobileInfo)) {
					List<MobileInfo> failMobileList = new ArrayList<MobileInfo>();
					String[] failMobileInfoList = failMobileInfo.split("\\|");
		        	if(failMobileInfoList!=null && failMobileInfoList.length!=0) {
		        		for(String failMobile:failMobileInfoList) {
		        			MobileInfo mobileInfo = new MobileInfo();
		        			String[] temp1= failMobile.split("\\^");
		        			mobileInfo.setPhone(temp1[0]);
		        			mobileInfo.setAttach(temp1[1]);
		        			mobileInfo.setParams(temp1[2]);
		        			failMobileList.add(mobileInfo);
		        			
		        		}
		        		
		        	}
		        	addPlanRes.setFailMobileInfo(failMobileList);
				}
				addPlanRes.setFailCount(failCount!=null?Integer.valueOf(failCount):0);
				addPlanRes.setRightCount(rightCount!=null?Integer.valueOf(rightCount):0);
				return addPlanRes;
			}
			return null;
		}else {
			logger.info("getMobileListForString is null, pls check");
			return null;
		}
		
	}
	
	

	public void batchPlanChanged(String batchName,String actionName) {
		//调用接口
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("batchName", batchName);
		String resJson = flashcallUtil.sendWithAuth(actionName,paramMap);
		System.out.println("resJson :  "+ resJson);
	}
	
	
	
	public void batchPlanChangedForPhoneList(String batchName,List<String> list,String url) {
		//调用接口
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("batchName", batchName);
		if(list!=null&&list.size()!=0) {
			StringBuffer phoneList = new StringBuffer("");
			for(String phoneNum:list) {
				phoneList.append(phoneNum+"|");
			}
			paramMap.put("phoneList", phoneList.toString());
			String resJson = flashcallUtil.sendWithAuth(url,paramMap);
		}
	}
	
	public BatchPlanCallSumary getBatchPlanCallSummary(String batchName) {
		//调用接口
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("batchName", batchName);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_BATCH_PLAN_CALL_SUMMARY,paramMap);
		if(StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			BatchPlanCallSumary batchPlanCallSumary = new BatchPlanCallSumary();
			batchPlanCallSumary.setBatchName(batchName);
			batchPlanCallSumary.setAcceptCount(jObj.getInteger("acceptCount"));
			batchPlanCallSumary.setEndCount(jObj.getInteger("endCount"));
			batchPlanCallSumary.setPlanCount(jObj.getInteger("planCount"));
			return batchPlanCallSumary;
		}
		return null;
	}
	
	public BatchPlanCallSumary getBatchSummaryGroupByLine(String batchName) {
		//调用接口
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("batchName", batchName);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_BATCH_SUMMARY_GROUP_BY_LINE,paramMap);
		if(StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			BatchPlanCallSumary batchPlanCallSumary = new BatchPlanCallSumary();
			batchPlanCallSumary.setBatchName(batchName);
			String lineSummaryList = jObj.getString("lineSummary");
			if(lineSummaryList!=null&&!lineSummaryList.equals("")) {
				batchPlanCallSumary.setLineSummary(lineSummaryList);
				String[] tempList = lineSummaryList.split("\\|");
				if(tempList!=null&&tempList.length!=0) {
					List<PlanLineSummary> bb = new ArrayList<PlanLineSummary>();
					for(String temp:tempList) {
						PlanLineSummary planLineSummary =new PlanLineSummary();
						String[] lineSummary = temp.split("\\^");
						planLineSummary.setLineId(lineSummary[0]);
						planLineSummary.setTotalNum(Integer.valueOf(lineSummary[1]));
						planLineSummary.setEndCount(Integer.valueOf(lineSummary[2]));
						planLineSummary.setLastCallTime(lineSummary[3]);
						bb.add(planLineSummary);
					}
					batchPlanCallSumary.setLineSummaryList(bb);
				}
				
				return batchPlanCallSumary;
			}
			
		}
		return null;
	}
	
	
	public BatchPlanListVo getBatchPlanList(String batchName,int page,int pageNum,String phoneStatus) {
		//调用接口
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("batchName", batchName);
		paramMap.put("page", page);
		paramMap.put("pageNum", pageNum);
		paramMap.put("phoneStatus", phoneStatus);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_BATCH_PLAN_LIST,paramMap);
		if(StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			BatchPlanListVo batchPlanListVo = new BatchPlanListVo();
			batchPlanListVo.setBatchName(batchName);
			batchPlanListVo.setPage(page);
			batchPlanListVo.setPageNum(pageNum);
			batchPlanListVo.setTotalPage(jObj.getInteger("totalPage"));
			batchPlanListVo.setTotalRecord(jObj.getInteger("totalRecord"));
			batchPlanListVo.setPhoneStatus(phoneStatus);
			String mobileJsonList = jObj.getString("mobileList");
			if(mobileJsonList!=null&&!mobileJsonList.equals("")) {
				String[] tempList = mobileJsonList.split("\\|");
				List<MobileInfo> list = new ArrayList<MobileInfo>();
				if(tempList!=null && tempList.length!=0) {
	        		for(String mobileString:tempList) {
	        			MobileInfo mobileInfo = new MobileInfo();
	        			String[] temp1= mobileString.split("\\^");
	        			mobileInfo.setPhone(temp1[0]);
	        			mobileInfo.setAttach(temp1[1]);
	        			mobileInfo.setParams(temp1[2]);
	        			
	        			
	        			list.add(mobileInfo);
	        			
	        		}
	        		
	        	}
				batchPlanListVo.setMobileList(list);
				return batchPlanListVo;
			}
			
		}
		return null;
	}
	
	
	public void getBatchPlanCallList(String batchName,int page,int pageNum,String notifyUrl) {
		//调用接口
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("batchName", batchName);
		paramMap.put("page", page);
		paramMap.put("pageNum", pageNum);
		paramMap.put("notifyUrl", notifyUrl);
		//paramMap.put("phoneStatus", phoneStatus);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_BATCH_BATCH_PLAN_CALL_LIST,paramMap);
	
	}
	
	
	
	
	/**
	 * 根据id查询电话通话详情
	 * @param callId
	 * @return
	 */
	public CallPlanDetailRecordVO queryCallDetailById(String callId) {
		//调用接口
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("callid", callId);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_GET_PLAN__CALL_BY_ID,paramMap);
		if(StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			String callListJson = jObj.getString("callList");
			if(StrUtils.isNotEmpty(callListJson)) {
				JSONArray array= JSONArray.parseArray(callListJson);
	        	if(array!=null && array.size()>0) {
	        		 JSONObject jsonObject=array.getJSONObject(0);
	        		 String intent = jsonObject.getString("accurateIntent");	//不知道为什么不会自己转，手工处理
	        		 if(StrUtils.isEmpty(intent)) {
	        			 String intentLeftStr = callListJson.substring(callListJson.indexOf("accurateIntent")+18);
        				 intent = intentLeftStr.substring(intentLeftStr.indexOf("\"")+1,intentLeftStr.indexOf("\"")+2);
	        		 }
	        		 CallPlanDetailRecordVO vo = JsonUtils.json2Bean(jsonObject.toJSONString(), CallPlanDetailRecordVO.class);
	        		 vo.setIntent(intent);
	        		 return vo;
	        	}
			}
		}
		return null;
	}
	
	
	/**
	 * 批次回调
	 * 
	 * 例如：
	 * {"msg":"SUCCESS","code":"0","success":true,"sign":"3638743059B1EC41FE254EC78A296BDB","body":{"accurateIntent":"F","batchName":"D0_20200616090000_0",
	 * "billSec":0,"callStartTime":1592278205000,"duration":60,"handupTime":1592278265000,"hangupDirection":1,"phoneNo":"8011504669","reason":"无人接听","remarks":"dueday-money"}}
	 */
	public CallDetail planCallback(String json) {
		log.info("return batchPlanCallback  json{}" ,json);
		if(StrUtils.isNotEmpty(json)) {
			ReturnData returnData = JSONObject.parseObject(json, Result.ReturnData.class);
			if(returnData!=null&&returnData.getBody()!=null) {
				JSONObject body = (JSONObject) returnData.getBody();
				CallDetail callDetail =JSONObject.toJavaObject(body, CallDetail.class);
				if(callDetail!=null&&StrUtils.isNotEmpty(callDetail.getDetailList())) {
					String  callDetailString = callDetail.getDetailList();
					String[] tempList = callDetailString.split("\\|");
					if(tempList!=null&&tempList.length!=0) {
						List<CallOutDetailVO> bb = new ArrayList<CallOutDetailVO>();
						for(String temp:tempList) {
							CallOutDetailVO callOutDetailVO =new CallOutDetailVO();
							String[] callOutDetailVOString = temp.split("\\^");
							if(callOutDetailVOString.length>=1) {
								callOutDetailVO.setBotAnswerText(callOutDetailVOString[0]);	
							}
							if(callOutDetailVOString.length>=2) {
								callOutDetailVO.setBotAnswerTime(callOutDetailVOString[1]);	
							}
							if(callOutDetailVOString.length>=3) {
								callOutDetailVO.setCustomerSayText(callOutDetailVOString[2]);	
							}
							if(callOutDetailVOString.length>=4) {
								callOutDetailVO.setCustomerSayTime(callOutDetailVOString[3]);	
							}
							
							bb.add(callOutDetailVO);
						}
						callDetail.setDetailVoList(bb);;
					}
					log.info("return callDetail {}" ,callDetail);
					return callDetail;
				}
			}
			
		}
		return null;
	}
	
	/**
	 * 批次回调
	 * 例如：
	 * {"msg":"SUCCESS","code":"0","success":true,"sign":"50B27CB7872D2C40471E40014F26BEFC","body":{"acceptCount":1,"batchName":"D0_20200616090000_0","endCount":1,"failCount":1,"failList":"8011504669^dueday-money^2020/06/16-5067","planCount":0,"successCount":0}}
	 */
	public BatchPlanCallSumary batchPlanCallback(String json) {
		log.info("return planCallback  json{}" ,json);
		if(StrUtils.isNotEmpty(json)) {
			ReturnData returnData = JSONObject.parseObject(json, Result.ReturnData.class);
			if(returnData!=null&&returnData.getBody()!=null) {
				JSONObject body = (JSONObject) returnData.getBody();
				BatchPlanCallSumary batchPlanCallSumary =JSONObject.toJavaObject(body, BatchPlanCallSumary.class);
				if(batchPlanCallSumary!=null&&batchPlanCallSumary.getFailList()!=null&&!batchPlanCallSumary.getFailList().equals("")) {
					String FailListString = batchPlanCallSumary.getFailList();
					String[] tempList = FailListString.split("\\|");
					if(tempList!=null&&tempList.length!=0) {
						List<MobileInfo> bb = new ArrayList<MobileInfo>();
						for(String temp:tempList) {
							MobileInfo mobileInfo =new MobileInfo();
							String[] mobileInfoString = temp.split("\\^");
							mobileInfo.setPhone(mobileInfoString[0]);
							mobileInfo.setAttach(mobileInfoString[1]);
							mobileInfo.setParams(mobileInfoString[2]);
							bb.add(mobileInfo);
						}
						batchPlanCallSumary.setFailMobileList(bb);;
					}
					
					
				}
				log.info("return batchPlanCallSumary {}" ,batchPlanCallSumary);
				return batchPlanCallSumary;
			}

		}
		return null;
	}
	
	
	private boolean checkRspSing(String resJson,String privateKey) {
		
		if(StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			String sign = jObj.getString("sign");
			if(sign!=null&&!sign.equals("")) {
				String jsonBody = jObj.getString("body");
				Map<String, Object> itemMap = JSONObject.toJavaObject(JSON.parseObject(jsonBody), Map.class);
				SignRo signRo = SignRo.builder()
						.privateKey(privateKey) //秘钥
						.params(itemMap)
						.build();
				String preSign = FlashcallSignUtils.sign(signRo);
				if(preSign!=null&&preSign.equals(sign)) {
					return true;
				}else {
					return false;
				}
			}else {
				log.error("sign is null");
			
			}
			
		}
		return false;
	}
	
	
	
	
}
