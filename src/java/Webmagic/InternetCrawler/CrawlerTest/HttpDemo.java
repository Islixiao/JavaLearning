package Webmagic.InternetCrawler.CrawlerTest;


import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/***Created by moyongzhuo
 *On 2017/9/27  ***10:05.
 ******/
public class HttpDemo {
    int countUrl=0;

    @Test
    public String getOneHtml(String htmlurl,String encoding,String cookie) throws IOException, InterruptedException
    {
        //最多重复请求5次，用来反爬的
        if(countUrl==5){
            countUrl=0;
            return "0";
        }
        //System.out.println(cookie);

        String temp;
        final StringBuffer sb = new StringBuffer();
        HttpURLConnection httpConn = null;
        try
        {
            URL url = new URL(htmlurl);

            httpConn = (HttpURLConnection) url.openConnection();
            //头设置，get方法
            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            httpConn.setRequestProperty("Connection","keep-alive");
            httpConn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml");
            httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            httpConn.setRequestProperty("cookie",cookie);
            httpConn.setRequestProperty("Cache-control","no-cache, no-store");
            httpConn.setRequestProperty("Host","www.linkedin.com");
            httpConn.setConnectTimeout(20000);
            httpConn.setReadTimeout(20000);
            // logger.info(httpConn.getResponseMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), encoding));////打开连接，获取内容
            if(httpConn.getResponseCode()!=200){
                //System.out.println(httpConn.getHeaderField("location"));
                // System.out.println(httpConn.getResponseCode()+htmlurl);
                httpConn.disconnect();
                Thread.sleep(30000);

               // cookie=login();
                return getOneHtml(htmlurl,encoding,cookie);
            }
            while ((temp = in.readLine()) != null)
            //替换点一些无用到符号
            {
                temp=temp.replaceAll("   ","");
                temp=temp.replaceAll("u002d","-");
                temp=temp.replaceAll("u0026","&");
                temp=temp.replaceAll("u002d","-");
                temp=temp.replaceAll("u0026","&");
                temp=temp.replaceAll("n","");
                temp=temp.replaceAll("t","");
                temp=temp.replaceAll("r","");
                sb.append(temp);
            }
            in.close();
            httpConn.disconnect();

        }
        catch (final MalformedURLException me)
        {
            System.out.println("url不存在!");
            me.getMessage();
            throw me;
        }
        catch (final FileNotFoundException me)
        {
            System.out.println(htmlurl+"反爬启动");
            return "0";
        }
        catch (final IOException e)
        {
            e.printStackTrace();
            System.out.println("反爬启动:"+htmlurl+"次数:"+countUrl++);
            httpConn.disconnect();
            Thread.sleep(20000);
            return this.getOneHtml(htmlurl, encoding,cookie);
        }

        //System.out.println(sb);
        countUrl=0;
        httpConn.disconnect();
        return sb.toString();
    }

/***
@Test
    public String login() throws MalformedURLException, InterruptedException {
        //Thread.sleep(3000000);
        String htmlurl = "https://www.linkedin.com/uas/login-submit";
        HttpURLConnection httpConn = null;
        String cookie = "";
        try {
            URL url = new URL(htmlurl);

            httpConn = (HttpURLConnection) url.openConnection();

            HttpURLConnection.setFollowRedirects(true);
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36");
            httpConn.setRequestProperty("Connection", "keep-alive");
            httpConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml");
            httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Cache-control", "no-cache, no-store");
            httpConn.setRequestProperty("Host", "www.linkedin.com");
            //httpConn.setRequestProperty("Referer","https://www.linkedin.com/uas/login?session_redirect=http://www.linkedin.com/profile/view?id=222323610&authType=name&authToken=fcEe");
            //post方法，重定向设置
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setInstanceFollowRedirects(true);
            //写入，post方法必须用流写入的方式传输数据
            StringBuffer str_buf = new StringBuffer(4096);
            OutputStream os = httpConn.getOutputStream();
            str_buf.append("session_key").append("=").append("email").append("&");
            str_buf.append("session_password").append("=").append("gmail").append("&");
            //str_buf.append("session_redirect").append("=").append(redictURL);
            os.write(str_buf.toString().getBytes());
            os.flush();
            os.close();
            httpConn.setConnectTimeout(20000);
            httpConn.setReadTimeout(20000);
            //获取重定向和cookie
            //String redictURL= httpConn.getHeaderField( "Location" );
            //System.out.println("第一次请求重定向地址 location="+redictURL);
            //获取cookie
         //   Map > map = httpConn.getHeaderFields();
            Map map = httpConn.getHeaderFields();
            System.out.println(map.toString());
            Set set = map.keySet();
            for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
               // String key = (String)iterator.next();
                String key = iterator.next();
                if (key != null) {
                    if (key.equals("Set-Cookie")) {
                        System.out.println("key=" + key + ",开始获取cookie");
                        List list = map.get(key);
                        for (String str : list) {
                            String temp = str.split("=")[0];
                            //System.out.println(temp);
                            //cookie包含到信息非常多，调试发现登录只需这条信息
                            if (temp.equals("li_at")) {
                                cookie = str;
                                return cookie;
                            }

                        }
                    }
                }

            }
            httpConn.disconnect();

        } catch (final MalformedURLException me) {
            System.out.println("url不存在!");
            me.getMessage();
            throw me;
        } catch (final FileNotFoundException me) {
            System.out.println(htmlurl + "反爬启动");
            return "0";
        } catch (final IOException e) {
            e.printStackTrace();
            System.out.println("反爬启动:" + htmlurl + "次数:" + countUrl++);
            httpConn.disconnect();
            Thread.sleep(20000);
            return login();
        }

        //System.out.println(sb);
        return cookie;
//return redictURL;
    }
***/
/***

    public static void main (String[] args) throws Exception,MalformedURLException, InterruptedException {
//教育信息"fosList":.*?schoolLogo
        String edu="null";
        ArrayList listEdu=new ArrayList();
        String regex1 = ""fosList":.*?schoolLogo";
        Pattern pa1 = Pattern.compile(regex1, Pattern.DOTALL);
        Matcher ma1 = pa1.matcher(s);
        while(ma1.find()){
            EduInfor ei=new EduInfor(ui.getCv_id());
            edu=ma1.group();
            //学校
            String school="null";
            String regex = ""schoolName":.*?,";
            Pattern pa= Pattern.compile(regex, Pattern.DOTALL);
            Matcher ma = pa.matcher(edu);
            if(ma.find()){
                school=ma.group();
                school=school.replaceAll("",schoolName":", "");
                school=school.replaceAll("", "");
                        school=school.replaceAll(",", "");
                if(!school.equals("")){
                    ei.setCollege(school);
                }
            }
            //学位
            String degree="null";
            regex = ""fmt__degree_highlight":.*?,";
            pa= Pattern.compile(regex, Pattern.DOTALL);
            ma = pa.matcher(edu);
            if(ma.find()){
                degree=ma.group();
                degree=degree.replaceAll(""fmt__degree_highlight":", "");
                degree=degree.replaceAll(""", "");
                        degree=degree.replaceAll(",", "");
                degree=degree.replaceAll("u0027s", "");
                if(!degree.equals("")){
                    ei.setDegree_name(degree);
                }
            }
            //专业
            String major="null";
            regex = ""fmt__fos_highlight":.*?,";
            pa= Pattern.compile(regex, Pattern.DOTALL);
            ma = pa.matcher(edu);
            if(ma.find()){
                major=ma.group();
                major=major.replaceAll(""fmt__fos_highlight":", "");
                major=major.replaceAll(""", "");
                        major=major.replaceAll(",", "");
                if(!major.equals("")){
                    ei.setMajor(major);
                }
            }
            //学历"grade":"1st"
            String academic="null";
            regex = ""grade":.*?,";
            pa= Pattern.compile(regex, Pattern.DOTALL);
            ma = pa.matcher(edu);
            if(ma.find()){
                academic=ma.group();
                academic=academic.replaceAll(""grade":", "");
                academic=academic.replaceAll(""", "");
                        academic=academic.replaceAll(",", "");
                if(!academic.equals("")){
                    ei.setAcademic_name(academic);
                }
            }
            //时间"enddate_my":"2005","startdate_my":"2002"
            String s_time="null";
            regex = ""startdate_my":.*?,";
            pa= Pattern.compile(regex, Pattern.DOTALL);
            ma = pa.matcher(edu);
            if(ma.find()){
                s_time=ma.group();
                s_time=s_time.replaceAll(""startdate_my":", "");
                s_time=s_time.replaceAll(""", "");
                        s_time=s_time.replaceAll(",", "");
                s_time=s_time.replaceAll(" ", "");
                if(!s_time.equals("")){
                    ei.setStart_time(s_time);
                }
            }

            String e_time="null";
            regex = ""enddate_my":.*?,";
            pa= Pattern.compile(regex, Pattern.DOTALL);
            ma = pa.matcher(edu);
            if(ma.find()){
                e_time=ma.group();
                e_time=e_time.replaceAll(""enddate_my":", "");
                e_time=e_time.replaceAll(""", "");
                        e_time=e_time.replaceAll(",", "");
                e_time=e_time.replaceAll(" ", "");
                if(!e_time.equals("")){
                    ei.setEnd_time(e_time);
                }
            }else{
                ei.setEnd_time("目前");
            }
            listEdu.add(ei);

        }

    }

***/

}
