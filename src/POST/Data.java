package POST;
public class Data {
    private String tag;
    private String value;
    public Data(String tag,String value){ this.tag=tag;this.value=value;
    }public String getTag(){ return tag;
    }public String getValue(){ return value;
    }public void setTag(){ this.tag=tag;
    }public void setValue(){ this.value=value;
    }public String textdata(){ return "标签："+tag+"内容："+value+"\n";
    }
}
