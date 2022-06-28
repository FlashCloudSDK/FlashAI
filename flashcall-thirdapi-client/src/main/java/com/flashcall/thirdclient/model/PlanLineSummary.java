package com.flashcall.thirdclient.model;

import lombok.Data;

@Data
public class PlanLineSummary {
	
	private String lineId;
	private int totalNum;
	private int endCount;
	private String lastCallTime;

}
