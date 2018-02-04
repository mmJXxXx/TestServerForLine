package TestFile;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AndroidPost {

    public static void main(String[] args) throws IOException {

        /**
         url这里是一个重点，服务器端使用的servlet，是tomcat容器在负责调用，
         tomcat默认的监听端口即是8080，IP地址如果是远程的或者使用的云服务器，
         那就换成自己的IP，关键是8080端口后面的内容，test1其实是tomcat目录下的一个webapp，
         test1可以理解成是一个tomcat下面的工程，而Upload_html这个则是具体的负责
         接收java代码传来的数据的servlet名称，后缀.java省掉。同时在tomcat的test1工程下面
         （或者说文件夹下面）要新建一个WEB-INF的文件夹，在这个文件夹下面要建一个
         web.xml文件，在里面要进行配置才能把url这个地址和具体servlet程序关联起来。
         后面会讲服务器端
         **/

        String url = "http://localhost:8080/web/Upload_html";
        String result = doPost(url, sendParam());
        System.out.println(result);
        System.out.println("come on");
    }

    public static Map<String, String> sendParam() {
        //这里用来存放键值对，为了程序看起来简单就手动输上去了
        String key1 = "param1";
        String value1 = "1";
        String key2 = "param2";
        String value2 = "14";
        String key3 = "param3";
        String value3 = "334";
        HashMap<String, String> param = new HashMap<String, String>();
        param.put(key1, value1);
        param.put(key2, value2);
        param.put(key3, value3);

        return param;
    }

    public static String doPost(String urlString, Map<String, String> nameValuePairs)
            throws IOException {
        URL url = new URL(urlString);//先new出一个URL地址，定位网络资源
        URLConnection connection = url.openConnection();//打开连接
        connection.setDoOutput(true);//使能往远程写操作

        //把建立的http的连接流返回给PrintWriter
        try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {

            boolean first = true;
            for (Map.Entry<String, String> pair : nameValuePairs.entrySet()) {
                if (first)
                    first = false;
                else
                    out.print('&');
                String name = pair.getKey().toString();
                String value = pair.getValue().toString();
                out.print(name);
                out.print('=');
                out.print(URLEncoder.encode(value, "UTF-8"));
            }

        }
        //下面的代码是去接收服务器端的反馈信号，将返回的信号全都存放在response这个对象中，
       // 看一下api文档的StringBuilder类，就明白下面的代码了

        StringBuilder response = new StringBuilder();
        try (Scanner in = new Scanner(connection.getInputStream())) {
            while (in.hasNextLine()) {
                response.append(in.nextLine());
                response.append("\n");
            }
        } catch (IOException e) {
            if (!(connection instanceof HttpURLConnection))
                throw e;
            InputStream err = ((HttpURLConnection) connection).getErrorStream();
            if (err == null)
                throw e;
            Scanner in = new Scanner(err);
            response.append(in.nextLine());
            response.append("\n");
            in.close();
        }

        return response.toString();
    }

}