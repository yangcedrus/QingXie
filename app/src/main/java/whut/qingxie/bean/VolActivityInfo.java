package whut.qingxie.bean;

public class VolActivityInfo {
    private Integer id;

    private String name;

    private Integer managerId;

    private String type;

    private Integer status;

    private double hours;

    private double hourPerTime;

    private Integer needVolunteers;

    private String place;

    private String general;

    private String descriptions;

    private String regTime;

    private String regEndTime;

    private String interviewTime;

    private String startTime;

    private String endTime;

    private String createTime;

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
