package com.allen.loltool.free_hero.bean;

import java.util.List;

/**
 * Created by hardy on 2015/11/13.
 */
public class FreeHeroBean {


    /**
     * start_time : 2015-11-06 00:00:00
     * data : [{"en_name":"Fiddlesticks","name":"费德提克 末日使者","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Fiddlesticks.png","money":"2000.0","coin":"1350.0","id":"9","newmoney":"","newhero":"false"},{"en_name":"Kayle","name":"凯尔 审判天使","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Kayle.png","money":"1000.0","coin":"450.0","id":"10","newmoney":"500","newhero":"false"},{"en_name":"MasterYi","name":"易 无极剑圣","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/MasterYi.png","money":"1000.0","coin":"450.0","id":"11","newmoney":"","newhero":"false"},{"en_name":"Twitch","name":"图奇 瘟疫之源","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Twitch.png","money":"3000.0","coin":"4800.0","id":"29","newmoney":"1500","newhero":"false"},{"en_name":"Veigar","name":"维迦 邪恶小法师","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Veigar.png","money":"2000.0","coin":"1350.0","id":"45","newmoney":"","newhero":"true"},{"en_name":"Orianna","name":"奥莉安娜 发条魔灵","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Orianna.png","money":"3000.0","coin":"6300.0","id":"61","newmoney":"","newhero":"false"},{"en_name":"Udyr","name":"乌迪尔 兽灵行者","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Udyr.png","money":"2500.0","coin":"3150.0","id":"77","newmoney":"1250","newhero":"false"},{"en_name":"Talon","name":"泰隆 刀锋之影","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Talon.png","money":"4500.0","coin":"6300.0","id":"91","newmoney":"","newhero":"false"},{"en_name":"Sejuani","name":"瑟庄妮 凛冬之怒","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Sejuani.png","money":"3500.0","coin":"4800.0","id":"113","newmoney":"","newhero":"false"},{"en_name":"Kalista","name":"卡莉丝塔 复仇之矛","img":"/static/lol_imgs/imgs/2015-10-20/img/champion/Kalista.png","money":"4500","coin":"6300","id":"429","newmoney":"","newhero":"false"}]
     * end_time : 2015-11-13 00:00:00
     */

    private String start_time;
    private String end_time;
    /**
     * en_name : Fiddlesticks
     * name : 费德提克 末日使者
     * img : /static/lol_imgs/imgs/2015-10-20/img/champion/Fiddlesticks.png
     * money : 2000.0
     * coin : 1350.0
     * id : 9
     * newmoney :
     * newhero : false
     */

    private List<DataEntity> data;

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
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
