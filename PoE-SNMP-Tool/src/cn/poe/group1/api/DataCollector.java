package cn.poe.group1.api;

import cn.poe.group1.entity.Switch;

/**
 *
 */
public interface DataCollector {

    void addSwitch(Switch sw);
    
    void removeSwitch(Switch sw);
    
    void shutdown();
}
