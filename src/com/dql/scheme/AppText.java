package com.dql.scheme;

/**
 * @author tr
 * @date 2020/12/18 17:44
 */
public enum AppText {
    /**
     * 按钮信息
     */
    CLICK_SHOW_MEMBER("点击查看所有会员"),
    /**
     * 错误日志
     */
    ERROR_LOG("component log error: mainFrame is null!"),
    /**
     * 点击查看所有会员监听
     */
    CLICK_SHOW_MEMBER_LISTENER("CLICK_SHOW_MEMBER_LISTENER");
    private String value;

    private AppText(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
