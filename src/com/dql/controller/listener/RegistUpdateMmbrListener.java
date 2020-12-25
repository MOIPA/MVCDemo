package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.ListenerPool;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.util.Utils;
import com.dql.view.componet.ComponentPool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 18:58
 * <p>
 * 新增/更新会员  确认提交页面监听
 * <p>
 * 新增和更新复用一个页面 MembrMngRegistView
 */
public class RegistUpdateMmbrListener extends MyListener {

    private ComponentPool pool = ComponentPool.getInstance();
    private String familyTag = "";

    /**
     * 必须要实现接口名
     */
    public RegistUpdateMmbrListener() {
        this.setListenerName(AppEnum.REGIST_MEMBER_MANAGEMENT_SUBMIT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (DataAccessor.getInstance().isRegister()) {
            registerLogic();
        } else {
            updateLogic();
        }
    }

    /**
     * 更新逻辑
     */
    private void updateLogic() {
        List<JTextField> textFiled = this.pool.getTextFiledList(AppEnum.REGIST_MEMBER_MANAGEMENT_FORM);
        JComboBox feeSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE);
        JComboBox memberSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
        JComboBox genderSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_GENDER);
        String gender = (String) genderSelectBox.getSelectedItem();
        int age = 0;

        age = calcAge(textFiled.get(3).getText());
        if (age == -1) return;
        if (!checkPhone(textFiled.get(2).getText())) return;

