package com.dql.I18;

/**
 * @author tr
 * @date 2020/12/18 17:44
 */
public enum AppText {
    /**
     * 按钮信息
     */
    MEMBER_MANAGEMENT("会员管理"),
    REGIST_MEMBER_MANAGEMENT("会员管理-注册会员"),
    MEMBER_MANAGEMENT_TABLE("会员管理-会员表格"),
    DELETE_MEMBER_MANAGEMENT("会员管理-删除会员"),
    CHANGE_MEMBER_MANAGEMENT("会员管理-修改会员"),
    /**
     * 注册会员输入框信息
     */
    REGIST_MEMBER_MANAGEMENT_FORM("FORM"),
    REGIST_MEMBER_MANAGEMENT_FIRSTNAME("first name"),
    REGIST_MEMBER_MANAGEMENT_LASTNAME("last name"),
    REGIST_MEMBER_MANAGEMENT_CONTACTPHONE("contact phone"),
    REGIST_MEMBER_MANAGEMENT_MEMBERTYPE("choose member type"),
    REGIST_MEMBER_MANAGEMENT_SUBMIT("confirm"),
    REGIST_MEMBER_MANAGEMENT_BIRTH_DATE("birth date"),
    REGIST_MEMBER_MANAGEMENT_GENDER("gender"),
    REGIST_MEMBER_MANAGEMENT_ADDRESS("address"),
    REGIST_MEMBER_MANAGEMENT_HEALTH("health condition"),
    REGIST_MEMBER_MANAGEMENT_ALLERGY("allergy"),
    REGIST_MEMBER_MANAGEMENT_FEE_TYPE("fee type"),
    REGIST_MEMBER_MANAGEMENT_FEE_TIMES("fee times"),
    /**
     * 收费选择类型
     */
    MONTHLY_FEES("monthly"),
    QUARTERLY_FEES("quarterly"),
    YEARLY_FEES("yearly"),
    /**
     * 收费选择类型
     */
    PERSONAL("personal"),
    FAMILY("family"),
    VISITOR("visitor"),
    /**
     * 性别选择
     */
    MALE("male"),
    FEMALE("female"),
    /**
     * 错误日志
     */
    ERROR_LOG("component log error: mainFrame is null!"),
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
