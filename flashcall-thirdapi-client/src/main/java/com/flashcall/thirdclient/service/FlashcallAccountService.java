
package com.flashcall.thirdclient.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flashcall.thirdclient.constant.AiConstants;
import com.flashcall.thirdclient.model.*;
import com.flashcall.thirdclient.util.JsonUtils;
import com.flashcall.thirdclient.util.FlashcallClient;
import com.flashcall.thirdclient.util.StrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: ParrotService
 * @Description: flashcall账户、线路、机器人配置等服务
 * @auth weiyunbo
 * @date 2019年6月23日 下午5:46:27
 * @version V1.0
 */
@Slf4j
@Service
public class FlashcallAccountService {
	@Autowired
	FlashcallClient flashcallUtil;
	
	public String recall(String batchName,String planUUid,String url) {
		// 调用接口
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("batchName", batchName);
		paramMap.put("planUUid", planUUid);
		paramMap.put("notifyUrl", url);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_RECALL, paramMap);
		JSONObject jsonObject = JSON.parseObject(resJson);
		if (jsonObject == null) {
			return null;
		}
		
		return jsonObject.toJSONString();
	}
	
	
	public String getCallRecordUrl(String callId) {
		// 调用接口
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("callId", callId);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_RECORDURL, paramMap);
		JSONObject jsonObject = JSON.parseObject(resJson);
		if (jsonObject == null) {
			return null;
		}
		String recordUrlJson = jsonObject.getString("recordUrl");
		
		return recordUrlJson;
	}

	/**
	 * 查询用户拥有的线路
	 * 
	 * @param callLineReq
	 * @return
	 */
	public List<CallLineRsp> getUserCallLine() {
		// 调用接口
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_GET_CALL_LINE, null);
		JSONObject jsonObject = JSON.parseObject(resJson);
		if (jsonObject == null) {
			return null;
		}
		String lineArrayJson = jsonObject.getString("lineList");
		if (StrUtils.isNotEmpty(lineArrayJson)) {
			List<CallLineRsp> lineList = new ArrayList<CallLineRsp>();
			// 多条线路以|分隔
			String[] lines = lineArrayJson.split("\\|");
			for (String lineStr : lines) {
				CallLineRsp b = new CallLineRsp();
				// 一条话术内容以^分隔（线路ID^线路名称^线路类型）
				String[] lData = lineStr.split("\\^");
				b.setLineId(Integer.valueOf(lData[0]));
				b.setLineName(lData[1]);
				b.setLineType(Integer.valueOf(lData[2]));
				lineList.add(b);
			}
			return lineList;
		}
		return null;
	}

	/**
	 * 查询用户拥有的话术
	 * 
	 * @return
	 */
	public List<BotenceRsp> getUserBotence() {
		// 调用接口
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_GET_BOTENCE, null);
		JSONObject jsonObject = JSON.parseObject(resJson);
		if (jsonObject == null) {
			return null;
		}
		String botencesJson = jsonObject.getString("botenceList");
		if (StrUtils.isNotEmpty(botencesJson)) {
			List<BotenceRsp> botenceList = new ArrayList<BotenceRsp>();
			// 多条话术以|分隔
			String[] botences = botencesJson.split("\\|");
			for (String botence : botences) {
				BotenceRsp b = new BotenceRsp();
				// 一条话术内容以^分隔（话术ID^话术名称^机器人数量）
				String[] bData = botence.split("\\^");
				b.setTemplateId(bData[0]);
				b.setTemplateName(bData[1]);
				b.setAgentFlag(bData[2]);
				botenceList.add(b);
			}
			return botenceList;
		}
		return null;
	}

	/**
	 * 查询短信模板
	 * 
	 * @return
	 */
	public List<SmsTemplateRsp> querySmsTemplate() {
		// 调用接口
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_GET_SMS_TEMPLATE, null);
		JSONObject jsonObject = JSON.parseObject(resJson);
		if (jsonObject == null) {
			return null;
		}
		String templateList = jsonObject.getString("templateLsit");
		if (StrUtils.isNotEmpty(templateList)) {
			List<SmsTemplateRsp> newList = new ArrayList<SmsTemplateRsp>();
			// 多条话术以|分隔
			String[] template = templateList.split("\\|");
			for (String templateString : template) {
				SmsTemplateRsp b = new SmsTemplateRsp();
				// 一条话术内容以^分隔（话术ID^机器人数量）
				String[] bData = templateString.split("\\^");
				b.setTemplateId(bData[0]);
				b.setTemplateName(bData[1]);
				b.setIntentionTag(bData[2]);
				b.setContentType(bData[3]);
				b.setSmsContent(bData[4]);
				newList.add(b);
			}
			return newList;
		}
		return null;
	}

	/**
	 * 根据id查询电话通话详情
	 * 
	 * @param callId
	 * @return
	 */
	public List<TtsParamVO> queryPlanParams(String botenceId) {
		// 调用接口
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("botenceId", botenceId);
		String resJson = flashcallUtil.sendWithAuth(AiConstants.PARROT_QUERY_PLAN_PARAMS, paramMap);
		if (StrUtils.isNotEmpty(resJson)) {
			JSONObject jObj = JSON.parseObject(resJson);
			String paramSeqLsitJson = jObj.getString("paramSeqLsit");
			if (StrUtils.isNotEmpty(paramSeqLsitJson)) {
				List<TtsParamVO> newList = new ArrayList<TtsParamVO>();
				// 多条话术以|分隔
				String[] paramSeqLsit = paramSeqLsitJson.split("\\|");
				for (String paramSeqString : paramSeqLsit) {
					TtsParamVO b = new TtsParamVO();
					// 一条话术内容以^分隔（话术ID^机器人数量）
					String[] bData = paramSeqString.split("\\^");
					if (bData.length >= 1) {
						b.setParamName(bData[0]);
					}
					if (bData.length >= 2) {
						b.setSeq(Integer.valueOf(bData[1]));
					}
					if (bData.length >= 3) {
						b.setParamType(bData[2]);
					}
					newList.add(b);
				}
				return newList;
			}
		}
		return null;
	}

}
