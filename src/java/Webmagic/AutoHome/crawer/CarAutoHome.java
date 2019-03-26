package Webmagic.AutoHome.crawer;

/***Created by moyongzhuo
 *On 2018/4/26  ***16:29.
 ******/


import Webmagic.AutoHome.DaAutoHome;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import mongoDBConnect.ExcelTest.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/***Created by mo
 *On 2018/4/23  ***10:04.
 ******/
public class CarAutoHome implements PageProcessor {
    private static int myid = 0;
    int size = 0;
    // 抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(5).setSleepTime(1000).setTimeOut(100000).setCharset("gbk")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    Calendar now = Calendar.getInstance();
    String time = new SimpleDateFormat("HHmm").format(now.getTime());

    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        DaAutoHome daAnimation = new DaAutoHome();
        Html html = page.getHtml();
        myid++;
        size++;

    }

    @Test//获取品牌，车系等
    public void brand_model(){
        try {
            long startTime, endTime;
            System.out.println("开始爬取...");
            startTime = System.currentTimeMillis();
            String https = "https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=1";
            Spider.create(new Webmagic.AutoHome.CarAutoHome()).addUrl(https).thread(5).run();
            endTime = System.currentTimeMillis();
            System.out.println("\t"+ (endTime-startTime) +"\t");
            Thread.sleep(10000);

        } catch (Exception e) { }
    }



    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36";

    public static String httpGet(String url) throws Exception{
        String response = null;
        int statusCode = 500;
        String _url = url;
        try {
            _url = url;
            HttpGet get = new HttpGet(_url);
            get.setHeader(new BasicHeader(HTTP.USER_AGENT, USER_AGENT));
            RequestConfig config = RequestConfig.DEFAULT;
            HttpClient http = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
            HttpResponse httpResponse = http.execute(get);
            System.out.println("get url: " + url);
            statusCode = httpResponse.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = httpResponse.getEntity();
                response = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            System.out.println("请求url失败: " +"\t"+ url);
        }
        return response;
    }

    @Test//获取品牌，id等
    public void brand_model_1() {
        try {
            List<String> ddt = new ArrayList<>();
            String words = httpGet("https://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=1");
            JSONObject jsonObject = JSONObject.parseObject(words);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            String array= jsonObject1.getString("branditems");
            JSONArray jsonArray = JSONArray.parseArray(array);
            Object[] strs = jsonArray.toArray(); //json转为数组。
            for(Object s:strs) {
                JSONObject json = JSONObject.parseObject(s.toString());
                String bfirstletter = json.getString("bfirstletter");
                String name = json.getString("name");
                String id = json.getString("id");
                String one = name + "\t\t" + id + "\t\t" + bfirstletter;
                ddt.add(one);
                System.out.println(one);
            }
            File file = new File("car_brand_model_20180426.txt");
            FileUtils.writeLines(file, ddt, true);

//            System.out.println(words);
        }catch(Exception e){
            e.printStackTrace();
        }


        }

    @Test//获取品牌，id等
    public void get_series() throws Exception{
        List<String> ddt = new ArrayList<>();

        try {
            File file = new File("car_brand_model_20180426.txt");
            List<String> brand_id = FileUtils.readLines(file);
            int jj = 0;
            for(String brand_id_fil: brand_id) {
                jj++;
                String[] bif = brand_id_fil.split("\t\t");
                String url = "http://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=3&value=" + bif[1];
                System.out.println(bif[0]+"\t\t"+bif[1]+"\t\t"+jj);
                String words = httpGet(url);
                JSONObject jsonObject = JSONObject.parseObject(words);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                String array = jsonObject1.getString("factoryitems");
                JSONArray jsonArray = JSONArray.parseArray(array);
                Object[] strs = jsonArray.toArray(); //json转为数组。
                //if(true){//strs.length > 1) {
                    for (Object s : strs) {
                        JSONObject json = JSONObject.parseObject(s.toString());
                        String json_001 = json.getString("id");
                        String json_002 = json.getString("name");

                        String json_003 = json.getString("seriesitems");
                        JSONArray jsonArray_00 = JSONArray.parseArray(json_003);
                        Object[] json_strs = jsonArray_00.toArray(); //json转为数组。
                        for (Object json_s : json_strs) {
                            JSONObject json_st = JSONObject.parseObject(json_s.toString());
                            String bfirstletter_00 = json_st.getString("id");
                            String name_00 = json_st.getString("name");
                            String id_00 = json_st.getString("seriesstate");
                            String one = bif[1]+"\t\t"+bif[0]+"\t\t"+json_001 + "\t\t" + json_002 + "\t\t" + bfirstletter_00 + "\t\t" + name_00 + "\t\t" + id_00;
                            ddt.add(one);
                        }
                    }
                }
//                if(strs.length == 1){
//                    JSONObject json_st = JSONObject.parseObject(strs.toString());
//                    String bfirstletter_00 = json_st.getString("id");
//                    String name_00 = json_st.getString("name");
//                    String id_00 = json_st.getString("seriesitems");
//
//                    for (Object json_s : id_00) {
//                        JSONObject json_st = JSONObject.parseObject(json_s.toString());
//                        String bfirstletter_00 = json_st.getString("id");
//                        String name_00 = json_st.getString("name");
//                        String id_00 = json_st.getString("seriesstate");
//                        String one = bfirstletter_00 + "\t\t" + name_00 + "\t\t" + id_00;
//                        ddt.add(one);
//                    }
//                }






           // }
            File file1 = new File("car_22_brand_series_20180426.txt");
            FileUtils.writeLines(file1, ddt, true);

//            System.out.println(words);
        }catch(Exception e){
            e.printStackTrace();
        }


    }







    @Test//获取品牌，id等
    public void get_car_model() throws Exception{
        List<String> ddt = new ArrayList<>();

        try {
            File file = new File("car_brand_model_20180426.txt");
            List<String> brand_id = FileUtils.readLines(file);
            int jj = 0;
            for(String brand_id_fil: brand_id) {
                jj++;
                String[] bif = brand_id_fil.split("\t\t");
                String url = "http://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=3&value=" + bif[1];
                System.out.println(bif[0]+"\t\t"+bif[1]+"\t\t"+jj);
                String words = httpGet(url);
                JSONObject jsonObject = JSONObject.parseObject(words);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                String array = jsonObject1.getString("factoryitems");
                JSONArray jsonArray = JSONArray.parseArray(array);
                Object[] strs = jsonArray.toArray(); //json转为数组。
                //if(true){//strs.length > 1) {
                for (Object s : strs) {
                    JSONObject json = JSONObject.parseObject(s.toString());
                    String json_001 = json.getString("id");
                    String json_002 = json.getString("name");

                    String json_003 = json.getString("seriesitems");
                    JSONArray jsonArray_00 = JSONArray.parseArray(json_003);
                    Object[] json_strs = jsonArray_00.toArray(); //json转为数组。
                    for (Object json_s : json_strs) {
                        JSONObject json_st = JSONObject.parseObject(json_s.toString());
                        String bfirstletter_00 = json_st.getString("id");
                        String name_00 = json_st.getString("name");
                        String id_00 = json_st.getString("seriesstate");
                        String one = bif[1]+"\t\t"+bif[0]+"\t\t"+json_001 + "\t\t" + json_002 + "\t\t" + bfirstletter_00 + "\t\t" + name_00 + "\t\t" + id_00;
                        ddt.add(one);
                    }
                }
            }
            File file1 = new File("car_22_brand_series_20180426.txt");
            FileUtils.writeLines(file1, ddt, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }








    @Test//获取品牌,型号,id等
    public void get_car_series() throws Exception{
        List<String> ddt = new ArrayList<>();

        try {
            File file = new File("car_22_brand_series_20180426.txt");
            List<String> brand_id = FileUtils.readLines(file);
            int jj = 0;
            for(String brand_id_fil: brand_id) {
                jj++;
                String[] bif = brand_id_fil.split("\t\t");
                String url = "http://www.autohome.com.cn/ashx/AjaxIndexCarFind.ashx?type=5&value=" + bif[4];
                System.out.println(bif[0]+"\t\t"+bif[1]+"\t\t"+bif[5]+"\t\t"+jj);
                String words = httpGet(url);
                JSONObject jsonObject = JSONObject.parseObject(words);
                JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                String array = jsonObject1.getString("yearitems");
                JSONArray jsonArray = JSONArray.parseArray(array);
                Object[] strs = jsonArray.toArray(); //json转为数组。
                //if(true){//strs.length > 1) {
                for (Object s : strs) {
                    JSONObject json = JSONObject.parseObject(s.toString());
                    String json_001 = json.getString("id");
                    String json_002 = json.getString("name");

                    String json_003 = json.getString("specitems");
                    JSONArray jsonArray_00 = JSONArray.parseArray(json_003);
                    Object[] json_strs = jsonArray_00.toArray(); //json转为数组。
                    for (Object json_s : json_strs) {
                        JSONObject json_st = JSONObject.parseObject(json_s.toString());
                        String bfirstletter_00 = json_st.getString("id");
                        String name_00 = json_st.getString("name");
                        String id_00 = json_st.getString("state");
                        String price1 = json_st.getString("minprice");
                        String price2 = json_st.getString("maxprice");
                        String one = brand_id_fil +"\t\t"+json_001 + "\t\t" + json_002 + "\t\t" + bfirstletter_00 + "\t\t" + name_00 + "\t\t" + id_00;
                        ddt.add(one);
                    }
                }
            }
            File file1 = new File("car_last_20180427.txt");
            FileUtils.writeLines(file1, ddt, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }








    @Test//获取品牌,型号
    public void get_car_brand() throws Exception{
        List<String> ddt = new ArrayList<>();

        try {
            File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\crawer\\car_22_brand_series_20180426.txt");
            List<String> brand_id = FileUtils.readLines(file);
            int jj = 0;
            for(String brand_id_fil: brand_id) {
                jj++;
                String brand = "";
                String model = "";
                String[] brands = brand_id_fil.split("\t\t");
                model = brands[5];
                if(brands[5].contains(brands[1])){
                    model = brands[5].replace(brands[1],"").trim();
                    if(StringUtils.isBlank(model)){
                        model = "gg";
                    }
                }
                String alls = brands[0] + "\t\t" + brands[1] + "\t\t" + model + "\t\t" + brands[2]+ "\t\t" + brands[3]+ "\t\t" + brands[4]+ "\t\t" + brands[5]+ "\t\t" + brands[6];
                ddt.add(alls);
            }
            File file1 = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\crawer\\brand_model_20180428.txt");
            FileUtils.writeLines(file1, ddt, true);

        }catch(Exception e){
            e.printStackTrace();
        }


    }







}