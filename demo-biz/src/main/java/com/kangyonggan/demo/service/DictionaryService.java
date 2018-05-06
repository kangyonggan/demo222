package com.kangyonggan.demo.service;

import com.kangyonggan.app.bean.Params;
import com.kangyonggan.demo.model.Dictionary;

import java.util.List;

/**
 * @author kangyonggan
 * @date 4/27/18
 */
public interface DictionaryService {

    /**
     * 搜索字典
     *
     * @param params
     * @return
     */
    List<Dictionary> searchDictionaries(Params params);

    /**
     * 保存字典
     *
     * @param dictionary
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 查找字典
     *
     * @param id
     * @return
     */
    Dictionary findDictionaryById(Long id);

    /**
     * 更新字典
     *
     * @param dictionary
     */
    void updateDictionary(Dictionary dictionary);

    /**
     * 删除字典
     *
     * @param id
     */
    void deleteDictionaryById(Long id);

    /**
     * 校验是否存在字典
     *
     * @param type
     * @param code
     * @return
     */
    boolean existsDictionary(String type, String code);

    /**
     * 查找指定类型的字典
     *
     * @param type
     * @return
     */
    List<Dictionary> findDictionariesByType(String type);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteDictionaries(String ids);

}
