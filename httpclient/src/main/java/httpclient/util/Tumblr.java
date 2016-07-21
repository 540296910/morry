package httpclient.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

public class Tumblr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientUtil hcu = new HttpClientUtil();
//		for(int i = 2;i<50;i++){
			String url = "http://wanimal1983.org/";
			System.err.println(url);
			try {
	//			System.out.println(getImgUrl(hcu.getHttpsClient(url)));
				for(String img : JsoupTool.getImgUrl(hcu.getHttpsClient(url))) {
					System.out.print(img);
					ImageDownloader.download(img, "F:\\http\\download\\img");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
	
	

}
