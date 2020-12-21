package com.dql.controller;

import com.dql.I18.AppText;

import java.awt.event.ActionListener;

/**
 * @author tr
 * @date 2020/12/18 19:45
 */
public abstract class MyListener implements ActionListener {
    private Enum<AppText> listenerName = null;

    public Enum<AppText> getListenerName() {
        return listenerName;
    }

    public void setListenerName(Enum<AppText> listenerName) {
        this.listenerName = listenerName;
    }
}
