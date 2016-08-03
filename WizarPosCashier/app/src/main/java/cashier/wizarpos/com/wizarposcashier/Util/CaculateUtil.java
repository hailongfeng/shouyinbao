package cashier.wizarpos.com.wizarposcashier.Util;

/**
 * Created by lixinchun on 16/7/30.
 */
public class CaculateUtil {
    /** 输入金额 */
    public static String inputMoney(String stext, String appendValue) {
        String text = stext;
        text = text.replace(".", "");
        text += appendValue;
        text = leftRemove(text, "0");
        text = leftPad(text, "0", 3);
        text =insertFromRight(text, ".", 2);
        if (text.length() > 10) {
            return stext;
        }
        return text;
    }
    /** 从左边删除指定字符 */
    public static String leftRemove(String src, String s) {
        while (src.startsWith(s)) {
            src = src.substring(s.length());
        }
        return src;
    }
    /** 左补字符串 */
    public static String leftPad(String src, String pad, int pos) {
        int length = src.length();
        if (length >= pos) {
            return src;
        }
        do {
            src = pad + src;
        } while (src.length() < pos);
        return src;
    }
    /** 从右边指定位置插入 */
    public static String insertFromRight(String src, String s, int index) {
        if (src.length() < index) {
            return src;
        }
        return src.substring(0, src.length() - index) + s + src.substring(src.length() - index);
    }
}
