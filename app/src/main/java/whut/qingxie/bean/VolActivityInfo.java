package whut.qingxie.bean;

public class VolActivityInfo {
    private Integer id;     //活动ID

    private String name;       //活动名称

    private Integer managerId;  //活动管理人ID

    private String type;    //活动类型

    private Integer status; //活动状态

    private double hours;   //活动总工时

    private double hourPerTime; //活动每次工时

    private Integer needVolunteers; //活动需要人数

    private String place;   //活动地点

    private String general;     //活动概况

    private String descriptions;    //活动详情

    private String regTime;     //活动报名时间

    private String regEndTime;      //活动报名截止时间

    private String interviewTime;   //活动面试时间

    private String startTime;   //活动开始时间

    private String endTime; //活动截止时间

    private String createTime;  //活动创建时间

    public VolActivityInfo(Integer id, String name, Integer managerId, String type, Integer status, double hours, double hourPerTime, Integer needVolunteers, String place, String general, String descriptions, String regTime, String regEndTime, String interviewTime, String startTime, String endTime, String createTime) {
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
        this.regTime = regTime;
        this.regEndTime = regEndTime;
        this.interviewTime = interviewTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createTime = createTime;
    }

    public VolActivityInfo() {
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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getHourPerTime() {
        return hourPerTime;
    }

    public void setHourPerTime(double hourPerTime) {
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

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getRegEndTime() {
        return regEndTime;
    }

    public void setRegEndTime(String regEndTime) {
        this.regEndTime = regEndTime;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
