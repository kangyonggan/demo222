package com.kangyonggan.demo.controller.dashboard.system;

import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Page;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.app.bean.Response;
import com.kangyonggan.demo.model.Menu;
import com.kangyonggan.demo.model.Role;
import com.kangyonggan.demo.service.MenuService;
import com.kangyonggan.demo.service.RoleService;
import com.kangyonggan.app.util.Collections3;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("dashboard/system/role")
public class DashboardSystemRoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 角色管理
     *
     * @return 返回角色管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    public String index() {
        return getPathList();
    }

    /**
     * 角色列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Page<Role> list() {
        Params params = getRequestParams();
        List<Role> roles = roleService.searchRoles(params);

        return new Page<>(roles);
    }

    /**
     * 添加角色
     *
     * @param model 数据
     * @return 返回添加角色模态框
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @Token(key = "createRole")
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return getPathFormModal();
    }

    /**
     * 保存角色
     *
     * @param role   角色
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_ROLE")
    @Token(key = "createRole", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            roleService.saveRole(role);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 编辑角色
     *
     * @param code  角色代码
     * @param model 数据
     * @return 返回编辑角色模态框
     */
    @RequestMapping(value = "{code:[\\w_]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @Token(key = "editRole")
    public String edit(@PathVariable("code") String code, Model model) {
        model.addAttribute("role", roleService.findRoleByCode(code));
        return getPathFormModal();
    }

    /**
     * 更新角色
     *
     * @param role   角色
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_ROLE")
    @Token(key = "editRole", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("role") @Valid Role role, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            roleService.updateRole(role);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除/恢复
     *
     * @param code      角色代码
     * @param isDeleted 是否删除
     * @return 响应
     */
    @RequestMapping(value = "{code:[\\w]+}/deleted/{isDeleted:\\b0\\b|\\b1\\b}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Response deleted(@PathVariable("code") String code, @PathVariable("isDeleted") byte isDeleted) {
        Role role = roleService.findRoleByCode(code);
        role.setIsDeleted(isDeleted);
        roleService.updateRole(role);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param code 角色代码
     * @return 响应
     */
    @RequestMapping(value = "{code:[\\w_]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Response remove(@PathVariable("code") String code) {
        roleService.deleteRoleByCode(code);
        return Response.getSuccessResponse();
    }

    /**
     * 修改角色的菜单
     *
     * @param code  角色代码
     * @param model 数据
     * @return 返回修改角色的菜单模态框
     */
    @RequestMapping(value = "{code:[\\w_]+}/menus", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @Token(key = "setMenus")
    public String menus(@PathVariable("code") String code, Model model) {
        Role role = roleService.findRoleByCode(code);
        List<Menu> roleMenus = menuService.findMenus4Role(role.getCode());
        if (roleMenus != null) {
            roleMenus = Collections3.extractToList(roleMenus, "code");
        }

        List<Menu> allMenus = menuService.findAllMenus();

        model.addAttribute("roleMenus", roleMenus);
        model.addAttribute("allMenus", allMenus);
        model.addAttribute("roleCode", code);
        return getPathRoot() + "/menus-modal";
    }

    /**
     * 更新角色菜单
     *
     * @param code  角色代码
     * @param menus 菜单代码
     * @return 响应
     */
    @RequestMapping(value = "{code:[\\w_]+}/menus", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    @Token(key = "setMenus", type = Token.Type.CHECK)
    public Response menus(@PathVariable("code") String code, @RequestParam(value = "menus") String menus) {
        Role role = roleService.findRoleByCode(code);

        roleService.updateRoleMenus(role.getCode(), menus);
        return Response.getSuccessResponse();
    }
}
