package com.allen.loltool.all_hero.bean;

import java.util.List;

/**
 * Created by hardy on 2015/11/13.
 */
public class AllHeroBean {


    /**
     * total_pages : 7
     * limit : 20
     * page : 1
     * data : [{"en_name":"Annie","name":"安妮 黑暗之女","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Annie.png","money":"2000.0","coin":"4800.0","id":"1","newmoney":"1000","newhero":"false"},{"en_name":"Olaf","name":"奥拉夫 狂战士","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Olaf.png","money":"1500.0","coin":"3150.0","id":"2","newmoney":"750","newhero":"false"},{"en_name":"Galio","name":"加里奥 哨兵之殇","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Galio.png","money":"2000.0","coin":"4800.0","id":"3","newmoney":"","newhero":"false"},{"en_name":"TwistedFate","name":"崔斯特 卡牌大师","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/TwistedFate.png","money":"3000.0","coin":"4800.0","id":"4","newmoney":"1500","newhero":"false"},{"en_name":"XinZhao","name":"赵信 德邦总管","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/XinZhao.png","money":"2500.0","coin":"3150.0","id":"5","newmoney":"","newhero":"false"},{"en_name":"Urgot","name":"厄加特 首领之傲","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Urgot.png","money":"1000.0","coin":"1350.0","id":"6","newmoney":"","newhero":"false"},{"en_name":"Leblanc","name":"乐芙兰 诡术妖姬","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Leblanc.png","money":"2500.0","coin":"4800.0","id":"7","newmoney":"1250","newhero":"false"},{"en_name":"Vladimir","name":"弗拉基米尔 猩红收割者","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Vladimir.png","money":"2500.0","coin":"3150.0","id":"8","newmoney":"1250","newhero":"false"},{"en_name":"Fiddlesticks","name":"费德提克 末日使者","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Fiddlesticks.png","money":"2000.0","coin":"1350.0","id":"9","newmoney":"","newhero":"false"},{"en_name":"Kayle","name":"凯尔 审判天使","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Kayle.png","money":"1000.0","coin":"450.0","id":"10","newmoney":"500","newhero":"false"},{"en_name":"MasterYi","name":"易 无极剑圣","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/MasterYi.png","money":"1000.0","coin":"450.0","id":"11","newmoney":"","newhero":"false"},{"en_name":"Alistar","name":"阿利斯塔 牛头酋长","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Alistar.png","money":"1000.0","coin":"1350.0","id":"12","newmoney":"500","newhero":"false"},{"en_name":"Ryze","name":"瑞兹 流浪法师","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Ryze.png","money":"1000.0","coin":"450.0","id":"13","newmoney":"","newhero":"false"},{"en_name":"Sion","name":"赛恩 亡灵战神","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Sion.png","money":"2000.0","coin":"1350.0","id":"14","newmoney":"","newhero":"false"},{"en_name":"Sivir","name":"希维尔 战争女神","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Sivir.png","money":"1000.0","coin":"450.0","id":"15","newmoney":"","newhero":"false"},{"en_name":"Soraka","name":"索拉卡 众星之子","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Soraka.png","money":"1000.0","coin":"450.0","id":"16","newmoney":"","newhero":"false"},{"en_name":"Teemo","name":"提莫 迅捷斥候","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Teemo.png","money":"3500.0","coin":"6300.0","id":"17","newmoney":"1750","newhero":"false"},{"en_name":"Tristana","name":"崔丝塔娜 麦林炮手","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Tristana.png","money":"1000.0","coin":"1350.0","id":"18","newmoney":"500","newhero":"false"},{"en_name":"Warwick","name":"沃里克 嗜血猎手","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Warwick.png","money":"2500.0","coin":"3150.0","id":"19","newmoney":"","newhero":"false"},{"en_name":"Nunu","name":"努努 雪人骑士","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Nunu.png","money":"1000.0","coin":"450.0","id":"20","newmoney":"","newhero":"false"}]
     */

    private int total_pages;
    private int limit;
    private int page;
    /**
     * en_name : Annie
     * name : 安妮 黑暗之女
     * img : /static/lol_imgs/imgs/2015-10-20/img/champion/Annie.png
     * money : 2000.0
     * coin : 4800.0
     * id : 1
     * newmoney : 1000
     * newhero : false
     */

    private List<DataEntity> data;

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getLimit() {
        return limit;
    }

    public int getPage() {
        return page;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String en_name;
        private String name;
        private String img;
        private String money;
        private String coin;
        private String id;
        private String newmoney;
        private String newhero;

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNewmoney(String newmoney) {
            this.newmoney = newmoney;
        }

        public void setNewhero(String newhero) {
            this.newhero = newhero;
        }

        public String getEn_name() {
            return en_name;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public String getMoney() {
            return money;
        }

        public String getCoin() {
            return coin;
        }

        public String getId() {
            return id;
        }

        public String getNewmoney() {
            return newmoney;
        }

        public String getNewhero() {
            return newhero;
        }
    }
}
