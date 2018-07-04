package whut.qingxie.entity.activity;

import java.util.Date;

public class TempActivity {
    private Integer id;     //活动ID

    private String name;       //活动名称

    private Integer managerId;  //活动管理人ID

    private String type;    //活动类型

    private Integer status; //活动状态

    private Double hours;   //活动总工时

    private Double hourPerTime; //活动每次工时

    private Integer needVolunteers; //活动需要人数

    private String place;   //活动地点

    private String general;     //活动概况

    private String descriptions;    //活动详情

    private Date regTime;

    private Date regEndTime;

    private Date interviewTime; //面试时间

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String sponsor; //举办方

    private String homepagePic;

    public TempActivity() {
    }

    public TempActivity(Integer id, String name, Integer managerId, String type, Integer status, Double hours, Double hourPerTime, Integer needVolunteers, String place, String general, String descriptions) {
        this.id = id;
        this.name = name;
        this.managerId = managerId;
        this.type = type;
        this.status = status;
        this.hours = hours;
        this.hourPerTime = hourPerTime;
        this.needVolunteers = needVolunteers;
        this.place = place;
        this.general = general;
        this.descriptions = descriptions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getHourPerTime() {
        return hourPerTime;
    }

    public void setHourPerTime(Double hourPerTime) {
        this.hourPerTime = hourPerTime;
    }

    public Integer getNeedVolunteers() {
        return needVolunteers;
    }

    public void setNeedVolunteers(Integer needVolunteers) {
        this.needVolunteers = needVolunteers;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(Date regEndTime) {
        this.regEndTime = regEndTime;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getHomepagePic() {
        return homepagePic;
    }

    public void setHomepagePic(String homepagePath) {
        this.homepagePic = homepagePath;
    }
}
