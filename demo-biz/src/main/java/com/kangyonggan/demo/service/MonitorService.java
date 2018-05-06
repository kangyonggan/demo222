package com.kangyonggan.demo.service;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.Monitor;
import com.kangyonggan.extra.core.model.MonitorInfo;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface MonitorService {

    /**
     * 保存监控信息
     *
     * @param monitorInfo 监控信息
     */
    void saveMonitor(MonitorInfo monitorInfo);

    /**
     * 搜索操作日志
     *
     * @param params 参数
     * @return 返回符合条件的操作日志
     */
    List<Monitor> searchMonitors(Params params);

}
