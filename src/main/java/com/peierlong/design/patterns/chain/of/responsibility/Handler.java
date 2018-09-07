package com.peierlong.design.patterns.chain.of.responsibility;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public abstract class Handler {
    Handler successor;

    public Handler(Handler successor) {
        this.successor = successor;
    }

    protected abstract void handlerRequest(Request request);
}
