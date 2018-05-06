package com.kangyonggan.demo.mapper;

import com.kangyonggan.demo.model.Menu;
import com.kangyonggan.app.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangyonggan
 * @date 2018/04/02
 */
@Repository
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 查找用户菜单
     *
     * @param username
     * @return
     */
    List<Menu> selectMenusByUsername(@Param("username") String username);

    /**
     * 查找角色菜单
     *
     * @param code
     * @return
     */
    List<Menu> selectMenus4Role(@Param("code") String code);
}