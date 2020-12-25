package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.ListenerPool;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.view.componet.ComponentPool;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author tr
 * @date 2020/12/23 16:45
 */
public class DeleteMmbrListener extends MyListener {

    private ComponentPool componentPool = ComponentPool.getInstance();

    public DeleteMmbrListener() {
        this.setListenerName(AppEnum.DELETE_MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTable table = componentPool.getTable(AppEnum.MEMBER_MANAGEMENT_TABLE);
        int selectedRow = table.getSelectedRow();
        String id = (String) table.getValueAt(selectedRow, 0);
        // 删除实体
        int confirmDelete = JOptionPane.showConfirmDialog(ComponentPool.getInstance().container,
                "confirm delete user?","delete",
                JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {
            DataAccessor.getInstance().deleteUserById(id);
            System.out.println("LOG: 删除用户 id:" + id);
            // 刷新表格
            MmbrMngListener listener = (MmbrMngListener) ListenerPool.getInstance().getListener(AppEnum.MEMBER_MANAGEMENT);
            listener.refreshTable();
        }
    }
}
