/* 
 * HttpRequestProxy.java 
 * 
 * Created on November 3, 2008, 9:53 AM 
 */

package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * 
 * @author 
 * 
 *    
 */
public class HttpRequestProxy {
	// 超时间隔
	private static int connectTimeOut = 60000;
	private static boolean alwaysClose = false;
	// 返回数据编码格式
	private String encoding = "UTF-8";
	
	private static HttpClient client = null;
//	private final HttpClient client = new HttpClient(
//			new SimpleHttpConnectionManager(alwaysClose));
	 static {
		if (client==null) 
		{
			client =  new HttpClient(//可以用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包
			new SimpleHttpConnectionManager());
		}
	}
	public HttpClient getHttpClient() {
		client.getParams().setParameter("http.socket.timeout",connectTimeOut);
		client.getParams().setParameter("http.connection.timeout",connectTimeOut);
		return client;
	}

	/**
	 * 用法： HttpRequestProxy hrp = new HttpRequestProxy();
	 * hrp.doRequest("http://www.163.com",null,null,"gbk");
	 * 
	 * @param url
	 *            请求的资源ＵＲＬ
	 * @param postData
	 *            POST请求时form表单封装的数据 没有时传null
	 * @param header
	 *            request请求时附带的头信息(header) 没有时传null
	 * @param encoding
	 *            response返回的信息编码格式 没有时传null
	 * @return response返回的文本数据
	 * @throws IOException
	 * @throws CustomException
	 */
	public String doRequest(String url, Map<String, Object> postData, Map<String, String> header,
			String encoding) throws IOException {
		String responseString = null;
		// 头部请求信息
		Header[] headers = null;
		if (header != null) {
			Set<Entry<String, String>> entrySet = header.entrySet();
			int dataLength = entrySet.size();
			headers = new Header[dataLength];
			int i = 0;
			for (Iterator<Entry<String, String>> itor = entrySet.iterator(); itor.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) itor.next();
				headers[i++] = new Header(entry.getKey().toString(), entry
						.getValue().toString());
			}
		}
		// post方式
		if (postData != null) {
			UTF8PostMethod postRequest = new UTF8PostMethod(url.trim());
			if (headers != null) {
				for (int i = 0; i < headers.length; i++) {
					postRequest.setRequestHeader(headers[i]);
				}
			}
			Set<Entry<String, Object>> entrySet = postData.entrySet();
			int dataLength = entrySet.size();
			NameValuePair[] params = null;
			boolean flag = true;
			int count = dataLength;
			int k = 0;
			for (Iterator<Entry<String, Object>> itor = entrySet.iterator(); itor.hasNext();) 
			{
				
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) itor.next();
				if (entry.getValue().getClass().isArray()) 
				{
					count+=((String[]) entry.getValue()).length - 1;
					flag = false;
				}
			}
			if (flag) 
			{
				params = new NameValuePair[dataLength];
			}else {
				params = new NameValuePair[count];
			}
			//List<NameValuePair> params = new ArrayList<>();
			int i = 0;
			List<String[]> nameValues = new ArrayList<String[]>();//name重复的value值
			List<String> nameKeys = new ArrayList<String>();//name重复的key值
			for (Iterator<Entry<String, Object>> itor = entrySet.iterator(); itor.hasNext();) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) itor.next();
				//处理value是数组的情况
				if (entry.getValue().getClass().isArray()) 
				{
					nameKeys.add(entry.getKey());  
					nameValues.add((String[]) entry.getValue());
					continue;
				}
				params[i++] = (new NameValuePair(entry.getKey().toString(),
						entry.getValue().toString())) ;
			}
			
			if (!nameValues.isEmpty()) 
			{
				for (int j = 0; j < nameValues.size(); j++) 
				{
					String nameTemp = nameKeys.get(j);
					String[] valueTemp = nameValues.get(j);
					//params[i++] = (new NameValuePair(nameKey, nameValues[j]));
					for (int j2 = 0; j2 < valueTemp.length; j2++) {
						params[i++] = (new NameValuePair(nameTemp, valueTemp[j2]));
					}
				}
			}
			//List<NameValuePair> temps = new ArrayList<>();
			
