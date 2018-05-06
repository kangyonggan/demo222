package com.kangyonggan.demo.mapper;

import com.kangyonggan.app.mapper.MyMapper;
import com.kangyonggan.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface UserMapper extends MyMapper<User> {

    /**
     * 保存用户角色
     *
     * @param username  用户名
     * @param roleCodes 角色代码
     */
    void insertUserRoles(@Param("username") String username, @Param("roleCodes") List<String> roleCodes);

}