package com.earthquake.managementPlatform.service;

import com.earthquake.managementPlatform.service.impl.OutPutImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class OutPutService {
    @Resource
    OutPutImpl OutPut;

    public boolean output(String categoryId,String code) throws IOException {
        return OutPut.outPut(categoryId,code);
    }
}
