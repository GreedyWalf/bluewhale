package com.qs.bluewhale.service.mapper;

import com.qs.bluewhale.entity.Menu;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MenuMapper {

    List<Menu> getMenuListByParentMenuId(String parentMenuId);

    /**
     * 指定返回map集合的key属性（对应的是实体类的属性名，不是数据库的）
     *
     * @param menuId
     * @return
     */
    @MapKey("menuId")
    Map<String, Menu> getMenuIdAndUrlMap(String menuId);
}
