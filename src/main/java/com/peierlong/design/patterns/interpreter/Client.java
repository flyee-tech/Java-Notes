package com.peierlong.design.patterns.interpreter;

/**
 * @author elong
 * @version V1.0
 * @date 2018/9/7
 */
public class Client {
    // 构建解析树
    public static Expression buildInterpreterTree(){
        //Literal
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");

        // B C
        OrExpression alternation1 = new OrExpression(terminal2, terminal3);
        // A Or (B C)
        OrExpression alternation2 = new OrExpression(terminal1, alternation1);
        // D And (A Or (B C))
        return new AndExpression(terminal4, alternation2);
    }

    public static void main(String[] args) {
        Expression define = buildInterpreterTree();
        String content1 = "D A";
        String content2 = "A B";
        System.out.println(define.interpret(content1));
        System.out.println(define.interpret(content2));
    }
}
