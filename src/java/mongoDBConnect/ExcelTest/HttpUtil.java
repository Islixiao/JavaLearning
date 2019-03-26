package mongoDBConnect.ExcelTest;

/***Created by moyongzhuo
 *On 2017/10/12  ***14:07.
 ******/

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * User: leo.yan
 * Date: 13-12-19
 * Time: 下午2:53
 */
public class HttpUtil {

    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public static String prefix = "http://";

    public static String domain(String url) {
        StringBuilder stringBuffer = new StringBuilder();
        url = url.replace(prefix, "");
        url = url.substring(0, url.indexOf("/"));
        stringBuffer.append(url);
        return stringBuffer.toString();
    }

    public static boolean isDomain(String url) {
        return !url.contains(prefix) && "".equals(domain(url));
    }


    public static String httpGetUrl(String url, Map<String, String> map) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        Set<String> keys = map.keySet();
        for (String name : keys) {
            Object value = map.get(name);
            if (value != null) {
                BasicNameValuePair nameValuePair = new BasicNameValuePair(name, value.toString());
                params.add(nameValuePair);
            }
        }
        String queryString = URLEncodedUtils.format(params, "UTF-8");
        return url + "?" + queryString;
    }

    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";

    public static void main(String[] args) {

    }

    public static String httpGet(String url, Map parameters) {
        String response = null;
        int statusCode = 500;
        String _url = url;
        try {
            _url = httpGetUrl(url, parameters);
            HttpGet get = new HttpGet(_url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            log.info("get url: " + url);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            log.info("请求url失败: " + url + " code:" + statusCode + ",_url=" + _url, e);
        }
        return response;
    }

    public static String httpGet(String url) {
        String response = null;
        int statusCode = 500;
        try {
            HttpGet get = new HttpGet(url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + url);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode);
            log.info(e.getMessage());
        }
        return response;
    }

    public static String httpGetByCharset(String url, String charset) {
        String response = null;
        int statusCode = 500;
        try {
            HttpGet get = new HttpGet(url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + url);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, charset);
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode, e);
            log.info(e.getMessage());
        }
        return response;
    }

    public static String httpGetByCharsetGzip(String url, String charset) {
        String response = null;
        int statusCode = 500;
        try {
            HttpGet get = new HttpGet(url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            Header[] headers = httpResponse.getHeaders("Content-Encoding");
            if (headers.length > 0 && ((BufferedHeader) headers[0]).getBuffer().toString().toLowerCase().contains("gzip")) {
                httpResponse.setEntity(new GzipDecompressingEntity(httpResponse.getEntity()));
            }
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + url);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, charset);
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode);
        }
        return response;
    }

    public static String httpPost(String url, Map parameters) {
        String response = null;
        int statusCode = 500;
        try {
            HttpPost post = new HttpPost(url);
            post.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            post.setEntity(httpPostParameters(parameters));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(post);
//            log.info("get url: " + url + JSON.toJSONString(parameters));
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + statusCode + "  " + url + JSON.toJSONString(parameters));
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode + "paramters=" + parameters, e);
        }
        return response;
    }


    public static String httpPost(String url, String xml, Map<String, String> headerMap) {
        int statusCode;
        String response = null;
        HttpPost post = new HttpPost(url);
        for (Map.Entry<String, String> header : headerMap.entrySet()) {
            post.addHeader(header.getKey(), header.getValue());
        }
        try {
            StringEntity xmlEntity = new StringEntity(xml, "UTF-8");
            post.addHeader("Content-Type", "text/xml;charset=UTF-8");
            post.setEntity(xmlEntity);
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(post);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String httpPostJson(String url, Map parameters) {
        String response = null;
        int statusCode = 500;
        try {
            String _url = httpGetUrl(url, parameters);
            HttpPost post = new HttpPost(_url);
            post.addHeader("Content-Type", "application/json;charset=UTF-8");
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(post);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + statusCode + "  " + url + JSON.toJSONString(parameters));
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode + "paramters=" + parameters, e);
        }
        return response;
    }

    public static String httpPostJson(String url, String json) {
        int statusCode;
        String response = null;
        HttpPost post = new HttpPost(url);
        try {
            StringEntity en = new StringEntity(json, "UTF-8");
            en.setContentType("text/json");
            en.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            post.addHeader("Content-Type", "application/json;charset=UTF-8");
//            post.addHeader("date", new Date().toString());
//            post.addHeader("server", "openresty");
//            post.addHeader("transfer-encoding","chunked");
            post.setEntity(en);
//log.info(post.toString());
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(post);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            } else {
                HttpEntity entity = httpResponse.getEntity();
                String responseError = EntityUtils.toString(entity, "UTF-8");
                log.info("response error info:" + responseError);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException"+e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
//        log.info("response:" + response);
        return response;
    }

    public static String httpGetJson(String url, Map parameters) {
        String response = null;
        int statusCode = 500;
        String _url = url;
        try {
            _url = httpGetUrl(url, parameters);
            HttpGet get = new HttpGet(_url);
            get.addHeader("Content-Type", "application/json;charset=UTF-8");
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            log.info("get url: " + url);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            log.info("请求url失败: " + url + " code:" + statusCode + ",_url=" + _url, e);
        }
        return response;
    }

    private static UrlEncodedFormEntity httpPostParameters(Map<String, String> map) {
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        Set<String> keys = map.keySet();
        for (String name : keys) {
            String value = map.get(name);
            BasicNameValuePair nameValuePair = new BasicNameValuePair(name, value);
            params.add(nameValuePair);
        }
        return new UrlEncodedFormEntity(params, Charset.forName("utf-8"));
    }
/***
    public static String httpsGet(String url) {
        String response = null;
        int statusCode = 500;
        try {
            HttpGet get = new HttpGet(url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpsClient.newHttpsClient();
            HttpResponse httpResponse = http.execute(get);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            log.info("get url: " + url);
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            log.info("请求url失败: " + url + " code:" + statusCode);
            log.info(e.getMessage());
        }
        return response;
    }
 ***/
}

