package com.flashcall.thirdclient.exception;

import org.springframework.stereotype.Component;

/**
* @ClassName: ParrotException 
* @Description: flashcall异常信息
* @auth weiyunbo
* @date 2019年7月15日 下午5:08:42 
* @version V1.0
 */
public class FlashcallException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;  //异常对应的描述信息

    /**
     * @param message
     */
	public FlashcallException(String message) {
		this.errorMessage = message;
	}
	
	
	public FlashcallException(){
		super();
	}
	
	public FlashcallException(Throwable throwable){
		super(throwable);
	}
	
	public FlashcallException(String msg, Throwable throwable){
		super(msg, throwable);
	}
	
    public String getErrorMessage() {
        return errorMessage;
    }
	
}
