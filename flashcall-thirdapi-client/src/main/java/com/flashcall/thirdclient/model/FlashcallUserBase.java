
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: FlashcallUserBase 
* @Description: flashcall用户基本信息
* @auth weiyunbo
* @date 2019年6月23日 下午5:54:57 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class FlashcallUserBase {
	//访问秘钥
	private String accessKey;
	//访问秘钥
	private String secretKey;
	//可用话术模板
	private String templates;
	//线路编号
	private String lineId;
	//机器人数量
	private Integer aiTotalNum;
}
