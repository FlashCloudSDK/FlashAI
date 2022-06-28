package com.flashcall.thirdclient.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 信审的返回类
 * @author lhq
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CallDetailToHJ{

	//返回码
	private Integer code;
	//拨打手机号码
	private String mobileNumber;
	//call_id
	private String callId;
	//通话时长
	private String callDuration;
	//开始时间
	private String callStartTime;
	//结束时间
	private String callEndTime;
	//录音文件地址 暂时不提供
	private String recordUrl;
	//batchName
	private String batchName;
	
	//信审
	/*6000 : 审核通过
	6001 ： 关机 F|W      shutdown
	6002 ： 无效的号码 F|W  empty number
	6003 ： 忙碌 F|W   busy
	6004 : 无应答 F|W  No answer
	6005 : 用户挂断或者其他问题
	6006 ： 非本人借款 D   
	6007 ： 放弃借款或者其他原因放弃借款 B
	6008 : 未知意向tag G或者未定义的其他标签
	6009 : 语音信箱 C
	6010 : 超出号码拨打限制 X
	*/
	
	/*
	 * 183 appDefaultIntent No answer appDefaultIntent en 无人接听 0 
	 * 184 appDefaultIntent shutdown appDefaultIntent en 关机 0 
	 * 185 appDefaultIntent busy appDefaultIntent en 占线 0 
	 * 186 appDefaultIntent empty number appDefaultIntenten 空号 0 
	 * 187 appDefaultIntent user hang up appDefaultIntent en 用户挂断 0
	 */    
	
	
	//补充资料
	/*
	8000 ： 通过 A
	8001 ： 关机 F|W      shutdown
	8002 ： 无效的号码 F|W  empty number
	8003 ： 忙碌 F|W   busy
	8004 : 无应答 F|W  No answer
	8005 : 用户挂断或者其他原因
	8006 ： 超出号码拨打限制 X 
	*/
	
	
	//提现提醒
	/*
	9000 ： 通过 A
	9001 ： 关机 F|W      shutdown
	9002 ： 无效的号码 F|W  empty number
	9003 ： 忙碌 F|W   busy
	9004 : 无应答 F|W  No answer
	9005 : 用户挂断或者其他原因 G
	9006 ： 超出号码拨打限制 X 
	*/
	
	
	//催收提醒
	/*
	7000 ： 通过 A
	7002 ： 关机 F|W      shutdown
	7003 ： 无效的号码 F|W  empty number
	7004 ： 忙碌 F|W   busy
	7005 : 无应答 F|W  No answer
	7006 : 用户挂断或者其他原因 G
	7007 ： 超出号码拨打限制 X 
	*/
}
