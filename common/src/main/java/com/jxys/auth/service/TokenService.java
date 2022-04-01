package com.jxys.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.Map;

/**
 * Token服务
 *
 * @author: 李建
 * @since: 2018/12/12 15:15
 * @update: 2018/12/24 14:14
 **/
public interface TokenService {
    String generate(Object subject, String id);

    String generate(Object subject, String id, int hours);

    String generate(Object subject, Map<String, Object> payload);

    String generate(Object subject, Map<String, Object> payload, int hours);

    Jws<Claims> parse(Object subject, String token);
}