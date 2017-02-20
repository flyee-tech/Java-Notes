package com.peierlong.spring;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 包名: com.peierlong.spring
 * 创建人 : Elong
 * 时间: 20/02/2017 11:57 AM
 * 描述 : Spring表达式语言使用示例
 */
public class SpELTest {

    @Test
    public void testHelloWorld() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('hello world').concat(#end)");
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("end", "!");
        System.out.println(expression.getValue(evaluationContext));
    }

    @Test
    public void testParserContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext parserContext = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };
        Expression expression = parser.parseExpression("#{'Hello'}#{' World!'}", parserContext);
        System.out.println(expression.getValue());
    }



}
