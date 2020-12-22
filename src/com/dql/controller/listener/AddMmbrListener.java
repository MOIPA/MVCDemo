package com.dql.controller.listener;

import com.dql.I18.AppText;
import com.dql.controller.MyListener;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/18 18:58
 */
public class AddMmbrListener extends MyListener {

    private ComponentPool componentPool = ComponentPool.getInstance();
    private JTable table = null;

    /**
     * 必须要实现接口名
     */
    public AddMmbrListener() {
        this.setListenerName(AppText.REGIST_MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IDialog dialog = componentPool.getDialog(AppText.REGIST_MEMBER_MANAGEMENT);
        dialog.setVisiable();
    }

}
