package com.kangyonggan.demo.mapper;

import com.kangyonggan.demo.model.Menu;
import com.kangyonggan.app.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 查找用户菜单
     *
     * @param username 用户名
     * @return 返回用户所有菜单
     */
    List<Menu> selectMenusByUsername(@Param("username") String username);

    /**
     * 查找角色菜单
     *
     * @param code 角色代码
     * @return 返回角色所有菜单
     */
    List<Menu> selectMenus4Role(@Param("code") String code);
}