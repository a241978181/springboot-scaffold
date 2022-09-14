package com.jxys.auth.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatisPlus自动写入参数配置
 *
 * @author 李建
 * @since 2020年11月20日14:39:28
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 获取UNIX时间戳（精确到秒的10位字符的时间戳）
     */
    private Date getCurrentUnixTimestamp() {
        return new Date();
    }

    /**
     * 自动插入创建时间到create_time字段
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 若要使用精确到秒级的时间戳请将getCurrentUnixTimestamp()改成System.currentTimeMillis()并在数据库中设置long类型的create_time字段
        this.setFieldValByName("createTime", getCurrentUnixTimestamp(), metaObject);
    }

    /**
     * 自动插入更新时间到update_time字段
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 若要使用精确到秒级的时间戳请将getCurrentUnixTimestamp()改成System.currentTimeMillis()并在数据库中设置long类型的create_time字段
        this.setFieldValByName("updateTime", getCurrentUnixTimestamp(), metaObject);
    }


}
