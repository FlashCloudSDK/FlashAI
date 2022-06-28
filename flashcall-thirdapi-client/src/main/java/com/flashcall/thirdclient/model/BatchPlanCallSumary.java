
package com.flashcall.thirdclient.model;


import java.util.List;

import lombok.Data;

/**
 * 通过批次号查询该批次的拨打情况
 * 结果信息
 */
@Data
public class BatchPlanCallSumary   {

	
	//拨打失败的总数
	private Integer failCount;
	//成功处理 ==呼叫完成的号码数量
	private Integer successCount;
	
	private String failList;
	
	private List<MobileInfo> failMobileList;
    //批次名称
    private String batchName;

    //系统接收到的号码数量
    private Integer acceptCount;

    //呼叫完成的号码数量
    private Integer endCount;

    //计划中的号码数量
    private Integer planCount;
    
    //任务状态
    private String phoneStatus;
    
    //lineId^totalNum^endCount^lastCallTime|
    private List<PlanLineSummary> lineSummaryList;
    
  //lineId^totalNum^endCount^lastCallTime|
    private String lineSummary;
    
    private Integer totalPage;
    
    private Integer page;
    
    private Integer pageNum;
    
    private String callList;

}
