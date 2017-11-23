package whut.qingxie.bean;

import whut.qingxie.R;

/*
 *自定义控件
 */

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
