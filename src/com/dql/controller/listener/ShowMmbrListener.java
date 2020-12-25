package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.view.componet.ComponentPool;
import com.dql.view.view.MmbrDetailView;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/23 18:02
 */
public class ShowMmbrListener extends MyListener {

    private ComponentPool pool = ComponentPool.getInstance();

    public ShowMmbrListener() {
        this.setListenerName(AppEnum.QUERY_MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("clicked");
        JTable table = pool.getTable(AppEnum.MEMBER_MANAGEMENT_TABLE);
        int selectedRow = table.getSelectedRow();
        String id = (String) table.getValueAt(selectedRow, 0);
        User user = DataAccessor.getInstance().getUserById(id);
        new MmbrDetailView().initView(user);

    }

}
