package com.earthquake.managementPlatform.service;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class XmlFile extends DisasterFile{
    public XmlFile() {
        this.fileBehavior = new XmlBehavior();
    }

    public XmlFile(String filePath){
        this.fileBehavior = new XmlBehavior();
        this.filePath = filePath;
    }
}
