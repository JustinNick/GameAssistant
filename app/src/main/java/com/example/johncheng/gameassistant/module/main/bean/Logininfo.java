package com.example.johncheng.gameassistant.module.main.bean;

/**
 * Created by johncheng on 2016/6/28.
 */
public class Logininfo {

    /**
     * uid : 318529449
     * detail_address :
     * hpic : http://www.yuu1.com//data/avatar/avatar_1.png
     * nickname : JustinNick
     * qq :
     * receiver : 13972455259
     * sex : 0
     * phone : 13972455259
     * username : 13972455259
     * score : 1200
     * expend : 1200
     * code : 100889
     * invite_num : 0
     * today_profit : 0
     * token : 501fv4/1ExoZPhS9Ibep4TM74rfJJzi2H7C6quDx9ZZ4fCM6F9O4YUe5G6zzjgs2gwX+HFArGQ
     */

    private InfoBean info;
    /**
     * info : {"uid":"318529449","detail_address":"","hpic":"http://www.yuu1.com//data/avatar/avatar_1.png","nickname":"JustinNick","qq":"","receiver":"13972455259","sex":"0","phone":"13972455259","username":"13972455259","score":"1200","expend":"1200","code":"100889","invite_num":0,"today_profit":0,"token":"501fv4/1ExoZPhS9Ibep4TM74rfJJzi2H7C6quDx9ZZ4fCM6F9O4YUe5G6zzjgs2gwX+HFArGQ"}
     * page : null
     * state : success
     */

    private Object page;
    private String state;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class InfoBean {

        private String uid;
        private String detail_address;
        private String hpic;
        private String nickname;
        private String qq;
        private String receiver;
        private String sex;
        private String phone;
        private String username;
        private String score;
        private String expend;
        private String code;
        private int invite_num;
        private int today_profit;
        private String token;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getHpic() {
            return hpic;
        }

        public void setHpic(String hpic) {
            this.hpic = hpic;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getExpend() {
            return expend;
        }

        public void setExpend(String expend) {
            this.expend = expend;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getInvite_num() {
            return invite_num;
        }

        public void setInvite_num(int invite_num) {
            this.invite_num = invite_num;
        }

        public int getToday_profit() {
            return today_profit;
        }

        public void setToday_profit(int today_profit) {
            this.today_profit = today_profit;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
