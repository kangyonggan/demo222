package com.kangyonggan.demo.freemarker;

import com.kangyonggan.app.freemarker.AbstractFunctionTag;
import com.kangyonggan.demo.model.Dictionary;
import com.kangyonggan.demo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class DictTag extends AbstractFunctionTag {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 获取字典列表, 根据type
     *
     * @param arguments 参数
     * @return 返回字典列表
     */
    public List<Dictionary> list(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取字典列表必须指定type！");
        }
        String type = arguments.get(1).toString();
        return dictionaryService.findDictionariesByType(type);
    }

}
