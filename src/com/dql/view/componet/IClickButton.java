package com.dql.view.componet;

import com.dql.I18.AppEnum;

import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 18:12
 *
 * 基础按钮组件，屏蔽底层实现细节，即开可用的按钮
 */
public interface IClickButton {
    /**
     * @param text 按钮修改内容
     */
    void changeText(String text);

    Component getComponent();

    Enum<AppEnum> getListenerName();

    void setListenerName(Enum<AppEnum> text);
}
