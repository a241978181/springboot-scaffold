package com.jxys.scaffold.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxys.scaffold.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李建
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //mybatis手写代码也可以出发plus的更新时间设置
    void insertOneUser(User user);
}