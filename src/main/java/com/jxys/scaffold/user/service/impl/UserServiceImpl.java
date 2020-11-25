package com.jxys.scaffold.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxys.scaffold.auth.enums.TokenSubject;
import com.jxys.scaffold.auth.service.TokenService;
import com.jxys.scaffold.auth.util.EncryptConfigUtil;
import com.jxys.scaffold.base.config.Constant;
import com.jxys.scaffold.base.entity.BaseService;
import com.jxys.scaffold.base.exception.ErrorServiceException;
import com.jxys.scaffold.base.exception.ServiceException;
import com.jxys.scaffold.user.data.SignInData;
import com.jxys.scaffold.user.data.SignUpData;
import com.jxys.scaffold.user.entity.User;
import com.jxys.scaffold.user.exception.PasswordErrorException;
import com.jxys.scaffold.user.exception.UserNotFoundException;
import com.jxys.scaffold.user.mapper.UserMapper;
import com.jxys.scaffold.user.service.UserService;
import com.jxys.scaffold.user.view.SignInView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private TokenService tokenService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;

    @Override
    public SignInView signIn(SignInData data) {
        // 找到对应name的用户
        User user = userMapper.selectOne(new QueryWrapper<User>().select("*").eq("name", data.getName()));
        // 判断用户是否存在
        if (user != null) {
            // 校验密码
            if (data.getPassword().equals(EncryptConfigUtil.decyptPwd(this.encryptorPassword, user.getPassword()))) {
                // 校验通过，登陆成功，返回Token
                SignInView signInView = new SignInView();
                // 生成AccessToken
                signInView.setAccessToken(tokenService.generate(TokenSubject.ACCESS, user.getId()));
                // 生成RrefreshToken，有效期为24小时
                signInView.setRefreshToken(tokenService.generate(TokenSubject.REFRESH, user.getId(), 24));
                //已登录校验(暂时关闭)
//                if (this.redisTemplate.hasKey(user.getName())){
//                    throw new LoggedNotException();
//                }
                //将对象序列化为JOSN格式储存在Redis中
                try {
                    this.redisTemplate.opsForValue().set(user.getId(), user, Constant.REDIS_OVERDUE_TIME_MINUTES, TimeUnit.MINUTES);
                } catch (Exception e) {
                    logger.error("向redis中存入数据失败!",e);
                    throw new ErrorServiceException();
                }finally {
                    //释放连接
                    RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
                }
                //登录对象信息
                signInView.setUser(user);
                return signInView;
            } else {
                // 自定义异常示范
                throw new PasswordErrorException();
            }

        } else {
            // 抛出用户不存在的服务异常
            throw new UserNotFoundException();
        }
    }

    @Override
    public String signUp(SignUpData data) {
        // 创建User对象
        User user = new User(data);

        try {
            //加密密码
            user.setPassword(EncryptConfigUtil.encyptPwd(this.encryptorPassword, user.getPassword()));
            // 尝试创建用户
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", user.getName());
            User u = this.userMapper.selectOne(queryWrapper);
            if (u != null) {
                return u.getName() + "用户已存在，请修改用户名后重新创建！";
            }
            // 建议使用索引约束来判断用户名是否存在，用户存在时会抛出异常，可以自行捕获数据库的异常，并返回用户已存在的错误提示
            userMapper.insertOneUser(user);
        } catch (Exception e) {
            logger.error("用户创建失败", e);
            throw new ServiceException("用户创建失败");
        }
        return "用户创建成功";
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }
    @Override
    public User getById(String userId) {
        return userMapper.selectById(userId);
    }
}
