package com.kangyonggan.demo.controller.dashboard.system;

import com.kangyonggan.app.annotation.Token;
import com.kangyonggan.app.controller.BaseController;
import com.kangyonggan.app.bean.Page;
import com.kangyonggan.app.bean.Params;
import com.kangyonggan.app.bean.Response;
import com.kangyonggan.demo.model.Dictionary;
import com.kangyonggan.demo.model.DictionaryType;
import com.kangyonggan.demo.service.DictionaryService;
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
@RequestMapping("dashboard/system/dict/content")
public class DashboardSystemDictContentController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    /**
     * 字典管理
     *
     * @param model 数据
     * @return 返回字典管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    public String index(Model model) {
        List<DictionaryType> dictionaryTypes = dictionaryTypeService.findAllDictionaryTypes();

        model.addAttribute("dictionaryTypes", dictionaryTypes);
        return getPathList();
    }

    /**
     * 字典列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @ResponseBody
    public Page<Dictionary> list() {
        Params params = getRequestParams();
        List<Dictionary> dictionaries = dictionaryService.searchDictionaries(params);

        return new Page<>(dictionaries);
    }

    /**
     * 添加字典
     *
     * @param model 数据
     * @return 返回添加字典模态框
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @Token(key = "createDictContent")
    public String create(Model model) {
        List<DictionaryType> dictionaryTypes = dictionaryTypeService.findAllDictionaryTypes();

        model.addAttribute("dictionary", new DictionaryType());
        model.addAttribute("dictionaryTypes", dictionaryTypes);
        return getPathFormModal();
    }

    /**
     * 保存字典
     *
     * @param dictionary 字典
     * @param result     绑定结果
     * @return 响应
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @Token(key = "createDictContent", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("dictionary") @Valid Dictionary dictionary, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryService.saveDictionary(dictionary);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 编辑字典
     *
     * @param id    字典ID
     * @param model 数据
     * @return 返回编辑字典模态框
     */
    @RequestMapping(value = "{id:[\\d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @Token(key = "editDictContent")
    public String create(@PathVariable("id") Long id, Model model) {
        List<DictionaryType> dictionaryTypes = dictionaryTypeService.findAllDictionaryTypes();

        model.addAttribute("dictionary", dictionaryService.findDictionaryById(id));
        model.addAttribute("dictionaryTypes", dictionaryTypes);
        return getPathFormModal();
    }

    /**
     * 更新字典
     *
     * @param dictionary 字典
     * @param result     绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @Token(key = "editDictContent", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("dictionary") @Valid Dictionary dictionary, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            dictionaryService.updateDictionary(dictionary);
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
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @ResponseBody
    public Response deleted(@PathVariable("id") Long id, @PathVariable("isDeleted") byte isDeleted) {
        Dictionary dictionary = dictionaryService.findDictionaryById(id);
        dictionary.setIsDeleted(isDeleted);
        dictionaryService.updateDictionary(dictionary);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除
     *
     * @param ids 字典ID
     * @return 响应
     */
    @RequestMapping(value = "deleted", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @ResponseBody
    public Response deleted(@RequestParam("ids") String ids) {
        dictionaryService.deleteDictionaries(ids);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param id 字典ID
     * @return 响应
     */
    @RequestMapping(value = "{id:[\\d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_DICT_CONTENT")
    @ResponseBody
    public Response remove(@PathVariable("id") Long id) {
        dictionaryService.deleteDictionaryById(id);
        return Response.getSuccessResponse();
    }

}
