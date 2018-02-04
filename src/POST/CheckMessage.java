package POST;

public class CheckMessage {
    static String MessageBuff=null;
    static String MessageExist=null;
    static class ThreadB extends Thread{
        @Override
        public void run() {
            try {
                while (true) {
                    MessageBuff=post.sendPost("http://119.23.110.11:7777/getvalue", "tag=1");
                    if (!MessageBuff.equals(MessageExist)) {
                        MessageExist=post.sendPost("http://119.23.110.11:7777/getvalue", "tag=1");
                        System.out.println(urldispose.geturlback(MessageBuff));
                    }else{
                        ThreadB.sleep(1000);
                    }
                }
            }catch (InterruptedException e){
                System.out.println("POST请求错误！");
            }
        }
    }
}