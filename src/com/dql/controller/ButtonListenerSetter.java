package com.dql.controller;

import com.dql.controller.listener.ShowMemberListener;
import com.dql.view.componet.IClickButton;

import javax.swing.*;
import java.io.File;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 18:41
 */
public class ButtonListenerSetter {


    /**
     * 所有的listener都要添加到这里
     * TODO 以后做个自动扫描注入包
     */
    public ButtonListenerSetter() {
        //自动扫描包 添加对象
        File file = new File("src/com/dql/controller/listener");
        File[] list = file.listFiles();
        for(int i=0;i<list.length;i++){
            File f = list[i];
            try {
                MyListener cc = (MyListener) Class.forName("com.dql.controller.listener."+f.getName().replace(".java","")).newInstance();
                ListenerPool.getInstance().addListener(cc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        ListenerPool.getInstance().addListener(new ShowMemberListener());
    }

    /**
     * 点击显示用户按钮监听
     */
    public void setListener(IClickButton btn,MyListener listener) {
        JButton jbtn = (JButton) btn.getComponent();
        jbtn.addActionListener(listener);
    }

    /**
     * 通过监听名自动设置按钮监听
     */
    public void autoInjectListener(List<IClickButton> buttons) {
        List<MyListener> listeners = ListenerPool.getInstance().getListeners();
        listeners.forEach(listener->{
                buttons.stream().forEach(btn->{
                    if (btn.getListnerName().equals(listener.getListenerName())){
                        setListener(btn,listener);
                    }
                });
        });
    }
}
