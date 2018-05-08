package whut.qingxie.entity.user;

/**
 * 签到页面
 */
public class UserSign {
    private Integer id =-1;
    private String name;
    private boolean confirm=false;

    public UserSign() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }
}
