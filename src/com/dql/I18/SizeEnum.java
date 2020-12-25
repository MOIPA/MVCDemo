package com.dql.I18;

/**
 * @author tr
 * @date 2020/12/18 17:44
 */
public enum SizeEnum {
    /**
     * 按钮大小
     */
    SMALLER_FRAME_WIDTH(400),MEDIUM_FRAME_WIDTH(800),LARGGER_FRAME_WIDTH(1200),
    SMALLER_FRAME_HEIGHT(200),MEDIUM_FRAME_HEIGHT(350),LARGGER_FRAME_HEIGHT(800),
    SHOW_MEMBER_DIALOG_WIDTH(600), SHOW_MEMBER_DIALOG_HEIGHT(900),
    BUTTON_WIDTH(200),BUTTON_HEIGHT(50),
    SMALL_BUTTON_WIDTH(140),SMALL_BUTTON_HEIGHT(30),
    DEFAULT_LOCATION_X(30),DEFAULT_LOCATION_Y(30);
    private int value;

    private SizeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
