package HttpStudy.Chengzheng_javaURL;

/***Created by moyongzhuo
 *On 2017/9/21  ***16:59.
 ******/
public class TestForString {
        public static void main(String[] args) {
            String str="ABC123345";
            String str1="DEF67890";
            String str2=str.concat(str1);
            String str3="";
            for (int i = str2.length()-1; i >=0; i--) {
                str3 = str3 + str2.charAt(i);
            }
            System.out.println(str3);
        }
    }
