package whut.qingxie.bean;

public class MyInfoItem {
    String s1,s2;    //两个字符串
    int image;  //图片

    //构造函数
    public MyInfoItem(String S1, String S2, int Image){
        s1=S1;
        s2=S2;
        image=Image;
    }

    public String getS1() {
        return s1;
    }

    public int getImage() {
        return image;
    }

    public String getS2() {
        return s2;
    }
}
