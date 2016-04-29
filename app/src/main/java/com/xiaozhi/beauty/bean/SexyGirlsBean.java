package com.xiaozhi.beauty.bean;

import java.util.List;

/**
 * Author: caizhi
 * Date:2016/3/30
 */
public class SexyGirlsBean {
    private String status;
    private int tatal;
    private List<SexyGirlsDataBean> tngou;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTatal() {
        return tatal;
    }

    public void setTatal(int tatal) {
        this.tatal = tatal;
    }

    public List<SexyGirlsDataBean> getTngou() {
        return tngou;
    }

    public void setTngou(List<SexyGirlsDataBean> tngou) {
        this.tngou = tngou;
    }
}
//{
//        "status":true,
//        "total":5,
//        "tngou":[
//        {"count":6,
//        "fcount":0,
//        "galleryclass":1,
//        "id":18,
//        "img":"/ext/150714/e76407c9a23da57a0f30690aa7917f3e.jpg",
//        "rcount":0,
//        "size":6,
//        "time":1436878500000,
//        "title":"MiStar苏小曼姿势性感诱人私房照"},
//        {……}
//        ]}