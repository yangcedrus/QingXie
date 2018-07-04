package whut.qingxie.entity.activity;

import java.util.Date;

/**
 * @author evans 2018/4/2 22:04
 */

public class Activity4User {
    /**
     * 记录id
     */
    private int id;
    /**
     * 活动id
     */
    private int activityId;
    /**
     * 活动名称
     */
    private String name;

    /**
     * 在活动中担任的角色
     */
    private String role;
    /**
     * 主办方
     */
    private String sponsor;
    /**
     * 活动简介
     */
    private String profile;
    /**
     * 1.参与进行状态，例：用户1 正在面试 活动2
     * 2.当作为收藏活动时，显示活动进行状态  例：活动1已结束
     */
    private String status;
    /**
     * 开始参与活动时间或者开始收藏活动的时间
     */
    private Date createTime;
    /**
     * 收藏活动没有结束时间，为null
     * 志愿工作或者志愿服务为 结束时间为志愿活动结束时间。
     */
    private Date endTime;

    public Activity4User() {
    }

    public Activity4User(int id, int activityId, String name, String sponsor, String profile, String status, Date createTime, Date endTime) {
        this.id = id;
        this.activityId = activityId;
        this.name = name;
        this.sponsor = sponsor;
        this.profile = profile;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Activity4User(int id, int activityId, String name, String role,
                         String sponsor, String profile, String status,
                         Date createTime, Date endTime) {
        this.id = id;
        this.activityId = activityId;
        this.name = name;
        this.role = role;
        this.sponsor = sponsor;
        this.profile = profile;
        this.status = status;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
