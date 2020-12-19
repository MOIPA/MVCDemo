package com.dql.view;

import com.dql.controller.ListenerSetter;
import com.dql.view.componet.ComponentPool;

/**
 * @author tr
 * @date 2020/12/18 17:59
 * <p>
 * 自动框架 自动注入组件 自动监听
 */
public abstract class AppFrameAbstract implements IAppFrame {

    // 页面元素池
    protected ComponentPool componentPool = null;
    // 监听设置器
    private ListenerSetter setter = null;

    /**
     * 初始化
     */
    public AppFrameAbstract() {
        componentPool =  ComponentPool.getInstance(setter);
        setter = new ListenerSetter();
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
        this.setter.autoInjectButtonListener();
    }

}
