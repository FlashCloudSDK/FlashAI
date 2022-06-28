package com.flashcall.thirdclient.model;


import java.util.List;

import com.flashcall.thirdclient.util.JsonUtils;

import lombok.Data;

@Data
public class BatchReCallParamRo {
	
	//[{"recallIntervalTime":"5","recallTag":"W","recallTimes":"1","ruleType":"0"},
	//{"recallIntervalTime":"5","recallTag":"F","recallTimes":"1","ruleType":"0"}]
	
	
	public String recallTag; //哪些标签，需要重播
	
	public String recallIntervalTime; //重播间隔 单位:分钟
	
	public String recallTimes; //重播 次数
	
	//0-smaple,1-complex
	public String ruleType = "0"; //重播模式， 默认0 普通模式
	
	public static void main(String[] args) {
		/*
		 * BatchReCallParamRo b = new BatchReCallParamRo(); b.setRecallTag("W");
		 * b.setRecallIntervalTime("5"); b.setRecallTimes("2");
		 * System.out.println(JsonUtils.bean2Json(b));
		 */
		
		
		String test = "[{\"recallIntervalTimesdf\":\"5\",\"recallTag\":\"W\",\"recallTimes\":\"1\"},{\"recallIntervalTimesdf\":\"5\",\"recallTag\":\"F\",\"recallTimes\":\"1\"}]";
		try {
			List<BatchReCallParamRo> ss = JsonUtils.json2List(test, BatchReCallParamRo.class);
			System.out.println("成功！！！");
		} catch (Exception e) {
			System.out.println("转换失败，格式有误");
		}
		
	}

}
