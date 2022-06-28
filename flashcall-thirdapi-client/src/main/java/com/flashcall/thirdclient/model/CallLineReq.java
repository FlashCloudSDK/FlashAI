
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: CallLineReq 
* @Description: 用户线路查询请求
* @auth weiyunbo
* @date 2019年6月24日 下午2:01:41 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class CallLineReq {
	private String userId;
}
