package com.earthquake.managementPlatform.service;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonBehavior implements FileBehavior{
    @Override
    public JSONArray transferToJson(String filePath) throws IOException {
        File jsonStringFile = new File(filePath);
        InputStream in = new FileInputStream(jsonStringFile);
        String jsonString = IOUtils.toString(in);
        JSONObject JsonJsonObj = new JSONObject(jsonString);
        String metaData = JsonJsonObj.toString();
        String[] splitData = metaData.split("\\{");
        String rootNode = splitData[1].replace(":","").replace("\"","");
        String secondNode = splitData[2].replace(":[","").replace("\"","");
        JSONArray datas = JsonJsonObj.getJSONObject(rootNode).getJSONArray(secondNode);
        return datas;
    }
}
