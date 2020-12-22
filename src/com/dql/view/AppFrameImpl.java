package com.dql.view;

import com.dql.I18.AppText;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;
import com.dql.view.view.MmbrMngView;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 17:37
 */
public class AppFrameImpl extends AppFrameAbstract {

    /**
     * 初始化所有面板组件 添加入组件列表
     * <p>
     * *********************以后主要修改这里的代码*************************
     */
    @Override
    public void initViewComponent() {
        initMainView();
        new MmbrMngView().initView();
    }

    /**
     * 主页面初始化
     */
    private void initMainView() {
        // 设置管理会员按钮
        IClickButton btn = new ClickButton(AppText.MEMBER_MANAGEMENT);
        // 设置按钮监听名
        btn.setListenerName(AppText.MEMBER_MANAGEMENT);
        this.componentPool.addClickButton(btn);
        this.componentPool.container.add(btn.getComponent());
    }


    /**
     * 不要动这里的代码
     */
    public AppFrameImpl() {
        // 注入 不用管
        this.injectFrame();
    }


}
