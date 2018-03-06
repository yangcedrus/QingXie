package whut.qingxie.Item;

public class CardActivityItem {
    String title,info,ID;
    int days;
    boolean favourite;

    public CardActivityItem(String ID,String title,String info,int days,boolean favourite){
        this.ID=ID;
        this.title=title;
        this.info=info;
        this.days=days;
        this.favourite=favourite;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public int getDays() {
        return days;
    }

    public boolean getFavourite(){
        return favourite;
    }
}
