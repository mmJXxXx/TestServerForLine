import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TEST {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 7400);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter( new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8" ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            //1.创建客户端Socket，指定服务器地址和端口
//            InputStreamReader isr = null;
//            Socket socket = new Socket("localhost", 7400);
//            byte[] sendMessage = ("As the moon,so beautiful!\n" ).getBytes();
//            OutputStream out = socket.getOutputStream();
//            out.write(sendMessage);
//            isr = new InputStreamReader(socket.getInputStream());
//            System.out.println(new BufferedReader(isr).readLine());
//            socket.close();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
        BufferedWriter finalWriter = writer;
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    String sendData ="***今晚月色真美！";
                    finalWriter.write(sendData + "\n");//必须加上换行
                    finalWriter.flush();
                } catch (IOException e) {
                    System.out.println("Error!");
                    e.printStackTrace();
                }

            }
        }).start();
        }
    }
