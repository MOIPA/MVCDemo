package com.dql.dao.domain;

/**
 * @author tr
 * @date 2020/12/25 10:54
 * <p>
 * 用户访问记录
 */
public class AccessLog {
    // 访问时间
    private String date = "";
    // 访问会员id
    private String id = "";

    public AccessLog(String date, String id) {
        this.date = date;
        this.id = id;
    }

    @Override
    public String toString() {
        return date+","+id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
