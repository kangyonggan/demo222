package com.kangyonggan.demo.service;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.Monitor;
import com.kangyonggan.extra.core.model.MonitorInfo;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/30/18
 */
public interface MonitorService {

    /**
     * 保存监控信息
     *
     * @param monitorInfo
     */
    void saveMonitor(MonitorInfo monitorInfo);

    /**
     * 搜索操作日志
     *
     * @param params
     * @return
     */
    List<Monitor> searchMonitors(Params params);

}
