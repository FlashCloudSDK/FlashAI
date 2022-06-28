
package com.flashcall.thirdclient.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: RechangeReq 
* @Description: 充值请求
* @auth weiyunbo
* @date 2019年6月23日 下午6:59:27 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class RechangeReq{
	//充值类型（1-天数；2-金额）
	private String rechangeType;
	//天数（如果充值天数的话，必输）
	private Integer rechangeDay;
	//金额（如果充值金额必输）
	private BigDecimal rechangeAccount;
	//唯一凭证编号
	private String evidence;
	
	//一般不用传
	private String accessKey;
	private String secretKey;
}
