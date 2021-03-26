package com.chzu.util.layimInit;
/**
 * 该类用来实例化聊天室的好友信息及群信息，参考前端layIM框架初始化接口的数据json格式
 * mine： 登录用户信息
 * username：用户名
 * id:用户唯一id
 * status:在线状态
 * sign:签名
 * avatar:头像图片的地址
 */
public class Mine {
    private String username;
    private String id;
    private String status;
    private String sign;
    private String avatar;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
