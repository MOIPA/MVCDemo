package com.dql.view.componet;

import com.dql.I18.AppText;

import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 18:23
 */
public interface MyComponent {
    Component getComponent();

    Enum<AppText> getListenerName();

    void setListenerName(Enum<AppText> text);
}
