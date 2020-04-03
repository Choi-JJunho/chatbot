package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.MenuDTO;
import org.springframework.stereotype.Service;
import repository.MenuMapper;
import javax.annotation.Resource;
import java.util.*;

@Service
public class MenuToJsonImpl implements MenuToJson {
    Map<String, Object> jsonSubObject1 = null;
    Map<String, Object> jsonSubObject2 = null;
    Map<String, Object> jsonSubObject3 = null;
    Map<String, Object> jsonObject = null;
    ArrayList<Map<String, Object>> jsonList = null;

    @Resource
    MenuMapper menuMapper;

    public String getBreakfast() throws NullPointerException {
        List<MenuDTO> breakfast = menuMapper.getBreakfast();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(breakfast);
            return jsonStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

    public Map<String, Object> getLunch() throws NullPointerException {
        jsonObject = new LinkedHashMap<String, Object>();
        jsonList = new ArrayList<Map<String, Object>>();
        jsonSubObject1 = new HashMap<String, Object>();
        jsonSubObject2 = new HashMap<String, Object>();
        jsonSubObject3 = new HashMap<String, Object>();

        jsonObject.put("version", "2.0");
        String lunchmenu = menuMapper.getLunch().toString();
        jsonSubObject3.put("text", lunchmenu);
        jsonSubObject2.put("simpleText", jsonSubObject3);
        jsonList.add(jsonSubObject2);
        jsonSubObject1.put("outputs", jsonList);
        jsonObject.put("template", jsonSubObject1);

        return jsonObject;
    }

    public String getDinner() throws NullPointerException {
        List<MenuDTO> dinner = menuMapper.getDinner();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(dinner);
            return jsonStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "error";
        }
    }

}
