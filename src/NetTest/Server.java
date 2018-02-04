package NetTest;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static Frame frame = new Frame("Message监听");
    static TextArea text = new TextArea();
    static File f1 = new File("f:/Message.txt");
    public static void addtext(String tex){
        text.append(tex);
        text.append("\n");
    }
    public static void main(String[] args) {
        frame.setSize(400,200);
        frame.add(text);
        frame.setVisible(true);
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket1=new ServerSocket(7400);
            Socket socket=null;
            //记录客户端的数量
            //int count=0;
            addtext("***服务器即将启动，等待客户端的连接***");
            addtext("当前服务器的IP："+InetAddress.getLocalHost().getHostAddress());
            //循环监听等待客户端的连接
            while(true){
                //调用accept()方法开始监听，等待客户端的连接
                socket=serverSocket1.accept();
                //创建一个新的线程
                ServerThread serverThread=new ServerThread(socket);
                //启动线程
                serverThread.start();
//                count++;//统计客户端的数量
//                System.out.println("客户端的数量："+count);
                InetAddress address=socket.getInetAddress();
                //System.out.println("当前客户端的IP："+address.getHostAddress());
                addtext("当前客户端的IP："+address.getHostAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
