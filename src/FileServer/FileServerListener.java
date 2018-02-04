package FileServer;

import ServerII.ChatManager;
import ServerII.ChatSocket;
import ServerII.MyServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static FileServer.FileServer.addtext;

public class FileServerListener extends Thread {
        @Override
        public void run() {
            try {
                ServerSocket serverSocket = new ServerSocket(7399);
                Socket filesocket=null;
                // 循环的监听
                while (true) {
                    Socket socket = serverSocket.accept();// 阻塞
                    try {
                        filesocket= new Socket("localhost", 7400);
                    }catch (IOException e){
                        addtext("!!Server Connection Error");
                    }
                    //Socket filesocket = fileSocket.accept();
                    // JOptionPane.showMessageDialog(null, "有客户端连接到本机的7400端口！");
                    // 将socket传给新的线程
                    MyServerSocket.addtext("File Server connected");
                    FileSocket fs = new FileSocket(filesocket);
                    fs.start();
                    //把socket加入ChatManager
                    //FileManager.getFileManager().add(fs);
                    //System.out.println("服务器反馈：有客户端连接到服务器！");
                    //ChatManager.getChatManager().publishAll("服务器连接成功...");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
