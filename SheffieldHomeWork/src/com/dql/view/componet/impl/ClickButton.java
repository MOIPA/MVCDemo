package com.dql.view.componet.impl;

import com.dql.scheme.AppSize;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 18:12
 */
public class ClickButton implements IClickButton {
    private JButton btn = null;
    private String listenerName = "";

    /**
     * 默认按钮
     */
    public ClickButton() {
        this.btn = new JButton("默认点击按钮");
        btn.setBounds(AppSize.DEFAULT_LOCATION_X.getValue(), AppSize.DEFAULT_LOCATION_Y.getValue(), AppSize.BUTTON_WIDTH.getValue(), AppSize.BUTTON_HEIGHT.getValue());
    }

    /**
     * 默认按钮 可配置内容
     * @param text
     */
    public ClickButton(String text) {
        this.btn = new JButton(text);
        btn.setBounds(AppSize.DEFAULT_LOCATION_X.getValue(), AppSize.DEFAULT_LOCATION_Y.getValue(), AppSize.BUTTON_WIDTH.getValue(), AppSize.BUTTON_HEIGHT.getValue());
    }

    /**
     * 默认大小按钮
     *
     * @param text
     * @param x
     * @param y
     */
    public ClickButton(String text, int x, int y) {
        this.btn = new JButton(text);
        btn.setBounds(x, y, AppSize.BUTTON_WIDTH.getValue(), AppSize.BUTTON_HEIGHT.getValue());
    }

    /**
     * 普通按钮
     */
    public ClickButton(String text, int x, int y,int width,int height) {
        this.btn = new JButton(text);
        btn.setBounds(x, y, width, height);
    }

    /**
     * @param text 按钮修改内容
     */
    @Override
    public void changeText(String text) {
        this.btn.setText(text);
    }

    @Override
    public Component getComponent() {
        return this.btn;
    }

    @Override
    public String getListnerName() {
        return this.listenerName;
    }

    @Override
    public String setListnerName(String text) {
        return this.listenerName = text;
    }
}
