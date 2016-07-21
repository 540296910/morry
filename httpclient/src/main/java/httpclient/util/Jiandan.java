package httpclient.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jiandan {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpClientUtil hcu = new HttpClientUtil();
		
		for(int i = 2060;i>0;i--){
			String url = "http://jandan.net/ooxx/page-"+i+"#comments";
			for(String img : getImgUrl(hcu.getClient(null,url,null))) {
				ImageDownloader.download(img, "F:\\http\\download\\jandan\\");
			}
		}
	}
	
	public static List<String> getImgUrl(String html){
		List<String> urls = new ArrayList<String>(); 
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.select("img");
		for (Element e:imgs) {
			urls.add(e.attr("src"));
		}
		return urls;
	}

}