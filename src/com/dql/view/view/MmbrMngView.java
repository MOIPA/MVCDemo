package com.dql.view.view;

import com.dql.I18.SizeEnum;
import com.dql.I18.AppEnum;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/22 14:18
 */
public class MmbrMngView  {

    /**
     * 页面上的三个按钮和一个表格
     */
    private ComponentPool pool = null;
    private IClickButton btnAdd = null;
    private IClickButton btnDelete = null;
    private IClickButton btnChange = null;
    private IClickButton btnShow = null;
    private JTable table = null;
    private IDialog dialog = null;

    public MmbrMngView() {
        dialog = new MemberDialog(AppEnum.MEMBER_MANAGEMENT_TABLE);
        this.pool = ComponentPool.getInstance();
        btnAdd = new ClickButton(AppEnum.REGIST_MEMBER_MANAGEMENT, SizeEnum.SHOW_MEMBER_DIALOG_WIDTH.getValue() - SizeEnum.SMALL_BUTTON_WIDTH.getValue() - 20, 20, SizeEnum.SMALL_BUTTON_WIDTH.getValue(), SizeEnum.SMALL_BUTTON_HEIGHT.getValue());
        btnDelete = new ClickButton(AppEnum.DELETE_MEMBER_MANAGEMENT, SizeEnum.SHOW_MEMBER_DIALOG_WIDTH.getValue() - SizeEnum.SMALL_BUTTON_WIDTH.getValue() - 20, SizeEnum.SMALL_BUTTON_HEIGHT.getValue() + 30, SizeEnum.SMALL_BUTTON_WIDTH.getValue(), SizeEnum.SMALL_BUTTON_HEIGHT.getValue());
        // 展示会员表格
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btnChange = new ClickButton(AppEnum.CHANGE_MEMBER_MANAGEMENT, SizeEnum.SHOW_MEMBER_DIALOG_WIDTH.getValue() - SizeEnum.SMALL_BUTTON_WIDTH.getValue() - 20, SizeEnum.SMALL_BUTTON_HEIGHT.getValue() * 2 + 40, SizeEnum.SMALL_BUTTON_WIDTH.getValue(), SizeEnum.SMALL_BUTTON_HEIGHT.getValue());
        btnShow = new ClickButton(AppEnum.QUERY_MEMBER_MANAGEMENT, SizeEnum.SHOW_MEMBER_DIALOG_WIDTH.getValue() - SizeEnum.SMALL_BUTTON_WIDTH.getValue() - 20, SizeEnum.SMALL_BUTTON_HEIGHT.getValue() * 3 + 40, SizeEnum.SMALL_BUTTON_WIDTH.getValue(), SizeEnum.SMALL_BUTTON_HEIGHT.getValue());
    }

    /**
     * 会员表格 和 三个按钮的页面初始化
     */
    public void initView() {
        this.pool.addDialogToMainFrame(dialog);
        pool.addTable(table, AppEnum.MEMBER_MANAGEMENT_TABLE);
        // 会员按钮
        this.pool.addClickButton(btnAdd);
        this.pool.addClickButton(btnShow);
        this.pool.addClickButton(btnDelete);
        this.pool.addClickButton(btnChange);
        // 组合
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setBounds(10, 10, SizeEnum.SHOW_MEMBER_DIALOG_WIDTH.getValue() - SizeEnum.SMALL_BUTTON_WIDTH.getValue() - 40, SizeEnum.SHOW_MEMBER_DIALOG_HEIGHT.getValue() - 60);
        dialog.getDialog().setLayout(null);
        dialog.getContainer().add(panel);
        dialog.getContainer().add(btnAdd.getComponent());
        dialog.getContainer().add(btnDelete.getComponent());
        dialog.getContainer().add(btnChange.getComponent());
        dialog.getContainer().add(btnShow.getComponent());

        // 初始化点击注册按钮页面
        initRegistView();
    }

    /**
     * 点击注册按钮页面
     */
    private void initRegistView() {
        new MmbrMngRegistView().initView();
    }

}
