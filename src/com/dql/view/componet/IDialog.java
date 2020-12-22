package com.dql.view.componet;

import com.dql.I18.AppText;

import javax.swing.*;
import java.awt.*;

public interface IDialog {
    void initDialog(JFrame mainFrame);

    Enum<AppText> getDialogName();

    void setVisiable();

    Container getContainer();

    JDialog getDialog();

    void addDialogToPanel(IDialog dialogComponent, Enum<AppText> panelName);

    void setBounds(int x, int y, int width, int height);
}
