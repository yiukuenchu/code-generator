![](https://ws2.sinaimg.cn/large/006tNbRwgy1fxp1qlcah7j31c00u00xh.jpg)
   
# DAO层代码生成器

> Generate the DAO codes in a fast, acurate and easy way.

## 效果图

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fxp27hzfn0g30ne0e7nni.gif)
  
## Build Setup

1.在src/main/resources/generator-config/application.properties里面，设置链接的数据库配置
```
db.url=jdbc:mysql://xx.xx.xx.xx:yyyy/zzzz  /* 这里填写连接的数据库的url */
db.user=yiukuenchu   /* 填写用户名 */
db.password=xxxxxx    /* 这里填写连接数据库的密码 */
db.jdbc.driver=com.mysql.jdbc.Driver  /* 这里若是mysql则不用修改*/
```

2.在src/main/java/main/MamsGenerator里面，设置实体父类。
```
// 自定义实体父类
   strategy.setSuperEntityClass("com.yiukuen.demo.entity.BaseEntity");
// 自定义实体，公共字段
// strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
// 自定义 mapper 父类
   strategy.setSuperMapperClass("com.yiukuenchu.demo.dao.base.BaseMapper");
// 自定义 service 父类
   strategy.setSuperServiceClass("com.yiukuenchu.demo.service.rpc.api.base.BaseService");
// 自定义 service 实现类父类
// strategy.setSuperServiceImplClass("com.yiukuen.demo.TestServiceImpl");
// 自定义 controller 父类
   strategy.setSuperControllerClass("com.yiukuenchu.rest.controller.base.BaseController");
```
