package Webmagic.AutoHome;

/***Created by moyongzhuo
 *On 2018/4/22  ***8:42.
 ******/
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class Car_getHTML {
    public final static String SCRIPT_PRE = "var rules = '';var document = {};document.createElement = function() {return {sheet: {insertRule: " +
            "function(rule, i) {if (rules.length == 0) {rules = rule;} else {rules = rules + '|' + rule;}}}}};document.getElementsByTagName = " +
            "function() {};document.querySelectorAll = function() {return {};};document.head = {};document.head.appendChild = function() " +
            "{};var window = {};window.decodeURIComponent = decodeURIComponent;window.location = {};window.location.href = 'car.m.autohome.com.cn';";

    /**
     * 该方法可以很好的解决中文乱码问题，同样采用java自带的HttpURLConnection类，方便
     */
    public String getHtmlcodeWithoutHeader(String pageUrl, String encoding) {
        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8580)); //设置代理服务器
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(pageUrl);
            //HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in, encoding));
            String line = null;
            while((line=br.readLine())!=null){
                sb.append(line);
                sb.append("\r\n");
            }
            br.close();
            in.close();
        } catch (MalformedURLException e) {
            System.err.println("url格式不规范:"+e.getMessage());
        } catch (IOException e) {
            System.err.println("IO操作错误："+e.getMessage());
        }
        return sb.toString();
    }

    public String getHtml(String url) {
        String html = "";
        String firstEncoding = "UTF-8";
        html = getHtmlcodeWithoutHeader(url, firstEncoding);
        System.out.println("\n"+"\n");

        //System.out.println(html);


        return html;
    }


    public void testScript(String html) throws Exception {

        Document document = Jsoup.parse(html, "UTF-8");
        Elements scripts = document.select("script:containsData(insertRule)");
//        Elements scripts = document.select("script");
//        for(int i=0; i<scripts.size();i++){
//            String ele = scripts.toString().replaceAll("</?[^>]+>","");
//            System.out.println(ele);
//        }

        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        scripts.forEach(element -> {
            String script = SCRIPT_PRE + element.html();
            try {
                engine.eval(script);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            String result = (String) engine.get("rules");
            System.out.println(result);
        });
    }

    @Test//获取指定行数据
    public void test3()throws Exception {
        String html = "";
        String url = "https://car.autohome.com.cn/config/series/650.html";
        html = getHtml(url);
        testScript(html);
        int gg = 0;
    }





    @Test
    public void test2()throws Exception {
        String html = "";
        String url = "https://car.autohome.com.cn/config/series/650.html";
        html = getHtml(url);
        Document pasedDoc=Jsoup.parse(html);
        Elements cells=pasedDoc.getElementsByTag("tr");
        String cell = cells.toString().replaceAll("</?[^>]+>","");
        System.out.println(cell);


    }


    @Test//获取第一行数据
    public void test()throws Exception {
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;

        String html = "";
        String url = "https://car.autohome.com.cn/config/series/650.html";
        html = getHtml(url);
        Document document = Jsoup.parse(html, "UTF-8");

        String html_1 = html;
        Elements scripts_1 = document.select("script:containsData(insertRule)");
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("JavaScript");
        List<String> list = new ArrayList<>();
        scripts_1.forEach(element -> {
            String script = SCRIPT_PRE + element.html();
            try {
                engine.eval(script);
            } catch (ScriptException e) {
                e.printStackTrace();
            }
            String result = (String) engine.get("rules");
            System.out.println(result);
            list.add("|"+result);
        });
        List<String> rulelist = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        for(int i = 0;i < list.size(); i++){
            String[] rule_content = list.get(i).trim().split("\\|.");
            for(int j=0; j<rule_content.length; j++) {
                if(j!=0) {
                    String rule_one = rule_content[j].replace("ZP::before { content:",":").replace("wo::before { content:",":").replace("Sd::before { content:",":").replace(" }","").trim();
                    String[] rule_con = rule_one.split(":");
                    map.put(rule_con[0], rule_con[1]);
                    System.out.println(rule_one);
                }
            }

        }

        Elements scripts_key = document.select("script:containsData(var keyLink)");
        String scripts_string = scripts_key.toString();
//        String regex_key=".*?keyLink|config(.*)?";
//        String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
//        String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
//        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//        String gg="";
//        String key = scripts_string.replaceAll(".*keyLink|var config(.*)","");
        String key  = scripts_string.substring(scripts_string.indexOf("keyLink ="), scripts_string.lastIndexOf("var config"));
        String config = scripts_string.substring(scripts_string.indexOf("config ="), scripts_string.lastIndexOf("var option"));
        String option  = scripts_string.substring(scripts_string.indexOf("option ="), scripts_string.lastIndexOf("var bag"));
        String color = scripts_string.substring(scripts_string.indexOf("color ="), scripts_string.lastIndexOf("var innerColor"));
        String color_inner = scripts_string.substring(scripts_string.indexOf("innerColor ="), scripts_string.lastIndexOf("var dealerPrices"));


        System.out.println(key);
        int oi=0;

//             = m_script.replaceAll(""); // 过滤script标签


//        List<String> list = new ArrayList<>();
//        list.add("keyLink");
//        list.add("config");
//        list.add("option");
//        list.add("color");
//        list.add("innerColor");
//        List<String> list_script = new ArrayList<>();
//        for(String words : list) {
//            Elements scripts_key = document.select("script:containsData(var " + words + ")");
//            list_script.add(scripts_key.toString());
//


//        Elements scripts_config = document.select("script:contains(var config)");
//        Elements scripts_option = document.select("script:contains(var option)");
//        Elements scripts_color = document.select("script:contains(var color)");
//        Elements scripts_inercolor = document.select("script:contains(var innerColor)");


//        for(int num = 0;num<scripts.size();num++) {
//            String scripts_string = scripts.select("color").toString();
//            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
//            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
//            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//
//            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
//            m_script = p_script.matcher(scripts_string);
////            htmlStr = m_script.replaceAll(""); // 过滤script标签
//        }


//        File file = new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\AutoHome\\html.txt");
//        FileUtils.writeStringToFile(file,html,true);

//        testScript(html);
    }


}