package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.BusService;
import service.IncorrectService;

import java.util.HashMap;

@Controller
public class BusController {

    @Autowired
    BusService busService;

    @Autowired
    IncorrectService incorrect;

    @RequestMapping("/getbustime")
    @ResponseBody
    public HashMap<String, Object> bustime(@RequestBody String map) {
        if(map.contains("한기대 야우리"))
            return busService.getKoreatech();
        else if(map.contains("야우리 한기대"))
            return busService.getTerminal();
        else if(map.contains("천안역 한기대"))
            return busService.getStationToKoreatech();
        else if(map.contains("천안역 야우리"))
            return busService.getStationToTerminal();
        else
            return incorrect.printMessage();
    }
}
