
package com.flashcall.thirdclient.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OpenActReq 
* @Description: 开户请求
* @auth weiyunbo
* @date 2019年6月23日 下午5:53:17 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class OpenActReq {
	private String userName;
	private String password;
}
