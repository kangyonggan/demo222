package com.kangyonggan.demo.service;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.LoginLog;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface LoginLogService {

    /**
     * 保存登录日志
     *
     * @param username 用户名
     * @param ip       IP
     */
    void saveLoginLog(String username, String ip);

    /**
     * 搜索登录日志
     *
     * @param params 参数
     * @return 返回符合条件的登录日志
     */
    List<LoginLog> searchLoginLogs(Params params);

}
