package com.allen.loltool.hero.bean;

import java.util.List;

/**
 * Created by Allen on 2015/12/29.
 */
public class ADBean {

    /**
     * next :
     * this_page_num : 3
     * list : [{"channel_id":"<2>:<12>,<2>:<134>,<2>:<134>:<136>,<2>:<134>:<140>,<2>:<13>,<2>:<32>,<2>:<3>","channel_desc":"鎺ㄨ崘","article_id":"13798","insert_date":"2015-12-29 10:32:53","publication_date":"2015-12-29 10:29:38","is_direct":"False","is_top":"False","article_url":"98/article_13798.shtml","title":"缇庢湇蹇锛氬☉濞滅绉樺奖鐗�","image_url_small":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/291041398094322_282.jpg","image_url_big":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/291041398094322_480.jpg","image_spec":"1","image_with_btn":"False","score":"3","summary":"濞戝鐨勯閮ㄥソ鍍忚鍒虹┛銆�","targetid":"1281396240","is_act":"0","is_hot":"0","is_new":"0"},{"channel_id":"<2>:<13>,<2>:<3>","channel_desc":"鎺ㄨ崘","article_id":"13703","insert_date":"2015-12-25 09:33:41","publication_date":"2015-12-28 17:40:07","is_direct":"True","is_top":"False","article_url":"http://lol.qq.com/m/act/a20151224assembly/","title":"闆粭闆嗙粨鍙� 璧㈠彇鍐伴洩鑺傚浘鏍�","image_url_small":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/28170749800758_282.jpg","image_url_big":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/28170749800758_480.jpg","image_spec":"1","image_with_btn":"False","score":"3","summary":"鍙備笌鎴樻枟锛屽畬鎴愪换鍔\u2033氨鍙互灏嗗畠浠鍥炲浜嗗摝!","targetid":"1278360270","is_act":"0","is_hot":"0","is_new":"0"},{"channel_id":"<2>:<12>,<2>:<134>,<2>:<134>:<136>,<2>:<134>:<140>,<2>:<13>,<2>:<3>","channel_desc":"鎺ㄨ崘","article_id":"13478","insert_date":"2015-12-15 12:41:14","publication_date":"2015-12-15 12:19:16","is_direct":"False","is_top":"False","article_url":"78/article_13478.shtml","title":"鐪嬶紝鍐伴洩鑺傛潵鍟︼紒","image_url_small":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/151241143579294_282.jpg","image_url_big":"http://ossweb-img.qq.com/upload/qqtalk/news/201512/151241143579294_480.jpg","image_spec":"1","image_with_btn":"False","score":"3","summary":"鍐伴洩鑺傛柊鐨偆锛屽啺闆妭绀煎寘锛岄瓌缃楃帇妯\u2033紡鍥炲綊锛�","targetid":"1270374348","video_info":"t0019e5sz5j 153","is_act":"0","is_hot":"0","is_new":"0"}]
     */

    private String next;
    private String this_page_num;
    /**
     * channel_id : <2>:<12>,<2>:<134>,<2>:<134>:<136>,<2>:<134>:<140>,<2>:<13>,<2>:<32>,<2>:<3>
     * channel_desc : 鎺ㄨ崘
     * article_id : 13798
     * insert_date : 2015-12-29 10:32:53
     * publication_date : 2015-12-29 10:29:38
     * is_direct : False
     * is_top : False
     * article_url : 98/article_13798.shtml
     * title : 缇庢湇蹇锛氬☉濞滅绉樺奖鐗�
     * image_url_small : http://ossweb-img.qq.com/upload/qqtalk/news/201512/291041398094322_282.jpg
     * image_url_big : http://ossweb-img.qq.com/upload/qqtalk/news/201512/291041398094322_480.jpg
     * image_spec : 1
     * image_with_btn : False
     * score : 3
     * summary : 濞戝鐨勯閮ㄥソ鍍忚鍒虹┛銆�
     * targetid : 1281396240
     * is_act : 0
     * is_hot : 0
     * is_new : 0
     */

    private List<ListEntity> list;

    public void setNext(String next) {
        this.next = next;
    }

    public void setThis_page_num(String this_page_num) {
        this.this_page_num = this_page_num;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getNext() {
        return next;
    }

    public String getThis_page_num() {
        return this_page_num;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String channel_id;
        private String channel_desc;
        private String article_id;
        private String insert_date;
        private String publication_date;
        private String is_direct;
        private String is_top;
        private String article_url;
        private String title;
        private String image_url_small;
        private String image_url_big;
        private String image_spec;
        private String image_with_btn;
        private String score;
        private String summary;
        private String targetid;
        private String is_act;
        private String is_hot;
        private String is_new;

        public void setChannel_id(String channel_id) {
            this.channel_id = channel_id;
        }

        public void setChannel_desc(String channel_desc) {
            this.channel_desc = channel_desc;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public void setInsert_date(String insert_date) {
            this.insert_date = insert_date;
        }

        public void setPublication_date(String publication_date) {
            this.publication_date = publication_date;
        }

        public void setIs_direct(String is_direct) {
            this.is_direct = is_direct;
        }

        public void setIs_top(String is_top) {
            this.is_top = is_top;
        }

        public void setArticle_url(String article_url) {
            this.article_url = article_url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setImage_url_small(String image_url_small) {
            this.image_url_small = image_url_small;
        }

        public void setImage_url_big(String image_url_big) {
            this.image_url_big = image_url_big;
        }

        public void setImage_spec(String image_spec) {
            this.image_spec = image_spec;
        }

        public void setImage_with_btn(String image_with_btn) {
            this.image_with_btn = image_with_btn;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setTargetid(String targetid) {
            this.targetid = targetid;
        }

        public void setIs_act(String is_act) {
            this.is_act = is_act;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }

        public void setIs_new(String is_new) {
            this.is_new = is_new;
        }

        public String getChannel_id() {
            return channel_id;
        }

        public String getChannel_desc() {
            return channel_desc;
        }

        public String getArticle_id() {
            return article_id;
        }

        public String getInsert_date() {
            return insert_date;
        }

        public String getPublication_date() {
            return publication_date;
        }

        public String getIs_direct() {
            return is_direct;
        }

        public String getIs_top() {
            return is_top;
        }

        public String getArticle_url() {
            return article_url;
        }

        public String getTitle() {
            return title;
        }

        public String getImage_url_small() {
            return image_url_small;
        }

        public String getImage_url_big() {
            return image_url_big;
        }

        public String getImage_spec() {
            return image_spec;
        }

        public String getImage_with_btn() {
            return image_with_btn;
        }

        public String getScore() {
            return score;
        }

        public String getSummary() {
            return summary;
        }

        public String getTargetid() {
            return targetid;
        }

        public String getIs_act() {
            return is_act;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public String getIs_new() {
            return is_new;
        }
    }
}
