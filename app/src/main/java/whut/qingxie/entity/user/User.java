package whut.qingxie.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class User implements Parcelable,Serializable {
    private Integer id;

    private String studentId;

    private String name;

    private String password;

    private String flag;

    private Integer roleId;

    private String gender;

    private Integer classId;

    private Double hours;

    private Integer iconId;

    private String telephone;

    private String qq;

    private String email;

    private String wechat;

    private String token;

    private String validation;

    private Date birthDate;

    private String politicalStatus;

    private Integer age;

    private String profile;

    private Date lastLoginTime;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Integer getIconId() {
        return iconId;
    }

    public void setIconId(Integer iconId) {
        this.iconId = iconId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(studentId);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(flag);
        dest.writeInt(roleId==null?0:roleId);
        dest.writeString(gender);
        dest.writeInt(classId==null?0:classId);
        dest.writeDouble(hours==null?0.0:hours);
        dest.writeInt(iconId==null?0:iconId);
        dest.writeString(telephone);
        dest.writeString(qq);
        dest.writeString(email);
        dest.writeString(wechat);
        dest.writeString(token);
        dest.writeString(validation);
        dest.writeLong(birthDate == null ? -1L : birthDate.getTime());
        dest.writeString(politicalStatus);
        dest.writeInt(age==null?18:age);
        dest.writeLong(lastLoginTime == null ? -1L : lastLoginTime.getTime());

    }

    //Parcelable方法传递对象
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            User user = new User();
            user.id = source.readInt();
            user.studentId = source.readString();
            user.name = source.readString();
            user.password = source.readString();
            user.flag = source.readString();
            user.roleId = source.readInt();
            user.gender = source.readString();
            user.classId = source.readInt();
            user.hours = source.readDouble();
            user.iconId = source.readInt();
            user.telephone = source.readString();
            user.qq = source.readString();
            user.email = source.readString();
            user.wechat = source.readString();
            user.token = source.readString();
            user.validation = source.readString();
            long date = source.readLong();
            if (date > 0) {
                user.birthDate = new Date(date);
            }
            user.politicalStatus = source.readString();
            user.age = source.readInt();
            user.profile = source.readString();
            date = source.readLong();
            if (date > 0) {
                user.lastLoginTime = new Date(source.readLong());
            }
            return user;
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
