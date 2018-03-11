package whut.qingxie.entity.activity;

import java.util.Date;

public class ActivityDetailsPicture {
    private Integer id;

    private Integer activityId;

    private String actividtDetailUri;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActividtDetailUri() {
        return actividtDetailUri;
    }

    public void setActividtDetailUri(String actividtDetailUri) {
        this.actividtDetailUri = actividtDetailUri == null ? null : actividtDetailUri.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}