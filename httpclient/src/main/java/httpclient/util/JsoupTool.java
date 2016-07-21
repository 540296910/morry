package httpclient.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClientUtil hcu = new HttpClientUtil();
		for(int i = 2;i<50;i++){
			String url = "https://meizi.us/?page="+i;
			System.err.println(url);
			try {
	//			System.out.println(getImgUrl(hcu.getHttpsClient(url)));
				for(String img : getImgUrl(hcu.getHttpsClient(url))) {
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
		}
	}
	
	public static List<String> getImgUrl(String html){
		List<String> urls = new ArrayList<String>(); 
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.select("img");
		for (Element e:imgs) {
			urls.add(e.attr("src").replace("_small", ""));
		}
		return urls;
	}
}
