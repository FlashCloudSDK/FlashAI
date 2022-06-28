package com.flashcall.thirdclient.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
* @ClassName: FlashcallEncryptUtils 
* @Description: Flashcall密码加密
* @auth weiyunbo
* @date 2019年6月23日 下午6:16:08 
* @version V1.0  
*/
public class FlashcallEncryptUtil {
	public static void main(String[] args) {
		System.out.println(FlashcallEncryptUtil.encrypt("123456"));
	}
	
	
	/**
	 * 这里和Flashcall的加密规则保持一致
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {
		String result = null;
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.update(str.getBytes(), 0, str.length());
            result =  new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
	}
}
