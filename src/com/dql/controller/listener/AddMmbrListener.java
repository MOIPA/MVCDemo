package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;

import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/18 18:58
 */
public class AddMmbrListener extends MyListener {

    private ComponentPool componentPool = ComponentPool.getInstance();

    /**
     * 必须要实现接口名
     */
    public AddMmbrListener() {
        this.setListenerName(AppEnum.REGIST_MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DataAccessor.getInstance().setRegister(true);
        System.out.println("LOG: regist member");
        DataAccessor.getInstance().setFamilyMember(false);
        IDialog dialog = componentPool.getDialog(AppEnum.REGIST_MEMBER_MANAGEMENT);
        dialog.setVisiable();
    }

}
