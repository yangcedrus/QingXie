package whut.qingxie.Item;

/**
 * Created by 22278 on 2018/3/12.
 * 我的志愿工时
 */

public class MyHoursItem {
    String serviceTime; //服务时间
    String serviceName;    //服务名称
    double hours;   //工时
    boolean status;  //认证状况

    public MyHoursItem(String serviceTime, String serviceName, double hours, boolean state) {
        this.serviceTime = serviceTime;
        this.serviceName = serviceName;
        this.hours = hours;
        this.status = state;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
