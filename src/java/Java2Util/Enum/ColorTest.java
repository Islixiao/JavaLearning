package Java2Util.Enum;
import Java2Util.Enum.Color;
import com.sun.deploy.security.ValidationState.TYPE;
import org.junit.Test;

/***Created by moyongzhuo
 *On 2018/5/10  ***11:27.
 ******/
public class ColorTest {
    /**
     * 测试继承接口的枚举的使用（by 大师兄 or 大湿胸。）
     */
    public void sing(Color state) throws Exception{
        switch (state) {
            case RED:
                singHappySong();
                break;
            case GREEN:
                singDirge();
                break;
            default:
                new IllegalStateException("Invalid State: " + state);
        }
    }

    private void singHappySong() {
        System.out.println("When you're happy and you know it ...");
    }

    private void singDirge() {
        System.out.println("Don't cry for me Argentina, ...");
    }

    public static void main(String[] args) {
//        Color.GREEN.setName("mo");
//        for (Color simpleEnum : Color.values()) {
//            System.out.println(simpleEnum + "  ordinal  " + simpleEnum.ordinal());
//        }
//        System.out.println("------------------");
//        for (TYPE type : TYPE.values()) {
//            System.out.println("type = " + type + "    type.name = " + type.name() + "   typeName = " + type.getDeclaringClass() + "   ordinal = " + type.ordinal());
//        }
//    }
        int k = 0;
    }
}
