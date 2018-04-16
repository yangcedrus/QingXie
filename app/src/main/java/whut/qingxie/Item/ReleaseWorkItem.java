package whut.qingxie.Item;

/**
 * 发布活动
 */

public class ReleaseWorkItem {
    String s1, s2;   //s1前面，s2后面

    public ReleaseWorkItem(String S1, String S2) {
        s1 = S1;
        s2 = S2;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS1() {
        return s1;
    }
}
