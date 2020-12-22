package com.dql.controller;

import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 18:41
 */
public class ListenerSetter {


    /**
     * 自动添加所有的监听
     */
    public ListenerSetter() {
        //自动扫描包 添加对象
        File file = new File("src/com/dql/controller/listener");
        File[] list = file.listFiles();
        for (int i = 0; i < list.length; i++) {
            File f = list[i];
            try {
                IMyListener cc = (IMyListener) Class.forName("com.dql.controller.listener." + f.getName().replace(".java", "")).newInstance();
                ListenerPool.getInstance().addListener(cc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过监听名自动设置按钮监听
     */
    public void autoInjectButtonListener() {
        // 获取页面按钮
        List<IClickButton> buttons = ComponentPool.getInstance(null).buttons;
        List<IMyListener> listeners = ListenerPool.getInstance().getListeners();
        // 按名组装监听
        listeners.forEach(listener -> {
            buttons.stream().forEach(btn -> {
                if (btn.getListenerName().equals(listener.getListenerName())) {
                    setBtnListener(btn, listener);
                }
            });
        });
    }

    /**
     * 设置下拉监听
     */
    public void autoInjectSelectBoxListener() {
        // 获取页面按钮
        List<IMyListener> listeners = ListenerPool.getInstance().getListeners();
        listeners.forEach(x->{
            JComboBox selectBox = ComponentPool.getInstance().getSelectBox(x.getListenerName());
            if (selectBox!=null)setSelectBoxListener(selectBox,x);
        });
    }

    /**
     * 绑定监听
     */
    private void setBtnListener(IClickButton btn, IMyListener listener) {
        JButton jbtn = (JButton) btn.getComponent();
        jbtn.addActionListener((ActionListener) listener);
    }

    private void setSelectBoxListener(JComboBox box, IMyListener listener) {
        box.addItemListener((ItemListener) listener);
    }
}
