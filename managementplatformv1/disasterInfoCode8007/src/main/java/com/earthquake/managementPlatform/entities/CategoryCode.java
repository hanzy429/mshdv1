package com.earthquake.managementPlatform.entities;

import org.springframework.stereotype.Repository;

@Repository
public class CategoryCode {
    private String categoryInfo;


    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public String codeForCategory(){
        if(categoryInfo.equals("人员死亡")||categoryInfo.equals("111")){
            return "111";
        }
        else if (categoryInfo.equals("人员受伤")||categoryInfo.equals("112")){
            return "112";
        }
        else if (categoryInfo.equals("人员失踪")||categoryInfo.equals("113")){
            return "113";
        }
        else if(categoryInfo.equals("房屋破坏土木")||categoryInfo.equals("221")){
            return "221";
        }
        else if(categoryInfo.equals("房屋破坏砖木")||categoryInfo.equals("222")){
            return "222";
        }
        else if(categoryInfo.equals("房屋破坏砖混")||categoryInfo.equals("223")){
            return "223";
        }
        else if(categoryInfo.equals("房屋破坏框架")||categoryInfo.equals("224")){
            return "224";
        }
        else if(categoryInfo.equals("房屋破坏其他")||categoryInfo.equals("225")){
            return "225";
        }
        else if(categoryInfo.equals("生命线工程灾情交通")||categoryInfo.equals("331")){
            return "331";
        }
        else if(categoryInfo.equals("生命线工程灾情供水")||categoryInfo.equals("332")){
            return "332";
        }
        else if(categoryInfo.equals("生命线工程灾情输油")||categoryInfo.equals("333")){
            return "333";
        }
        else if(categoryInfo.equals("生命线工程灾情燃气")||categoryInfo.equals("334")){
            return "334";
        }
        else if(categoryInfo.equals("生命线工程灾情电力")||categoryInfo.equals("335")){
            return "335";
        }
        else if(categoryInfo.equals("生命线工程灾情通信")||categoryInfo.equals("336")){
            return "336";
        }
        else if(categoryInfo.equals("生命线工程灾情水利")||categoryInfo.equals("337")){
            return "337";
        }
        else if(categoryInfo.equals("次生灾害崩塌")||categoryInfo.equals("441")){
            return "441";
        }
        else if(categoryInfo.equals("次生灾害滑坡")||categoryInfo.equals("442")){
            return "442";
        }
        else if(categoryInfo.equals("次生灾害泥石流")||categoryInfo.equals("443")){
            return "443";
        }
        else if(categoryInfo.equals("次生灾害岩溶塌陷")||categoryInfo.equals("444")){
            return "444";
        }
        else if(categoryInfo.equals("次生灾害地裂缝")||categoryInfo.equals("445")){
            return "445";
        }
        else if(categoryInfo.equals("次生灾害地面沉降")||categoryInfo.equals("446")){
            return "446";
        }
        else if(categoryInfo.equals("次生灾害其他")||categoryInfo.equals("447")){
            return "447";
        }
        else if(categoryInfo.equals("基本震情")||categoryInfo.equals("551")){
            return "551";
        }
        else if(categoryInfo.equals("灾情预测")||categoryInfo.equals("552")){
            return "552";
        }
        return "000";
    }
}
