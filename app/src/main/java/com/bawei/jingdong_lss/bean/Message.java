package com.bawei.jingdong_lss.bean;

/**
 * Created by love_mysunshine on 2017/12/18.
 */

public class Message {
    String haohuo_iv;
    String haohuo_tv;

    public Message(String haohuo_iv, String haohuo_tv) {
        this.haohuo_iv = haohuo_iv;
        this.haohuo_tv = haohuo_tv;
    }

    public String getHaohuo_iv() {
        return haohuo_iv;
    }

    public void setHaohuo_iv(String haohuo_iv) {
        this.haohuo_iv = haohuo_iv;
    }

    public String getHaohuo_tv() {
        return haohuo_tv;
    }

    public void setHaohuo_tv(String haohuo_tv) {
        this.haohuo_tv = haohuo_tv;
    }
}
