package NetTest;
import java.net.*;
import java.io.*;
public class SimpleSocketServer {
    public static void main(String[] args){
        ServerSocket serverSocket = null;
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        //监听端口号
        int port = 7400;
        try{
            //建立连接
            serverSocket = new ServerSocket(port);
            //获得连接
            socket = serverSocket.accept();
            byte[] b =new byte[1024];
            int n = is != null ? is.read(b) : 0;
            //输出
            System.out.println("客户端发送内容为:"+new String(b,0,n));
            //向客户端发送反馈内容
            os = socket.getOutputStream();
            os.write(b,0,n);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                //关闭流和连接
                assert os != null;
                os.close();
                socket.close();
                serverSocket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
