package com.zenith.demo.transform;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;

import com.alibaba.fastjson.JSON;

public class XmlJsonMain {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");

        //json串
        String jsonStr = JSON.toJSONString(map);
        System.out.println("source json : " + jsonStr);

        //json转xml
        String xml = json2xml(jsonStr);
        System.out.println("xml  :  " + xml);
        //xml转json
        String targetJson = xml2json(xml);
        System.out.println("target json : " + targetJson);
    }

    /**
     * json to xml
     * @param json
     * @return
     */
    public static String json2xml(String json) {
        JSONObject jsonObj = new JSONObject(json);
        return "<xml>" + XML.toString(jsonObj) + "</xml>";
    }

    /**
     * xml to json
     * @param xml
     * @return
     */
    public static String xml2json(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml.replace("<xml>", "").replace("</xml>", ""));
        return xmlJSONObj.toString();
    }
}