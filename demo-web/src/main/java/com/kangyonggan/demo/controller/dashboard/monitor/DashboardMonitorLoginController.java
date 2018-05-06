package com.kangyonggan.demo.controller.dashboard.monitor;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Page;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.LoginLog;
import com.kangyonggan.demo.service.LoginLogService;
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
 * @date 2017/1/8
 */
@Controller
@RequestMapping("dashboard/monitor/login")
public class DashboardMonitorLoginController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录日志
     *
     * @return
     * @throws ParseException
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_LOGIN")
    public String index() {
        return getPathList();
    }

    /**
     * 登录日志列表查询
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("MONITOR_LOGIN")
    @ResponseBody
    public Page<LoginLog> list() {
        Params params = getRequestParams();
        List<LoginLog> loginLogs = loginLogService.searchLoginLogs(params);

        return new Page<>(loginLogs);
    }

}
