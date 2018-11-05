package com.hp.test.Wangluo;



import java.util.List;

public class Person {
    private String roleName;//角色名称
    private Integer roletType;//角色类型  0-地主/1-非地主
    private List<Poker> pokerList;//角色的牌

    public String getRoleName() {
        return roletType==Constant.DI_ZHU?roleName+"(地主)":roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public Integer getRoletType() {
        return roletType;
    }
    public void setRoletType(Integer roletType) {
        this.roletType = roletType;
    }
    public List<Poker> getPokerList() {
        return pokerList;
    }
    public void setPokerList(List<Poker> pokerList) {
        this.pokerList = pokerList;
    }




}
