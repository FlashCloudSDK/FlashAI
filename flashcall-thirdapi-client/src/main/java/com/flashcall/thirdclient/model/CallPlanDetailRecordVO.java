package com.flashcall.thirdclient.model;

/**
 * @Date: 2018/12/3 0003 14:59
 * @Description:
 */
public class CallPlanDetailRecordVO extends CallOutPlanVO {
	//录音
    private String recordUrl;
    //用户ID
    private String userName;
    //批次号
    private String batchName;

    public String getRecordUrl() {
        return recordUrl;
    }

    public void setRecordUrl(String recordUrl) {
        this.recordUrl = recordUrl;
    }

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the batchName
	 */
	public String getBatchName() {
		return batchName;
	}

	/**
	 * @param batchName the batchName to set
	 */
	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}
}
