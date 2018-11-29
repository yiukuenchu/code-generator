![](https://ws2.sinaimg.cn/large/006tNbRwgy1fxp1qlcah7j31c00u00xh.jpg)
   
# 🚀DAO层代码生成器

> Generate the DAO codes in a fast, acurate and easy way.

## 🍪效果图

![](https://ws4.sinaimg.cn/large/006tNbRwgy1fxp27hzfn0g30ne0e7nni.gif)
  
## 🏀Build Setup

1.在src/main/resources/generator-config/application.properties里面，设置链接的数据库配置
```
db.url=jdbc:mysql://xx.xx.xx.xx:yyyy/zzzz  /* 这里填写连接的数据库的url */
db.user=yiukuenchu   /* 填写用户名 */
db.password=xxxxxx    /* 这里填写连接数据库的密码 */
db.jdbc.driver=com.mysql.jdbc.Driver  /* 这里若是mysql则不用修改*/
```

2.在src/main/java/main/MamsGenerator.java里面，设置实体父类。
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

3.根据实际修改pom.xml文件中<parent></parent>。**一般情况下无需修改其他依赖**。
  
## 🐱Usage

**‼️进行如下操作前，请确保配置文件连接数据库正确‼️**  
   
⚠️如果是第一次进行操作：
```
1. 把整个项目下载至你的本地。  
2. 把项目Import进IDE。  
3. 运行src/main/java/main/MamsGenerator.java，稍等几秒，将会生成**generated**文件夹在根目录下，并且将自动打开文件夹。   
4. 所生成DAO层，Service层，Controller层代码全部放在**generated**文件夹中。  
```
  
⚠️如之前进行过上述操作，在更改数据库表内容后，想重新生成代码：
```
1. 请首先把根目录下生成的generated文件夹删除。    
2. 执行同样操作生成代码即可。
```

## Changelog
- 2018-11-29   支持对controller层代码的生成
- 2018-12-03   支持对vue代码的生成
- 2018-12-07   支持对web js代码对生成

## License
[MIT](https://github.com/yiukuenchu/code-generator/blob/master/LICENSE)
