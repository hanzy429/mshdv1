package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CodeOfResourceService {
    @Resource
    CodeFactory codeFactory;

    public String getCode(String categoryId){
        return codeFactory.createCode(categoryId);
    }
}
