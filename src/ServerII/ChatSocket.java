package ServerII;
import NetTest.Server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatSocket extends Thread {
   // String filename="s1.c";
     File f1 = new File("C:/Message.txt");
    Socket socket;
    BufferedWriter bw;
    BufferedWriter serverbw;
    BufferedReader br;
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public ChatSocket(Socket s) {
        this.socket = s;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8"));
            serverbw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8"));
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //发送数据
    public  void out(String out) {
        try {
            bw.write(out + "\n");// 必须要加换行符号,不然数据发不出去
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void serverout(String out) {
        try {
           serverbw.write(out);
           serverbw.flush();
           //serverbw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            serverout("***感谢您参与测试...此app仍在初始编写测试阶段.您在此app所发送信息将传输至服务器.若您与其他用户输入相同密码并在同一时间段内发送消息即可实现简易的聊天功能......再次感谢您的参与                     联系方式：mmjxxxx@163.com" + "\n");
            String line = null;
            while ((line = br.readLine()) != null)
            {//监听客户端发来的数据
               // System.out.println("客户端发来数据："+line);
               // MyServerSocket.addtext(dateFormat.format(date.getTime()));
                MyServerSocket.addtext(dateFormat.format(date.getTime())+" "+"Client："+line);
                 datasave(f1,dateFormat.format(date.getTime())+" :"+line);
                //把数据发给其余的客户端
                ChatManager.getChatManager().publish(this, line);

            }
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IOException e){
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
    }
}