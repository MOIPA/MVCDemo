package com.dql.view.componet.impl;

import com.dql.I18.SizeEnum;
import com.dql.I18.AppEnum;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 18:12
 */
public class ClickButton implements IClickButton {
    private JButton btn = null;
    private Enum<AppEnum> listenerName = null;

    /**
     * 默认按钮
     */
    public ClickButton() {
        this.btn = new JButton("默认点击按钮");
        btn.setBounds(SizeEnum.DEFAULT_LOCATION_X.getValue(), SizeEnum.DEFAULT_LOCATION_Y.getValue(), SizeEnum.BUTTON_WIDTH.getValue(), SizeEnum.BUTTON_HEIGHT.getValue());
    }

    /**
     * 默认按钮 可配置内容
     * @param text
     */
    public ClickButton(Enum<AppEnum> text) {
        this.listenerName = text;
        this.btn = new JButton(text.toString());
        btn.setBounds(SizeEnum.DEFAULT_LOCATION_X.getValue(), SizeEnum.DEFAULT_LOCATION_Y.getValue(), SizeEnum.BUTTON_WIDTH.getValue(), SizeEnum.BUTTON_HEIGHT.getValue());
    }

    /**
     * 默认大小按钮
     *
     * @param text
     * @param x
     * @param y
     */
    public ClickButton(Enum<AppEnum> text, int x, int y) {
        this.listenerName = text;
        this.btn = new JButton(text.toString());
        btn.setBounds(x, y, SizeEnum.BUTTON_WIDTH.getValue(), SizeEnum.BUTTON_HEIGHT.getValue());
    }

    /**
     * 普通按钮
     */
    public ClickButton(Enum<AppEnum> text, int x, int y, int width, int height) {
        this.listenerName = text;
        this.btn = new JButton(text.toString());
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
    public Enum<AppEnum> getListenerName() {
        return this.listenerName;
    }

    @Override
    public void setListenerName(Enum<AppEnum> text) {
        this.listenerName = text;
    }
}
