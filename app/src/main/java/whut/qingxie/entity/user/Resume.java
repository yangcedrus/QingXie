package whut.qingxie.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

/**
 * @author evans 2018/3/9 8:57
 */

public class Resume implements Parcelable {
    private int userId;
    private String studentId;
    private String name;
    private String gender;
    private String className;
    private Date birthDate;
    private Integer age;
    private String politicalStatus;
    /**
     * 个人简历
     */
    private String profile;
    private String qq;
    private String telephone;
    private String email;
    private String wechat;
    private Double hours;
    private List<UserExperience> experiences;

    public Resume() {
    }

    public Resume(User user) {
        this.userId = user.getId();
        this.studentId = user.getStudentId();
        this.name = user.getName();
        this.qq = user.getQq();
        this.telephone = user.getTelephone();
        this.email = user.getTelephone();
        this.wechat = user.getWechat();
        this.hours = user.getHours();
        this.birthDate = user.getBirthDate();
        this.age = user.getAge();
        this.politicalStatus = user.getPoliticalStatus();
        this.profile = user.getProfile();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public List<UserExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<UserExperience> experiences) {
        this.experiences = experiences;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(studentId);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(className);
        dest.writeLong(birthDate == null ? -1 : birthDate.getTime());
        dest.writeInt(age);
        dest.writeString(politicalStatus);

        dest.writeString(profile);
        dest.writeString(qq);
        dest.writeString(telephone);
        dest.writeString(email);
        dest.writeString(wechat);
        dest.writeTypedList(experiences);
    }

    public static final Parcelable.Creator<Resume> CREATOR = new Parcelable.Creator<Resume>() {
        @Override
        public Resume createFromParcel(Parcel source) {
            Resume item = new Resume();
            item.userId=source.readInt();
            item.studentId=source.readString();
            item.name=source.readString();
            item.gender=source.readString();
            item.className=source.readString();
            Long date = source.readLong();
            if(date>0)
                item.birthDate=new Date(date);
            item.age=source.readInt();
            item.politicalStatus=source.readString();

            item.profile=source.readString();
            item.qq=source.readString();
            item.telephone=source.readString();
            item.email=source.readString();
            item.wechat=source.readString();
            item.experiences=source.createTypedArrayList(UserExperience.CREATOR);
            //source.readTypedList(item.experiences,UserExperience.CREATOR);
            return item;
        }

        @Override
        public Resume[] newArray(int size) {
            return new Resume[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
