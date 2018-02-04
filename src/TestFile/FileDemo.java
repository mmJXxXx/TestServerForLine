package TestFile;

import java.io.*;

public class FileDemo {
    public static void main(String[] args) {
        //创建File对象
        File f1 = new File("F:/test.txt");
        try {
            f1.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(f1));
            out.write("777\r\n"); // \r\n即为换行
            out.write("666\r\n"); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
            File filepath = new File("f:/test.txt");
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filepath)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言

            System.out.println(checkline(false,2,filepath.toString()));


        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(f1.exists());
    }
    //txt字符读取
    static String checkline(boolean f,int i, String filepath) {
        String line = "";
        if (i <= 0) {
            i = 1;
        }
        File file = new File(filepath);
        try {
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(file));
            if(f){
                BufferedReader bra = new BufferedReader(reader);
                String linecheck=null;
                StringBuffer lineall = new StringBuffer();
                while((linecheck = bra.readLine()) != null){
                        lineall.append(linecheck+"\n");
                }
                line = lineall.toString();
            }else {
                BufferedReader br = new BufferedReader(reader);
                for (int j = 0; j < i; j++) {
                    line = br.readLine();
                }
            }
        } catch (FileNotFoundException e) {
            return e.getMessage();
        } catch (IOException e) {
            return "IOerror";
        }
        return line;
    }

}
