
package com.flashcall.thirdclient.exception;

import org.springframework.stereotype.Component;



@Component
public class Result {
	
	
	public static <T> Result.ReturnData<T> ok(T obj){
		return new Result.ReturnData<T>(obj);
	}

	public static <T> Result.ReturnData<T> ok(){
		return new Result.ReturnData<T>();
	}



	public static class ReturnData<T>{
		public String code="0";
		public String msg="SUCCESS";
		public boolean success = true;
		public T body;
		
		public ReturnData(){
		}
		
		public ReturnData(String code,String msg,boolean success){
			this.code=code;
			this.msg=msg;
			this.success=success;
		}
		
		public ReturnData(T body){
			this.body=body;
		}
		
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}

		public T getBody() {
			return body;
		}

		public void setBody(T body) {
			this.body = body;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ReturnData [code=" + code + ", msg=" + msg + ", success=" + success + ", body=" + body + "]";
		}
	}
}