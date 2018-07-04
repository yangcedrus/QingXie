package whut.qingxie.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class UserExperience implements Parcelable {
    private Integer id;

    private String activityName;

    private Date begin;

    private Date end;

    public UserExperience() {

    }

    public UserExperience(String activityName, Date begin, Date end) {
        this.activityName = activityName;
        this.begin = begin;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(activityName);
        dest.writeLong(begin == null ? -1 : begin.getTime());
        dest.writeLong(end == null ? -1 : end.getTime());
    }

    public static final Parcelable.Creator<UserExperience> CREATOR = new Parcelable.Creator<UserExperience>() {
        @Override
        public UserExperience createFromParcel(Parcel source) {
            UserExperience item = new UserExperience();
            item.id = source.readInt();
            item.activityName = source.readString();
            Long date = source.readLong();
            if (date > 0)
                item.begin = new Date(date);
            date = source.readLong();
            if (date > 0)
                item.end = new Date(date);
            return item;
        }

        @Override
        public UserExperience[] newArray(int size) {
            return new UserExperience[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}