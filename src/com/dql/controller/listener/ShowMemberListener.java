package com.dql.controller.listener;

import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.scheme.AppText;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
        this.setListenerName(AppText.CLICK_SHOW_MEMBER_LISTENER.getValue());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IDialog dialog = componentPool.dialogMap.get("展示会员会话");
        dialog.setVisiable();
        JTable table = componentPool.tableMap.get("会员列表");
        // 定义弹出框内部按钮
        // 表头（列名）
        Object[] columnNames = {"姓名", "姓名"};
        List<User> userList = DataAccessor.getInstance().getUserList();
        // 表格所有行数据
        List<String[]> collect = userList.stream().map(x -> new String[]{x.getNameOne(), x.getNameTwo()}).collect(Collectors.toList());
        String[][] rowData = new String[userList.size()][2];
        for (int i = 0; i < rowData.length; i++) {
            rowData[i] = collect.get(i);
        }

        table.setModel(new DefaultTableModel(rowData,columnNames));
        table.setBounds(3,3,40,100);
        dialog.getContainer().add(table);
    }
}
