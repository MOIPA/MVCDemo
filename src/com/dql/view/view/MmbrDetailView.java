package com.dql.view.view;

import com.dql.I18.AppEnum;
import com.dql.dao.domain.User;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/24 16:03
 */
public class MmbrDetailView {

    private ComponentPool pool = null;

    public MmbrDetailView() {
        this.pool = ComponentPool.getInstance();
    }

    public void initView(User user) {
        // 注册会员会话框
        IDialog dialog = new MemberDialog(AppEnum.QUERY_MEMBER_MANAGEMENT);
        this.pool.addDialogToMainFrame(dialog);
        dialog.getDialog().setLayout(null);
        dialog.setBounds(50, 50, 300, 600);

        // 输入会员信息面板 4行5列的表格
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(15, 2, 0, 10));
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.setBounds(10,10,dialog.getDialog().getWidth()-30,dialog.getDialog().getHeight()-20);
        gridPanel.add(new Label("id:"));
        gridPanel.add(new Label(user.getNumber()));
        gridPanel.add(new Label("first name:"));
        gridPanel.add(new Label(user.getFirstName()));
        gridPanel.add(new Label("last name:"));
        gridPanel.add(new Label(user.getLastName()));
        gridPanel.add(new Label("birth date:"));
        gridPanel.add(new Label(user.getBirthDate()));
        gridPanel.add(new Label("gender:"));
        gridPanel.add(new Label(user.getGender()));
        gridPanel.add(new Label("address:"));
        gridPanel.add(new Label(user.getAddress()));
        gridPanel.add(new Label("phone:"));
        gridPanel.add(new Label(user.getPhone()));
        gridPanel.add(new Label("health condition:"));
        gridPanel.add(new Label(user.getHealthCondition()));
        gridPanel.add(new Label("health allergy:"));
        gridPanel.add(new Label(user.getAllergy()));
        gridPanel.add(new Label("age:"));
        gridPanel.add(new Label(user.getAge()));
        gridPanel.add(new Label("membership start time:"));
        gridPanel.add(new Label(user.getMemberStartTime()));
        gridPanel.add(new Label("membership end time:"));
        gridPanel.add(new Label(user.getMemberEndTime()));
        gridPanel.add(new Label("membership type:"));
        gridPanel.add(new Label(user.getMemberType()));
        gridPanel.add(new Label("fee type:"));
        gridPanel.add(new Label(user.getFeeType()));
        gridPanel.add(new Label("family number:"));
        gridPanel.add(new Label(user.getFamilyTag()));
        dialog.getDialog().add(panel);
        dialog.setVisiable();
    }
}
