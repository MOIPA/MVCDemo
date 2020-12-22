package com.dql.view.componet.impl;

import com.dql.I18.AppSize;
import com.dql.I18.AppText;
import com.dql.view.componet.IDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 17:53
 */
public class MemberDialog implements IDialog {

    private JDialog dialog = null;
    private String title = "";
    private Enum<AppText> name = null;

    @Override
    public void initDialog(JFrame mainFrame) {
        if (mainFrame == null) {
            System.out.println(AppText.ERROR_LOG.getValue());
            return;
        }
        dialog = new JDialog(mainFrame);
        dialog.setTitle(this.title);
        dialog.setSize(AppSize.SHOW_MEMBER_DIALOG_WIDTH.getValue(), AppSize.SHOW_MEMBER_DIALOG_HEIGHT.getValue());
    }

    @Override
    public Enum<AppText> getDialogName() {
        return name;
    }

    @Override
    public void setVisiable() {
        this.dialog.setVisible(true);
    }

    @Override
    public Container getContainer() {
        return this.dialog.getContentPane();
    }

    @Override
    public JDialog getDialog() {
        return this.dialog;
    }

    @Override
    public void addDialogToPanel(IDialog dialogComponent, Enum<AppText> panelName) {

    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        this.dialog.setBounds(x, y, width, height);
    }

    public MemberDialog(Enum<AppText> name) {
        this.title = name.toString();
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
