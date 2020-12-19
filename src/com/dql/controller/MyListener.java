package com.dql.controller;

import java.awt.event.ActionListener;

/**
 * @author tr
 * @date 2020/12/18 19:45
 */
public abstract class MyListener implements ActionListener {
    private String listenerName = "";

    public String getListenerName() {
        return listenerName;
    }

    public void setListenerName(String listenerName) {
        this.listenerName = listenerName;
    }
}
