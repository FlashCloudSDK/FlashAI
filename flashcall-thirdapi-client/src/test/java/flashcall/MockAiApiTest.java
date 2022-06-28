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
public class MockAiApiTest {

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
    public void queryQCModel() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/ai/queryQCModel";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/ai/queryQCModel";
//        String url = "http://127.0.0.1:18019/thirdApi/ai/queryQCModel";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void queryQCPlan() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/ai/queryQCPlan";
////        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/ai/queryQCPlan";
//        String url = "http://127.0.0.1:18019/thirdApi/ai/queryQCPlan";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void queryQCPlanDetail() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("batchName", "test");
        m.put("fileIdList", "fileId1|fileId2");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/ai/queryQCPlanDetail";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/ai/queryQCPlanDetail";
//        String url = "http://127.0.0.1:18019/thirdApi/ai/queryQCPlanDetail";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

    @Test
    public void addQCPlan() throws Exception {
        Map<String, Object> m = new TreeMap<>();
        m.put("clientId", clientId);
        m.put("batchName", "test");
        m.put("modelId", "1");
        m.put("fileObj", "[{\"fileUrl\":\"https://test/test1.wav\",\"fileId\":\"abcd1\"},{\"fileUrl\":\"https://test/test2.wav\",\"fileId\":\"abcd2\"}]");
        m.put("notifyUrl", "https://test/notify");
        m.put("notifyBatchUrl", "https://test/batchNotify");
        m.put("timestamp", System.currentTimeMillis()+"");
        m.put("nonce", MD5Util.toMD5(System.currentTimeMillis()+"").substring(0, 6));

        getSign(m);

        log.info(JSONObject.toJSONString(m));

        String url = host + "/v1/thirdApi/mock/ai/addQCPlan";
//        String url = "http://127.0.0.1:18000/v1/thirdApi/mock/ai/addQCPlan";
//        String url = "http://127.0.0.1:18019/thirdApi/ai/addQCPlan";
        String s = HttpUtils.postJsonForData(url, null, m);
        log.info(s);

        chack(s);
    }

}
