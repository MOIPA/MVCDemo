package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.IMyListener;
import com.dql.view.componet.ComponentPool;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * @author tr
 * @date 2020/12/22 14:44
 */
public class MmbrTypeSelectListener implements ItemListener, IMyListener {
    private Enum<AppEnum> listenerName = null;

    public MmbrTypeSelectListener() {
        this.setListenerName(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("LOG: clicked:"+ ComponentPool.getInstance().getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE).getSelectedItem());
        }
        /**
         * 访客点击时需要取消付费下拉
         */
        if (ComponentPool.getInstance().getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE).getSelectedItem() == AppEnum.VISITOR.toString()) {
            ComponentPool.getInstance().getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE).setEnabled(false);
        }else{
            ComponentPool.getInstance().getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE).setEnabled(true);
        }
    }

    @Override
    public Enum<AppEnum> getListenerName() {
        return this.listenerName;
    }

    @Override
    public void setListenerName(Enum<AppEnum> listenerName) {
        this.listenerName = listenerName;
    }
}
