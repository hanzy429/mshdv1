package com.earthquake.managementPlatform.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;

@Slf4j
public class XmlBehavior implements FileBehavior{
    @Override
    public JSONArray transferToJson(String filePath) throws IOException {
        File xmlFile = new File(filePath);
        InputStream in = new FileInputStream(xmlFile);
        String xml = IOUtils.toString(in);
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        String metaData = xmlJSONObj.toString();
        String[] splitData = metaData.split("\\{");
        for (String item:splitData
             ) {
            log.info(item);
        }
        String rootNode = splitData[1].replace(":","").replace("\"","");
        log.info(rootNode);
        JSONArray datas = new JSONArray();
        try{
        String secondNode = splitData[2].replace(":[","").replace("\"","");
        log.info(secondNode);
        datas = xmlJSONObj.getJSONObject(rootNode).getJSONArray(secondNode);
        }catch (JSONException e){
            String secondNode = splitData[2].replace(":","").replace("\"","");
            log.info(secondNode);
            JSONObject jsonObject= xmlJSONObj.getJSONObject(rootNode).getJSONObject(secondNode);
            datas.put(jsonObject);
        }
        return datas;
    }
}
