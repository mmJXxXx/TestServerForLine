package TestFile;

import TcpServer.ServerThread2;
import TcpServer.ServerThread3;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    static Frame frame = new Frame("Message Board");
    static TextArea text = new TextArea();
    public static void addtext(String tex){
        text.append(tex);
        text.append("\n");
    }
    static File f1 = new File("/home/mmj/data.txt");
    public static void main(String[] args) {
        frame.setSize(400,200);
        frame.add(text);
        frame.setVisible(true);
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        try {
            ServerSocket server = new ServerSocket(7776);
           // System.out.println("Server started, waiting for message.");
            Socket client = server.accept();
            PrintWriter pw = null;
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        client.getInputStream()));
                String content = br.readLine();
                if (content.equals("end")) {
                    server.close();
                    br.close();
                    if (pw != null) {
                        pw.close();
                    }
                    break;
                }
                addtext(content);
                Date date = new Date();
                addtext("date=" + dateFormat.format(date.getTime()));
                datasave(f1,content+dateFormat.format(date.getTime()));
            }
           addtext("Client left! Server Closed.");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void datasave(File file,String text){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(file,true));
            out.write(text+"\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(file.length()+"save success");
    }

}
