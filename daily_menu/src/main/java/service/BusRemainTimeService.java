package service;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusRemainTimeService {

    @Autowired
    BusTimeService busTimeService;

    public String getBusTime(String Daesung[], String Shuttle[]) {
        boolean flag = false;
        LocalTime time = LocalTime.now();
        String currentTime = time.toString("HH:mm:ss");
        String result = "";
        result += "[ 학교셔틀 ]\n";
        for (String bustime : Shuttle) {
            bustime += ":00";
            if (currentTime.compareTo(bustime) > 0) {
                continue;
            } else {
                result += busTimeService.getRemainTime(currentTime, bustime) +
                        "남았습니다.\n";
                flag = true;
                break;
            }
        }
        if(!flag)
            result += "운행정보가 없습니다\n";

        flag = false;
        result += "[ 대성고속 ]\n";
        for (String bustime : Daesung) {
            bustime += ":00";
            if (currentTime.compareTo(bustime) > 0) {
                continue;
            } else {
                result += busTimeService.getRemainTime(currentTime, bustime) +
                        "남았습니다.\n";
                flag = true;
                break;
            }
        }
        if(!flag)
            result += "운행정보가 없습니다\n";

        return result;
    }

}