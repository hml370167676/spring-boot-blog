/**
 * <p>Title: HttpClientUtil.java</p>
 * @Package com.uusoft.fundbase.util
 * <p>Description:通过httpClient请求查询投资人银行卡信息</p>
 * <p>Company:上海投投金融信息服务有限公司</p>
 * @author 廖明华
 * @since 2015年8月31日 上午10:33:45
 * @version V1.0
 */
package com.hml.blog.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Description:通过httpClient请求查询投资人银行卡卡bin信息</p>
 * <p>Company:上海投投金融有限责任公司</p>
 * @author 廖明华
 * @version V1.0
 */
public class HttpClientUtil
{
  /**
   * 默认最大连接数
   */
  private static final int DEFAULT_MAX_TOTAL = 80;

  /**
   * 默认单个路由最大的连接线程数量
   */
  private static final int DEFAULT_MAX_ROUTE_TOTAL = 15;

  /**
   * 默认连接超时（3000ms）
   */
  private static final int DEFAULT_CONNECT_MAX_TIMEOUT = 3000;

  /**
   * 默认等待读取数据超时（2000s）
   */
  private static final int DEFAULT_READ_MAX_TIMEOUT = 2000;

  /**
   * 默认从连接池中获取连接超时（2000ms）
   */
  private static final int DEFAULT_GET_CON_MAX_TIMEOUT = 2000;

  /**
   * 字符编码
   */
  private static final String CHARSET = "UTF-8";

  /**
   * 日志
   */
  private static Logger log=LoggerFactory.getLogger(HttpClientUtil.class);

  /**
   * Http线程池
   */
  private static PoolingHttpClientConnectionManager connManager = null;

  /**
   * 请求参数配置
   */
  private static RequestConfig requestConfig;
  {
    // 创建httpclient连接池
    connManager = new PoolingHttpClientConnectionManager();
    connManager.setMaxTotal(DEFAULT_MAX_TOTAL); // 设置连接池线程最大数量
    connManager.setDefaultMaxPerRoute(DEFAULT_MAX_ROUTE_TOTAL); // 设置单个路由最大的连接线程数量

    RequestConfig.Builder configBuilder = RequestConfig.custom();
    // 设置连接超时
    configBuilder.setConnectTimeout(DEFAULT_CONNECT_MAX_TIMEOUT);
    // 设置读取超时
    configBuilder.setSocketTimeout(DEFAULT_READ_MAX_TIMEOUT);
    // 设置从连接池获取连接实例的超时
    configBuilder.setConnectionRequestTimeout(DEFAULT_GET_CON_MAX_TIMEOUT);
    requestConfig = configBuilder.build();
  }

  /**
   * <p>Description:httpClient post请求</p>
   * @param url url地址
   * @param params 参数
   * @return 请求对象的字符串
   * @author 廖明华
   * @date 2015年8月31日 下午4:50:25
   */
  public static String post(String url, Map<String, String> params)
  {
    log.info("HTTP POST URL:{}",url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String httpStr = null;
    HttpPost httpPost = new HttpPost(url);
    CloseableHttpResponse response = null;
    try
    {
      httpPost.setConfig(requestConfig);
      List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
      for (Map.Entry<String, String> entry : params.entrySet())
      {
        NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
            .getValue());
        pairList.add(pair);
      }
      httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(CHARSET)));
      response = httpClient.execute(httpPost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode != HttpStatus.SC_OK)
      {
        log.error("调用Http请求返回状态[{}]------------------请求失败[1]--------", statusCode);
        return null;
      }
      HttpEntity entity = response.getEntity();
      if (entity == null)
      {
        log.error("调用Http请求返回对象为空------------------请求失败[2]--------");
        return null;
      }
      httpStr = EntityUtils.toString(entity, "utf-8");
    }
    catch (Exception e)
    {
      log.error("调用Http请求异常---------请求失败[3]------异常信息[{}]--", e);
    }
    finally
    {
      if (response != null)
      {
        try
        {
          EntityUtils.consume(response.getEntity());
        }
        catch (IOException e)
        {
          log.error("调用Http请求关闭连接异常---------请求失败[4]------异常信息[{}]--", e);
        }
      }
    }
    return httpStr;
  }

  public static String postXml(String url, String xml)
  {
    log.info("HTTP POST URL:{}",url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String httpStr = null;
    HttpPost httpPost = new HttpPost(url);
    CloseableHttpResponse response = null;
    try
    {
      httpPost.setConfig(requestConfig);
      httpPost.setEntity(new StringEntity(xml, "utf-8"));
      httpPost.setHeader("Accept","application/xml;charset=GBK");
      httpPost.setHeader("Content-Type","application/xml");
      response = httpClient.execute(httpPost);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode != HttpStatus.SC_OK)
      {
        log.error("调用Http请求返回状态[{}]------------------请求失败[1]--------", statusCode);
        return null;
      }
      HttpEntity entity = response.getEntity();
      if (entity == null)
      {
        log.error("调用Http请求返回对象为空------------------请求失败[2]--------");
        return null;
      }
      httpStr = EntityUtils.toString(entity, "utf-8");
    }
    catch (Exception e)
    {
      log.error("调用Http请求异常---------请求失败[3]------异常信息[{}]--", e);
    }
    finally
    {
      if (response != null)
      {
        try
        {
          EntityUtils.consume(response.getEntity());
        }
        catch (IOException e)
        {
          log.error("调用Http请求关闭连接异常---------请求失败[4]------异常信息[{}]--", e);
        }
      }
    }
    return httpStr;
  }

  public static String get(String url){
    log.info("HTTP GET URL:{}",url);
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String httpStr = null;
    HttpGet httpGet=new HttpGet(url);
    httpGet.setConfig(requestConfig);
    try {
      CloseableHttpResponse response=httpClient.execute(httpGet);
      int statusCode = response.getStatusLine().getStatusCode();
      if (statusCode != HttpStatus.SC_OK)
      {
        log.error("调用Http请求返回状态[{}]------------------请求失败[1]--------", statusCode);
        return null;
      }
      HttpEntity entity = response.getEntity();
      if (entity == null)
      {
        log.error("调用Http请求返回对象为空------------------请求失败[2]--------");
        return null;
      }
      httpStr = EntityUtils.toString(entity, "utf-8");
    } catch (Exception e) {
      log.error("get请求失败,url={}",url,e);
    }
    return httpStr;
  }
  public static void main(String[] args)
  {
    //
    String reqXml="<Request><instId>FC0000001</instId><isIndividual>0</isIndividual></Request>";
    String postRes=HttpClientUtil.postXml("http://127.0.0.1:8080/top-web/open.api", reqXml);
    System.out.println(postRes);
  }
}
