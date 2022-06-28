
package com.flashcall.thirdclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ItemCount 
* @Description: 通用统计项
* @auth weiyunbo
* @date 2019年6月27日 下午12:00:08 
* @version V1.0  
*/
@Data
@Builder @NoArgsConstructor @AllArgsConstructor
public class ItemCount {
	private String key;
	private int count;
}
