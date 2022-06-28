package com.flashcall.thirdclient.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: SignRo 
* @Description: flashcall签名
* @auth weiyunbo
* @date 2019年6月23日 下午5:49:53 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class SignRo {
	private String privateKey;
    private Map<String, Object> params;
    private String signature;
}
