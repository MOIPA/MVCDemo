package com.dql.view.impl;

import com.dql.scheme.AppText;
import com.dql.view.AppFrameAbstract;
import com.dql.view.componet.IClickButton;
import com.dql.view.componet.IDialog;
import com.dql.view.componet.impl.ClickButton;
import com.dql.view.componet.impl.MemberDialog;

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
        initClickShowMember();
    }

    private void initClickShowMember() {

        // 查看用户按钮
        IClickButton btn = new ClickButton(AppText.CLICK_SHOW_MEMBER.getValue());
        // 设置按钮监听名
        btn.setListnerName(AppText.CLICK_SHOW_MEMBER_LISTENER.getValue());
        // 添加按钮到序列
        this.componentPool.addClickButton(btn);

        // 按钮弹出框
        IDialog dialogComponent = new MemberDialog("所有会员信息","展示会员会话");
        this.componentPool.addDialog(dialogComponent);

        // 展示会员表格
        JTable table = new JTable();
        componentPool.addTable(table,"会员列表");
    }

    /**
     * 不要动这里的代码
     */
    public AppFrameImpl() {
        // 注入 不用管
        this.injectFrame();
    }


}
