package com.dql.view.view;

import com.dql.I18.AppText;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/22 14:23
 */
public class MmbrMngRegistView implements IMmbrMngRegistView{
    private ComponentPool pool = null;

    public MmbrMngRegistView() {
        this.pool = ComponentPool.getInstance();
    }

    /**
     * 点击注册页面初始化
     */
    public void initView() {
        // 注册会员会话框
        IDialog dialog = new MemberDialog(AppText.REGIST_MEMBER_MANAGEMENT);
        this.pool.addDialogToMainFrame(dialog);
        dialog.setBounds(10, 10, 400, 400);

        // 输入会员信息面板 4行5列的表格
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(4, 2,30,30));
        IClickButton submitButton = new ClickButton(AppText.REGIST_MEMBER_MANAGEMENT_SUBMIT);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(submitButton.getComponent(), BorderLayout.SOUTH);

        // grid panel 拼接输入框等组件
        JLabel fnLabel = new JLabel(AppText.REGIST_MEMBER_MANAGEMENT_FIRSTNAME.toString());
        JTextField fnField = new JTextField();
        fnField.setEditable(true);
        fnField.setColumns(20);
        fnField.setVisible(true);
        JLabel lnLabel = new JLabel(AppText.REGIST_MEMBER_MANAGEMENT_LASTNAME.toString());
        JTextField lnField = new JTextField();
        lnField.setEditable(true);
        lnField.setColumns(20);
        lnField.setVisible(true);
        JLabel phoneLabel = new JLabel(AppText.REGIST_MEMBER_MANAGEMENT_CONTACTPHONE.toString());
        JTextField phoneField = new JTextField();
        phoneField.setEditable(true);
        phoneField.setColumns(20);
        phoneField.setVisible(true);
        JLabel memberSelectLabel = new JLabel(AppText.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE.toString());
        JComboBox<String> memberSelectBox = new JComboBox<String>(new String[]{"test","test2"});

        gridPanel.add(fnLabel);
        gridPanel.add(fnField);
        gridPanel.add(lnLabel);
        gridPanel.add(lnField);
        gridPanel.add(phoneLabel);
        gridPanel.add(phoneField);
        gridPanel.add(memberSelectLabel);
        gridPanel.add(memberSelectBox);
        dialog.getDialog().add(panel);

    }

}
