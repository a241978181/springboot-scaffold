[返回目录](../index.md)

# jasypt 加密

## 简述

官方支持的一款能够对配置文件及数据加密的组件，可以屏蔽用户密码或者配置文件中的数据库账号密码。

## 使用
#### 1. 加密数据
参照`EncryptConfigUtil.java`中的加密与解密进行使用即可。

#### 2. 加密配置文件
1. 添加秘钥配置，可以在配置文件中设置，但是**推荐在idea等工具的虚拟机参数中设置**。默认写在了配置文件中。

```java_holder_method_tree
jasypt:
  encryptor:
    # 密钥
    password: jasypt
    # 指定加密方式
    algorithm: PBEWithMD5AndDES
    iv-generator-classname: org.jasypt.iv.NoIvGenerator

```
2. 手动通过`EncryptConfigUtil.java`生成你需要的密码串放到配置文件中即可。
> 注意：生成的加密数据放到配置文件时需要用`ENC()`包围起来

如下
```java_holder_method_tree
username: ENC(3g2WGnuz+u7Fq1T6xbQ7TQ==)     # 数据库账号
password: ENC(zniE4Hlid104xsxdzQcK/w==)     # 数据库密码
```