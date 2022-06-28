package com.flashcall.thirdclient.util;

import org.springframework.util.DigestUtils;

import com.flashcall.thirdclient.model.SignRo;

import java.util.*;

/**
* @ClassName: FlashcallSignUtils 
* @Description: Flashcall验签工具类
* @auth weiyunbo
* @date 2019年6月23日 下午6:27:10 
* @version V1.0
 */
public class FlashcallSignUtils {

    /**
     * 验签
     *
     * @param signRo
     * @return
     */
    public static boolean verify(SignRo signRo) {

        String signature = signRo.getSignature();

        String newSignature = sign(signRo);

        return newSignature.equals(signature.toUpperCase());
    }

    /**
     * 签名
     *
     * @param signRo
     * @return
     */
    public static String sign(SignRo signRo) {
        return buildRequestMysign(signRo.getParams(), signRo.getPrivateKey());
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("")
                    || key.equalsIgnoreCase("sign")
                    || key.equalsIgnoreCase("signType")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }


    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        params = paraFilter(params);
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        System.out.println("keys :" + keys.toString());
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if(value!=null && !value.equals(null)) {
            	if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                    prestr = prestr + key + "=" + value;
                } else {
                    prestr = prestr + key + "=" + value + "&";
                }
            }
        }
        System.out.println("拼接后的字符串 ： " + prestr);
        return prestr;
    }

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, Object> sPara, String privateKey) {
        Map<String, String> map = new HashMap<>();
        sPara.forEach((k, v) -> {
            map.put(k, v.toString());
        });
        String prestr = "";
        if (sPara != null) {
            prestr = createLinkString(map); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        }
        String mysign = "";
        //body={"lineList":"2^外呼^1"}&code=0&msg=处理成功&success=0abcdefg
        mysign = DigestUtils.md5DigestAsHex((prestr + privateKey).getBytes()).toUpperCase();

        return mysign;
    }
    
    public static void main(String[] a) {
        //ClientId =c8b4f569e0d5a7a57fc6240541827443
        //SecretKey =799d6055c68c8f91de3ee7d852c13844
        
        
        //
        long currDate = System.currentTimeMillis();
        
        System.out.println("currDate"+currDate);
        
        SignRo signRo = new SignRo();
        Map<String, Object> map = new HashMap<>();
        //map.put("signType","MD5");
        map.put("batchName", "123hh");
        map.put("callDate", "20190505");
        map.put("isClear", "1");
        map.put("lineIds", "258");
        map.put("botenceId", "jr_36123_en");
        map.put("mobileList", "18335618326^afg^123");
        map.put("callHour", "9,10");
        map.put("notifyUrl", "http://xxxx:8011/notify");
        map.put("notifyBatchUrl","http://xxxx:8011/notify");
        map.put("nonce","abcdef");
        map.put("timestamp","1561067298071");
        map.put("clientId","c8b4f569e0d5a7a57fc6240541827443");	
        signRo.setPrivateKey("799d6055c68c8f91de3ee7d852c13844");
        signRo.setParams(map);
        System.out.println(FlashcallSignUtils.sign(signRo));
        
        
//        SignRo signRo2 = new SignRo();
//        Map<String, Object> map2 = new HashMap<>();
//        //map2.put("signType","MD5");
//        map2.put("timestamp","134214124132");
//        map2.put("nonce","abcdef");
//        map2.put("clientId","c8b4f569e0d5a7a57fc6240541827443");
//        signRo2.setPrivateKey("799d6055c68c8f91de3ee7d852c13844");
//        signRo2.setParams(map2);
//        
//        signRo2.setSignature("1158C47E91CB3C570C5E85FE3559CB54");
//
//        
//        System.out.println(Md5Utils.verify(signRo2));
//        
        
    }

}
