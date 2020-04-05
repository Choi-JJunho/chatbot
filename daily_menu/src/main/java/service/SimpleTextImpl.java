package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleTextImpl implements SimpleText{

    Map<String, Object> jsonSubObject1 = null;
    Map<String, Object> jsonSubObject2 = null;
    Map<String, Object> jsonSubObject3 = null;
    HashMap<String, Object> jsonObject = null;
    ArrayList<Map<String, Object>> jsonList = null;

    public HashMap<String, Object> simpleText(Object input) throws NullPointerException {
        jsonObject = new HashMap<String, Object>();
        jsonList = new ArrayList<Map<String, Object>>();
        jsonSubObject1 = new HashMap<String, Object>();
        jsonSubObject2 = new HashMap<String, Object>();
        jsonSubObject3 = new HashMap<String, Object>();

        jsonObject.put("version", "2.0");
        jsonSubObject3.put("text", input);
        jsonSubObject2.put("simpleText", jsonSubObject3);
        jsonList.add(jsonSubObject2);
        jsonSubObject1.put("outputs", jsonList);
        jsonObject.put("template", jsonSubObject1);

        return jsonObject;
    }
}
