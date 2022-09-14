# springboot-scaffold

SpringBoot Scaffold脚手架

## 简介

这是一个基于`SpringBoot 2.2.7 RELEASE`，用于构建`RESTful API`工程的脚手架，工程已经集成大量插件与 风格定义类，为您节省了大量的开发时间，只需`一分钟`即可开始编写业务代码。

## 快速开始

1. 构建数据库，sql文件在`/docs/sql`下。
2. 修改`application.yml`等三个配置文件下的数据库与Redis信息
3. 开始编写业务代码

## 更新简报

> ### v1.20
>

> 2022-9-14

1. 修改pom归集方式，升级几乎全部引入包版本
2. 优化了logkback日志
3. 引入了mybatis-plus-join（连表查询）
4. 公共实体类中添加了mybatisplus逻辑删除字段

## [文档](./docs/index.md)

文档内提供了详细的功能说明，强烈建议阅读文档后再开始编码。


当前版本：v1

## 基本功能

- 封装了 RESTful 风格的返回结果
- 全局接管异常，并使用 RESTful 风格返回异常信息
- 简单封装了 jjwt，提供了一个开箱即用的认证模块
- @Auth 注解，用注解的方式校验用户信息
- 集成了常用的库，详见相关依赖
- 集成了Mybatis与MybatisPlus和mybatis-plus-join
- 集成了logback细致日志打印
- 集成了swagger文档生成器
- 集成了Redis
- 封装了entity与Service的父类
- 集成了jasypt用于配置文件及数据库数据的加密

## 相关依赖

- SpringBoot
- MybatisPlus
- lombok
- jjwt
- fastjson
- druid
- Mybatis
- logback
- swagger
- Redis
- jasypt
- hutool
- Swagger-Bootstrap-UI

