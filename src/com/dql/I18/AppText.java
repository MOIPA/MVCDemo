package com.dql.I18;

/**
 * @author tr
 * @date 2020/12/18 17:44
 */
public enum AppText {
    /**
     * 按钮信息
     */
    CLICK_SHOW_MEMBER("点击查看所有会员"),
    MEMBER_MANAGEMENT("会员管理"),
    REGIST_MEMBER_MANAGEMENT("会员管理-注册会员"),
    REGIST_MEMBER_MANAGEMENT_FIRSTNAME("first name"),
    REGIST_MEMBER_MANAGEMENT_LASTNAME("last name"),
    REGIST_MEMBER_MANAGEMENT_CONTACTPHONE("contact phone"),
    REGIST_MEMBER_MANAGEMENT_MEMBERTYPE("choose member type"),
    REGIST_MEMBER_MANAGEMENT_SUBMIT("confirm"),
    MEMBER_MANAGEMENT_TABLE("会员管理-会员表格"),
    DELETE_MEMBER_MANAGEMENT("会员管理-删除会员"),
    CHANGE_MEMBER_MANAGEMENT("会员管理-修改会员"),
    /**
     * 错误日志
     */
    ERROR_LOG("component log error: mainFrame is null!"),
    /**
     * 点击查看所有会员监听
     */
    MEMBER_MANAGEMENT_LISTENER("CLICK_SHOW_MEMBER_LISTENER"),
    DOC_LOCATION("src/customerlist.csv"), ERROR_LOG_PARSE_LINE("ERROR: 数据不充足 无法解析："),
    SUCCESS_LOG_PARSE_LINE("SUCCESS: 数据解析完毕 数据量："), ERROR_LOG_NOT_FOUND("ERROR: 未找到目标文件"), ERROR_LOG_IO("ERROR: 文件读写错误 请检查文件内容是否正确");
    private String value;

    private AppText(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
