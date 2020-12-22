package com.dql.controller;

import com.dql.I18.AppText;

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

    private List<IMyListener> listeners = new LinkedList<>();

    public List<IMyListener> getListeners() {
        return listeners;
    }

    public IMyListener getListener(Enum<AppText> textEnum) {
        for (IMyListener listener :
                this.listeners) {
            if (listener.getListenerName().equals(textEnum)) {
                return listener;
            }
        }
        return null;
    }

    public void setListeners(List<IMyListener> listeners) {
        this.listeners = listeners;
    }

    public void addListener(IMyListener listener) {
        this.listeners.add(listener);
    }
}
