package com.earthquake.managementPlatform.service;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileBehavior {
    public JSONArray transferToJson(String filePath) throws IOException;
}
