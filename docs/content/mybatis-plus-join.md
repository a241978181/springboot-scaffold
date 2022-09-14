[返回目录](../index.md)

# mybatis-plus-join 连表查询

## 简述

众所周知，mybatis plus 封装的 mapper 不支持 join，如果需要支持就必须自己去实现。但是对于大部分的业务场景来说，都需要多表 join，要不然就没必要采用关系型数据库了。

所以，**mybatis-plus-join**组件便诞生了。

## 使用

#### 1. 继承

* mapper继承MPJBaseMapper (必选)
* service继承MPJBaseService (可选)
* serviceImpl继承MPJBaseServiceImpl (可选)

#### 2. 简单使用

```java_holder_method_tree
class test {
    @Resource
    private UserMapper userMapper;

    void testJoin() {
        List<UserDTO> list = userMapper.selectJoinList(UserDTO.class,
                new MPJLambdaWrapper<UserDO>()
                        .selectAll(UserDO.class)
                        .select(UserAddressDO::getTel)
                        .selectAs(UserAddressDO::getAddress, UserDTO::getUserAddress)
                        .select(AreaDO::getProvince, AreaDO::getCity)
                        .leftJoin(UserAddressDO.class, UserAddressDO::getUserId, UserDO::getId)
                        .leftJoin(AreaDO.class, AreaDO::getId, UserAddressDO::getAreaId)
                        .eq(UserDO::getId, 1)
                        .like(UserAddressDO::getTel, "1")
                        .gt(UserDO::getId, 5));
    }
}
```

对应SQL

```java_holder_method_tree
SELECT 
    t.id,
    t.name,
    t.sex,
    t.head_img,
    t1.tel,
    t1.address AS userAddress,
    t2.province,
    t2.city 
FROM 
    user t 
    LEFT JOIN user_address t1 ON t1.user_id = t.id 
    LEFT JOIN area t2 ON t2.id = t1.area_id 
WHERE (
    t.id = ? 
    AND t1.tel LIKE ? 
    AND t.id > ?)
```

#### 3. 说明

* UserDTO.class 查询结果返回类(resultType)
* selectAll() 查询指定实体类的全部字段
* select() 查询指定的字段,支持可变参数,同一个select只能查询相同表的字段 故将UserAddressDO和AreaDO分开为两个select()
* selectAs() 字段别名查询,用于数据库字段与业务实体类属性名不一致时使用
* leftJoin() 参数说明 第一个参数: 参与连表的实体类class 第二个参数: 连表的ON字段,这个属性必须是第一个参数实体类的属性 第三个参数: 参与连表的ON的另一个实体类属性
* 默认主表别名是t,其他的表别名以先后调用的顺序使用t1,t2,t3....
* 条件查询,可以查询主表以及参与连接的所有表的字段,全部调用mp原生的方法,正常使用没有sql注入风险

##### MPJLambdaWrapper 还有很多其他的功能

*
简单的SQL函数使用：[https://gitee.com/best_handsome/mybatis-plus-join/wikis/selectFunc()?sort_id=4082479](https://gitee.com/best_handsome/mybatis-plus-join/wikis/selectFunc()?sort_id=4082479)
*
ON语句多条件支持：[https://gitee.com/best_handsome/mybatis-plus-join/wikis/leftJoin?sort_id=3496671](https://gitee.com/best_handsome/mybatis-plus-join/wikis/leftJoin?sort_id=3496671)