package com.lucas.token.tokendemo.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.management.loading.PrivateMLet;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String status;

    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String userType;

    private Long createBy;
    private Date createdTime;
    private Long updateBy;
    private Date updatedTime;
    private Integer delFlag;

}
