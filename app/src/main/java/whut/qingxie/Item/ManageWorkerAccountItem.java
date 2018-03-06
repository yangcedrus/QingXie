package whut.qingxie.Item;

public class ManageWorkerAccountItem {
    String s1,s2,s3,s4; //ID，账号ID，是否授权，时间

    public ManageWorkerAccountItem(String s1, String s2, String s3, String s4){
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
        this.s4=s4;
    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    public String getS4() {
        return s4;
    }
}
