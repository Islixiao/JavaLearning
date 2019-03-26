package AlibabaFastjson;

/***Created by moyongzhuo
 *On 2017/9/27  ***9:14.
 ******/
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


/***public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
 public static final JSONObject parseObject(String text); //把JSON文本parse成JSONObject
 public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
 public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
 public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
 public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
 public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
 public static final Object toJSON(Object javaObject); //将JavaBean转换为JSONObject或者JSONArray。
 * ***/


import static java.lang.System.out;


/**
 * Created by paranoid on 17-4-2.
 *
 * fastJson在反序列化的时候需要调用对象的默认构造方法，如果该对象之中还包含其他的对象，
 * 那么都需要创建默认的无参构造方法.
 */

public class TestFastJson {
    public static void main(String[] args) {
        TestPerson testPerson = new TestPerson(19, "李明");

        List<TestPerson> list = new ArrayList<>();

        list.add(testPerson);
        list.add(new TestPerson(12, "张三"));

        //将集合或对象序列化为Json
        out.println(JSON.toJSON(testPerson));
        out.println(JSON.toJSON(list));

        //JSON串反序列化为对象
        TestPerson person = JSON.parseObject("{\"age\":19,\"name\":\"李明\"}",
                TestPerson.class);
        out.printf("name:%s, age:%d\n", person.getName(), person.getAge());

        //字符串对象反序列化成集合
        String str = "[{\"name\":\"李明\",\"age\":19},{\"name\":\"张三\",\"age\":12}]";
        List<TestPerson> listPerson = JSON.parseArray(str,TestPerson.class);

        for(TestPerson item : listPerson){
            out.println(item.getName() );
            out.println(item.getAge());
        }

        //没有对象直接解析JSON对象(常用)
        JSONObject jsonObject = JSON.parseObject("{\"name\":\"李明\",\"age\":19}");
        System.out.printf("name: %s, age: %d\n", jsonObject.getString("name"),
                jsonObject.getBigInteger("age"));

        //没有对象直接解析JSON数组
        JSONArray jsonArray = JSON.parseArray("[{\"name\":\"李明\",\"age\":19}," +
                "{\"name\":\"张三\",\"age\":12}]");

        for(int i = 0; i < jsonArray.size(); i++){
            JSONObject temp =  jsonArray.getJSONObject(i);
            System.out.printf("name: %s, age: %d\n", temp.getString("name"),
                    temp.getBigInteger("age"));
        }

        for(Object obj : jsonArray){
            System.out.println(obj.toString());
        }
    }
}
