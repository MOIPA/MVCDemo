package com.dql.controller;

import com.dql.controller.listener.ShowMemberListener;
import com.dql.view.componet.ComponentPool;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
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
                MyListener cc = (MyListener) Class.forName("com.dql.controller.listener." + f.getName().replace(".java", "")).newInstance();
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
        List<IClickButton> buttons = ComponentPool.getInstance(null).ClickButtons;
        List<MyListener> listeners = ListenerPool.getInstance().getListeners();
        // 按名组装监听
        listeners.forEach(listener -> {
            buttons.stream().forEach(btn -> {
                if (btn.getListnerName().equals(listener.getListenerName())) {
                    setListener(btn, listener);
                }
            });
        });
    }

    /**
     * 绑定监听
     */
    private void setListener(IClickButton btn, MyListener listener) {
        JButton jbtn = (JButton) btn.getComponent();
        jbtn.addActionListener(listener);
    }

}
