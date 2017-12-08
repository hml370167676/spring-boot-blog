package learn.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/3 10:37
 */
public class HttpClientLearn {

  public void ssl(String pathname, String url) {
    CloseableHttpClient httpClient = null;
    try {
      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      FileInputStream inputStream = new FileInputStream(new File(pathname));
      try {
        //加载keyStore（密钥）
        trustStore.load(inputStream, "123456".toCharArray());
      } catch (IOException e) {
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch (CertificateException e) {
        e.printStackTrace();
      } finally {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      //相信自己的CA和所以自己签名的证书
      SSLContext sslContext = SSLContexts.custom()
          .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
      //只允许使用TLSv1协议
      SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
          new String[]{"TLSv1"}, null,
          SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
      httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
      //创建HTTP请求（GET方式）
      HttpGet httpGet = new HttpGet(url);
      System.out.println("executing request" + httpGet.getRequestLine());
      CloseableHttpResponse response = httpClient.execute(httpGet);
      try {
        HttpEntity entity = response.getEntity();
        System.out.println("-----------------------------------------------");
        System.out.println(response.getStatusLine());
        if (entity != null) {
          System.out.println("Response content length" + entity.getContentLength());
          System.out.println(EntityUtils.toString(entity));
          EntityUtils.consume(entity);
        }
      } finally {
        response.close();
      }
    } catch (KeyStoreException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (KeyManagementException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (httpClient != null) {
        try {
          httpClient.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }


  public void postForm(String url){
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(url);

  }

  public void getRequest(String url) throws IOException, URISyntaxException {
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet(url);
    CloseableHttpResponse response = httpclient.execute(httpget);

    URI uri = new URIBuilder()
        .setScheme("http")
        .setHost("www.google.com")
        .setPath("/search")
        .setParameter("q", "httpclient")
        .setParameter("btnG", "Google Search")
        .setParameter("aq", "f")
        .setParameter("oq", "")
        .build();
    HttpResponse response1 = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

  }

}
