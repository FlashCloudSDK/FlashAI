package com.flashcall.thirdclient.model;

import lombok.Data;

/**
 *
 * @author 张朋
 * @Description:普通短信发送实体类
 */
@Data
public class KmicallSendRequest {
	/**
     * token，必填
     */
    private String token;
    
    private String accessKey;
    
    private String secretKey;
    
    private String from;
    
    private String to;
    
    private String callbackUrl;
    
    /**
     * 发送类型，默认1
     */
    private String sendType;
    /**
     * 短信内容。长度不能超过536个字符，必填
     */
    private String message;
    /**
     * 机号码。多个手机号码使用英文逗号分隔，必填
     */
    private String msisdn;
    /**
     * 该条短信在您业务系统内的ID，如订单号或者短信发送记录流水号，选填
     */
    private String uid;

    public KmicallSendRequest() {

    }
    public KmicallSendRequest(String token, String sendType, String message, String msisdn) {
        super();
        this.token = token;
        this.sendType = sendType;
        this.message = message;
        this.msisdn = msisdn;
    }
    
    
    public KmicallSendRequest(String accessKey,String secretKey, String to ,String message,String from,String callbackUrl) {
    	super();
    	this.accessKey=accessKey;
    	this.secretKey=secretKey;
    	this.to=to;
    	this.message=message;
    	this.from = from;
    	this.callbackUrl = callbackUrl;
    }

}
