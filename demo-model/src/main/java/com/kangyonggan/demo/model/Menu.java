package com.kangyonggan.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Table(name = "tb_menu")
@Data
public class Menu implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 菜单代码
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单代码
     */
    private String pcode;

    /**
     * 菜单地址
     */
    private String url;

    /**
     * 菜单排序(从0开始)
     */
    private Integer sort;

    /**
     * 菜单图标的样式
     */
    private String icon;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * 子菜单
     */
    @Transient
    private List<Menu> leaf;

    /**
     * 父菜单ID
     */
    @Transient
    private Long pid;

    private static final long serialVersionUID = 1L;
}