package POST;
import java.util.HashMap;
import java.util.Map;

public class Datas {
    public void datas() {
        Data T = new Data("T", "0");
        Data H = new Data("H", "0");
        Map dataslist = new HashMap();
        dataslist.put("T", T);
        dataslist.put("H", H);
        for (Object num:dataslist.keySet()){
            System.out.println(dataslist.get(num));
        }
    }
}
