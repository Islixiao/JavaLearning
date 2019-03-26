package AlibabaFastjson;

/***Created by moyongzhuo
 *On 2017/9/27  ***9:13.
 ******/
/**
 * Created by paranoid on 17-4-2.
 */
public class TestPerson {
    private int age;
    private String name;

    public TestPerson() {

    }

    public TestPerson(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
