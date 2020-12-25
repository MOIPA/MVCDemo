package com.dql.controller;

import com.dql.I18.AppEnum;

/**
 * @author tr
 * @date 2020/12/22 14:42
 */
public interface IMyListener {
    Enum<AppEnum> getListenerName();

    void setListenerName(Enum<AppEnum> listenerName);
}
