package com.jxys.base.entity;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class BaseService {

    protected final Logger logger = getLogger();
    protected Gson gson = new Gson();

    public Logger getLogger() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        return logger;
    }
}