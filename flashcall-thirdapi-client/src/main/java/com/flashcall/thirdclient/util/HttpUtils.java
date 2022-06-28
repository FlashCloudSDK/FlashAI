package com.flashcall.thirdclient.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

@Slf4j
public class HttpUtils {

    private static final Integer timeOut = 30;

    private static Boolean isProxy = false;

    private static OkHttpClient okHttpNoProxyClient = new OkHttpClient();

    public static void setIsProxy(Boolean isProxy) {
        isProxy = isProxy;
    }

    public static Boolean getIsProxy() {
        return isProxy;
    }

    public static OkHttpClient getOkHttpProxyClient() {
//        if (getIsProxy()) {
//            return okHttpProxyClient;
//        }
        return okHttpNoProxyClient;
    }

    public static String postRequest(String url, Object data) throws Exception {
        return postJsonForData(url, null, data);
    }

    public static String getRequest(String url) throws Exception {
        return getForData(url, null);
    }

    public static String postJsonForData(String url, Headers headers, Object data) throws Exception {
        Response response = postJsonForResponse(url, headers, data);
        int code = response.code();
        if (code != 200) {
            log.error("Response code: {}, url: {}, param: {}", code, url, JSON.toJSONString(data));
        }
        return response.body().string();
    }

    public static String getForData(String url, Headers headers) throws Exception {
        Response response = getForResponse(url, headers);
        int code = response.code();
        if (code != 200) {
            log.error("Response code: {}, url: {}", code, url);
        }
        return response.body().string();
    }

    public static Response postJsonForResponse(String url, Headers headers, Object data) throws Exception {
        String text;
        if (data instanceof String) {
            text = (String) data;
        } else {
            text = JSON.toJSONString(data);
        }
        OkHttpClient okHttpClient = getOkHttpProxyClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), text);
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        builder.url(url);
        builder.post(body);
        builder.build();
        Call call = okHttpClient.newCall(builder.build());
        return call.execute();
    }

    public static Response getForResponse(String url, Headers headers) throws Exception {
        OkHttpClient okHttpClient = getOkHttpProxyClient();
        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        builder.url(url);
        builder.get();
        builder.build();
        Call call = okHttpClient.newCall(builder.build());
        return call.execute();
    }

    public static String postDataForData(String url, Headers headers, byte[] data) throws Exception {
        Response response = postDataForResponse(url, headers, data);
        int code = response.code();
        if (code != 200) {
            log.error("Response code: {}, url: {}, param: {}", code, url, JSON.toJSONString(data));
        }
        return response.body().string();
    }

    public static Response postDataForResponse(String url, Headers headers, byte[] data) throws Exception {
        OkHttpClient okHttpClient = getOkHttpProxyClient();

        RequestBody body = RequestBody.create(null, data);

        Request.Builder builder = new Request.Builder();
        if (headers != null) {
            builder.headers(headers);
        }
        builder.url(url);
        builder.post(body);
        builder.build();
        Call call = okHttpClient.newCall(builder.build());
        return call.execute();
    }

//    public static String postFormForData(String url, Headers headers, Map<String, String> form) throws Exception {
//        Response response = postFormForResponse(url, headers, form);
//        int code = response.code();
//        if (code != 200) {
//            log.error("Response code: {}, url: {}, param: {}", code, url, JSON.toJSONString(form));
//        }
//        return response.body().string();
//    }
//
//    public static Response postFormForResponse(String url, Headers headers, Map<String, String> form) throws Exception {
//        OkHttpClient okHttpClient = getOkHttpProxyClient();
//
//        FormBody.Builder bodyBuilder = new FormBody.Builder();
//        for (Map.Entry<String, String> entry : form.entrySet()) {
//            bodyBuilder.add(entry.getKey(), entry.getValue());
//        }
//        FormBody body = bodyBuilder.build();
//
//        Request.Builder builder = new Request.Builder();
//        if (headers != null) {
//            builder.headers(headers);
//        }
//        builder.url(url);
//        builder.post(body);
//        builder.build();
//        Call call = okHttpClient.newCall(builder.build());
//        return call.execute();
//    }
}