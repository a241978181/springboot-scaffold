[返回目录](../index.md)

# 自动填充创建与更新日期

## 简述

这是一个基于[MybatisPlus 特性](https://mp.baomidou.com/guide/auto-fill-metainfo.html)的功能，
只要数据库的表中定义了 create_time 和 update_time 字段，在创建和更新数据时
 MybatisPlus 会自动把对应的时间戳填充上去。

## 如何使用

1. 在数据库的表中添加 create_time 和 update_time 字段
2. 在对应的 entity 中继承PojoParent.java,源码如下。
3. 如id需要自行制定则无需继承该类直接将createTime与updateTime属性复制到新对象中即可。

```java
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
        private Integer createTime;
    
        //update_time(修改时间)
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Integer updateTime;
    
    
    }
```

> 注意：为了节省数据库的存储空间，这里默认生成的时间戳是 UNIX 时间戳（精确到秒的 10 位数的时间戳），若需要精确到毫秒的时间戳，可以自行修改源码。
