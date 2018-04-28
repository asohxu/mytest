package spider;

import common.StringUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import spider.jsoup.JsoupTest;

import java.io.FileWriter;
import java.io.IOException;


public class SpiderTest1 {
		private static HttpClient httpClient = new HttpClient();
		static GetMethod getmethod;
		public static boolean downloadPage(String path) throws HttpException,
		IOException {
			getmethod = new GetMethod(path);
			//获得响应状态码
			int statusCode = httpClient.executeMethod(getmethod);
			if(statusCode == HttpStatus.SC_OK){
			//写入本地文件
			FileWriter fwrite = new FileWriter("F://hello.html");
			String pageString = getmethod.getResponseBodyAsString();
//			String result = pageString;
			String result = "";
			if(StringUtil.isNotEmpty(pageString)){
				result = new String(pageString.getBytes("ISO-8859-1"), "gbk");
				result = result.substring(result.indexOf("<TBODY>"), result.lastIndexOf("</TBODY>") + 8);
				result = result.replace("\n", "");
				result = result.replace("\r", "");
				JsoupTest.analysisHtml(result);
			}
			
			
			getmethod.releaseConnection();
			fwrite.write(result,0,result.length());
			fwrite.flush();
			//关闭文件
			fwrite.close();
			//释放资源
			return true;
		}
			return false;
		}
		
		/**
		* 测试代码
		*/
		public static void main(String[] args) {
		// 抓取制指定网页，并将其输出
		try {
//		Scanner in = new Scanner(System.in);
//		System.out.println("Input the URL of the page you want to get:");
//		String path = in.next();
//		System.out.println("program start!");
//		SpiderTest1.downloadPage("http://baidu.lecai.com/lottery/draw/sorts/cqssc.php?phase=20161013050&agentId=5591");
//		SpiderTest1.downloadPage("http://search.51job.com/list/060000,000000,0000,00,9,99,java%25E5%25B7%25A5%25E7%25A8%258B%25E5%25B8%2588,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");
		//国家区域码
		SpiderTest1.downloadPage("http://www.yadexp.com/yadexp/209/d.htm");

		
		System.out.println("Program end!");
		} catch (HttpException e) {
		e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
	}
