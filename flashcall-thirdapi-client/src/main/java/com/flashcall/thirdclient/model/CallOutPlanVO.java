package com.flashcall.thirdclient.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Date: 2018/10/29 0029 19:02
 * @Description:
 */
@Data

public class CallOutPlanVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String callId;

    private String phoneNum;

    private String customerId;

    private String tempId;

    private Integer lineId;
    private Date callStartTime;

    private String serverid;
    private String agentId;
    private Date agentAnswerTime;
    private String agentChannelUuid;
    private String agentGroupId;
    private Date agentStartTime;
    private Date createTime;
    private Date scheduleTime;
    private Date hangupTime;
    private Date answerTime;
    private Integer duration;
    private Integer billSec;
    private Integer callDirection;
    private Integer callState;
    private String intent;
    private Integer hangupDirection;
    private String reason;
    private String hangupCode;
    private String originateCmd;
    private String remarks;
    private Integer freason;
    private Integer isread;
    private List<CallOutDetailVO> detailList;

    private String attach;
    private String planUuid;

    private String enterprise;
    private String answerUser;
}
