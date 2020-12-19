package com.dql.controller;

import com.dql.controller.listener.MyListener;

import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 19:34
 */
public class ListenerPool {
    private List<MyListener> listeners = new LinkedList<>();

    public List<MyListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<MyListener> listeners) {
        this.listeners = listeners;
    }

    public void addListener(MyListener listener) {
        this.listeners.add(listener);
    }
}
