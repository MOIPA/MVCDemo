package com.dql.controller;

import com.dql.I18.AppText;

/**
 * @author tr
 * @date 2020/12/22 14:42
 */
public interface IMyListener {
    Enum<AppText> getListenerName();

    void setListenerName(Enum<AppText> listenerName);
}
