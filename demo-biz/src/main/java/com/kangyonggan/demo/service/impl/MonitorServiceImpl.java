package com.kangyonggan.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.app.bean.Query;
import com.kangyonggan.app.util.ShiroUtils;
import com.kangyonggan.demo.model.Monitor;
import com.kangyonggan.demo.service.MonitorService;
import com.kangyonggan.app.util.StringUtil;
import com.kangyonggan.extra.core.model.MonitorInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @date 3/30/18
 */
@Service
public class MonitorServiceImpl extends BaseService<Monitor> implements MonitorService {

    @Override
    public void saveMonitor(MonitorInfo monitorInfo) {
        Monitor monitor = new Monitor();
        BeanUtils.copyProperties(monitorInfo, monitor);

        monitor.setBeginTime(new Date(monitorInfo.getMethodStartTime()));
        monitor.setEndTime(new Date(monitorInfo.getMethodEndTime()));

        monitor.setHasReturnValue((byte) (monitorInfo.getHasReturnValue() ? 1 : 0));
        if (monitorInfo.getHasReturnValue()) {
            monitor.setReturnValue(JSONObject.toJSONString(monitorInfo.getReturnValue()));
        }
        if (StringUtils.isEmpty(monitor.getReturnValue())) {
            monitor.setReturnValue(StringUtils.EMPTY);
        }
        monitor.setArgs(JSONObject.toJSONString(monitorInfo.getArgs()));
        monitor.setUsername(ShiroUtils.getShiroUsername());

        myMapper.insertSelective(monitor);
    }

    @Override
    public List<Monitor> searchMonitors(Params params) {
        Example example = new Example(Monitor.class);
        Example.Criteria criteria = example.createCriteria();
        Query query = params.getQuery();

        String app = query.getString("app");
        if (StringUtils.isNotEmpty(app)) {
            criteria.andEqualTo("app", app);
        }

        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }

        String hasReturnValue = query.getString("hasReturnValue");
        if (StringUtils.isNotEmpty(hasReturnValue)) {
            criteria.andLike("hasReturnValue", hasReturnValue);
        }

        Date beginDate = query.getDate("beginDate");
        if (beginDate != null) {
            criteria.andGreaterThanOrEqualTo("beginTime", beginDate);
        }
        Date endDate = query.getDate("endDate");
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("endTime", endDate);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        }

        example.selectProperties("id", "app", "type", "description", "beginTime", "endTime", "hasReturnValue", "username");

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }
}
