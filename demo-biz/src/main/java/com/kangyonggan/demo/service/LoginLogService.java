package com.kangyonggan.demo.service;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.LoginLog;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/27/18
 */
public interface LoginLogService {

    /**
     * 保存登录日志
     *
     * @param username
     * @param ip
     */
    void saveLoginLog(String username, String ip);

    /**
     * 搜索登录日志
     *
     * @param params
     * @return
     */
    List<LoginLog> searchLoginLogs(Params params);

}
