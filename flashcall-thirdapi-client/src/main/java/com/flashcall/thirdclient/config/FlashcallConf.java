package com.flashcall.thirdclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;


/** 
* @ClassName: ParrotConf 
* @Description: flashcall服务配置信息
* @auth weiyunbo
* @date 2019年6月24日 上午9:28:28 
* @version V1.0  
*/
@Data
@ConfigurationProperties(prefix = "flashcall")
@Component
public class FlashcallConf {
	//flashcall系统调用url
	private String url;
	//批次回调地址
	private String notifyBatchUrl;
	//单笔回调地址
	private String notifyUrl;
	
	private String accessKey;
	
	private String secretKey;
}
