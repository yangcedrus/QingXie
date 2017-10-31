package whut.qingxie.adapter;

import whut.qingxie.R;

//自定义适配器
public class MyItem {
    private String name;
    private int imageId=R.drawable.ic_home_black_24dp;

    public MyItem(String name){
        this.name=name;
        this.imageId= R.drawable.ic_home_black_24dp;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
