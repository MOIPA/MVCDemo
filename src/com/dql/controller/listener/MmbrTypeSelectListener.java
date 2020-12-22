package com.dql.controller.listener;

import com.dql.I18.AppText;
import com.dql.controller.IMyListener;
import com.dql.view.componet.ComponentPool;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author tr
 * @date 2020/12/22 14:44
 */
public class MmbrTypeSelectListener implements ItemListener, IMyListener {
    private Enum<AppText> listenerName = null;

    public MmbrTypeSelectListener() {
        this.setListenerName(AppText.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("LOG: clicked:"+ ComponentPool.getInstance().getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE).getSelectedItem());
        }
        /**
         * 访客点击时需要取消付费下拉
         */
        if (ComponentPool.getInstance().getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE).getSelectedItem() == AppText.VISITOR.toString()) {
            ComponentPool.getInstance().getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_FEE_TYPE).setEnabled(false);
        }else{
            ComponentPool.getInstance().getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_FEE_TYPE).setEnabled(true);
        }
    }

    @Override
    public Enum<AppText> getListenerName() {
        return this.listenerName;
    }

    @Override
    public void setListenerName(Enum<AppText> listenerName) {
        this.listenerName = listenerName;
    }
}
