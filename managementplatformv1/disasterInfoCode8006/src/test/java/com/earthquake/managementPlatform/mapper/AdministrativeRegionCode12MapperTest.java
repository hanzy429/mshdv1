package com.earthquake.managementPlatform.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AdministrativeRegionCode12MapperTest {
    @Resource
    AdministrativeRegionCode12Mapper administrativeRegionCode12Mapper;

    @Test
    public void getProvinceTest(){
        log.info(administrativeRegionCode12Mapper.getProvinceCode("北京市"));
    }

}