package ServerII;
import java.awt.*;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

public class MyServerSocket {
    static Frame frame = new Frame("Message监听");
    static TextArea text = new TextArea();
    public static void addtext(String tex){
        text.append(tex);
        text.append("\n");
    }
    public static void main(String[] args) {

        frame.setSize(300,200);
        frame.add(text);
        frame.setVisible(true);
        try {
            addtext(">>Local Server IP："+InetAddress.getLocalHost().getHostAddress());
            addtext(">>Server is Running...");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //启动服务器监听线程
        new ServerListener().start();
    }
}