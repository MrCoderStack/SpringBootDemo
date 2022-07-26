package com.mrcoder.test;

import com.mrcoder.sbspelannotations.SbSpelAnnotationsApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbSpelAnnotationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SbSpelAnnotationsApplicationTests {

    @Test
    public void test(){
        String expressionStr = "1+2";
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(expressionStr);
        Integer val = expression.getValue(Integer.class);
        System.out.println(expressionStr + "的结果是：" + val);
    }
}
