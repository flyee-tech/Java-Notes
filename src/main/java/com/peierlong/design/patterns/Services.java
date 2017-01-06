package com.peierlong.design.patterns;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务提供者框架 DEMO
 * <p>
 * 包名: com.elong.effective
 * 创建人 : Elong
 * 时间: 2016/11/25 上午11:02
 * 描述 : 不可实例化类 > 服务注册API 和 客户端访问API
 */
public class Services {

    private Services() {
    }

    //Maps service name to services
    private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
    private static final String DEFAULT_PROVIDER_NAME = "<def>";

    //Provider registration API
    public static void registerDefaultProvider(Provider provider) {
        registerProvider(DEFAULT_PROVIDER_NAME, provider);
    }
    public static void registerProvider(String name, Provider provider) {
        providers.put(name, provider);
    }

    //Service Access API
    public static Service newInstance(){
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    public static Service newInstance(String name) {
        Provider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("No provider registered with name: " + name);
        }
        return provider.newService();
    }

    public static void main(String[] args) {

        Service service = Services.newInstance();
        System.out.println(service);


    }




}
