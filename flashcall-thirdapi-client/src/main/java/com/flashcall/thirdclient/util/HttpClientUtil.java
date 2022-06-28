package com.flashcall.thirdclient.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.codec.binary.Base64;   
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** 
* @ClassName: HttpClientUtil 
* @Description: Http工具类
* @author: weiyunbo
* @date 2018年8月29日 下午4:04:15 
* @version V1.0  
*/
public class HttpClientUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	
	private static CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }
	
	/**
	 * GET - 拼url方式
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doGet(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			logger.error("调用接口异常！",e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				logger.error("关闭连接异常！",e);
			}
		}
		return resultString;
	}
 
	/**
	 * POST-拼URL方式
	 * @param url
	 * @param param
	 * @return
	 */
	public static String doPost(String url, Map<String, String> param) {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			logger.error("调用接口异常！",e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				logger.error("关闭连接异常！",e);
			}
		}
 
		return resultString;
	}
 
	public static String doPost(String url) {
		return doPost(url, null);
	}
	
	/**
	 * POST-JSON方式
	 * @param url
	 * @param json
	 * @return
	 */
	
	
	public static String doPostJson(String url, String json) {
		System.out.println("请求URL ： " + url);
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建请求内容
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);
			// 执行http请求
			response = httpClient.execute(httpPost);
			logger.info("调用接口url！"+url+"request:"+json);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (Exception e) {
			logger.error("调用接口异常！",e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				logger.error("关闭连接异常！",e);
			}
		}
		return resultString;
	}
	
	
	/**
	 * POST JSON请求，可以设置header
	 * @param url
	 * @param jsonString
	 * @param headerMap
	 * @return
	 */
	public static String doPostJsonHeader(String url, String jsonString,Map<String,String> headerMap) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            if(headerMap != null && !headerMap.isEmpty()) {
    			Iterator headerIterator = headerMap.entrySet().iterator();          //循环增加header
    			while(headerIterator.hasNext()){  
    			    Entry<String,String> elem = (Entry<String, String>) headerIterator.next();  
    			    httpPost.addHeader(elem.getKey(), elem.getValue()); //设置header
    			}
    		}
            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
	
	
	
	
	/**
	 * POST-以流的方式提交
	 * form表单方式提交
	 * 比如某些场景需要在url后挂大批量数据，使用url会报request超长，可以使用流的方式进行处理
	 * @param sendUrl
	 * @param backEncodType
	 * @return
	 */
	public String doPostStream(String sendUrl, String sendParam,String backEncodType) {
		StringBuffer receive = new StringBuffer();
		BufferedWriter wr = null;
		try {
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}
			URL url = new URL(sendUrl);
			HttpURLConnection URLConn = (HttpURLConnection) url.openConnection();
			URLConn.setDoOutput(true);
			URLConn.setDoInput(true);
			((HttpURLConnection) URLConn).setRequestMethod("POST");
			URLConn.setUseCaches(false);
			URLConn.setAllowUserInteraction(true);
			HttpURLConnection.setFollowRedirects(true);
			URLConn.setInstanceFollowRedirects(true);

			URLConn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded;charset=UTF-8");
			URLConn.setRequestProperty("Content-Length", String
					.valueOf(sendParam.getBytes().length));
			DataOutputStream dos = new DataOutputStream(URLConn.getOutputStream());
			dos.writeBytes(sendParam);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					URLConn.getInputStream(), backEncodType));
			String line;
			while ((line = rd.readLine()) != null) {
				receive.append(line).append("\r\n");
			}
			rd.close();
		} catch (java.io.IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				wr = null;
			}
		}
		return receive.toString();
	}

	/**
	 * GET-以流的方式提交
	 * 比如某些场景需要在url后挂大批量数据，使用url会报request超长，可以使用流的方式进行处理
	 * @param sendUrl
	 * @param backEncodType
	 * @return
	 */
	public String doGetStrem(String sendUrl, String backEncodType) {
		StringBuffer receive = new StringBuffer();
		BufferedReader in = null;
		try {
			if (backEncodType == null || backEncodType.equals("")) {
				backEncodType = "UTF-8";
			}
			URL url = new URL(sendUrl);
			HttpURLConnection URLConn = (HttpURLConnection) url
					.openConnection();
			URLConn.setDoInput(true);
			URLConn.setDoOutput(true);
			URLConn.connect();
			URLConn.getOutputStream().flush();
			in = new BufferedReader(new InputStreamReader(URLConn.getInputStream(), backEncodType));
			String line;
			while ((line = in.readLine()) != null) {
				receive.append(line).append("\r\n");
			}
		} catch (IOException e) {
			receive.append("访问产生了异常-->").append(e.getMessage());
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (java.io.IOException ex) {
					ex.printStackTrace();
				}
				in = null;
			}
		}
		return receive.toString();
	}
	
	
	public static String sendMessageHttp(String url ,String username ,String password, Integer port, String mobiles, String smscontent) {  
		String SEND_SMS_URL = "http://"+url+"/API/SendSMS";
		String SEND_SMS_USER = username;
		String SEND_SMS_PASSWD = password;
	    int respCode=0;    
	    StringBuilder result = new StringBuilder();   
	    HttpURLConnection connection = null;   
	    try {   
	        URL postUrl = new URL(SEND_SMS_URL);   
	        // 打开连接   
	        connection = (HttpURLConnection) postUrl.openConnection();  
	        connection.setDoOutput(true);   
	        connection.setDoInput(true);   
	        connection.setRequestMethod("POST");   
	        connection.setUseCaches(false);   
	        connection.setInstanceFollowRedirects(true);   
	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	        //增加一个认证字段。   
	        System.out.println("Authorization: "+Base64.encodeBase64String(new String(SEND_SMS_USER+":"+SEND_SMS_PASSWD).getBytes("utf-8")));
	        connection.setRequestProperty("Authorization", "Basic " +Base64.encodeBase64String(new String(SEND_SMS_USER+":"+SEND_SMS_PASSWD).getBytes("utf-8")));   
	        connection.connect();   
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());   
	        StringBuffer sendMessage = new StringBuffer();   
	        //POST请求动作  
	        sendMessage.append("{\"event\":\"txsms\",\"num\":\""+mobiles+"\",\"port\":\""+port+"\",\"encoding\":\"8\",\"smsinfo\":\""+smscontent+"\"}");       
	        out.write(sendMessage.toString().getBytes("UTF-8"));  
	        out.flush();   
	        out.close();   
	        respCode=connection.getResponseCode();   
	        // 设置编码,否则中文乱码  
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));   
	        String lines;   
	        while ((lines = reader.readLine()) != null) {   
	           result.append(lines);   
	        }   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    } finally {   
	        if (connection != null) {   
	            connection.disconnect();   
	        }   
	    }   
	    System.out.println("result.toString(): " + result.toString());
	    return result.toString();   
}  
	

}
