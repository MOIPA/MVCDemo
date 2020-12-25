package com.dql.view.view;

import com.dql.I18.AppEnum;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/22 14:23
 */
public class MmbrMngRegistView  {
    private ComponentPool pool = null;

    public MmbrMngRegistView() {
        this.pool = ComponentPool.getInstance();
    }

    /**
     * 点击注册页面初始化
     */
    public void initView() {
        // 注册会员会话框
        IDialog dialog = new MemberDialog(AppEnum.REGIST_MEMBER_MANAGEMENT);
        this.pool.addDialogToMainFrame(dialog);
        dialog.setBounds(10, 10, 400, 900);

        // 输入会员信息面板 4行5列的表格
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(11, 2, 30, 30));
        IClickButton submitButton = new ClickButton(AppEnum.REGIST_MEMBER_MANAGEMENT_SUBMIT);
        panel.add(gridPanel, BorderLayout.CENTER);
        panel.add(submitButton.getComponent(), BorderLayout.SOUTH);

        // 待生成的标签
        List<AppEnum> formNames = new LinkedList<>();
        List<JTextField> formTextField = new LinkedList<>();
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_FIRSTNAME);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_LASTNAME);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_CONTACTPHONE);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_BIRTH_DATE);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_ADDRESS);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_HEALTH);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_ALLERGY);
        formNames.add(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TIMES);

        formNames.forEach(x -> {
            JLabel label = new JLabel(x.toString());
            JTextField field = new JTextField();
            field.setEditable(true);
            field.setColumns(20);
            formTextField.add(field);
            gridPanel.add(label);
            gridPanel.add(field);
        });
        this.pool.addTextFiledList(AppEnum.REGIST_MEMBER_MANAGEMENT_FORM, formTextField);
        // grid panel 拼接输入框标签框等组件
        // 标签框
        JLabel memberSelectLabel = new JLabel(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE.toString());
        JLabel feeSelectLabel = new JLabel(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE.toString());
        JLabel genderSelectLabel = new JLabel(AppEnum.REGIST_MEMBER_MANAGEMENT_GENDER.toString());

        // 输入框
        JComboBox<String> feeSelectBox = new JComboBox<String>(new String[]{AppEnum.MONTHLY_FEES.toString(), AppEnum.QUARTERLY_FEES.toString(), AppEnum.YEARLY_FEES.toString()});
        JComboBox<String> memberSelectBox = new JComboBox<String>(new String[]{AppEnum.PERSONAL.toString(), AppEnum.FAMILY.toString(), AppEnum.VISITOR.toString()});
        JComboBox<String> genderSelectBox = new JComboBox<String>(new String[]{AppEnum.MALE.toString(), AppEnum.FEMALE.toString()});
        this.pool.addSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE, memberSelectBox);
        this.pool.addSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE, feeSelectBox);
        this.pool.addSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_GENDER, genderSelectBox);
        this.pool.addClickButton(submitButton);

        gridPanel.add(memberSelectLabel);
        gridPanel.add(memberSelectBox);
        gridPanel.add(feeSelectLabel);
        gridPanel.add(feeSelectBox);
        gridPanel.add(genderSelectLabel);
        gridPanel.add(genderSelectBox);
        dialog.getDialog().add(panel);
    }

}
