package com.dql.scheme;

/**
 * @author tr
 * @date 2020/12/18 17:44
 */
public enum AppSize {
    /**
     * 按钮大小
     */
    SMALLER_FRAME_WIDTH(400),MEDIUM_FRAME_WIDTH(800),LARGGER_FRAME_WIDTH(1200),
    SMALLER_FRAME_HEIGHT(200),MEDIUM_FRAME_HEIGHT(500),LARGGER_FRAME_HEIGHT(800),
    DIALOG_WIDTH(600),DIALOG_HEIGHT(900),
    BUTTON_WIDTH(200),BUTTON_HEIGHT(50),
    DEFAULT_LOCATION_X(30),DEFAULT_LOCATION_Y(30);
    private int value;

    private AppSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
