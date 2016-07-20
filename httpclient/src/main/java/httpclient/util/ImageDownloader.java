package httpclient.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class ImageDownloader {
	public static void download(String url, String path) {
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
				long size = 10000l;
				if (entity != null&&entity.getContentLength()>size) {
					
					System.out.println(entity.getContentLength()+" : "+url);
					InputStream input = entity.getContent();
					if (path == null || "".equals(path)) {
						path = "d:\\";
					}
					File file = new File(path);
					if (!file.exists()) {
						file.mkdirs();
					}
					OutputStream output = new FileOutputStream(new File(path
							+ "\\" + getImgName(url)));
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

	public static String getImgName(String url) {
		return url.substring(url.lastIndexOf("/") + 1, url.length());
	}
}
