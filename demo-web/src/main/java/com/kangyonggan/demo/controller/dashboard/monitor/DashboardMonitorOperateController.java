package com.kangyonggan.demo.controller.dashboard.monitor;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Page;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.Monitor;
import com.kangyonggan.demo.service.MonitorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("dashboard/monitor/operate")
public class DashboardMonitorOperateController extends BaseController {

    @Autowired
    private MonitorService monitorService;

    /**
     * 操作日志
     *
     * @return 返回操作日志界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_OPERATE")
    public String index() {
        return getPathList();
    }

    /**
     * 操作日志列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_OPERATE")
    @ResponseBody
    public Page<Monitor> list() {
        Params params = getRequestParams();
        List<Monitor> monitors = monitorService.searchMonitors(params);

        return new Page<>(monitors);
    }
}
