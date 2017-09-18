package httpclient.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;


@SuppressWarnings("deprecation")
public class HttpClientUtil {

	public String postClient(BasicCookieStore cookieStore, String url,
			Map<String, String> param) {
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		try {
			RequestBuilder rb = RequestBuilder.post();
			rb.addHeader("charset", "UTF-8");
			rb.addHeader("Content-Type","application/x-www-form-urlencoded");
			rb.setUri(new URI(url));
			if (param!=null&&!param.isEmpty()) {
				Iterator<String> iter = param.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					rb.addParameter(key, param.get(key)).setCharset(CharsetUtils.get("UTF-8"));
				}
			}
			HttpHost hh = new HttpHost("127.0.0.1", 1080, "http");
			 RequestConfig config = RequestConfig.custom().setProxy(hh).build();  
			
			HttpUriRequest post = rb.setConfig(config).build();
			CloseableHttpResponse response2 = httpclient.execute(post);
			try {
				
				HttpEntity entity = response2.getEntity();
				System.out.println("contentType = " +entity.getContentType());
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "UTF-8"), 8 * 1024);
				StringBuilder entityStringBuilder = new StringBuilder();
				String line = null;
				try {
					while ((line = br.readLine()) != null) {
						entityStringBuilder.append(line + "/n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(entityStringBuilder.toString());
				List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (UnsupportedOperationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {
				try {
					response2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String postClientWithCookie(Cookie info, String url,
			Map<String, String> param) {
		BasicCookieStore cookie = new BasicCookieStore();
		cookie.addCookie(info);
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookie).build();
		try {
			// HttpUriRequest login = RequestBuilder
			// .post()
			// .setUri(new URI(
			// "http://localhost:8080/scales/submit.htm"))
			// .addParameter("username", "tgpms")
			// .addParameter("password", "tgpmssmpgt").build();
			RequestBuilder rb = RequestBuilder.post();
			rb.addHeader("charset", "UTF-8");
			rb.addHeader("Content-Type","application/x-www-form-urlencoded");
			rb.setUri(new URI(url));
			if (param!=null&&!param.isEmpty()) {
				Iterator<String> iter = param.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					rb.addParameter(key, param.get(key)).setCharset(CharsetUtils.get("UTF-8"));
				}
			}

			HttpUriRequest post = rb.build();
			CloseableHttpResponse response2 = httpclient.execute(post);
			try {
				HttpEntity entity = response2.getEntity();
				System.out.println("contentType = " +entity.getContentType());
				// System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));  
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "UTF-8"), 8 * 1024);
				StringBuilder entityStringBuilder = new StringBuilder();
				String line = null;
				try {
					while ((line = br.readLine()) != null) {
						entityStringBuilder.append(line + "/n");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(entityStringBuilder.toString());
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (UnsupportedOperationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} finally {
				try {
					response2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	 /**
     * 创建SSL安全连接
     *
     * @return
     */
    @SuppressWarnings("deprecation")
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            @SuppressWarnings("deprecation")
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				public boolean isTrusted(
						java.security.cert.X509Certificate[] chain,
						String authType)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					return true;
				}
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }

				public void verify(String host,
						java.security.cert.X509Certificate cert)
						throws SSLException {
					// TODO Auto-generated method stub
					
				}
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

	
	public String getHttpsClient(String url) throws UnsupportedEncodingException, UnsupportedOperationException, IOException, URISyntaxException{
		String html = null;
		 CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).build();  
		 try {
				RequestBuilder rb = RequestBuilder.get();
			//	rb.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				/*rb.addHeader("Accept-Encoding","gzip, deflate, sdch");
				rb.addHeader("Accept-Language","zh-CN,zh;q=0.8");
				rb.addHeader("Cache-Control","no-cache");
				rb.addHeader("Connection","keep-alive");
				rb.addHeader("Host","my.oschina.net");
				
				rb.addHeader("Pragma","no-cache");
				rb.addHeader("Upgrade-Insecure-Requests","1");*/
//				rb.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
				rb.addHeader("User-Agent","User-Agent	Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
				rb.addHeader("charset", "UTF-8");
				rb.addHeader("Connection","Keep-Alive");
				rb.addHeader("Content-Type","text/html, application/xhtml+xml, */*");
				rb.setUri(new URI(url));
				// HttpGet httpget = new HttpGet(
				// "http://localhost:8080/scales/scales/1.htm");
				HttpUriRequest get = rb.build();
				CloseableHttpResponse response1 = httpclient.execute(get);
				try {
					HttpEntity entity = response1.getEntity();
					BufferedReader br = new BufferedReader(new InputStreamReader(
							entity.getContent(), "UTF-8"), 8 * 1024);
					StringBuilder entityStringBuilder = new StringBuilder();
					String line = null;
					while ((line = br.readLine()) != null) {
						entityStringBuilder.append(line + "/n");
					}
					System.out.println(entityStringBuilder.length());
					System.err.println(entityStringBuilder.toString());
					html = entityStringBuilder.toString();
					EntityUtils.consume(entity);

				} finally {
					response1.close();
				}
			} finally {
				httpclient.close();
			}
		 return html;
	}
	
	public String getClient(BasicCookieStore cookieStore, String url,
			Map<String, String> param) throws Exception {
		String html = null;
		CloseableHttpClient httpclient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();
		try {
			RequestBuilder rb = RequestBuilder.get();
		//	rb.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			/*rb.addHeader("Accept-Encoding","gzip, deflate, sdch");
			rb.addHeader("Accept-Language","zh-CN,zh;q=0.8");
			rb.addHeader("Cache-Control","no-cache");
			rb.addHeader("Connection","keep-alive");
			rb.addHeader("Host","my.oschina.net");
			
			rb.addHeader("Pragma","no-cache");
			rb.addHeader("Upgrade-Insecure-Requests","1");*/
//			rb.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
			rb.addHeader("User-Agent","User-Agent	Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)");
			rb.addHeader("charset", "UTF-8");
			rb.addHeader("Connection","Keep-Alive");
			rb.addHeader("Content-Type","text/html, application/xhtml+xml, */*");
			rb.setUri(new URI(url));
			if (param!=null&&!param.isEmpty()) {
				Iterator<String> iter = param.keySet().iterator();
				while (iter.hasNext()) {
					String key = iter.next();
					rb.addParameter(key, param.get(key)).setCharset(CharsetUtils.get("UTF-8"));
				}
			}
			// HttpGet httpget = new HttpGet(
			// "http://localhost:8080/scales/scales/1.htm");
			HttpUriRequest get = rb.build();
			CloseableHttpResponse response1 = httpclient.execute(get);
			try {
				HttpEntity entity = response1.getEntity();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						entity.getContent(), "UTF-8"), 8 * 1024);
				StringBuilder entityStringBuilder = new StringBuilder();
				String line = null;
				while ((line = br.readLine()) != null) {
					entityStringBuilder.append(line + "/n");
				}
				System.out.println(entityStringBuilder.length());
				System.err.println(entityStringBuilder.toString());
				html = entityStringBuilder.toString();
			/*	List<Cookie> cookies = cookieStore.getCookies();
				if (cookies.isEmpty()) {
					System.out.println("None");
				} else {
					for (int i = 0; i < cookies.size(); i++) {
						System.out.println("- " + cookies.get(i).toString());
					}
				}*/
				EntityUtils.consume(entity);

			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
		return html;
	}
	
	public static void downloadImg(String url, String path) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		RequestBuilder rb = RequestBuilder.get();
		rb.addHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
		try {
			rb.setUri(new URI(url));
			HttpUriRequest get = rb.build();
			CloseableHttpResponse response = httpClient.execute(get);
			try {
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getStatusCode() >= 400) {
					throw new IOException("Got bad response, error code = "
							+ response.getStatusLine().getStatusCode()
							+ " imageUrl: " + url);
				}
				if (entity != null) {
					InputStream input = entity.getContent();
					if(path == null||"".equals(path)){
						path = "d:\\";
					}
					File file = new File(path);
					if(!file.exists()){
						file.mkdirs();
					}
					OutputStream output = new FileOutputStream(new File(
							path + "\\" + getImgName(url)));
					IOUtils.copy(input, output);
					output.flush();
				}
				EntityUtils.consume(entity);

			} finally {
				response.close();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static String getImgName(String url){
		return url.substring(url.lastIndexOf("/")+1,url.length());
	}
	
	public static void downloadimg(String url){
		 System.out.println("获取Bing图片地址中……");

	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(url);
	        CloseableHttpResponse response = null;
	        try {
	            response = httpClient.execute(httpGet);
	            Pattern p = Pattern.compile("https://.*?\\.jpg");
	            Matcher m = p.matcher(EntityUtils.toString(response.getEntity()));
	            String address = null;
	            if (m.find()) {
	                address = m.group();
	            } else {
	                System.exit(0);
	            }
	            System.out.println("图片地址:" + address);
	            System.out.println("正在下载……");
	            HttpGet getImage = new HttpGet(address);
	            CloseableHttpResponse responseImg = httpClient.execute(getImage);
	            HttpEntity entity = responseImg.getEntity();

	            writeImgEntityToFile(entity,dateFormat.format(new Date()) + ".jpg");

	            System.out.println(dateFormat.format(new Date()) + ".jpg"+"下载完毕.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                httpClient.close();
	                response.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	}
	 public static void writeImgEntityToFile(HttpEntity imgEntity,String fileAddress) {
	        File storeFile = new File(fileAddress);
	        FileOutputStream output = null;
	        try {
	            output = new FileOutputStream(storeFile);

	            if (imgEntity != null) {
	                InputStream instream;
	                instream = imgEntity.getContent();
	                byte b[] = new byte[8 * 1024];
	                int count;
	                while ((count = instream.read(b)) != -1) {
	                    output.write(b, 0, count);
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                output.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	
	public static void main(String[] args) throws Exception {
//		downloadImg("http://ww4.sinaimg.cn/mw690/9e5389bbjw1f5zop4pzc6j20c80gddg123.jpg", "d:\\http\\download\\img");
//		System.out.println(getImgName("http://ww4.sinaimg.cn/mw690/9e5389bbjw1f5zop4pzc6j20c80gddgv.jpg"));
		HttpClientUtil hcu = new HttpClientUtil();
		String url = "https://www.tumblr.com/oauth/access_token";
		Map<String,String> param = new HashMap<String, String>();
	/*	param.put("x_auth_username", "maolei1990@gmail.com");
		param.put("x_auth_password", "maolei1990");
		param.put("x_auth_mode", "client_auth");*/
		param.put("api_key", "fuiKNFp9vQFvjLNvx4sUwti4Yb5yGutBN4Xh10LXZhhRKjWlV4");
		hcu.postClient(new BasicCookieStore(), url, param);
	}
}
