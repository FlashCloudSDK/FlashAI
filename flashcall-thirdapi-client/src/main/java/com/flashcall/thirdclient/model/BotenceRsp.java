
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: BotenceRsp 
* @Description: 话术信息
* @auth weiyunbo
* @date 2019年6月24日 下午2:08:14 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class BotenceRsp {
	//话术ID
	private String templateId;
	//agentFlag=1 为支持人工坐席 ，0-为不支持
	private String  agentFlag;
	//话术名称
	private String templateName;
}
