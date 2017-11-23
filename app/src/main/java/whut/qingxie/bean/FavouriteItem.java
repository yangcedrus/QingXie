package whut.qingxie.bean;

/*
 *自定义收藏页面控件
 */

public class FavouriteItem {
    String title;   //标题
    String text;    //文字

    public FavouriteItem(String Title,String Text){
        title=Title;
        if(Text.length()>=36) text = Text.substring(0, 35) + "...";
        else text = Text;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }
}
