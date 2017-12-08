package com.hml.blog.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * <p>Description:</p>
 *
 * @author minglu@toutoujinrong.com
 * @date 2017/11/1 17:05
 */
public class HttpClientUtil {



  public HttpClientUtil() throws IOException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet("");
    CloseableHttpResponse response = httpClient.execute(httpGet);
    try{
      try {
        URI uri = new URIBuilder()
            .setScheme("http")
            .setHost("www.google.com")
            .setPath("/search")
            .setParameter("q", "httpclient")
            .setParameter("btnG", "Google Search")
            .setParameter("aq", "f")
            .setParameter("oq", "")
            .build();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }

    }finally{
      response.close();
    }

  }
}
