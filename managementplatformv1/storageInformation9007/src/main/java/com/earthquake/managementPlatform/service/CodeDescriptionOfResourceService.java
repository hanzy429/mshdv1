package com.earthquake.managementPlatform.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CodeDescriptionOfResourceService {
    @Resource
    CodeDescriptionFactory codeDescriptionFactory;

    public String getCodeDescription(String categoryId){
        return codeDescriptionFactory.createCodeDescription(categoryId).toString();
    }
}
