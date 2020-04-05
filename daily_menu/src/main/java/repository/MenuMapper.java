package repository;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository()
public interface MenuMapper {
    @Select(value = "SELECT * FROM breakfast")
    HashMap<String, Object> getBreakfast();
    @Select(value = "SELECT * FROM lunch")
    HashMap<String, Object> getLunch();
    @Select(value = "SELECT * FROM dinner")
    HashMap<String, Object> getDinner();

}
