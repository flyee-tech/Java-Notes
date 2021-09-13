package com.peierlong.design.patterns;

/**
 * 服务提供者框架 DEMO
 *
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/11/25 上午10:58
 * 描述 : 服务提供者接口
 */
public interface Provider {
    Service newService();
}
