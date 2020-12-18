package com.dql.view.componet.impl;

import com.dql.scheme.AppSize;
import com.dql.scheme.AppText;
import com.dql.view.componet.IMemberDIalog;

import javax.swing.*;
import java.awt.*;

/**
 * @author tr
 * @date 2020/12/18 17:53
 */
public class MemberDialogComponnet extends JDialog implements IMemberDIalog {

    public MemberDialogComponnet(JFrame mainFrame) {
        super(mainFrame);
        if (mainFrame == null) System.out.println(AppText.ERROR_LOG.getValue());
        Container container = this.getContentPane();
        container.add(new JLabel("存放表格"));
        this.setSize(AppSize.DIALOG_WIDTH.getValue(), AppSize.DIALOG_HEIGHT.getValue());
    }

    public MemberDialogComponnet() {

    }
}
