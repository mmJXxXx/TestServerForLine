package FileServer;
import ServerII.ServerListener;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileServer {
    static Frame frame = new Frame("Message监听");
    static TextArea text = new TextArea();
    static Socket socket=null;
    static BufferedReader br;
    public static void addtext(String tex){
        text.append(tex);
        text.append("\n");
    }
    public static void datasave(File file, String text){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
            out.write(text+"\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filename="f1.c";
        File f1 = new File("/home/mmj/Documents/"+filename);
        frame.setSize(500,200);
        frame.add(text);
        frame.setVisible(true);
        try{
            addtext(">>Local Server IP："+InetAddress.getLocalHost().getHostAddress());
            addtext(">>Server is Running...");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        //启动服务器监听线程
        new FileServerListener().start();
    }
}
