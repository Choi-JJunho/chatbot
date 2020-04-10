package service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BusTimeServiceImpl implements BusTimeService {

    public String getRemainTime(String currentTime, String busTime) {
        Pattern pattern = Pattern.compile("[0-9]{2}");
        Matcher matcher_current = pattern.matcher(currentTime);
        Matcher matcher_bus = pattern.matcher(busTime);

        int cur, bus, flag = 0, totalTime = 0;
        int hour = 0, min = 0, sec = 0;

        while(matcher_bus.find() && matcher_current.find()) {
            cur = Integer.parseInt(matcher_current.group());
            bus = Integer.parseInt(matcher_bus.group());
            switch(flag) {
                case 0:
                    if(bus > cur)
                        hour = bus - cur;
                    break;
                case 1:
                    if(cur > bus) {
                        bus += 60;
                        --hour;
                    }
                    min = bus - cur;
                    break;
                case 2:
                    if(cur > bus) {
                        bus += 60;
                        if(min == 0) {
                            --hour;
                            min+=59;
                        }
                        else
                            --min;
                    }
                    sec = bus - cur;
                    break;
            }
            ++flag;
        }
        String result = Integer.toString(hour) + "시간 "
                + Integer.toString(min) + "분 "
                + Integer.toString(sec)+"초\n";
        System.out.println(result);
        return result;
    }
}
