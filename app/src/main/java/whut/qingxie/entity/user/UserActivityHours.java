package whut.qingxie.entity.user;

import java.util.Date;

public class UserActivityHours extends UserActivityHoursKey {
    private Integer voluntaryHours;

    private Integer isConfirm;

    private Date createTime;

    public Integer getVoluntaryHours() {
        return voluntaryHours;
    }

    public void setVoluntaryHours(Integer voluntaryHours) {
        this.voluntaryHours = voluntaryHours;
    }

    public Integer getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Integer isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}