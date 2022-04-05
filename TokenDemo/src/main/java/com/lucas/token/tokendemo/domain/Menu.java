package com.lucas.token.tokendemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "sys_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Menu implements Serializable {
    private static final long serialVersionUID = -6618144092284947161L;

    @TableId
    private Long id;

    private String menuName;
    private String path;
    private String component;
    private String visible;
    private String status;
    private String perms;
    private String icon;

    private Long createBy;
    private Date createdTime;
    private Long updateBy;
    private Date updatedTime;
    private Integer delFlag;
    private String remark;


}
