package Webmagic.AutoHome;

/***Created by moyongzhuo
 *On 2018/4/21  ***20:39.
 ******/
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;

/**
 * <br/>
 * Created on 2017/10/13 16:25.
 *
 * @author zhubenle
 */
public class UtilTest {

    public final static String SCRIPT_PRE = "var rules = '';var document = {};document.createElement = function() {return {sheet: {insertRule: " +
            "function(rule, i) {if (rules.length == 0) {rules = rule;} else {rules = rules + '|' + rule;}}}}};document.getElementsByTagName = " +
            "function() {};document.querySelectorAll = function() {return {};};document.head = {};document.head.appendChild = function() " +
            "{};var window = {};window.decodeURIComponent = decodeURIComponent;window.location = {};window.location.href = 'car.m.autohome.com.cn';";

    @Test
    public void testScript() throws Exception {

        Document document = Jsoup.parse(new File("D:\\workspace\\java\\WebAnimationTest\\src\\main\\java\\Webmagic\\Downloads\\【Polo参数配置表】_大众_Polo配置_价格单_汽车之家.html"), "UTF-8");
        Elements scripts = document.select("script:containsData(insertRule)");

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
}
