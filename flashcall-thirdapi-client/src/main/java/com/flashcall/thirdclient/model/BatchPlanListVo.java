
package com.flashcall.thirdclient.model;

import java.util.List;

import lombok.Data;

/**
 * 通过批次号查询该批次的号码列表
 */
@Data
public class BatchPlanListVo {

    //总页数
    private Integer totalPage;

    //当前页
    private Integer page;

    //每页条数
    private Integer pageNum;

    //批次名称
    private String batchName;
    
    private String phoneStatus;

    //号码列表
    private List<MobileInfo> mobileList;
    
    //系统成功接收到的号码的数量
    private Integer batchCount;
    
    private Integer totalRecord;
}
