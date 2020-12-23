package com.dql.controller.listener;

import com.dql.I18.AppSize;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.I18.AppText;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tr
 * @date 2020/12/18 18:58
 *
 * 表格和按钮页监听
 */
public class MmbrMngListener extends MyListener {

    private ComponentPool componentPool = ComponentPool.getInstance();
    private JTable table = null;

    /**
     * 必须要实现接口名
     */
    public MmbrMngListener() {
        this.setListenerName(AppText.MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.table = componentPool.getTable(AppText.MEMBER_MANAGEMENT_TABLE);
        IDialog dialog = componentPool.getDialog(AppText.MEMBER_MANAGEMENT_TABLE);
        setTableData();
        dialog.setVisiable();
    }

    private void setTableData() {
        // 设置表格数据
        Object[] columnNames = {"ID","name", "phone", "member type", "end date","age"};
        List<User> userList = DataAccessor.getInstance().getUserList();
        List<String[]> collect = userList.stream().map(x -> new String[]{x.getNumber(),x.getFirstName() + x.getLastName(), x.getPhone(), x.getMemberType(), x.getMemberEndTime(),x.getAge()}).collect(Collectors.toList());
        String[][] rowData = new String[userList.size()][6];
        for (int i = 0; i < rowData.length; i++) {
            rowData[i] = collect.get(i);
        }
        this.table.setModel(new DefaultTableModel(rowData, columnNames));
    }

    /**
     * 刷新表格数据
     */
    public void refreshTable() {
        setTableData();
    }
}
