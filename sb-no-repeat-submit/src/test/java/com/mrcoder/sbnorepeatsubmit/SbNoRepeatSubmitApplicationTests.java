package com.mrcoder.sbnorepeatsubmit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SbNoRepeatSubmitApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SbNoRepeatSubmitApplicationTests {

    @Test

    public void test() {
        StringBuilder name = new StringBuilder("张三");
        this.changeReferenceType(name);
        log.info("name:{}", name);
    }

    private void changeReferenceType(StringBuilder str) {
        str = new StringBuilder("李四");
    }
}
