package com.peierlong.design.patterns.interpreter;

import java.util.StringTokenizer;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class TerminalExpression extends Expression {
    private String literal = null;

    public TerminalExpression(String str){
        literal = str;
    }

    @Override
    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals(literal)) {
                return true;
            }
        }
        return false;
    }

}
