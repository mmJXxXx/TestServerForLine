package NetTest;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 服务器线程处理类
 */
public class ServerThread extends Thread {
    // 和本线程相关的Socket
    Socket socket = null;


    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    //线程执行的操作，响应客户端的请求
    public void run(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        BufferedWriter bw=null;
        try {
            //获取输入流，并读取客户端信息
            is = socket.getInputStream();
          //  isr = new InputStreamReader(is);
          //  br = new BufferedReader(isr);
            Date date = new Date();
            // 创建接收客户端数据数组
            byte[] receive = new byte[1024];
            // 接收客户端传送过来的数据
            int len = is.read(receive);
            // 打印接收过来的数据
           String Buff=new String(receive, 0, len);
          //  System.out.println("ip:" +socket.getInetAddress().getHostAddress() + ", port:" +socket.getPort() + ",message:" + Buff);
            Server.addtext("Message:"+Buff);
            Server.addtext(dateFormat.format(date.getTime()));
         datasave(Server.f1,dateFormat.format(date.getTime())+" "+Buff+"\n");
            try {
                bw = new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream(), "utf-8"));
                br = new BufferedReader(new InputStreamReader(
                        socket.getInputStream(), "utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bw.write("Hello!"+"\n");
            bw.flush();
            socket.close();
            //pw.write("Hello,It's Server.");
           // pw.print("\n");
           // socket.shutdownOutput();
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
//    public void out(String out) {
//        try {
//            bw.write(out + "\n");// 必须要加换行符号,不然数据发不出去
//            bw.flush();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}