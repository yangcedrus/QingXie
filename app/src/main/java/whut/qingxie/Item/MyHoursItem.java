package whut.qingxie.Item;

/**
 * Created by 22278 on 2018/3/12.
 * 我的志愿工时
 */

public class MyHoursItem {
    String activityStarTime; //服务时间
    String activityName;    //服务名称
    Integer count;  //参加次数
    double voluntaryHours;   //工时

    public String getActivityStarTime() {
        return activityStarTime;
    }

    public void setActivityStarTime(String activityStarTime) {
        this.activityStarTime = activityStarTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public double getVoluntaryHours() {
        return voluntaryHours;
    }

    public void setVoluntaryHours(double voluntaryHours) {
        this.voluntaryHours = voluntaryHours;
    }
}
