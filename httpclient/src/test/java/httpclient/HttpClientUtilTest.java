package httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.BasicCookieStore;

import httpclient.util.HttpClientUtil;

public class HttpClientUtilTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientUtil hcu = new HttpClientUtil();
		BasicCookieStore bcs = new BasicCookieStore();
		String url = "http://weibo.com/?topnav=1&mod=logo";
		Map<String, String> param = new HashMap<String, String>();
		getWeibo(hcu, bcs, url, param);
	}
	
	public static void getWeibo(HttpClientUtil hcu,BasicCookieStore bcs,String url,Map<String, String> param){
		try {
			hcu.getClient(bcs, url, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
