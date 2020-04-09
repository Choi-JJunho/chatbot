package service;

import java.util.HashMap;

public interface BusService {
    HashMap<String, Object> getKoreatech();
    HashMap<String, Object> getTerminal();
    HashMap<String, Object> getStationToKoreatech();
    HashMap<String, Object> getStationToTerminal();
}
