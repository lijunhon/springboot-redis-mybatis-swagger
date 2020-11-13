package org.springbootmybaitsredis.redis1.vo;

import javax.persistence.Column;
import java.util.Date;

public class UserVo {
    private Integer id;

    private String username;
    private String password;
    //0=女 1=男
    private Byte sex;
    //0=不删除 1=删除
    private Byte deleted;

    private Date updateTime;

    private Date createTime;

}
