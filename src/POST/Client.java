package POST;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 客户端
 */
public class Client {

    public static void main(String[] args) {
        try {
            String webtime = "http://www.ntsc.ac.cn";
            Socket client = new Socket("localhost", 10001);
            PrintWriter pw = null;
            InputStreamReader isr = null;
            System.out.println("Client started, ready to write content.");
            String input = "";
            while (true) {
               System.out.print("Client : ");
                InputStream is = System.in;
                input = new BufferedReader(new InputStreamReader(is)).readLine();
                pw = new PrintWriter(client.getOutputStream(), true);
                pw.println(getNetworkTime(webtime)+" " + input);
//                if (input.equals("end")) {
//                    client.close();
//                    is.close();
//                    pw.close();
//                    if (isr != null) {
//                        isr.close();
//                    }
//                    break;
//                }
                isr = new InputStreamReader(client.getInputStream());
                System.out.println(new BufferedReader(isr).readLine());
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
