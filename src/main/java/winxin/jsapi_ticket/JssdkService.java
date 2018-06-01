package winxin.jsapi_ticket;

/**
 * @author huang_kangjie
 * @create 2018-05-18 15:41
 **/
public class JssdkService {

     private final String appid = "公共账号id";
     private final String secret = "公共账号secret";

     private String getJsapiTicket() {
          return "";
     }
     //     String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?";
     //     String params = "grant_type=client_credential&appid=" + appid + "&secret=" + secret + "";
     //     String result = HttpRequestUtils.httpGet(requestUrl + params);
     //     Map<String, Object> map = js
     //     String access_token = JSONObject.parseObject(result).getString("access_token");
     //     requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
     //     params = "access_token=" + access_token + "&type=jsapi";
     //     result = HttpRequestUtils.httpGet(requestUrl + params);
     //     String jsapi_ticket = JSONObject.parseObject(result).getString("ticket");
     //     int activeTime = Integer.parseInt(JSONObject.parseObject(result).getString("expires_in"));
     //     Jssdk jssdk = new Jssdk();
     //     jssdk.setActiveTime(activeTime - 600);
     //     jssdk.setJsapiTicket(jsapi_ticket);
     //     jssdkDao.update(jssdk);
     //     return jsapi_ticket;
     //}
     //
     //private Jssdk getSign(String url, String jsapi_ticket) {
     //     String noncestr = getNonceStr();
     //     String timestamp = getTimeStamp();
     //     String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "×tamp=" + timestamp + "&url=" + url;
     //     sign = Sha1Util.getSha1(sign);
     //     Jssdk jssdk = new Jssdk();
     //     jssdk.setAppid(appid);
     //     jssdk.setNoncestr(noncestr);
     //     jssdk.setTimestamp(timestamp);
     //     jssdk.setSign(sign);
     //     return jssdk;
     //}
}
