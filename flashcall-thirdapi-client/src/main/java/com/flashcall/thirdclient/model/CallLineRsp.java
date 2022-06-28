
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: CallLineRsp 
* @Description: 线路信息
* @auth weiyunbo
* @date 2019年6月24日 下午2:08:14 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class CallLineRsp {
	//线路编号
	private Integer lineId;
	//线路名称
	private String lineName;
	//线路类型（1-SIP 2-SIM）
	private Integer lineType;
}
