package com.dql.controller;

import com.dql.I18.AppEnum;

import java.awt.event.ActionListener;

/**
 * @author tr
 * @date 2020/12/18 19:45
 */
public abstract class MyListener implements ActionListener,IMyListener {
    private Enum<AppEnum> listenerName = null;

    @Override
    public Enum<AppEnum> getListenerName() {
        return listenerName;
    }

    @Override
    public void setListenerName(Enum<AppEnum> listenerName) {
        this.listenerName = listenerName;
    }
}
