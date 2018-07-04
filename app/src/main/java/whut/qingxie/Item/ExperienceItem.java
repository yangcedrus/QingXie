package whut.qingxie.Item;

/**
 * 个人志愿经历
 */

public class ExperienceItem {
    String time, name;

    public ExperienceItem(String time, String name) {
        this.time = time;
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
