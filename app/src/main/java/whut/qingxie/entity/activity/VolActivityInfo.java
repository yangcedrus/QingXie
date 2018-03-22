package whut.qingxie.entity.activity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class VolActivityInfo implements Parcelable, Serializable {
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

    private Date interviewTime;

    private Date startTime;

    private Date endTime;

    private Date createTime;

    private String sponser;

    private String homepagePic;

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

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getHomepagePic() {
        return homepagePic;
    }

    public void setHomepagePic(String homepagePic) {
        this.homepagePic = homepagePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(managerId);
        dest.writeString(type);
        dest.writeInt(status);
        dest.writeDouble(hours);
        dest.writeDouble(hourPerTime);
        dest.writeInt(needVolunteers);
        dest.writeString(place);
        dest.writeString(general);
        dest.writeString(descriptions);
        dest.writeLong(regTime == null ? -1 : regTime.getTime());
        dest.writeLong(regEndTime == null ? -1 : regEndTime.getTime());
        dest.writeLong(interviewTime == null ? -1 : interviewTime.getTime());
        dest.writeLong(startTime == null ? -1 : startTime.getTime());
        dest.writeLong(endTime == null ? -1 : endTime.getTime());
        dest.writeLong(createTime == null ? -1 : createTime.getTime());
        dest.writeString(sponser);
        dest.writeString(homepagePic);
    }

    public static final Parcelable.Creator<VolActivityInfo> CREATOR = new Parcelable.Creator<VolActivityInfo>() {
        @Override
        public VolActivityInfo createFromParcel(Parcel source) {
            VolActivityInfo item = new VolActivityInfo();
            Long date = 0L;
            item.id = source.readInt();
            item.name = source.readString();
            item.managerId = source.readInt();
            item.type = source.readString();
            item.status = source.readInt();
            item.hours = source.readDouble();
            item.hourPerTime = source.readDouble();
            item.needVolunteers = source.readInt();
            item.place = source.readString();
            item.general = source.readString();
            item.descriptions = source.readString();
            date = source.readLong();
            if (date > 0) {
                item.regTime = new Date(date);
            }
            date = source.readLong();
            if (date > 0) {
                item.regEndTime = new Date(date);
            }
            date = source.readLong();
            if (date > 0) {
                item.interviewTime = new Date(date);
            }
            date = source.readLong();
            if (date > 0) {
                item.startTime = new Date(date);
            }
            date = source.readLong();
            if (date > 0) {
                item.endTime = new Date(date);
            }
            date = source.readLong();
            if (date > 0) {
                item.createTime = new Date(date);
            }
            item.sponser = source.readString();
            item.homepagePic = source.readString();
            return item;
        }

        @Override
        public VolActivityInfo[] newArray(int size) {
            return new VolActivityInfo[size];
        }
    };
}
