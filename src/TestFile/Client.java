package TestFile;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        try {
            Socket client = new Socket("119.23.110.11", 7400);
            PrintWriter pw = null;
            InputStreamReader isr = null;
            System.out.println("Client started, ready to write content.");
            String input = "";
            while (true) {
                System.out.print("Client : ");
                InputStream is = System.in;
                input = new BufferedReader(new InputStreamReader(is)).readLine();
                pw = new PrintWriter(client.getOutputStream(), true);
                pw.println(input);
                if (input.equals("end")) {
                    client.close();
                    is.close();
                    pw.close();
                    if (isr != null) {
                        isr.close();
                    }
                    break;
                }
                isr = new InputStreamReader(client.getInputStream());
               System.out.print(new BufferedReader(isr).readLine());
                System.out.print(new BufferedReader(isr).readLine());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    private static String getNetworkTime(String webtime) {
        try {
            URL url = new URL(webtime);
            URLConnection conn = url.openConnection();
            conn.connect();
            long dateL = conn.getDate();
            Date date = new Date(dateL);
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
