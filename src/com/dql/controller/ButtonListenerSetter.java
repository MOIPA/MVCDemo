package com.dql.controller;

import com.dql.controller.listener.MyListener;
import com.dql.controller.listener.ShowMemberListener;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 18:41
 */
public class ButtonListenerSetter {

    private ListenerPool pool = new ListenerPool();

    /**
     * 所有的listener都要添加到这里
     * TODO 以后做个自动扫描注入包
     */
    public ButtonListenerSetter() {
        pool.addListener(new ShowMemberListener());
    }

    /**
     * 点击显示用户按钮监听
     */
    public void setListener(IClickButton btn,MyListener listener) {
        JButton jbtn = (JButton) btn.getComponent();
        jbtn.addActionListener(listener);
    }

    /**
     * 通过监听名自动设置按钮监听
     */
    public void autoInjectListener(List<IClickButton> buttons) {
        List<MyListener> listeners = this.pool.getListeners();
        listeners.forEach(listener->{
                buttons.stream().forEach(btn->{
                    if (btn.getListnerName().equals(listener.getListenerName())){
                        setListener(btn,listener);
                    }
                });
        });
    }
}