        int confirmDelete = JOptionPane.showConfirmDialog(ComponentPool.getInstance().container,
                "confirm update user?","update",
                JOptionPane.YES_NO_OPTION);
        if (confirmDelete == JOptionPane.YES_OPTION) {

            User user = new User();
            user.setFirstName(textFiled.get(0).getText());
            user.setLastName(textFiled.get(1).getText());
            user.setPhone(textFiled.get(2).getText());
            user.setAge(age+"");
            user.setBirthDate(textFiled.get(3).getText());
            user.setAddress(textFiled.get(4).getText());
            user.setHealthCondition(textFiled.get(5).getText());
            user.setAllergy(textFiled.get(6).getText());
            user.setGender(gender);
            DataAccessor.getInstance().updateUserById(DataAccessor.getInstance().getUserId(),user);
            System.out.println("LOG: User updated");

            // 恢复编辑框 防止影响下次输入
            textFiled.get(7).setEditable(true);
            memberSelectBox.setEditable(true);
            feeSelectBox.setEditable(true);
            // 刷新表格
            MmbrMngListener listener = (MmbrMngListener) ListenerPool.getInstance().getListener(AppEnum.MEMBER_MANAGEMENT);
            listener.refreshTable();
        }

    }

    /**
     * 执行注册逻辑
     */
    private void registerLogic() {
        List<JTextField> textFiled = this.pool.getTextFiledList(AppEnum.REGIST_MEMBER_MANAGEMENT_FORM);
        JComboBox feeSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_FEE_TYPE);
        JComboBox memberSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
        JComboBox genderSelectBox = this.pool.getSelectBox(AppEnum.REGIST_MEMBER_MANAGEMENT_GENDER);
        String memberType = (String) memberSelectBox.getSelectedItem();

        String phone = textFiled.get(2).getText();
        String birthDate = textFiled.get(3).getText();
        String feeTimes = textFiled.get(7).getText();
        int age = 0;
        int timesInt = 0;
        String endTime = "";

        timesInt = checkFee(feeTimes);
        age = calcAge(birthDate);
        if (age == -1) return;
        if (timesInt == -1) return;
        if (!checkPhone(phone)) return;
        //  访客没有结束时间 结束时间为使用次数
        if (memberType.equals(AppEnum.VISITOR.toString())) {
            endTime = feeTimes;
        } else {
            endTime = Utils.calcEndTime(new Date(), (String) feeSelectBox.getSelectedItem(), timesInt);
        }

        /**
         * 封装用户数据
         */
        User user = new User();
        user.setFirstName(textFiled.get(0).getText());
        user.setLastName(textFiled.get(1).getText());
        user.setPhone(phone);
        user.setBirthDate(birthDate);
        user.setAddress(textFiled.get(4).getText());
        user.setHealthCondition(textFiled.get(5).getText());
        user.setAllergy(textFiled.get(6).getText());
        user.setFeeType((String) feeSelectBox.getSelectedItem());
        user.setGender((String) genderSelectBox.getSelectedItem());
        user.setMemberType(memberType);
        user.setAge(String.valueOf(age));
        user.setMemberEndTime(endTime);
        if (DataAccessor.getInstance().isFamilyMember()) {
            user.setFamilyTag(familyTag);
        } else {
            textFiled.get(7).setEditable(true);
            feeSelectBox.setEnabled(true);
            memberSelectBox.setEnabled(true);
        }
        /**
         * 如果用户选择了家庭类型  那么理论可以添加家庭成员
         */
        // 付钱
        // 作为家庭成员被添加不需要付钱
        if (!DataAccessor.getInstance().isFamilyMember()) {
            int optPay = JOptionPane.showConfirmDialog(ComponentPool.getInstance().container,
                    Utils.calcFamilyMoney(memberType, (String) feeSelectBox.getSelectedItem(), timesInt), "pay",
                    JOptionPane.YES_NO_OPTION);
            if (optPay == JOptionPane.YES_OPTION) {
                // 付钱成功
                System.out.println("user paid");
            } else {
                // 付钱失败
                return;
            }
        }
        DataAccessor.getInstance().addUser(user);
        if (memberType.equals(AppEnum.FAMILY.toString())) {
            System.out.println(user.toString());
            if (!DataAccessor.getInstance().isFamilyMember()) {
                user.setFamilyTag(user.getNumber());
            }
            //确认是否继续添加成员
            int optAddMember = JOptionPane.showConfirmDialog(ComponentPool.getInstance().container,
                    "continue add other family member ?", "continue add",
                    JOptionPane.YES_NO_OPTION);
            if (optAddMember == JOptionPane.YES_OPTION) {
                //确认继续操作
                // 不是被添加成员 且要添加其他成员
                if (!DataAccessor.getInstance().isFamilyMember()) {
                    this.familyTag = user.getNumber();
                    DataAccessor.getInstance().setFamilyMember(true);
                }
                // 清空页面
                clearInputArea(textFiled);
                textFiled.get(7).setEditable(false);
                feeSelectBox.setEnabled(false);
                memberSelectBox.setEnabled(false);
            } else {
                DataAccessor.getInstance().setFamilyMember(false);
                textFiled.get(7).setEditable(true);
                feeSelectBox.setEnabled(true);
                memberSelectBox.setEnabled(true);
            }
        }

        rewriteTableData();
        // 清空页面
        clearInputArea(textFiled);
    }

    /**
     * 清空输入区域
     *
     * @param textFiled
     */
    private void clearInputArea(List<JTextField> textFiled) {
        textFiled.get(0).setText("");
        textFiled.get(1).setText("");
        textFiled.get(2).setText("");
        textFiled.get(3).setText("");
        textFiled.get(4).setText("");
        textFiled.get(5).setText("");
        textFiled.get(6).setText("");
    }

    /**
     * 回写并刷新表数据
     */
    private void rewriteTableData() {
        // 回写数据
        DataAccessor.getInstance().reWriteUserData();
        MmbrMngListener listener = (MmbrMngListener) ListenerPool.getInstance().getListener(AppEnum.MEMBER_MANAGEMENT);
        listener.refreshTable();
    }

    /**
     * 校验手机
     */
    private boolean checkPhone(String phone) {
        if (!Utils.isChinaPhoneLegal(phone)) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "your phone number is not legal");
            return false;
        }
        // 手机号重复
        if (DataAccessor.getInstance().isExistByPhone(phone)) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "this phone is registed");
            return false;
        }
        return true;
    }

    /**
     * 校验并且计算生日
     */
    private int calcAge(String birthDate) {
        int age = -1;
        try {
            age = Utils.calcAge(birthDate);
            //  访客没有结束时间 结束时间为使用次数
        } catch (ParseException parseException) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: create user failed ,birth day is not legal");
            System.out.println("ERROR: create user failed ,birth day is not legal");
        }
        return age;
    }

    /**
     * 校验付费是否正确
     */
    private int checkFee(String feeTimes) {
        try {
            if (feeTimes == "") {
                JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "Fee time is not filled");
                return -1;
            }
            int timesInt = Integer.parseInt(feeTimes);
            if (timesInt < 1) {
                JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: fee times can not less than 1");
                return -1;
            }
            return timesInt;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: fee times is not legal");
            return -1;
        }
    }


}
