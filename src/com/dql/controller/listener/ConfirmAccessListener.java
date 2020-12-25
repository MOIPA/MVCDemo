package com.dql.controller.listener;

import com.dql.I18.AppEnum;
import com.dql.controller.MyListener;
import com.dql.dao.DataAccessor;
import com.dql.dao.domain.AccessLog;
import com.dql.dao.domain.User;
import com.dql.view.componet.ComponentPool;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/25 10:47
 */
public class ConfirmAccessListener extends MyListener {

    private ComponentPool pool = null;
    private JComboBox<String> comboxList = null;
    private JTextField inputPhone  = null;
    private DataAccessor accessor = null;

    public ConfirmAccessListener() {

        this.setListenerName(AppEnum.MEMBER_ACCESS_CONFIRM);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        accessor = DataAccessor.getInstance();
        pool = ComponentPool.getInstance();
        comboxList = pool.getSelectBox(AppEnum.MEMBER_ACCESS_SELECT);
        inputPhone = pool.getTextFiledList(AppEnum.MEMBER_ACCESS_INPUT).get(0);

        String phone = inputPhone.getText().trim();
        // check is able to access equipment
        boolean exist = accessor.isExistByPhone(phone);
        if (exist) {
            accessor.addLog(new AccessLog(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), accessor.getUserByPhone(phone).getNumber()));
            JOptionPane.showMessageDialog(pool.container, "ok to access", "access", JOptionPane.INFORMATION_MESSAGE);
            accessor.reWriteAccessLogData();
            pool.getJlabel(AppEnum.MEMBER_ACCESS).setText("今日访问量:"+ DataAccessor.getInstance().getAccessNum());
        }else{
            JOptionPane.showMessageDialog(pool.container, "can not access", "access", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
