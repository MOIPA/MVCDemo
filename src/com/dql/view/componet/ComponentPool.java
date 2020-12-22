package com.dql.view.componet;

import com.dql.I18.AppText;
import com.dql.controller.ListenerSetter;
import com.dql.I18.AppSize;

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
    public List<IClickButton> buttons = null;
    public ListenerSetter setter = null;
    public Container container = null;
    public JFrame mainFrame = new JFrame();
    public Map<Enum<AppText>, IDialog> dialogMap = new HashMap<>();
    public Map<Enum<AppText>, JTable> tableMap = new HashMap<>();

    private ComponentPool(ListenerSetter setter) {
        container = mainFrame.getContentPane();
        mainFrame.setTitle("management system");
        mainFrame.setSize(AppSize.LARGGER_FRAME_WIDTH.getValue(), AppSize.LARGGER_FRAME_HEIGHT.getValue());
        //置窗口是否可以关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口是否可见
        mainFrame.setVisible(true);
        // 初始化设置 组件容器和主布局
        this.buttons = new LinkedList<>();
        this.setter = setter;
        this.container = mainFrame.getContentPane();
        this.container.setLayout(null);
    }

    private ComponentPool() {
    }

    /**
     * 添加按钮入池  添加到页面容器
     *
     * @param btn
     */
    public void addClickButton(IClickButton btn) {
        this.buttons.add(btn);
    }

    public IClickButton getClickButton(String buttonName) {
        for (IClickButton btn :
                this.buttons) {
            if (btn.getListenerName().equals(buttonName)) {
                return btn;
            }
        }
        return null;
    }

    public static ComponentPool getInstance(ListenerSetter setter) {
        if (componentPool == null) componentPool = new ComponentPool(setter);
        return componentPool;
    }

    public static ComponentPool getInstance() {
        if (componentPool == null) {
            System.out.println("ERROR: 页面元素池未被初始化");
        }
        return componentPool;
    }

    /**
     * 添加会话  默认添加至主容器
     *
     * @param dialogComponent
     */
    public void addDialogToMainFrame(IDialog dialogComponent) {
        dialogComponent.initDialog(this.mainFrame);
        dialogMap.put(dialogComponent.getDialogName(), dialogComponent);
    }

    /**
     * 将会话添加至某一容器
     *
     * @param dialog
     * @param panelName
     */
    public void addDialogToPanel(IDialog dialog, Enum<AppText> panelName) {
        this.dialogMap.keySet().forEach(x -> {
            if (dialogMap.get(x).getDialogName() == panelName) {
                dialogMap.get(x).getDialog().add(dialog.getDialog());
            }
        });
        dialogMap.put(dialog.getDialogName(), dialog);
    }

    /**
     * 添加表格
     *
     * @param table
     */
    public void addTable(JTable table, Enum<AppText> tableName) {
        tableMap.put(tableName, table);
    }

    /**
     * 获取表格
     */
    public JTable getTable(Enum<AppText> tableName) {
        return tableMap.get(tableName);
    }

    /**
     * 获取会话框
     */
    public IDialog getDialog(Enum<AppText> dialogName) {
        return dialogMap.get(dialogName);
    }
}
