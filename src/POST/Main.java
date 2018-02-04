package POST;
import java.util.Scanner;
public class Main {
    public static String string2Unicode(String string) {
        char cx = '\\';
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append(cx+"u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);   //转换为16进制整型字符串
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\" + hexB;
        }
        return unicodeBytes;
    }
    public static void main(String[] args) {
        String tag_T = "tag=T", tag_H = "tag=H", tag_VOC = "tag=VOC", tag_T1 = "tag=T1";
        String value = "value=";
        post.sendPost("http://119.23.110.11:7777/storeavalue", "tag=T0" + "&" + value +"今天也是充满希望的一天！");

        String urlback =urldispose.geturlback(post.sendPost("http://119.23.110.11:7777/getvalue", "tag=1"));
//            System.out.println(urlback);
//            System.out.println(23);
//        // post.sendPost("http://119.23.110.11:7777/storeavalue", "tag=H0" + "&" + value +"就提前祝你");
//            //post.sendPost("http://119.23.110.11:7777/storeavalue", "tag=V0" + "&" + value +"狗年大吉吧！");
//           // post.sendPost("http://119.23.110.11:7777/storeavalue", "tag=P0" + "&" + value +"！！！");
//        if(urlback.equals("Hel!!")) {
//            System.out.println(urlback);
//        }
        String text = "123456789abcdefghhh";
        text= text.substring(16);
        System.out.println(text);
        System.out.println(gbEncoding("麻麻吉"));
    }
    }



