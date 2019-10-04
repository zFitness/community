## spring社区

## 资料
[社区原型](https://elasticsearch.cn/ )

[spring文档](https://spring.io/guides/gs/serving-web-content/)

[bootstrap文档](https://v3.bootcss.com/getting-started/)

[github OAuth文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[okhttp 文档](https://square.github.io/okhttp/)

[springboot-mybatis 文档](https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/index.html)

[thymeleaf 文档](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)

[Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

## 工具
* idea
* git
* maven
* [Visual Paradigm](https://www.visual-paradigm.com)

## 脚本
```sql
create table USER
(
    ID           int auto_increment,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR(100),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    constraint USER_PK
        primary key (ID)
);

```