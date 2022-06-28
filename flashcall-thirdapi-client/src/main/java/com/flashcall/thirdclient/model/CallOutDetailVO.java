package com.flashcall.thirdclient.model;


import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;


/**
 * @Date: 2018/10/30 0030 14:30
 * @Description:
 */
@Data
public class CallOutDetailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String callDetailId;
    private BigInteger callId;
    private String intent;
    private String agentAnswerText;
    private String agentAnswerTime;
    private Integer aiDuration;
    private Integer asrDuration;
    private String botAnswerText;
    private String botAnswerTime;
    private Integer callDetailType;
    private String customerSayText;
    private String customerSayTime;
    private String reason;
    private Integer totalDuration;

    private String agentRecordFile;
    private String agentRecordUrl;
    private String botRecordFile;
    private String botRecordUrl;
    private String customerRecordFile;
    private String customerRecordUrl;

    private String wordSegmentResult;
    private String keywords;
    private Integer isupdate;
}
