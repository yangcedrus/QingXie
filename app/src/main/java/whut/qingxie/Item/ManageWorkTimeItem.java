package whut.qingxie.Item;

public class ManageWorkTimeItem {
    int image;
    String name,title,detail;

    public ManageWorkTimeItem(int image,String name,String title,String detail){
        this.image=image;
        this.name=name;
        this.title=title;
        this.detail=detail;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getDetail() {
        return detail;
    }
}
