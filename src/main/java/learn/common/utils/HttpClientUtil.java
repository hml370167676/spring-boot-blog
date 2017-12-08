package learn.common.utils;

import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/23 17:06
 */
public class HttpClientUtil {

  private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
  private static PoolingHttpClientConnectionManager connectionManager = null;

  public static String post(String url, Map<String, String> params) {
    logger.info("HTTP POST URL : {}", url);
    long beginTime = System.currentTimeMillis();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);
    CloseableHttpResponse response = null;
    return url;
  }

}
