package com.dql.controller.listener;

import com.dql.controller.MyListener;
import com.dql.scheme.AppText;

import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/18 18:58
 */
public class ShowMemberListener extends MyListener {

    /**
     * 必须要实现接口名
     */
    public ShowMemberListener() {
        this.setListenerName(AppText.CLICK_SHOW_MEMBER_LISTENER.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("*********************show member*************************");
    }
}
