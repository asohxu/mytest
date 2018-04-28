package spider.ajax;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AjaxTest {

	
	public static void main(String[] args){
		String url = "http://baidu.lecai.com/lottery/draw/sorts/ajax_get_draw_data.php?lottery_type=200&date=2016-11-02";
		CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
        	
            HttpGet httpget = new HttpGet(url);  
            httpget.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            httpget.setHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Host", "baidu.lecai.com");
            httpget.setHeader("Referer", "http://baidu.lecai.com/lottery/draw/sorts/cqssc.php?phase=20161013050&agentId=5591");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.63 Safari/537.36");
            httpget.setHeader("X-Requested-With", "XMLHttpRequest");
            
            CloseableHttpResponse httpResponse = httpclient.execute(httpget);  
            if (httpResponse != null) {
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					try {
						BufferedReader bufferedReader = new BufferedReader(
						new InputStreamReader(httpEntity.getContent(),"UTF-8"), 8 * 1024);
						StringBuilder entityStringBuilder = new StringBuilder();
						String line = null;
						while ((line = bufferedReader.readLine()) != null) {
							entityStringBuilder.append(line + "/n");
						}
						// 利用从HttpEntity中得到的String生成JsonObject
//						JSONObject obj = new JSONObject(entityStringBuilder.toString());
						JSONObject obj = JSONObject.fromObject(entityStringBuilder.toString());
						System.out.println(">>>>>>>>>"+obj.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                httpclient.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        } 
	}

}

