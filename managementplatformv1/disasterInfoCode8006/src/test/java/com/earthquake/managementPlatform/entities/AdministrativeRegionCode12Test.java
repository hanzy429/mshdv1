package com.earthquake.managementPlatform.entities;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class AdministrativeRegionCode12Test {
    @Resource
    AdministrativeRegionCode12 administrativeRegionCode12;

    @Test
    public void codeForAdministrativeRegionTest(){

        administrativeRegionCode12.setAdministrativeRegionCode12("北京市","市辖区","东城区","东华门街道办事处","多福巷社区居委会");
        AdministrativeRegionCode administrativeRegionCode = administrativeRegionCode12;
        log.info(administrativeRegionCode.codeForAdministrativeRegion());

    }

}