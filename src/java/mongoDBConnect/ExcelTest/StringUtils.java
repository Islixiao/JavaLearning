package mongoDBConnect.ExcelTest;

/***Created by moyongzhuo
 *On 2017/10/12  ***13:49.
 ******/
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Chenfei
 *         Date: 13-4-24
 *         Time: 下午5:05
 */
public class StringUtils {

    /**
     * 是否为半角字符串
     *
     * @param param
     * @return true为半角，反正为全角
     */
    public static boolean isAllHalf(String param) {
        char[] chs = param.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (!(('\uFF61' <= chs[i]) && (chs[i] <= '\uFF9F')) && !(('\u0020' <= chs[i]) && (chs[i] <= '\u007E'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNum(String str) {
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }

    /**
     * 生成md5字符串
     *
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        try {
            byte[] res = str.getBytes();
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5".toUpperCase());
            byte[] digest = md.digest(res);
            return bytesToString(digest);
        } catch (Exception e) {
            return null;
        }
    }

    private static String bytesToString(byte[] data) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        char[] temp = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
            temp[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(temp).toUpperCase();
    }

    /**
     * 用户账号正则表达式判断
     * 1、必须以半角的字母开头
     * 2、不能包括任何标点符号
     * 3、长度不超过10位
     * 4、不能为空
     * 5、不能有中文
     *
     * @param value
     * @return 通过规则为true, 反之为false
     */
    public static Boolean isUserIdOk(String value) {
        if (isAllHalf(value) && value.length() > 0 && value.length() < 11) {
            String userRegEx = "^[a-z]+[0-9a-z]*";
            Pattern useridPattern = Pattern.compile(userRegEx);
            Matcher m = useridPattern.matcher(value);
            return m.matches();
        }
        return false;
    }

    /**
     * 判断用户真实姓名
     * 1.只能是中文
     * 2.中文个数只能是2-6个
     *
     * @param value
     * @return Boolean
     */
    public static Boolean isUserRealNameOk(String value) {
        String regEx = "[\\u4e00-\\u9fa5]{2,6}";
        Pattern sChinese = Pattern.compile(regEx);
        Matcher m = sChinese.matcher(value);
        return m.matches();
    }

    public static Boolean isTshu(String value) {
        String userRegEx = "[0-9a-z]{6,20}";
        Pattern useridPattern = Pattern.compile(userRegEx);
        Matcher m = useridPattern.matcher(value);
        return m.matches();
    }

    /**
     * 手机号码正则判断,只能是11位数字
     *
     * @param value
     * @return
     */
    public static Boolean isPhoneNumber(String value) {
        String userRegEx = "^1([0-9]{10})";
        Pattern useridPattern = Pattern.compile(userRegEx);
        Matcher m = useridPattern.matcher(value);
        return m.matches();
    }

    /**
     * 不是手机号码返回true
     *
     * @param phone
     * @return
     */
    public static Boolean isNotPhoneNumber(String phone) {
        return !isPhoneNumber(phone);
    }

    /**
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((!Character.isWhitespace(str.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>Checks if a String is not empty (""), not null and not whitespace only.</p>
     * <p/>
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is
     * not empty and not null and not whitespace
     * @since 2.0
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static String replace(String str, String replaceStr, int begin, int end) {
        String prefix = str.substring(0, begin - 1);
        String suffix = str.substring(end);

        return prefix + replaceStr + suffix;
    }


}

