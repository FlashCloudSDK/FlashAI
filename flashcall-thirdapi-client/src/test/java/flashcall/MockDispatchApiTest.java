package flashcall;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.flashcall.thirdclient.model.SignRo;
import com.flashcall.thirdclient.util.FlashcallSignUtils;
import com.flashcall.thirdclient.util.HttpUtils;
import com.flashcall.thirdclient.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class MockDispatchApiTest {

    private String host = "https://mock.flashai.ai/api";
//    private String host = "http://127.0.0.1:18000";
//    private String host = "http://127.0.0.1:18019";

    private String privateKey = "2fd5574a026e1da1a7735b6843e11991";

    private String clientId = "S1BpIyaTZl";

    private void getSign(Map<String, Object> m) {
        SignRo signRo = new SignRo();
        signRo.setPrivateKey(privateKey);
        signRo.setParams(m);
        m.put("signType", "MD5");
        m.put("sign", FlashcallSignUtils.sign(signRo));
    }

    public void chack(String s) {
        JSONObject jsonObject = JSONObject.parseObject(s);
        assert "0".equals(jsonObject.getString("code")) && jsonObject.getBoolean("success");
    }

    @Test
    public void verifyCode() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("countryCode", "MEX");
        m.put("mobileNumber", "18335618326");
        m.put("notifyUrl", "www.xxx");
        m.put("number", "9999");
        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/verifyCode";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/verifyCode";
//        String url = "http://127.0.0.1:18019/thirdApi/verifyCode";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void creditCheck() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("callDate", "20200805");
        m.put("callHour", "9,10,11,12,13,14,15,16,17,18,19");
        m.put("countryCode", "MEX");
        m.put("mobileNumber", "18335618326");
        m.put("notifyUrl", "www.xxx");
        m.put("amount", "1000");
        m.put("name", "ala");
        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("date", "18/06/2020");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/creditCheck";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/creditCheck";
//        String url = "http://127.0.0.1:18019/thirdApi/creditCheck";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void preCollection() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("callDate", "20200805");
        m.put("callHour", "9,10,11,12,13,14,15,16,17,18,19");
        m.put("countryCode", "MEX");
        m.put("mobileNumber", "18335618326");
        m.put("notifyUrl", "www.xxx");
        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("name", "ala");
        m.put("amount", "1000");
        m.put("date", "18/06/2022");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/preCollection";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/preCollection";
//        String url = "http://127.0.0.1:18019/thirdApi/preCollection";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void preCollectionRobot() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("callDate", "20200805");
        m.put("callHour", "9,10,11,12,13,14,15,16,17,18,19");
        m.put("countryCode", "MEX");
        m.put("mobileNumber", "18335618326");
        m.put("notifyUrl", "www.xxx");
        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("name", "ala");
        m.put("amount", "1000");
        m.put("date", "18/06/2022");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/preCollectionRobot";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/preCollectionRobot";
//        String url = "http://127.0.0.1:18019/thirdApi/preCollectionRobot";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void batchVerifyCode() throws Exception {
        List<JSONObject> mobileList = new ArrayList<>();
//        MobileVerifyCodeInfo mobileVerifyCodeInfo = new MobileVerifyCodeInfo();
//        mobileVerifyCodeInfo.setNumber("999");
//        mobileVerifyCodeInfo.setMobileNumber("18335618326");
//        mobileVerifyCodeInfo.setPlatform("flashcall");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "999");
        jsonObject.put("mobileNumber", "18335618326");
        jsonObject.put("platform", "flashcall");
        mobileList.add(jsonObject);
        JSONArray objects = new JSONArray();
        objects.add(jsonObject);

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("mobileList", objects.toString());
        m.put("clientId", clientId);
        m.put("countryCode", "MEX");
        m.put("notifyUrl", "www.xxx");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        StringBuffer sign = new StringBuffer();
        m.forEach((k, v) -> {
//            String cs = JSONObject.toJSONString(v);
//            if (cs.indexOf("\"") == 0 && cs.lastIndexOf("\"") == cs.length() -1) {
//                cs = cs.substring(1, cs.length() - 1);
//            }
            String cs = v.toString();
            if (!k.equalsIgnoreCase("sign") && v != null && StringUtils.isNotBlank(cs)) {
                sign.append(k).append("=").append(cs).append("&");
            }
        });
        log.info(sign.toString());
        m.put("signType", "MD5");
        m.put("sign", MD5Util.toMD5(sign.substring(0, sign.length() - 1) + "2fd5574a026e1da1a7735b6843e11991"));

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/batchVerifyCode";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/batchVerifyCode";
//        String url = "http://127.0.0.1:18019/thirdApi/batchVerifyCode";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void batchCreditCheck() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "999");
        jsonObject.put("mobileNumber", "18335618326");
        jsonObject.put("platform", "flashcall");
        jsonObject.put("amount", "1000");
        jsonObject.put("date", "18/06/2022");
        JSONArray objects = new JSONArray();
        objects.add(jsonObject);

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("callDate", "20200805");
        m.put("callHour", "9,10,11,12,13,14,15,16,17,18,19");
        m.put("countryCode", "MEX");
        m.put("mobileList", objects.toString());
        m.put("notifyUrl", "www.xxx");
//        m.put("amount", "1000");
//        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
//        m.put("date", "18/06/2022");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/batchCreditCheck";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/batchCreditCheck";
//        String url = "http://127.0.0.1:18019/thirdApi/batchCreditCheck";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void phoneCheck() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", "999");
        jsonObject.put("mobileNumber", "18335618326");
        jsonObject.put("platform", "flashcall");
        jsonObject.put("amount", "1000");
        jsonObject.put("date", "18/06/2022");
        JSONArray objects = new JSONArray();
        objects.add(jsonObject);

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("callDate", "20200805");
        m.put("callHour", "9,10,11,12,13,14,15,16,17,18,19");
        m.put("countryCode", "MEX");
        m.put("mobileList", objects.toString());
        m.put("notifyUrl", "www.xxx");
//        m.put("amount", "1000");
//        m.put("platform", "flashcall");
        m.put("timestamp", System.currentTimeMillis()+"");
//        m.put("date", "18/06/2022");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/phoneCheck";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/phoneCheck";
//        String url = "http://127.0.0.1:18019/thirdApi/phoneCheck";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void addPlan() throws Exception {
        String str = "{\"api\":true,\"batchName\":\"123\",\"botenceId\":\"20210903543515406419463681_en\",\"callDate\":\"20220623\",\"callHour\":\"9,10,11,12,13,14,15,16,17,18,19\",\"clear\":0,\"lineIds\":\"5\",\"mobileList\":\"18335618326^number-platform^9 9 9-flashcall|\",\"notifyUrl\":\"www.xxx\",\"orgCode\":\"1.44.\",\"orgId\":7,\"recall\":0,\"userId\":187}";
        Map<String, Object> m = new TreeMap<>();
        m = JSONObject.parseObject(str, TreeMap.class);
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        StringBuffer sign = new StringBuffer();
        m.forEach((k, v) -> {
//            String cs = JSONObject.toJSONString(v);
//            if (cs.indexOf("\"") == 0 && cs.lastIndexOf("\"") == cs.length() -1) {
//                cs = cs.substring(1, cs.length() - 1);
//            }
            String cs = v.toString();
            if (!k.equalsIgnoreCase("sign") && !k.equalsIgnoreCase("signType")
                    && v != null && StringUtils.isNotBlank(cs)) {
                sign.append(k).append("=").append(cs).append("&");
            }
        });
        log.info(sign.toString());
        m.put("signType", "MD5");
        m.put("sign", MD5Util.toMD5(sign.substring(0, sign.length() - 1) + "2fd5574a026e1da1a7735b6843e11991"));

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/addPlan";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/addPlan";
//        String url = "http://127.0.0.1:18019/thirdApi/addPlan";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void getBatchPlanCallSummary() throws Exception {

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/getBatchPlanCallSummary";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/getBatchPlanCallSummary";
//        String url = "http://127.0.0.1:18019/thirdApi/getBatchPlanCallSummary";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void getBatchSummaryGroupByLine() throws Exception {

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/getBatchSummaryGroupByLine";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/getBatchSummaryGroupByLine";
//        String url = "http://127.0.0.1:18019/thirdApi/getBatchSummaryGroupByLine";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void getBatchPlanList() throws Exception {

        Map<String, Object> m = new TreeMap<>();
        m.put("batchName", "123");
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));
        m.put("page", "1");
        m.put("pageNum", "10");
        m.put("phoneStatus", "1");

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/getBatchPlanList";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/getBatchPlanList";
//        String url = "http://127.0.0.1:18019/thirdApi/getBatchPlanList";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }
}
