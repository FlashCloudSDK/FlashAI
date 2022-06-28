package com.flashcall.thirdclient.constant;

/** 
* @ClassName: AiConstants 
* @Description: 常量
* @auth weiyunbo
* @date 2019年6月23日 下午6:11:46 
* @version V1.0  
*/
public class AiConstants {
	
	//查询人工座席组
	public static final String PARROT_QUERY_AGENT_GROUP = "thirdApi/queryAgentGroup";
	
	//添加人工座席拨打计划
	public static final String PARROT_ADD_AGENT_PLAN = "thirdApi/addAgentPlan";
	
	//取消人工座席拨打计划
	public static final String PARROT_CANCEL_AGENT_PLAN = "thirdApi/cancelAgentPlan";
	
	//获取用户线路
	public static final String PARROT_GET_CALL_LINE = "thirdApi/getCallLine";
	//获取用户话术模板
	public static final String PARROT_GET_BOTENCE = "thirdApi/getBotence";
	//查询短信模板
	public static final String PARROT_GET_SMS_TEMPLATE = "thirdApi/sms/querySmsTemplate";
	//根据callid查询一笔详情
	public static final String PARROT_GET_PLAN__CALL_BY_ID = "thirdApi/getPlanCallById";
	//添加拨打计划
	public static final String PARROT_ADD_PLAN = "thirdApi/addPlan";
	//批量计划停止
	public static final String PARROT_STOP_PLAN = "thirdApi/batchPlanStop";
	//批量计划恢复
	public static final String PARROT_RESTART_PLAN = "thirdApi/batchPlanRestart";
	//批量计划暂停
	public static final String PARROT_PAUSE_PLAN = "thirdApi/batchPlanPause";
	//批量手机号删除
	public static final String PARROT_DELETE_PHONE="thirdApi/batchPhoneDelete";
	//查询计划参数
	public static final String PARROT_QUERY_PLAN_PARAMS = "thirdApi/queryPlanParams";
	
	public static final String PARROT_QUERY_BATCH_PLAN_CALL_SUMMARY ="thirdApi/getBatchPlanCallSummary";
	
	public static final String PARROT_QUERY_BATCH_SUMMARY_GROUP_BY_LINE ="thirdApi/getBatchSummaryGroupByLine";
	
	public static final String PARROT_QUERY_BATCH_PLAN_LIST ="thirdApi/getBatchPlanList";

	public static final String PARROT_QUERY_BATCH_BATCH_PLAN_CALL_LIST ="thirdApi/getBatchPlanCallList";
	
	
	//查询计划参数
	public static final String PARROT_QUERY_RECORDURL = "thirdApi/getRecordUrl";
	
	public static final String PARROT_RECALL = "thirdApi/recall";
	
	public static final String PARROT_PHONE_DETAIL = "thirdApi/getPhoneDetail";
	
}

