package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MenuMapper;
import javax.annotation.Resource;
import java.util.*;

@Service
public class MenuToJsonImpl implements MenuToJson {

    @Resource
    MenuMapper menuMapper;

    @Autowired
    SimpleText simpleText;

    public String menuString(HashMap<String, Object> map) {
        Iterator<String> keys = map.keySet().iterator();
        String desc = "";

        while( keys.hasNext() ){
            String key = keys.next();
            switch (key) {
                case "korean":
                    desc += "[한식]";
                    break;

                case "special":
                    desc += "[일품]";
                    break;

                case "onedish":
                    desc += "[특식]";
                    break;

                case "western":
                    desc += "[양식]";
                    break;

                case "faculty":
                    desc += "[능수관]";
                    break;

                case "subak":
                    desc += "[수박여]";
                    break;

                default:
                    break;
            }
            for(Object menulist : map.values()) {
                desc += menulist;
            }
            desc += "\n────────────\n";
        }
        return desc;
    }

    public HashMap<String, Object> getBreakfast() throws NullPointerException {
        HashMap<String, Object> breakfast = menuMapper.getBreakfast();
        breakfast.values().removeIf(val -> "-".equals(val));
        HashMap<String, Object> result;

        if(breakfast.isEmpty())
            result = simpleText.simpleText("오늘은 아침이 없습니다.");
        else {
            String strbreakfast = menuString(breakfast);
            result = simpleText.simpleText(strbreakfast);
        }
        return result;
    }

    public HashMap<String, Object> getLunch() throws NullPointerException {
        HashMap<String, Object> lunch = menuMapper.getLunch();
        lunch.values().removeIf(val -> "-".equals(val));

        HashMap<String, Object> result;

        if(lunch.isEmpty())
            result = simpleText.simpleText("오늘은 점심이 없습니다.");
        else {
            String strlunch = menuString(lunch);
            result = simpleText.simpleText(strlunch);
        }
        return result;
    }

    public HashMap<String, Object> getDinner() throws NullPointerException {
        HashMap<String, Object> dinner = menuMapper.getDinner();
        dinner.values().removeIf(val -> "-".equals(val));

        HashMap<String, Object> result;

        if(dinner.isEmpty())
            result = simpleText.simpleText("오늘은 저녁이 없습니다.");
        else {
            String strdinner = menuString(dinner);
            result = simpleText.simpleText(strdinner);
        }
        return result;
    }

}