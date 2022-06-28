package com.flashcall.thirdclient.model;

import lombok.Data;

/**
 *
 * @author 张朋
 * @Description:普通短信发送响应实体类
 */
@Data
public class KmicallSendResponse {
	/*   *//**
			 * 响应时间
			 */
	/*
	 * private String trxdate;
	 *//**
		 * 消息id
		 */
	/*
	 * private String trxid;
	 *//**
		 * 状态码说明（成功返回空）
		 */
	/*
	 * private String desc;
	 *//**
		 * 状态码（详细参考提交响应状态码）
		 *//*
			 * private int code;
			 * 
			 * private String msg;
			 */
	
	private Boolean success;
	private String message;
	private Integer code;
	private KmicallResult result;
	private Long timestamp;
	
}
