package com.kangyonggan.demo.controller.dashboard;

import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Response;
import com.kangyonggan.app.bean.ShiroUser;
import com.kangyonggan.demo.model.Menu;
import com.kangyonggan.demo.model.User;
import com.kangyonggan.demo.service.MenuService;
import com.kangyonggan.demo.service.UserService;
import com.kangyonggan.app.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kangyonggan
 * @date 3/22/18
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 工作台模板
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String layout(Model model) {
        ShiroUser shiroUser = ShiroUtils.getShiroUser();
        User user = userService.findUserByUsername(shiroUser.getUsername());
        List<Menu> menus = menuService.findMenusByUsername(shiroUser.getUsername());

        model.addAttribute("_user", user);
        model.addAttribute("_menus", menus);
        return getPathRoot() + "/layout";
    }

    /**
     * 工作台首页
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String index() {
        return getPathRoot() + "/index";
    }

    /**
     * 开发手册
     *
     * @return
     */
    @RequestMapping(value = "help", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String help() {
        return getPathRoot() + "/help/index";
    }

    /**
     * 模态框示例
     *
     * @return
     */
    @RequestMapping(value = "help/create", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String create() {
        return getPathRoot() + "/help/help-modal";
    }

    /**
     * 模态框示例
     *
     * @return
     */
    @RequestMapping(value = "help/save", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD")
    @ResponseBody
    public Response save() {
        return Response.getSuccessResponse();
    }
}
