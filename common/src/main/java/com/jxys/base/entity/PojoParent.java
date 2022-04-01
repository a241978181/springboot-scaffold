package com.jxys.base.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类父类
 * @Author 李建
 * @Date 2020/11/20 10:19
 * @Version 1.0
 **/
@Data
public class PojoParent implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    //create_time(增加时间)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    //update_time(修改时间)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
