package TcpServer;

import java.io.*;
import java.net.Socket;

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
        InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        try {
            //获取输入流，并读取客户端信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info=null;
            // 创建接收客户端数据数组
            byte[] receive = new byte[1024];
            // 接收客户端传送过来的数据
            int len = is.read(receive);
            // 打印接收过来的数据
            String Buff=new String(receive, 0, len);
            //System.out.println("ip:" +socket.getInetAddress().getHostAddress() + ", port:" +socket.getPort() + ",message:" + Buff);
            Server.addtext("ip1:" +socket.getInetAddress().getHostAddress() + ", port:" +socket.getPort() + ",message:" + Buff+"\n");//GUI界面上显示
            Server.datasave(Server.f1,"ip1:" +socket.getInetAddress().getHostAddress() + ", port:" +socket.getPort() + ",message:" + Buff+"\n");//.txt文件存储
            post.sendPost("http://119.23.110.11:7777/storeavalue", "tag=T0" + "&" + "value=" +Buff);//将TCP传输到的数据post到TinyWeb服务器上
            socket.shutdownInput();//关闭输入流
            //获取输出流，响应客户端的请求
//            os = socket.getOutputStream();
//            pw = new PrintWriter(os);
//            pw.write("欢迎您！");
//            pw.flush();//调用flush()方法将缓冲输出
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            //关闭资源
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}