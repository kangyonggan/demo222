package com.kangyonggan.demo.controller.dashboard.system;

import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Page;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.app.bean.Response;
import com.kangyonggan.demo.model.DictionaryType;
import com.kangyonggan.demo.service.DictionaryTypeService;
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
@RequestMapping("dashboard/system/dict/type")
public class DashboardSystemDictTypeController extends BaseController {

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 字典类型管理
     *
     * @return 返回字典类型管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    public String index() {
        return getPathList();
    }

    /**
     * 字典类型列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Page<DictionaryType> list() {
        Params params = getRequestParams();
        List<DictionaryType> dictionaryTypes = dictionaryTypeService.searchDictionaryTypes(params);

        return new Page<>(dictionaryTypes);
    }

    /**
     * 添加字典类型
     *
     * @param model 数据
     * @return 返回添加字典类型模态框
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "createDictType")
    public String create(Model model) {
        model.addAttribute("dictionaryType", new DictionaryType());
        return getPathFormModal();
    }

    /**
     * 保存字典类型
     *
     * @param dictionaryType 字典类型
     * @param result         绑定结果
     * @return 响应
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "createDictType", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("dictionaryType") @Valid DictionaryType dictionaryType, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryTypeService.saveDictionaryType(dictionaryType);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 编辑字典类型
     *
     * @param id    字典类型ID
     * @param model 数据
     * @return 返回编辑字典类型模态框
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "editDictType")
    public String create(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dictionaryType", dictionaryTypeService.findDictionaryTypeById(id));
        return getPathFormModal();
    }

    /**
     * 更新字典类型
     *
     * @param dictionaryType 字典类型
     * @param result         绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @Token(key = "editDictType", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("dictionaryType") @Valid DictionaryType dictionaryType, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryTypeService.updateDictionaryType(dictionaryType);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除/恢复
     *
     * @param id        字典ID
     * @param isDeleted 是否删除
     * @return 响应
     */
    @RequestMapping(value = "{id:[\\d]+}/deleted/{isDeleted:\\b0\\b|\\b1\\b}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Response deleted(@PathVariable("id") Long id, @PathVariable("isDeleted") byte isDeleted) {
        DictionaryType dictionaryType = dictionaryTypeService.findDictionaryTypeById(id);
        dictionaryType.setIsDeleted(isDeleted);
        dictionaryTypeService.updateDictionaryType(dictionaryType);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param id 字典ID
     * @return 响应
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_TYPE")
    @ResponseBody
    public Response remove(@PathVariable("id") Long id) {
        dictionaryTypeService.deleteDictionaryTypeById(id);
        return Response.getSuccessResponse();
    }

}
