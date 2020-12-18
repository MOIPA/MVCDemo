package com.dql.view;

import com.dql.controller.ButtonListenerSetter;
import com.dql.scheme.AppSize;
import com.dql.view.IAppFrame;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.MyComponent;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author tr
 * @date 2020/12/18 17:59
 * <p>
 * 自动框架 自动注入组件 自动监听
 */
public abstract class AppFrameAbstract implements IAppFrame {

    protected List<IClickButton> frameComponents = null;
    protected ButtonListenerSetter setter = null;
    protected Container container = null;
    protected JFrame mainFrame = new JFrame();

    /**
     * 布局基础配置
     * 改变初始设定
     * 一般以后写代码 这边都不需要动了
     */
    public AppFrameAbstract() {
        mainFrame.setTitle("management system");
        mainFrame.setSize(AppSize.LARGGER_FRAME_WIDTH.getValue(), AppSize.LARGGER_FRAME_HEIGHT.getValue());
        //置窗口是否可以关闭
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口是否可见
        mainFrame.setVisible(true);
        // 初始化设置 组件容器和主布局
        this.frameComponents = new LinkedList<>();
        this.setter = new ButtonListenerSetter();
        this.container = mainFrame.getContentPane();
        this.container.setLayout(null);
    }


    /**
     * 初始化所有面板组件 添加入组件列表
     * 以后主要修改这里的代码
     */
    public abstract void initViewComponent();

    /**
     * 注入面板组件  自动注入组件 自动设置监听
     * 以后不用动这里代码
     */
    public void injectFrame() {
        this.initViewComponent();
        frameComponents.forEach(x->this.container.add(x.getComponent()));
        this.setter.autoInjectListener(frameComponents);
    }

}
