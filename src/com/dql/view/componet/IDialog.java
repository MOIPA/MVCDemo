package com.dql.view.componet;

import javax.swing.*;
import java.awt.*;

public interface IDialog {
    void initDialog(JFrame mainFrame);

    String getDialogName();

    void setVisiable();

    Container getContainer();
}
