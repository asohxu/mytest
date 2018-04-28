package spider.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupTest {
			
	public static void main(String[] args) {
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p class='test'>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse("F://hello.html");
		System.out.println(">>>>"+doc.getElementsByClass("title"));
	}
	
	public static void analysisHtml(String html){
		Document doc = Jsoup.parse(html);
		//爬国家区域码
		Elements li = doc.getElementsByAttribute("TBODY");
		boolean flag = false;
		for (int i = 0; i < li.size(); i++) {
			System.out.println(li.get(i).text());
		}
		//分析的是58同城
		//Elements links1 = doc.getElementsByClass("t1");
		//Elements links2 = doc.getElementsByClass("t2");
		//Elements links3 = doc.getElementsByClass("t3");
		//Elements links4 = doc.getElementsByClass("t4");
		//Elements links5 = doc.getElementsByClass("t5");
     //
     //
		//for (int i = 0; i < links1.size(); i++) {
		//
		//	System.out.println(links1.get(i).text() + " "
		//	+ links2.get(i).text() + " "
		//			+ links3.get(i).text() + " "
		//	+ links4.get(i).text() + " "
		//			+ links5.get(i).text());
		//}
		
	}
}
