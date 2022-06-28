
package com.flashcall.thirdclient.model;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 通过批次号查询该批次的通话列表，具体通话详情
 */
@Data
public class CallDetail {

    private Date callStartTime; //拨打开始时间

    private Date hangupTime; //挂断时间

    private Date answerTime; 

    private Integer duration;

    private Integer billSec;

    private Date endTime;

    private Date handupTime;

//    private String addDate;

    private String callTime;

    private Integer hangupDirection;

    private String accurateIntent;

    private String reason;

    private String remarks;

    private Integer freason;

    private List<CallOutDetailVO> detailVoList;
    
    private String detailList;
    
    private String batchName;
    
    private String phoneNo;
}
