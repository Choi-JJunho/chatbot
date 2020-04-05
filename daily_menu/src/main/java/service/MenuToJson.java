package service;

import java.util.HashMap;

public interface MenuToJson {
    HashMap<String, Object> getBreakfast();
    HashMap<String, Object> getLunch();
    HashMap<String, Object> getDinner();
    HashMap<String, Object> incorrect();

}
