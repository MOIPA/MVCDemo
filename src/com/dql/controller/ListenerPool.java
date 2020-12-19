package com.dql.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 19:34
 */
public class ListenerPool {

    private static ListenerPool listenerPool = null;

    public static ListenerPool getInstance() {
        if (listenerPool==null) listenerPool = new ListenerPool();
        return listenerPool;
    }

    private ListenerPool() {

    }

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
