package POST;
import POST.CheckMessage;
public class TextRun {

        public static void main(String[] args){
            CheckMessage.ThreadB msgB = new CheckMessage.ThreadB();
           msgB.setName("B");
           msgB.start();

        }
}
