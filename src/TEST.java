import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

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
        BufferedReader finalReader = reader;
        BufferedWriter finalWriter = writer;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String line=null;
                    try {
                        while ((line = finalReader.readLine()) != null)
                        {
                            System.out.println(line);
                        }
                        finalReader.close();
                        //Scanner sc = new Scanner(System.in);
                        finalWriter.write("***TestMessage...");
                       // System.out.println(str);
//                        if (finalWriter != null) {
                        finalWriter.flush();
//                        }
                         finalWriter.close();
                       }catch (Exception e){
                           System.out.println(e);
                       }
                }
            }).start();
        }
    }
