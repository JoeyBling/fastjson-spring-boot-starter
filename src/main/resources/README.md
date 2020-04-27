# Doc文档

## spring.factories文件介绍
> 配置多个启动类需要在后面加上`,\`结尾，最后一个无需加
```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
io.gitee.zhousiwei.FastJsonAutoConfigurationV1_X,\
io.gitee.zhousiwei.FastJsonAutoConfigurationV2_X
```