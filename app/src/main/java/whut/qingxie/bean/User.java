package whut.qingxie.bean;

/**
 * 用户对应的实体类
 * Created by evans on 2017/11/1.
 */

public class User {
    //学生学号
    private String studentId;
    private String password;
    //学生姓名
    private String name;
    //学生标识,默认为S, S:学生,Q:青协工作人员,A:管理人员
    private String flag;
    private String gender;
    //班级id
    private int classId;
    private String telephone;
    private String qq;
    private String email;

    public User() {
    }

    public User(String studentId, String password, String name, String flag) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.flag = flag;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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
}
