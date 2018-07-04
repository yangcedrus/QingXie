package whut.qingxie.entity.activity;

import java.util.Date;

public class HomepagePicture {
    private Integer id;

    private Integer activityId;

    private String general;

    private String homePagePic;

    private Date createTime;

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

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

    public String getHomePagePic() {
        return homePagePic;
    }

    public void setHomePagePic(String homePagePic) {
        this.homePagePic = homePagePic == null ? null : homePagePic.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}