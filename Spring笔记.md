# Spring

Spring框架是一个开源的轻量级Java开发框架，广泛用于构建企业级应用程序。

Spring下包含多个项目：

* Spring Boot
* Spring Framework
  * SpringMVC
* Spring Data
* Spring Cloud
* Spring Cloud Data Flow
* Spring

## Spring Boot

* 创建独立的 Spring 应用程序
* 直接嵌入 Tomcat、Jetty 或 Undertow（无需部署 WAR 文件）
* 提供固定的“启动器”依赖项来简化您的构建配置
* 尽可能自动配置 Spring 和第三方库
* 提供可用于生产的功能，例如指标、健康检查和外部化配置
* 完全无需代码生成，也无需 XML 配置

### 核心特性

* 自动配置
* 起步依赖
* 嵌入式服务器

### CommandLineRunner

单次执行：
继承CommandLineRunner，重写run方法。
（不想执行把@Component注释掉）

```java
@Component
public class xxx implements CommandLineRunner {
@Override
public void run(String... args) {
// 代码
}
}
```

## Spring Framework

* [核心技术](https://docs.spring.io/spring-framework/reference/core.html)：依赖注入、事件、资源、i18n、验证、数据绑定、类型转换、SpEL、AOP。
  * dependency injection, events, resources, i18n, validation, data binding, type conversion, SpEL, AOP.
* [测试](https://docs.spring.io/spring-framework/reference/testing.html#testing)：模拟对象、TestContext framework, Spring MVC Test, `WebTestClient`.
  * mock objects
* [数据访问](https://docs.spring.io/spring-framework/reference/data-access.html)：事务、DAO support、JDBC、ORM、Marshalling XML。
  * transactions
* [Spring MVC](https://docs.spring.io/spring-framework/reference/web.html)和[Spring WebFlux](https://docs.spring.io/spring-framework/reference/web-reactive.html) Web 框架。
* [集成](https://docs.spring.io/spring-framework/reference/integration.html)：远程处理、JMS、JCA、JMX、电子邮件、任务、调度、缓存和可观察性。
  * [Integration](https://docs.spring.io/spring-framework/reference/integration.html): remoting, JMS, JCA, JMX, email, tasks, scheduling, cache and observability.
* [语言](https://docs.spring.io/spring-framework/reference/languages.html)：Kotlin, Groovy, dynamic languages.

### 依赖注入

* 扫描类并创建Bean：查找标有@Component，@Service，@Repository，@Controller等注解的类。使用反射机制动态加载类。
* 解析并注入依赖：

#### 循环依赖

* Spring Boot 2.6.0 开始，默认不支持循环依赖，需要通过 `spring.main.allow-circular-references = true` 显式允许。
* 循环依赖通常反映了设计问题，如果不显式配置允许循环依赖，可以使用 `@Lazy` 解决
* Spring 框架中通过三级缓存来处理循环依赖

#### 三级缓存

* 一级缓存（singletonObjects）： 存储完全初始化且可以直接使用的单例 Bean。只有当 Bean 的所有依赖已经被完全注入之后，才会放入一级缓存。
* 二级缓存（earlySingletonObjects）： 存储正在创建但尚未完成初始化的 Bean，用于在依赖循环中暴露未完成的 Bean。这一层允许 Spring 提前返回一个部分完成的 Bean 实例。

* 三级缓存（singletonFactories）： 存储 Bean 的工厂方法（ObjectFactory），在需要时可以通过调用该方法创建代理对象。这层缓存的主要作用是为了支持 AOP 等代理场景，可以确保获取的是代理后的对象。

工作流程：
Spring 在创建一个 Bean 时，首先会检查一级缓存中是否存在该 Bean。
如果一级缓存中没有，会查看二级缓存，尝试获取未完全初始化的实例。
如果二级缓存也没有，Spring 会调用三级缓存中的工厂方法获取 Bean。
在 Bean 初始化完成后，会将其从二级或三级缓存中移除，并放入一级缓存。
