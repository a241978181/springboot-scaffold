package com.jxys.base.entity;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: 李建
 * Date: 2021/4/15
 * Time: 10:59
 */
public class BaseController {
    protected final Logger logger = getLogger();
    protected final Gson gson = new Gson();

    protected Logger getLogger() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        return logger;
    }

    /**
     * 封装成功时返回的对象（返回json数据时使用）
     */
    protected Result success(String msg, Object data) {
        return new Result<>().success(data, msg);
    }

    protected Result result(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    protected Result success(String msg) {
        return new Result().success(msg);
    }

    /**
     * 封装失败时返回的对象（返回json数据时使用）
     */
    protected Result fail(String msg, Integer code) {
        return new Result().fail(msg, code);
    }

    protected Result fail(String msg, Object data) {
        return new Result().fail(data, msg);
    }

    protected Result fail(String msg, Object data, Exception e) {
        logger.error(msg, e);
        return new Result().fail(data, msg);
    }

    protected Result fail(String msg, Exception e) {
        logger.error(msg, e.getMessage(), e);
        return new Result().fail(msg);
    }

    protected Result fail(String msg) {
        return new Result().fail(msg);
    }

    protected Map<String, Object> getMap() {
        return new HashMap<>();
    }


    protected String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected <T> List<T> fromJson2Array(String json, Class<T> clazz) {
        List<Object> list = gson.fromJson(json, ArrayList.class);
        List<T> result = new ArrayList<>();
        for (Object o : list) {
            String oJson = gson.toJson(o);
            T data = gson.fromJson(oJson, clazz);
            result.add(data);
        }
        return result;
    }
}
