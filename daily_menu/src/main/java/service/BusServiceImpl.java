package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    SimpleText simpleText;

    @Autowired
    BusTimeService busTimeService;

    @Autowired
    BusRemainTimeService busRemainTimeService;
    static String NonData[] = {"00:00"};

    static String atTerminal_DaeSung[] = {
            "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
            "15:00", "16:00", "17:00", "18:00", "19:00", "20:30"
    };
    static String atKoreatech_DaeSung[] = {
            "08:35", "09:35", "10:35", "11:35", "12:35", "13:35", "14:35",
            "15:30", "16:35", "17:35", "18:35", "19:35", "20:30", "22:05"
    };

    static String atKoreatech_daily_shuttle[] = {
            "09:10", "11:00", "14:00", "15:00", "16:00",
            "16:30", "17:00", "19:30", "21:00", "22:40"
    };

    static String atTerminal_daily_shuttle[] = {
            "10:10", "11:25", "14:25", "16:05", "16:25",
            "16:55", "17:25", "18:45", "19:55", "22:00"
    };

    static String atStation_toKoreatech_daily_shuttle[] = {
            "10:15", "11:30", "14:30", "16:10", "16:30",
            "17:00", "17:30", "18:50", "20:00", "22:05"
    };

    static String atKoreatech_saturday_shuttle[] = {
            "14:00"
    };

    static String atTerminal_saturday_shuttle[] = {
            "14:25", "18:45"
    };

    public HashMap<String, Object> getKoreatech() {

        String result = busRemainTimeService.getBusTime(atKoreatech_DaeSung, atKoreatech_daily_shuttle);
        return simpleText.simpleText(result);
    }

    public HashMap<String, Object> getTerminal() {
        String result = busRemainTimeService.getBusTime(atTerminal_DaeSung, atTerminal_daily_shuttle);
        return simpleText.simpleText(result);
    }

    public HashMap<String, Object> getStationToKoreatech() {
        String result = busRemainTimeService.getBusTime(NonData, atKoreatech_daily_shuttle);
        return simpleText.simpleText(result);
    }

    public HashMap<String, Object> getStationToTerminal() {
        String result = busRemainTimeService.getBusTime(NonData, NonData);
        return simpleText.simpleText(result);
    }
}