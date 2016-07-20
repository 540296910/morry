package httpclient.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file =  new File("D:\\zhihu.html");
		Document doc = null;
		try {
			doc = Jsoup.parse(file, "UTF-8", "http://example.com/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Elements links = doc.select("a[href]"); // a with href
		Elements pngs = doc.select("img");
		for (Element e:pngs) {
			System.out.println(e.attr("src"));
		}
		  // img with src ending .png

		Element masthead = doc.select("div.masthead").first();
		  // div with class=masthead

		Elements resultLinks = doc.select("h3.r > a");
	}
	
	public List<String> getImgUrl(String html){
		List<String> urls = new ArrayList<String>(); 
		Document doc = Jsoup.parse(html);
		Elements imgs = doc.select("img");
		for (Element e:imgs) {
			urls.add(imgs.attr("src"));
		}
		return urls;
	}
}
