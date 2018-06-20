package com.zenith.demo.transform;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class Main {  
    public static int PRETTY_PRINT_INDENT_FACTOR = 4;  
    public static String TEST_XML_STRING =  
            "<breakfast_menu>\n" +  
                    "<food>\n" +  
                    "<name>Belgian Waffles</name>\n" +  
                    "<price>$5.95</price>\n" +  
                    "<description>\n" +  
                    "Two of our famous Belgian Waffles with plenty of real maple syrup\n" +  
                    "</description>\n" +  
                    "<calories>650</calories>\n" +  
                    "</food>\n" +  
                    "<food>\n" +  
                    "<name>Strawberry Belgian Waffles</name>\n" +  
                    "<price>$7.95</price>\n" +  
                    "<description>\n" +  
                    "Light Belgian waffles covered with strawberries and whipped cream\n" +  
                    "</description>\n" +  
                    "<calories>900</calories>\n" +  
                    "</food>\n" +  
                    "</breakfast_menu>";  
  
    public static void main(String[] args) {  
    	XmlToJson();
    } 
    
    public static void XmlToJson()
    {
    	 try {  
             JSONObject xmlJSONObj = XML.toJSONObject(TEST_XML_STRING);  
             String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);  
             System.out.println(jsonPrettyPrintString);  
             System.out.println("**********************************************");  
             String json=xml2json(TEST_XML_STRING);
             System.out.println(json); 
             System.out.println("**********************************************");  
             //xml转json
             String targetJson = json2xml(jsonPrettyPrintString);
             System.out.println("XML : " +targetJson);
         } catch (JSONException je) {  
             System.out.println(je.toString());  
         }  
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
        return xmlJSONObj.toString(4);
    }
}  