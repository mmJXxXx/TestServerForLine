package TcpServer;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static Frame frame = new Frame("服务器监听");
    static TextArea text = new TextArea();
    static File f1 = new File("c:/data.txt");
    public static void addtext(String tex){
        text.append(tex);
        text.append("\n");
    }
    public static String getNetworkTime(String webtime) {
        try {
            URL url = new URL(webtime);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        try {
            //1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket1=new ServerSocket(8087);
            ServerSocket serverSocket2=new ServerSocket(8088);
            ServerSocket serverSocket3=new ServerSocket(8089);
            Socket socket=null;
            Socket socket2=null;
            Socket socket3=null;
            //记录客户端的数量
            //int count=0;
            //System.out.println("***服务器即将启动，等待客户端的连接***");
            frame.setSize(400,200);
            frame.add(text);
            frame.setVisible(true);
            addtext("***服务器已启动，等待客户端的连接***");
            //循环监听等待客户端的连接
            while(true){
                //调用accept()方法开始监听，等待客户端的连接
                socket=serverSocket1.accept();
                //创建一个新的线程
                ServerThread serverThread=new ServerThread(socket);

                socket2=serverSocket2.accept();
                //创建一个新的线程
                ServerThread2 serverThread2= new ServerThread2(socket2);

                socket3=serverSocket3.accept();
                //创建一个新的线程
                ServerThread3 serverThread3=new ServerThread3(socket3);
                //启动线程
                serverThread.start();
                serverThread2.start();
                serverThread3.start();
                Date date = new Date();
                addtext("date=" + dateFormat.format(date.getTime()));//GUI界面显示
                datasave(f1,dateFormat.format(date.getTime()));//.txt数据存储

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        System.out.println(file.length()+"save success");
    }
}
