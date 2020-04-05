package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MenuToJson;

import java.util.HashMap;

@Controller
public class MenuController {

    @Autowired
    MenuToJson menuToJson;

    @RequestMapping("/getmenu")
    @ResponseBody
    public HashMap<String, Object> breakfast(@RequestBody String map) {
        if(map.contains("아침"))
            return menuToJson.getBreakfast();
        else if(map.contains("점심"))
            return menuToJson.getLunch();
        else if(map.contains("저녁"))
            return menuToJson.getDinner();
        else
            return menuToJson.incorrect();
    }
}
