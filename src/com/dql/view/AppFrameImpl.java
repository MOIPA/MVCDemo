package com.dql.view;

import com.dql.I18.AppEnum;
import com.dql.dao.DataAccessor;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.view.MmbrAccessView;
import com.dql.view.view.MmbrMngView;

import javax.swing.*;

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
        new MmbrAccessView().initView();
        this.componentPool.mainFrame.repaint();
    }

    /**
     * 主页面初始化
     */
    private void initMainView() {
        // 设置管理会员按钮
        IClickButton btnManagement = new ClickButton(AppEnum.MEMBER_MANAGEMENT);
        IClickButton btnAccess = new ClickButton(AppEnum.MEMBER_ACCESS,30,160);
        // 设置按钮监听名
        btnManagement.setListenerName(AppEnum.MEMBER_MANAGEMENT);
        btnAccess.setListenerName(AppEnum.MEMBER_ACCESS);
        this.componentPool.addClickButton(btnManagement);
        this.componentPool.container.add(btnManagement.getComponent());
        this.componentPool.addClickButton(btnAccess);
        this.componentPool.container.add(btnAccess.getComponent());

        JLabel accessNum = new JLabel("今日访问量:"+ DataAccessor.getInstance().getAccessNum());
        accessNum.setBounds(30,240,100,40);
        this.componentPool.addJlabel(AppEnum.MEMBER_ACCESS, accessNum);
        this.componentPool.container.add(accessNum);
    }


    /**
     * 不要动这里的代码
     */
    public AppFrameImpl() {
        // 注入 不用管
        this.injectFrame();
    }


}
