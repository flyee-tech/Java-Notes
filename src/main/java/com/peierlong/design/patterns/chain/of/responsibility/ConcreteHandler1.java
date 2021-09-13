package com.peierlong.design.patterns.chain.of.responsibility;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class ConcreteHandler1 extends Handler {

    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    protected void handlerRequest(Request request) {
        if (request.getRequestType() == RequestType.TYPE1) {
            System.out.println(request.getName() + " si handle by ConcreteHandler1");
            return;
        }
        if (successor != null) {
            successor.handlerRequest(request);
        }
    }

}
