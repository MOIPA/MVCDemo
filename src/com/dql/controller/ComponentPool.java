package com.dql.controller;

public class ComponentPool {
    private ComponentPool componentPool = null;

    private ComponentPool() {

    }

    public ComponentPool getInstance() {
        if (componentPool == null) componentPool = new ComponentPool();
        return componentPool;
    }
}
