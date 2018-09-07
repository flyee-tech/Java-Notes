package com.peierlong.design.patterns.chain.of.responsibility;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Request {

    private RequestType requestType;
    private String name;

    public Request(RequestType requestType, String name) {
        this.requestType = requestType;
        this.name = name;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getName() {
        return name;
    }
}
