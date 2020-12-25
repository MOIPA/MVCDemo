package com.dql.view.view;

import com.dql.I18.AppEnum;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/25 10:06
 */
public class MmbrAccessView {
    private ComponentPool pool = null;

    public MmbrAccessView() {
        this.pool = ComponentPool.getInstance();
    }

    public void initView() {
        // 请求访问设备会话框
        IDialog dialog = new MemberDialog(AppEnum.MEMBER_ACCESS);
        this.pool.addDialogToMainFrame(dialog);
        dialog.getDialog().setLayout(null);
        dialog.setBounds(10, 10, 400, 200);
        JPanel gridPanel = new JPanel();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBounds(10,10,dialog.getDialog().getWidth()-30,dialog.getDialog().getHeight()-60);

        gridPanel.setLayout(new GridLayout(2, 2, 15, 15));
        gridPanel.add(new JLabel("please choose one equipment"));
        JComboBox<String> equipSelectBox = new JComboBox<>(new String[]{"gym", "swimming pool"});
        gridPanel.add(equipSelectBox);
        gridPanel.add(new JLabel("please enter phone"));
        JTextField inputPhone = new JTextField();
        gridPanel.add(inputPhone);
        pool.addSelectBox(AppEnum.MEMBER_ACCESS_SELECT, equipSelectBox);
        List<JTextField> textFieldList = new LinkedList<>();
        textFieldList.add(inputPhone);
        pool.addTextFiledList(AppEnum.MEMBER_ACCESS_INPUT,textFieldList);
        IClickButton confirm = new ClickButton(AppEnum.MEMBER_ACCESS_CONFIRM);
        pool.addClickButton(confirm);


        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(confirm.getComponent(), BorderLayout.SOUTH);
        dialog.getDialog().add(panel);
    }
}
