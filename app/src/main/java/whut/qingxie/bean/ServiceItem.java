package whut.qingxie.bean;

/*
 *自定义志愿服务页面控件
 */

public class ServiceItem {
    private String name;    //活动名称
    private String time;    //活动时间及单位
    private int num;        //活动序号（写在卡片右上角）
    private String info;    //活动信息
    private int status;     //活动状态 1未开始 2面试中 3进行中 4已完成

    public ServiceItem(String Name,String Time,int Num,String Info,int Status){
        name=Name;
        time=Time;
        num=Num;
        if(Info.length()>58)
            info = Info.substring(0,57) + "...";
        else
            info=Info;
        status=Status;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getNum() {
        return num;
    }

    public String getInfo() {
        return info;
    }

    public int getStatus() {
        return status;
    }
}
