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
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8"));
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            BufferedWriter finalWriter = writer;
            BufferedReader finalReader = reader;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String sendData = "***今晚月色真美！";
                        finalWriter.write(sendData + "\n");//必须加上换行
                        finalWriter.flush();
                        finalWriter.write(sendData + "\n");//必须加上换行
                        finalWriter.flush();
                        finalWriter.write(sendData + "\n");//必须加上换行
                        finalWriter.flush();
                        System.out.println(finalReader.readLine());
                    } catch (IOException e) {
                        System.out.println("Error!");
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
