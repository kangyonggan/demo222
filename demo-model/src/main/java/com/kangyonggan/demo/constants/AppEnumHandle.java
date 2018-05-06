package com.kangyonggan.demo.constants;

import com.kangyonggan.extra.core.annotation.Handle;
import com.kangyonggan.extra.core.handle.EnumHandle;
import com.kangyonggan.extra.core.model.EnumInfo;

/**
 * 枚举句柄
 *
 * @author kangyonggan
 * @since 5/4/18
 */
@Handle(type = Handle.Type.ENUM)
public class AppEnumHandle extends EnumHandle {

    /**
     * 实例
     */
    private static AppEnumHandle instance = new AppEnumHandle();

    /**
     * 私有化构造
     */
    private AppEnumHandle() {
    }

    /**
     * 获取枚举信息
     *
     * @param key 枚举的key
     * @return 返回枚举信息
     */
    public static EnumInfo getEnumInfo(String key) {
        return instance.get(key);
    }

    /**
     * 搜索枚举信息
     *
     * @param key   枚举的key
     * @param code  代码
     * @param name  名称
     * @param clazz 类
     */
    private static void collectionEnumInfo(String key, String code, String name, String clazz) {
        try {
            instance.put(key, new EnumInfo(key, code, name, Class.forName(clazz)));
        } catch (ClassNotFoundException e) {
        }
    }
}
