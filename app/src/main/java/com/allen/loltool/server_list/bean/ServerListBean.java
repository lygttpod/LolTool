package com.allen.loltool.server_list.bean;

import java.util.List;

/**
 * Created by allen on 2015/11/12.
 */
public class ServerListBean {

    /**
     * data : [{"list":[{"status":"正常","sname":"比尔吉沃特","id":2},{"status":"拥挤","sname":"德玛西亚","id":6},{"status":"正常","sname":"弗雷尔卓德","id":9},{"status":"正常","sname":"无畏先锋","id":12},{"status":"正常","sname":"恕瑞玛","id":16},{"status":"正常","sname":"扭曲丛林","id":20},{"status":"正常","sname":"巨龙之巢","id":26}],"name":"网通"},{"list":[{"status":"拥挤","sname":"艾欧尼亚","id":1},{"status":"正常","sname":"祖安","id":3},{"status":"正常","sname":"诺克萨斯","id":4},{"status":"正常","sname":"班德尔城","id":5},{"status":"正常","sname":"皮尔特沃夫","id":7},{"status":"正常","sname":"战争学院","id":8},{"status":"正常","sname":"巨神峰","id":10},{"status":"正常","sname":"雷瑟守备","id":11},{"status":"正常","sname":"裁决之地","id":13},{"status":"拥挤","sname":"黑色玫瑰","id":14},{"status":"正常","sname":"暗影岛","id":15},{"status":"正常","sname":"钢铁烈阳","id":17},{"status":"正常","sname":"水晶之痕","id":18},{"status":"正常","sname":"均衡教派","id":19},{"status":"正常","sname":"影流","id":22},{"status":"正常","sname":"守望之海","id":23},{"status":"正常","sname":"征服之海","id":24},{"status":"正常","sname":"卡拉曼达","id":25},{"status":"正常","sname":"皮城警备","id":27}],"name":"电信"},{"list":[{"status":"正常","sname":"教育网专区","id":21}],"name":"其他"}]
     * utime : 2015-11-16 16:14:38
     */

    private String utime;
    /**
     * list : [{"status":"正常","sname":"比尔吉沃特","id":2},{"status":"拥挤","sname":"德玛西亚","id":6},{"status":"正常","sname":"弗雷尔卓德","id":9},{"status":"正常","sname":"无畏先锋","id":12},{"status":"正常","sname":"恕瑞玛","id":16},{"status":"正常","sname":"扭曲丛林","id":20},{"status":"正常","sname":"巨龙之巢","id":26}]
     * name : 网通
     */

    private List<DataEntity> data;

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getUtime() {
        return utime;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String name;
        /**
         * status : 正常
         * sname : 比尔吉沃特
         * id : 2
         */

        private List<ListEntity> list;

        public void setName(String name) {
            this.name = name;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public String getName() {
            return name;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public static class ListEntity {
            private String status;
            private String sname;
            private int id;

            public void setStatus(String status) {
                this.status = status;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public String getSname() {
                return sname;
            }

            public int getId() {
                return id;
            }
        }
    }
}
