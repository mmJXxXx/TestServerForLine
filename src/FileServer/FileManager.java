package FileServer;
import ServerII.ChatSocket;

import java.util.Vector;

//类单例化处理
public class FileManager {
    Vector<FileSocket> vector = new Vector<FileSocket>();

    private FileManager() {
    }

    private static class FileManagerHolder {
        private static final FileManager cm = new FileManager();
    }

    public static FileManager getFileManager() { //获取单例
        return FileManagerHolder.cm;
    }

    public void add(FileSocket cs) {
        vector.add(cs);
    }

    public void publish(FileSocket cs, String out) {
        for (int i = 0; i < vector.size(); i++) {
            FileSocket csFileSocket = vector.get(i);
            // 不发给自己，只是发给别人
            if (!cs.equals(csFileSocket)) {
                csFileSocket.out(out);
            }
        }
    }
    //发给所有的人
    public void publishAll(String out) {
        for (int i = 0; i < vector.size(); i++) {
            FileSocket csChatSocket = vector.get(i);
            csChatSocket.out(out);
        }
    }

}