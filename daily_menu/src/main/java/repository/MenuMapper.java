package repository;

import dto.MenuDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface MenuMapper {
    @Select(value = "SELECT * FROM breakfast")
    List<MenuDTO> getBreakfast();

    @Select(value = "SELECT * FROM lunch")
    List<MenuDTO> getLunch();

    @Select(value = "SELECT * FROM dinner")
    List<MenuDTO> getDinner();

}
