package com.liuxch.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Http发送请求类
 * 
 * @author landingbj
 * 
 */
public class HttpUtils {

	public static String sendHttpRequest(String urlPath, String cookieName,
			String cookieValue) throws Exception {
		String result = "";
		StringBuffer params = new StringBuffer();
		params.append("cookieName=");
		params.append(URLEncoder.encode(cookieName, "UTF-8"));
		params.append("&");
		params.append("cookieValue=");
		params.append(URLEncoder.encode(cookieValue, "UTF-8"));
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");

		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setRequestProperty("Connection", "Keep-Alive");// 保持长连接
		conn.setRequestProperty("Charset", "UTF-8");

		conn.connect();
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(params.toString());

		dos.flush();
		dos.close();

		int rescode = conn.getResponseCode();

		StringBuffer sb = new StringBuffer();
		if (HttpURLConnection.HTTP_OK == rescode) {
			
			String line = new String();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
			System.out.println("response is" + sb.toString());
			br.close();
		}

		return sb.toString();
	}

}
