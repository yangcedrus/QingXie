package whut.qingxie.Item;

/**
 * Created by 22278 on 2018/3/12.
 * 我的志愿工时
 */

public class MyHoursItem {
    String serviceTime; //服务时间
    String servicePlace;    //服务地点
    String servicePeople;   //服务对象
    double hours;   //工时
    boolean status;  //认证状况

    public MyHoursItem(String serviceTime, String servicePlace, String servicePeople, double hours, boolean state) {
        this.serviceTime = serviceTime;
        this.servicePlace = servicePlace;
        this.servicePeople = servicePeople;
        this.hours = hours;
        this.status = state;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServicePlace() {
        return servicePlace;
    }

    public void setServicePlace(String servicePlace) {
        this.servicePlace = servicePlace;
    }

    public String getServicePeople() {
        return servicePeople;
    }

    public void setServicePeople(String servicePeople) {
        this.servicePeople = servicePeople;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
