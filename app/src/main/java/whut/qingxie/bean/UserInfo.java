package whut.qingxie.bean;

public class UserInfo {
    private Integer id;

    private String studentId;

    private String name;

    private String password;

    private String flag;

    private Integer roleId;

    private String gender;

    private Integer classId;

    private double hours;

    private Integer iconId;

    private String telephone;

    private String qq;

    private String email;

    private String wechat;

    private String token;

    private String validation;

    private String lastLoginTime;

    public UserInfo(Integer id, String studentId, String name, String password, String flag, Integer roleId, String gender, Integer classId, double hours, Integer iconId, String telephone, String qq, String email, String wechat, String token, String validation, String lastLoginTime) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.flag = flag;
        this.roleId = roleId;
        this.gender = gender;
        this.classId = classId;
        this.hours = hours;
        this.iconId = iconId;
        this.telephone = telephone;
        this.qq = qq;
        this.email = email;
        this.wechat = wechat;
        this.token = token;
        this.validation = validation;
        this.lastLoginTime = lastLoginTime;
    }

    public UserInfo() {
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

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
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

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}