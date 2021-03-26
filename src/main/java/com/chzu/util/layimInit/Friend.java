package com.chzu.util.layimInit;


import java.util.List;
/**
 * 该类用来实例化聊天室的好友信息及群信息，参考前端layIM框架初始化接口的数据json格式
 * friend：用户的好友信息
 * groupname:好友分组的组名
 * id:1,2,3...前端页面据此显示分组的上下顺序，1为置顶
 * online:在线人员个数
 * list:好友信息
 */
public class Friend {
    private String groupname;
    private String id;
    private int online;
    private List<Mine> list;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public List<Mine> getList() {
        return list;
    }

    public void setList(List<Mine> list) {
        this.list = list;
    }
}
