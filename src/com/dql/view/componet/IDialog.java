package com.dql.view.componet;

import com.dql.I18.AppEnum;

import javax.swing.*;
import java.awt.*;

public interface IDialog {
    void initDialog(JFrame mainFrame);

    Enum<AppEnum> getDialogName();

    void setVisiable();

    Container getContainer();

    JDialog getDialog();

    void addDialogToPanel(IDialog dialogComponent, Enum<AppEnum> panelName);

    void setBounds(int x, int y, int width, int height);
}
