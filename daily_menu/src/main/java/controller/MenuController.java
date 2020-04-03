package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MenuToJson;

import java.util.Map;

@Controller
public class MenuController {

    @Autowired
    MenuToJson menuToJson;

    @RequestMapping("/getdata")
    @ResponseBody
    public Map<String, Object> test3(@RequestBody String map) {

        return menuToJson.getLunch();
    }

}
