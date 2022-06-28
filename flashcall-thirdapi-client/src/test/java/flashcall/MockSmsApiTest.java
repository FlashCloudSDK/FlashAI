package flashcall;

import com.alibaba.fastjson.JSONObject;
import com.flashcall.thirdclient.model.SignRo;
import com.flashcall.thirdclient.util.FlashcallSignUtils;
import com.flashcall.thirdclient.util.HttpUtils;
import com.flashcall.thirdclient.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class MockSmsApiTest {

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
        assert "0".equals(jsonObject.getString("code")) && "SUCCESS".equals(jsonObject.getString("msg"));
    }

    @Test
    public void querySmsConfig() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/sms/querySmsConfig";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/sms/querySmsConfig";
//        String url = "http://127.0.0.1:18019/thirdApi/sms/querySmsConfig";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void addPlanSms() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("configId", "4");
        m.put("smsPhoneDetail", "[{\"paramVal\":{\"amount\":\"100\",\"platform\":\"flashcall\",\"URL\":\"xxx.com\"},\"phone\":\"15211517575\"},{\"paramVal\":{\"amount\":\"200\",\"platform\":\"flashcall\",\"URL\":\"xxx2.com\"},\"phone\":\"15211517576\"}]");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/sms/addPlanSms";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/sms/addPlanSms";
//        String url = "http://127.0.0.1:18019/thirdApi/sms/addPlanSms";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void querySmsTemplate() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/sms/querySmsTemplate";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/sms/querySmsTemplate";
//        String url = "http://127.0.0.1:18019/thirdApi/sms/querySmsTemplate";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

}