//			postRequest.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
			
			postRequest.setRequestBody(params);
			try {
				responseString = this.executeMethod(postRequest, encoding);
			} finally {
				postRequest.releaseConnection();
			}
		}
		// get方式
		if (postData == null) {
			GetMethod getRequest = new GetMethod(url.trim());
			if (headers != null) {
				for (int i = 0; i < headers.length; i++) {
					getRequest.setRequestHeader(headers[i]);
				}
			}
			try {
				responseString = this.executeMethod(getRequest, encoding);
			} finally {
				getRequest.releaseConnection();
			}
		}

		return responseString;
	}

	private String executeMethod(HttpMethod request, String encoding)
			throws IOException {
		String responseContent = null;
		InputStream responseStream = null;
		BufferedReader rd = null;
		try {
			this.getHttpClient().executeMethod(request);
			if (encoding != null) {
				responseStream = request.getResponseBodyAsStream();
				rd = new BufferedReader(new InputStreamReader(responseStream,
						encoding));
				String tempLine = rd.readLine();
				StringBuffer tempStr = new StringBuffer();
				String crlf = System.getProperty("line.separator");
				while (tempLine != null) {
					tempStr.append(tempLine);
					tempStr.append(crlf);
					tempLine = rd.readLine();
				}
				responseContent = tempStr.toString();
			} else
				responseContent = request.getResponseBodyAsString();

			Header locationHeader = request.getResponseHeader("location");
			// 返回代码为302,301时，表示页面己经重定向，则重新请求location的url，这在
			// 一些登录授权取cookie时很重要
			if (locationHeader != null) {
				String redirectUrl = locationHeader.getValue();
				Map<String, String> header = new HashMap<String, String>();
				header.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:36.0) Gecko/20100101 Firefox/36.0");
				header.put("Connection", "keep-alive");
				 return this.doRequest(redirectUrl, null, header, null);
			}
		} finally {
			if (rd != null)
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (responseStream != null)
				try {
					responseStream.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
		}
		return responseContent;
	}

	/**
	 * 特殊请求数据,这样的请求往往会出现redirect本身而出现递归死循环重定向 所以单独写成一个请求方法
	 * 比如现在请求的url为：http://localhost:8080/demo/index.jsp 返回代码为302
	 * 头部信息中location值为:http://localhost:8083/demo/index.jsp
	 * 这时httpclient认为进入递归死循环重定向，抛出CircularRedirectException异常
	 * 
	 * @param url
	 * @return
	 * @throws CustomException
	 */
	public String doSpecialRequest(String url, int count, String encoding) {
		String str = null;
		InputStream responseStream = null;
		BufferedReader rd = null;
		GetMethod getRequest = new GetMethod(url);
		// 关闭httpclient自动重定向动能
		getRequest.setFollowRedirects(false);
		try {

			this.client.executeMethod(getRequest);
			Header header = getRequest.getResponseHeader("location");
			if (header != null) {
				// 请求重定向后的ＵＲＬ，count同时加1
				this.doSpecialRequest(header.getValue(), count + 1, encoding);
			}
			// 这里用count作为标志位，当count为0时才返回请求的ＵＲＬ文本,
			// 这样就可以忽略所有的递归重定向时返回文本流操作，提高性能
			if (count == 0) {
				getRequest = new GetMethod(url);
				getRequest.setFollowRedirects(false);
				this.client.executeMethod(getRequest);
				responseStream = getRequest.getResponseBodyAsStream();
				rd = new BufferedReader(new InputStreamReader(responseStream,
						encoding));
				String tempLine = rd.readLine();
				StringBuffer tempStr = new StringBuffer();
				String crlf = System.getProperty("line.separator");
				while (tempLine != null) {
					tempStr.append(tempLine);
					tempStr.append(crlf);
					tempLine = rd.readLine();
				}
				str = tempStr.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getRequest.releaseConnection();
			if (rd != null)
				try {
					rd.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (responseStream != null)
				try {
					responseStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return str;
	}

	private static class UTF8PostMethod extends PostMethod {
		public UTF8PostMethod(String url) {
		super(url);
		}
		
		@Override
		public String getRequestCharSet() {
		//return super.getRequestCharSet();
		return "UTF-8";
		} 
	}
	

	public static void main(String[] args) throws Exception {
//		HttpRequestProxy hrp = new HttpRequestProxy();
		Map<String, String> header = new HashMap<String, String>();
		HttpRequestProxy hrp = new HttpRequestProxy();
		String str=hrp.doRequest("http://192.168.0.151:8088/jenkins/label/146/api/json?pretty=true", null, header, "utf-8");
		JSONObject json=JSONObject.fromObject(str);
		String value=json.get("busyExecutors").toString();
		System.out.println(value);
		// System.out.println(str);
	}
	
}