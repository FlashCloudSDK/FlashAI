package com.flashcall.thirdclient.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.flashcall.thirdclient.config.FlashcallConf;
import com.flashcall.thirdclient.exception.FlashcallException;
import com.flashcall.thirdclient.model.SignRo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlashcallClient {
	@Autowired
	FlashcallConf flashcallConf;
	
	
	/**
	 * 调用Flashcall服务接口（带加密验签）
	 * @param url
	 * @param req
	 * @return
	 */
	public String sendWithAuth(String url,Object req) {
		//调用Flashcall服务
		Map<String,Object> params = new HashMap<String,Object>();
		if(req!=null) {
			if(req instanceof Map) {
				params = (Map<String, Object>) req;
			}else {
				params = BeanUtil.bean2Map(req);
			}
		}
		//因为要加签，所以里边不能有list，有list的话将list多数据改为中竖线|分隔
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if(entry.getValue() instanceof List) {
				String value = null;
				StringBuffer valueSb = new StringBuffer();
				List list = (List) entry.getValue();
				if(ListUtil.isNotEmpty(list)) {
					for(Object obj : list) {
						valueSb.append("|"+obj);
					}
					value = valueSb.toString().substring(1);
				}
				params.put(entry.getKey(), value);
			}
		}
		params.put("accessKey",flashcallConf.getAccessKey());
		params.put("secretKey",flashcallConf.getSecretKey());
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("nonce", UUID.randomUUID().toString().substring(0, 6));
		params.put("clientId", params.get("accessKey"));
		String privateKey = params.get("secretKey").toString();
		params.remove("accessKey");	 //验签不需要
		params.remove("secretKey");  //验签不需要
		System.out.println("params :" + params.toString());
		SignRo signRo = SignRo.builder()
				.privateKey(privateKey) //秘钥
				.params(params)
				.build();
		//生成签名
		String sign = FlashcallSignUtils.sign(signRo);
		params.put("signType", "MD5");
		params.put("sign", sign);
		String reqJson = JsonUtils.bean2Json(params);
		System.out.println("reqJson : " + reqJson);
		String res = HttpClientUtil.doPostJson(flashcallConf.getUrl()+url, reqJson);
		System.out.println("res : " + res);
		if(StrUtils.isNotEmpty(res)) {
			JSONObject jsonObject = JSON.parseObject(res);
			String code = jsonObject.getString("code");
			if(StrUtils.isEmpty(code) || !"0".equals(code)) {
				log.error("调用Flashcall接口发生异常,请求参数：{}",reqJson);
				log.error("调用Flashcall接口返回异常，返回结果：{}!",res);
				//throw new FlashcallException("Flashcall接口返回异常");
			}
			return jsonObject.getString("body");
		}else {
			throw new FlashcallException("调用Flashcall服务返回报文为空!");
		}
	}
	
	
	/**
	 * 调用Flashcall服务接口（带加密验签）test
	 * @param url
	 * @param req
	 * @return
	 */
	public String sendWithAuthTest(Object req) {
		//调用Flashcall服务
		Map<String,Object> params = new HashMap<String,Object>();
		if(req!=null) {
			if(req instanceof Map) {
				params = (Map<String, Object>) req;
			}else {
				params = BeanUtil.bean2Map(req);
			}
		}
		//因为要加签，所以里边不能有list，有list的话将list多数据改为中竖线|分隔
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			if(entry.getValue() instanceof List) {
				String value = null;
				StringBuffer valueSb = new StringBuffer();
				List list = (List) entry.getValue();
				if(ListUtil.isNotEmpty(list)) {
					for(Object obj : list) {
						valueSb.append("|"+obj);
					}
					value = valueSb.toString().substring(1);
				}
				params.put(entry.getKey(), value);
			}
		}
		params.put("accessKey","38UvGsdEme");
		params.put("secretKey","0fa980e311e471944a6b82380c6fcf46");
		params.put("timestamp", String.valueOf(System.currentTimeMillis()));
		params.put("nonce", UUID.randomUUID().toString().substring(0, 6));
		params.put("clientId", params.get("accessKey"));
		String privateKey = params.get("secretKey").toString();
		params.remove("accessKey");	 //验签不需要ok
		params.remove("secretKey");  //验签不需要
		SignRo signRo = SignRo.builder()
				.privateKey(privateKey) //秘钥
				.params(params)
				.build();
		//生成签名
		String sign = FlashcallSignUtils.sign(signRo);
		params.put("signType", "MD5");
		params.put("sign", sign);
		String reqJson = JsonUtils.bean2Json(params);
		System.out.println("reqJson : " + reqJson);
		String res = HttpClientUtil.doPostJson("http://tel.jishuyuansu.com/api/v1/thirdApi/addPlan", reqJson);
		System.out.println("res : " + res);
		if(StrUtils.isNotEmpty(res)) {
			JSONObject jsonObject = JSON.parseObject(res);
			String code = jsonObject.getString("code");
			if(StrUtils.isEmpty(code) || !"0".equals(code)) {
				log.error("调用Flashcall接口发生异常,请求参数：{}",reqJson);
				log.error("调用Flashcall接口返回异常，返回结果：{}!",res);
				//throw new FlashcallException("Flashcall接口返回异常");
			}
			return jsonObject.getString("body");
		}else {
			throw new FlashcallException("调用Flashcall服务返回报文为空!");
		}
	}
	
}
