package ServerII;
import NetTest.Server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatSocket extends Thread {
    String filename="s1.c";
    File f1 = new File("/home/mmj/Documents/"+filename);
    Socket socket;
    BufferedWriter bw;
    BufferedReader br;
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public ChatSocket(Socket s) {
        this.socket = s;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8"));
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //发送数据
    public void out(String out) {
        try {
            bw.write(out + "\n");// 必须要加换行符号,不然数据发不出去
            bw.flush();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String line = null;
            while ((line = br.readLine()) != null)
            {//监听客户端发来的数据
               // System.out.println("客户端发来数据："+line);
               // MyServerSocket.addtext(dateFormat.format(date.getTime()));
                MyServerSocket.addtext(dateFormat.format(date.getTime())+" "+"Reveived from Client："+line);
                datasave(f1,line);
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