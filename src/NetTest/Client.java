package NetTest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        int i = 0;
        try {
            //1.创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("119.23.110.11", 8087);
            Socket socket2 = new Socket("119.23.110.11", 8088);
            Socket socket3 = new Socket("119.23.110.11", 8089);
            byte[] sendMessage = ("0000").getBytes();
            OutputStream out = socket.getOutputStream();
            OutputStream out2 = socket2.getOutputStream();
            OutputStream out3 = socket3.getOutputStream();
            out.write(sendMessage);
            out2.write(sendMessage);
            out3.write(sendMessage);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
