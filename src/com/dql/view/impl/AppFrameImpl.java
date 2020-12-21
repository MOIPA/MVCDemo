package com.dql.view.impl;

import com.dql.I18.AppSize;
import com.dql.I18.AppText;
import com.dql.view.AppFrameAbstract;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 17:37
 */
public class AppFrameImpl extends AppFrameAbstract {

    /**
     * 初始化所有面板组件 添加入组件列表
     * <p>
     * *********************以后主要修改这里的代码*************************
     */
    @Override
    public void initViewComponent() {
        initMainView();
        initMemberManagementView();
    }

    private void initMainView() {
        // 设置管理会员按钮
        IClickButton btn = new ClickButton(AppText.MEMBER_MANAGEMENT);
        // 设置按钮监听名
        btn.setListenerName(AppText.MEMBER_MANAGEMENT);
        this.componentPool.addClickButton(btn);
        this.componentPool.container.add(btn.getComponent());
    }

    private void initMemberManagementView() {
        // 按钮弹出框
        IDialog dialog = new MemberDialog(AppText.MEMBER_MANAGEMENT_TABLE.getValue(),AppText.MEMBER_MANAGEMENT_TABLE);
        this.componentPool.addDialogToMainFrame(dialog);

        // 展示会员表格
        JTable table = new JTable();
        componentPool.addTable(table,AppText.MEMBER_MANAGEMENT_TABLE);

        // 会员按钮
        IClickButton btnAdd = new ClickButton(AppText.ADD_MEMBER_MANAGEMENT, AppSize.DIALOG_WIDTH.getValue()-AppSize.SMALL_BUTTON_WIDTH.getValue()-20,20,AppSize.SMALL_BUTTON_WIDTH.getValue(),AppSize.SMALL_BUTTON_HEIGHT.getValue());
        IClickButton btnDelete = new ClickButton(AppText.DELETE_MEMBER_MANAGEMENT, AppSize.DIALOG_WIDTH.getValue()-AppSize.SMALL_BUTTON_WIDTH.getValue()-20,AppSize.SMALL_BUTTON_HEIGHT.getValue()+30,AppSize.SMALL_BUTTON_WIDTH.getValue(),AppSize.SMALL_BUTTON_HEIGHT.getValue());
        IClickButton btnChange = new ClickButton(AppText.CHANGE_MEMBER_MANAGEMENT, AppSize.DIALOG_WIDTH.getValue()-AppSize.SMALL_BUTTON_WIDTH.getValue()-20,AppSize.SMALL_BUTTON_HEIGHT.getValue()*2+40,AppSize.SMALL_BUTTON_WIDTH.getValue(),AppSize.SMALL_BUTTON_HEIGHT.getValue());
        this.componentPool.addClickButton(btnAdd);
        this.componentPool.addClickButton(btnDelete);
        this.componentPool.addClickButton(btnChange);

        // 组合
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane,BorderLayout.CENTER);
        panel.setBounds(10,10, AppSize.DIALOG_WIDTH.getValue()-AppSize.SMALL_BUTTON_WIDTH.getValue()-40,AppSize.DIALOG_HEIGHT.getValue()-60);
        dialog.getDialogComponent().setLayout(null);
        dialog.getContainer().add(panel);
        dialog.getContainer().add(btnAdd.getComponent());
        dialog.getContainer().add(btnDelete.getComponent());
        dialog.getContainer().add(btnChange.getComponent());
    }

    /**
     * 不要动这里的代码
     */
    public AppFrameImpl() {
        // 注入 不用管
        this.injectFrame();
    }


}
