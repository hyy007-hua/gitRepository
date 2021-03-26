package com.chzu.util.layimInit;


import java.util.List;

/**
 * 该类用来实例化聊天室的好友信息及群信息，参考前端layIM框架初始化接口的数据json格式
 */
public class LayIMInit {
    private Mine mine;
    private List<Friend> friend;
    //private List<ChatGroup> group;

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public List<Friend> getFriend() {
        return friend;
    }

    public void setFriend(List<Friend> friend) {
        this.friend = friend;
    }

    /*public List<ChatGroup> getGroup() {
        return group;
    }

    public void setGroup(List<ChatGroup> group) {
        this.group = group;
    }*/
}
