# 出现的问题：
1.数据库实体类需要实现序列化接口，不然无法set入redis
2.redis连接不上，需要修改服务器redis配置，修改为任何ip可连接
3.service层无法注入mapper，需要启动类上加上注解@MapperScan("xxxxx.xxxx.xxx.xxx")，mapper接口上加上注解@Mapper
4.swagger文档不生效，SwaggerConfig.java中的指定包路径写错了，写正确就行
5.开发中经常会遇到DO转VO，VO转DO，DTO转DO等需求，如果一个一个set属性太麻烦了，可以用BeanUtils.copyProperties(guideCardInfo,guideCardInfoVo)方法，但只能复制同名且同类型的字段，必须有getter和setter方法，不然复制无法设置。
6.序列化问题，jdk中的序列化方法会将实例序列化为二进制格式的，不方便调试查看的时候也是乱码，故需要修改序列化方法，详见redisConfiguration类，修改GenericJackson2JsonRedisSerializer这种序列化方式即可，这是一种非常通用的序列化方式，
# 使用方式
1.启动之后打开 127.0.0.1:8080/swagger-ui.html 
2.点击user-controller即可看到四个api，点击可以执行
