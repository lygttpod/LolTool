package com.allen.loltool.server_list.bean;

import java.util.List;

/**
 * Created by hardy on 2015/11/12.
 */
public class ServerListBean {

    /**
     * status : 正常
     * sname : 比尔吉沃特
     * id : 2
     * utime : 2015-11-12 14:16:19
     */

    private List<WTEntity> 网通;
    /**
     * status : 正常
     * sname : 艾欧尼亚
     * id : 1
     * utime : 2015-11-12 14:16:19
     */

    private List<DXEntity> 电信;
    /**
     * status : 正常
     * sname : 教育网专区
     * id : 21
     * utime : 2015-11-12 14:16:19
     */

    private List<OtherEntity> 其他;

    public void set网通(List<WTEntity> 网通) {
        this.网通 = 网通;
    }

    public void set电信(List<DXEntity> 电信) {
        this.电信 = 电信;
    }

    public void set其他(List<OtherEntity> 其他) {
        this.其他 = 其他;
    }

    public List<WTEntity> get网通() {
        return 网通;
    }

    public List<DXEntity> get电信() {
        return 电信;
    }

    public List<OtherEntity> get其他() {
        return 其他;
    }

    public static class WTEntity {
        private String status;
        private String sname;
        private int id;
        private String utime;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUtime(String utime) {
            this.utime = utime;
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

        public String getUtime() {
            return utime;
        }
    }

    public static class DXEntity {
        private String status;
        private String sname;
        private int id;
        private String utime;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUtime(String utime) {
            this.utime = utime;
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

        public String getUtime() {
            return utime;
        }
    }

    public static class OtherEntity {
        private String status;
        private String sname;
        private int id;
        private String utime;

        public void setStatus(String status) {
            this.status = status;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUtime(String utime) {
            this.utime = utime;
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

        public String getUtime() {
            return utime;
        }
    }
}
