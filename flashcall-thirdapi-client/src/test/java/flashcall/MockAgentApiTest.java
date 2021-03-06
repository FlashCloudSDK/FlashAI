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
public class MockAgentApiTest {

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
    public void queryAgentGroup() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/queryAgentGroup";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/queryAgentGroup";
//        String url = "http://127.0.0.1:18019/thirdApi/queryAgentGroup";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void addAgentPlan() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));
        m.put("batchName", "123ha");
        m.put("agentGroupId", "test?????????2");
        m.put("addType", "add&clean");
        m.put("callType", "1");
        m.put("agentId", "testzx");
        m.put("mobileList", "18016491920^ID-AMOUNT-DATE^91-3000-20201216|18016491921^ID-AMOUNT-DATE^91-3000-20201216");
        m.put("notifyUrl", "http://xxxx:8011/notify");
        m.put("timestamp", System.currentTimeMillis()+"");

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/addAgentPlan";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/addAgentPlan";
//        String url = "http://127.0.0.1:18019/thirdApi/addAgentPlan";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void cancelAgentPlan() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));
        m.put("batchName", "123ha");
        m.put("mobileList", "18016491920");
        m.put("timestamp", System.currentTimeMillis()+"");

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/cancelAgentPlan";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/cancelAgentPlan";
//        String url = "http://127.0.0.1:18019/thirdApi/cancelAgentPlan";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }
}
