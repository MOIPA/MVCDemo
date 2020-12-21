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
 */
public class ShowMemberListener extends MyListener {

    private ComponentPool componentPool = ComponentPool.getInstance();

    /**
     * 必须要实现接口名
     */
    public ShowMemberListener() {
        this.setListenerName(AppText.MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IDialog dialog = componentPool.getDialog(AppText.MEMBER_MANAGEMENT_TABLE);
        JTable table = componentPool.getTable(AppText.MEMBER_MANAGEMENT_TABLE);

        dialog.setVisiable();

        // 设置表格数据
        Object[] columnNames = {"姓名", "姓名"};
        List<User> userList = DataAccessor.getInstance().getUserList();
        List<String[]> collect = userList.stream().map(x -> new String[]{x.getFirstName(), x.getLastName()}).collect(Collectors.toList());
        String[][] rowData = new String[userList.size()][2];
        for (int i = 0; i < rowData.length; i++) {
            rowData[i] = collect.get(i);
        }
        table.setModel(new DefaultTableModel(rowData,columnNames));

    }
}
