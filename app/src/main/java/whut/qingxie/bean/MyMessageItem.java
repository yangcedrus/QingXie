package whut.qingxie.bean;

/*
 *自定义我的消息页面控件
 */

public class MyMessageItem {
    String title;   //标题
    String text;    //文字
    int imageView;  //图片

    public MyMessageItem(String Title,String Text,int ImageView){
        title=Title;
        if(Text.length()>=25)
            text=Text.substring(0,25)+"...";
        else
            text=Text;
        imageView=ImageView;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public int getImageView() {
        return imageView;
    }
}
