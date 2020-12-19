package com.dql.view.componet;

import com.dql.controller.ListenerSetter;
import com.dql.scheme.AppSize;
import com.dql.view.componet.impl.MemberDialog;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 页面容器 容纳页面所有元素 包括主界面，页面所有按钮，页面所有容器
 */
public class ComponentPool {
    private static ComponentPool componentPool = null;
    public List<IClickButton> ClickButtons = null;
    public ListenerSetter setter = null;
    public Container container = null;
    public JFrame mainFrame = new JFrame();
    public Map<String, IDialog> dialogMap = new HashMap<>();
    public Map<String, JTable> tableMap = new HashMap<>();

    private ComponentPool(ListenerSetter setter) {
        container = mainFrame.getContentPane();
        mainFrame.setTitle("management system");
        mainFrame.setSize(AppSize.LARGGER_FRAME_WIDTH.getValue(), AppSize.LARGGER_FRAME_HEIGHT.getValue());
        //置窗口是否可以关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口是否可见
        mainFrame.setVisible(true);
        // 初始化设置 组件容器和主布局
        this.ClickButtons = new LinkedList<>();
        this.setter = setter;
        this.container = mainFrame.getContentPane();
        this.container.setLayout(null);
    }

    private ComponentPool() {
    }

    /**
     * 添加按钮入池  添加到页面容器
     * @param btn
     */
    public void addClickButton(IClickButton btn) {
        this.container.add(btn.getComponent());
        this.ClickButtons.add(btn);
    }

    public static ComponentPool getInstance(ListenerSetter setter) {
        if (componentPool == null) componentPool = new ComponentPool(setter);
        return componentPool;
    }

    public static ComponentPool getInstance() {
        if (componentPool == null){
            System.out.println("ERROR: 页面元素池未被初始化");
        }
        return componentPool;
    }

    /**
     * 添加会话  默认添加至主容器
     * @param dialogComponent
     */
    public void addDialog(IDialog dialogComponent) {
        dialogComponent.initDialog(this.mainFrame);
        dialogMap.put(dialogComponent.getDialogName(), dialogComponent);
    }
    /**
     * 添加表格
     * @param table
     */
    public void addTable(JTable table,String tableName) {
        tableMap.put(tableName, table);
    }
}
