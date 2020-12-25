package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;

import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/25 10:03
 */
public class BtnAccessListener extends MyListener {
    ComponentPool pool = null;
    public BtnAccessListener() {
        this.pool = ComponentPool.getInstance();
        this.setListenerName(AppEnum.MEMBER_ACCESS);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DataAccessor.getInstance();
        IDialog dialog = pool.getDialog(AppEnum.MEMBER_ACCESS);
        dialog.setVisiable();
    }
}
