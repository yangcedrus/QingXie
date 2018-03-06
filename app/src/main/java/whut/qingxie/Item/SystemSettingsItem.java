package whut.qingxie.Item;

import whut.qingxie.R;

/*
 *自定义控件
 */

public class SystemSettingsItem {
    private String name;
    private int imageId;

    public SystemSettingsItem(String name){
        this.name=name;
        this.imageId= R.drawable.ic_chevron_right_black_24dp;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}
