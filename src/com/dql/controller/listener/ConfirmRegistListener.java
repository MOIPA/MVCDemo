package com.dql.controller.listener;

import com.dql.I18.AppText;
import com.dql.controller.IMyListener;
import com.dql.controller.ListenerPool;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.User;
import com.dql.util.Utils;
import com.dql.view.componet.ComponentPool;
import com.dql.view.view.MmbrMngRegistView;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 18:58
 * <p>
 * 新增会员  确认提交页面监听
 */
public class ConfirmRegistListener extends MyListener {

    private ComponentPool pool = ComponentPool.getInstance();
    private String familyTag = "";

    /**
     * 必须要实现接口名
     */
    public ConfirmRegistListener() {
        this.setListenerName(AppText.REGIST_MEMBER_MANAGEMENT_SUBMIT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<JTextField> textFiled = this.pool.getTextFiledList(AppText.REGIST_MEMBER_MANAGEMENT_FORM);
        JComboBox feeSelectBox = this.pool.getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_FEE_TYPE);
        JComboBox memberSelectBox = this.pool.getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_MEMBERTYPE);
        JComboBox genderSelectBox = this.pool.getSelectBox(AppText.REGIST_MEMBER_MANAGEMENT_GENDER);
        String memberType = (String) memberSelectBox.getSelectedItem();

        /**
         * 校验手机号和出生日期是否合法  这个手机号美国的验证规则之后再改 就先不验证了
         */
        String phone = textFiled.get(2).getText();
        String birthDate = textFiled.get(3).getText();
        int age = 0;
        int timesInt = 0;
        String endTime = "";
        String feeTimes = textFiled.get(7).getText();
        if (feeTimes == "") {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "Fee time is not filled");
            return;
        }
        /**
         *  访客没有结束时间 结束时间为使用次数
         */
        try {
            timesInt = Integer.parseInt(feeTimes);
            if (timesInt < 1) {
                JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: fee times can not less than 1");
                return;
            }
            age = Utils.calcAge(birthDate);
            if (memberType.equals(AppText.VISITOR.toString())) {
                endTime = feeTimes;
            }else{
                endTime = Utils.calcEndTime(new Date(), (String) feeSelectBox.getSelectedItem(), timesInt);
            }
        } catch (ParseException parseException) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: create user failed ,birth day is not legal");
            System.out.println("ERROR: create user failed ,birth day is not legal");
            return;
        } catch (Exception ce) {
            JOptionPane.showMessageDialog(ComponentPool.getInstance().container, "ERROR: create user failed ,fee times is not legal");
            System.out.println("ERROR: create user failed ,fee times is not legal");
            return;
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
        System.out.println(memberType);
        System.out.println(AppText.FAMILY.toString());
        System.out.println(memberType.equals(AppText.FAMILY.toString()));
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
        if (memberType.equals(AppText.FAMILY.toString())) {
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
                textFiled.get(0).setText("");
                textFiled.get(1).setText("");
                textFiled.get(2).setText("");
                textFiled.get(3).setText("");
                textFiled.get(4).setText("");
                textFiled.get(5).setText("");
                textFiled.get(6).setText("");
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

        // 回写数据
        DataAccessor.getInstance().reWriteData();
        MmbrMngListener listener = (MmbrMngListener) ListenerPool.getInstance().getListener(AppText.MEMBER_MANAGEMENT);
        listener.refreshTable();
    }

}
