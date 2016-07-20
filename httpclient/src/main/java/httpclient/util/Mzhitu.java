package httpclient.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Mzhitu {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpClientUtil hcu = new HttpClientUtil();
		String name="69132";
		int size = 50;
		String url = "http://www.mzitu.com/"+name;
		for(int i = 1;i<=size;i++){
			if(i>1){
			url = url+"/"+i;
			}
			for(String img : getImgUrl(hcu.getClient(null,url,null))) {
				ImageDownloader.download(img, "F:\\http\\download\\mzitu\\"+name);
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
