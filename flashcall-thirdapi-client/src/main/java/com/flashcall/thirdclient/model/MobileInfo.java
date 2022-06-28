
package com.flashcall.thirdclient.model;



import lombok.Data;

@Data
public class MobileInfo {
	private String phone; //
	private String attach; //key 多个用-隔开
	private String params; //value 和attach对应
}
