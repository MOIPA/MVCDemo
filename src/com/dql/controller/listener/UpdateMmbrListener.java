package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/23 17:02
 */
public class UpdateMmbrListener extends MyListener {

    private ComponentPool pool = ComponentPool.getInstance();

    public UpdateMmbrListener() {
        this.setListenerName(AppEnum.CHANGE_MEMBER_MANAGEMENT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取选中用户
        JTable table = pool.getTable(AppEnum.MEMBER_MANAGEMENT_TABLE);
        int selectedRow = table.getSelectedRow();
        String id = (String) table.getValueAt(selectedRow, 0);
        DataAccessor.getInstance().setUserId(id);
        // 将更新页面的元素绑定数据
        setDataOfRegistView();
        // 展开更新页面
        DataAccessor.getInstance().setRegister(false);
        System.out.println("LOG: update member");
        DataAccessor.getInstance().setFamilyMember(false);
        IDialog dialog = pool.getDialog(AppEnum.REGIST_MEMBER_MANAGEMENT);
        dialog.setVisiable();
        // 刷新表格
    }

    private void setDataOfRegistView() {
        List<JTextField> textFiled = this.pool.getTextFiledList(AppEnum.REGIST_MEMBER_MANAGEMENT_FORM);
        JComboBox feeSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE);
        JComboBox memberSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
        JComboBox genderSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_GENDER);
        String memberType = (String) memberSelectBox.getSelectedItem();
        String gender = (String) genderSelectBox.getSelectedItem();
        String feeType = (String) feeSelectBox.getSelectedItem();

        User user = DataAccessor.getInstance().getUserById(DataAccessor.getInstance().getUserId());
        textFiled.get(0).setText(user.getFirstName());
        textFiled.get(1).setText(user.getLastName());
        textFiled.get(2).setText(user.getPhone());
        textFiled.get(3).setText(user.getBirthDate());
        textFiled.get(4).setText(user.getAddress());
        textFiled.get(5).setText(user.getHealthCondition());
        textFiled.get(6).setText(user.getAllergy());
        textFiled.get(7).setEditable(false);
        memberSelectBox.setEditable(false);
        feeSelectBox.setEditable(false);
        if (user.getGender().equals(AppEnum.MALE.toString())) {
            genderSelectBox.setSelectedIndex(0);
        }else{
            genderSelectBox.setSelectedIndex(1);
        }
    }

}
