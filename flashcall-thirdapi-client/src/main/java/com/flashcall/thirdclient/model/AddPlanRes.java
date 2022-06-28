
package com.flashcall.thirdclient.model;

import java.util.List;

import lombok.Data;




@Data
public class AddPlanRes  {

    /**
     * failMobileInfo
     */
    private List<MobileInfo> failMobileInfo;

    /**
     * 正确的数量
     */
    private Integer rightCount;

    /**
     * 错误的数量
     */
    private Integer failCount;

}
