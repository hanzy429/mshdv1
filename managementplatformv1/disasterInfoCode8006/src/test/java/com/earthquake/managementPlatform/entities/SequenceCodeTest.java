package com.earthquake.managementPlatform.entities;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class SequenceCodeTest {
    @Resource
    SequenceCode sequenceCode;

    @Test
    public void CodeForSequenceTest(){
        log.info(sequenceCode.CodeForSequence("140108000000","551"));
    }

}