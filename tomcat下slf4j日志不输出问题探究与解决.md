## 问题描述

近日，由于公司系统架构调整，发现tomcat8下日志输出有问题，但是tomcat7没有问题，其中但凡引用 `slf4j` 输出的日志都不输出了。

## 问题分析

首先，定位问题，由于代码没有做过任何改动，所以直接定位到tomcat7和tomcat8差异引起的，感觉像是包冲突，所以沿着这个思路排查问题。

<!-- more -->

### 通过日志来分析：在我的电脑上分别下载 `tomcat7` 和 `tomcat8`，在日志中看到打印到如下异常日志

> **不想看分析过程的直接看结果吧**[点此跳转到结果](https://www.peierlong.com/2018/02/05/tomcat%E4%B8%8Bslf4j%E6%97%A5%E5%BF%97%E4%B8%8D%E8%BE%93%E5%87%BA%E9%97%AE%E9%A2%98%E6%8E%A2%E7%A9%B6%E4%B8%8E%E8%A7%A3%E5%86%B3/#%E6%80%BB%E7%BB%93)

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/elong/Desktop/war/apache-tomcat-8.5.24/webapps/niuban-mobile/WEB-INF/lib/slf4j-nop-1.7.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/elong/Desktop/war/apache-tomcat-8.5.24/webapps/niuban-mobile/WEB-INF/lib/slf4j-log4j12-1.7.9.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.helpers.NOPLoggerFactory]
```

此报错为依赖冲突引起的日志输出，通过一下方式查看依赖冲突：

1. 使用 idea 的 Maven Helper 插件
2. 使用 `mvn dependency:tree` 查看依赖树

使用 `exclusion` 来排除 `org.slf4j.helpers.NOPLoggerFactory` 的依赖即可。

### 另一种方式：通过源码分析的思路，Debug模式下查看slf4j的`Logger`接口的具体实现类是什么?

先上代码

```Java
private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);
private void testLogger(){
    LOGGER.info("this is a test");
}
```

在Tomcat7下，用可以打印出日志的配置执行代码，`Logger`的具体实现类是`org.slf4j.impl.Log4jLoggerAdapter`，其中打印的启动日志也说明了这一点。

![006tKfTcly1fo6t6i7be4j31kw04dtdo.jpg](http://peierlong-blog.oss-cn-hongkong.aliyuncs.com/006tKfTcly1fo6t6i7be4j31kw04dtdo.jpg)

同样的配置，在Tomcat8下，`Logger`的具体实现类是`org.slf4j.helpers.NOPLogger(NOP)`
，NOPLogger为slf4j默认的无操作Logger实现类，所以很显然不会有日志输出的。

![006tKfTcly1fo6ta061i8j31kw04g79d.jpg](http://peierlong-blog.oss-cn-hongkong.aliyuncs.com/006tKfTcly1fo6ta061i8j31kw04g79d.jpg)

那么问题来了，是什么导致的`Logger`实现类的改变？看源码找找线索

首先`LoggerFactory`的`getLogger()`方法实现如下：

```Java
  public static Logger getLogger(Class clazz) {
    return getLogger(clazz.getName());
  }
  
  /**
   * Return a logger named according to the name parameter using the statically
   * bound {@link ILoggerFactory} instance.
   *
   * @param name The name of the logger.
   * @return logger
   */
  public static Logger getLogger(String name) {
    ILoggerFactory iLoggerFactory = getILoggerFactory();
    return iLoggerFactory.getLogger(name);
  }
```

具体选择那种日志实现(e.g. java.util.logging, logback, log4j) 就在以上代码中 `ILoggerFactory` 接口的具体实现，所以我们接着看`getILoggerFactory()`方法的具体实现。

```Java
public static ILoggerFactory getILoggerFactory() {
    if (INITIALIZATION_STATE == UNINITIALIZED) {
      INITIALIZATION_STATE = ONGOING_INITIALIZATION;
      performInitialization();
    }
    switch (INITIALIZATION_STATE) {
      case SUCCESSFUL_INITIALIZATION:
        return StaticLoggerBinder.getSingleton().getLoggerFactory();
      case NOP_FALLBACK_INITIALIZATION:
        return NOP_FALLBACK_FACTORY;
      case FAILED_INITIALIZATION:
        throw new IllegalStateException(UNSUCCESSFUL_INIT_MSG);
      case ONGOING_INITIALIZATION:
        // support re-entrant behavior.
        // See also http://bugzilla.slf4j.org/show_bug.cgi?id=106
        return TEMP_FACTORY;
    }
    throw new IllegalStateException("Unreachable code");
  }
```

根据debug执行，tomcat7和tomcat8中都会执行到switch语句 `SUCCESSFUL_INITIALIZATION` 分支中，其中`StaticLoggerBinder`类的实例，其中`StaticLoggerBinder`中的`loggerFactory`接口属性指向了不同的实现（这也正是slf4j所谓的外观(Facade)模式，并不是具体的日志解决方案），所以我们接下来看`LoggerFactory`初始化时类加载器对`org/slf4j/impl/StaticLoggerBinder.class`类的加载的验证过程。我们看`performInitialization()`方法的实现。

```Java
  private final static void performInitialization() {
    bind();
    if (INITIALIZATION_STATE == SUCCESSFUL_INITIALIZATION) {
      versionSanityCheck();
    }
}

  private final static void bind() {
    try {
      Set staticLoggerBinderPathSet = findPossibleStaticLoggerBinderPathSet();
      reportMultipleBindingAmbiguity(staticLoggerBinderPathSet);
      // the next line does the binding
      StaticLoggerBinder.getSingleton();
      INITIALIZATION_STATE = SUCCESSFUL_INITIALIZATION;
      reportActualBinding(staticLoggerBinderPathSet);
      emitSubstituteLoggerWarning();
    } catch (NoClassDefFoundError ncde) {
      String msg = ncde.getMessage();
      if (messageContainsOrgSlf4jImplStaticLoggerBinder(msg)) {
        INITIALIZATION_STATE = NOP_FALLBACK_INITIALIZATION;
        Util.report("Failed to load class \"org.slf4j.impl.StaticLoggerBinder\".");
        Util.report("Defaulting to no-operation (NOP) logger implementation");
        Util.report("See " + NO_STATICLOGGERBINDER_URL
                + " for further details.");
      } else {
        failedBinding(ncde);
        throw ncde;
      }
    } catch (java.lang.NoSuchMethodError nsme) {
      String msg = nsme.getMessage();
      if (msg != null && msg.indexOf("org.slf4j.impl.StaticLoggerBinder.getSingleton()") != -1) {
        INITIALIZATION_STATE = FAILED_INITIALIZATION;
        Util.report("slf4j-api 1.6.x (or later) is incompatible with this binding.");
        Util.report("Your binding is version 1.5.5 or earlier.");
        Util.report("Upgrade your binding to version 1.6.x.");
      }
      throw nsme;
    } catch (Exception e) {
      failedBinding(e);
      throw new IllegalStateException("Unexpected initialization failure", e);
    }
  }
  
  private static Set findPossibleStaticLoggerBinderPathSet() {
    // use Set instead of list in order to deal with  bug #138
    // LinkedHashSet appropriate here because it preserves insertion order during iteration
    Set staticLoggerBinderPathSet = new LinkedHashSet();
    try {
      ClassLoader loggerFactoryClassLoader = LoggerFactory.class
              .getClassLoader();
      Enumeration paths;
      if (loggerFactoryClassLoader == null) {
        paths = ClassLoader.getSystemResources(STATIC_LOGGER_BINDER_PATH);
      } else {
        paths = loggerFactoryClassLoader
                .getResources(STATIC_LOGGER_BINDER_PATH);
      }
      while (paths.hasMoreElements()) {
        URL path = (URL) paths.nextElement();
        staticLoggerBinderPathSet.add(path);
      }
    } catch (IOException ioe) {
      Util.report("Error getting resources from path", ioe);
    }
    return staticLoggerBinderPathSet;
  }
  
```

其中，`findPossibleStaticLoggerBinderPathSet()`方法中查找所有加载过的path，然后将其放到一个Set中，在后续的`reportMultipleBindingAmbiguity()`方法中进行验证打印错误日志，tomcat日志中我们看到的日志就是这个方法输出的。至此，源码分析完毕。

## 总结

真正没有输出日志的原因是因为`Logger`引用指向的实现不是`log4j`的实现所导致的，其中`LoggerFactory`类在类加载过程中，`ClassLoader`对多个`StaticLoggerBinder.class`文件的加载顺序不同，导致实现的不同。

### 解决

有两种思路都解决了，第二种更优雅。
1. 尝试升级`slf4j`版本和`slf4j-log4j`桥接包版本，从1.7.9升级至1.7.25，问题解决。
2. 不升级版本，解决依赖冲突，找到`slf4j-nop`包的maven依赖来源，从mvaen中使用`<exclusions>`标签排除依赖。

**maven检查依赖冲突方式:**
使用`mvn dependency:tree`查看依赖树。

```
[INFO] +- com.aliyun:aliyun-java-sdk-core:jar:2.1.9:compile
[INFO] +- com.aliyun.oss:aliyun-sdk-oss:jar:2.0.5:compile
[INFO] |  +- org.apache.httpcomponents:httpclient:jar:4.4:compile
[INFO] |  |  \- org.apache.httpcomponents:httpcore:jar:4.4:compile
[INFO] |  \- net.sf.json-lib:json-lib:jar:jdk15:2.4:compile
[INFO] |     \- net.sf.ezmorph:ezmorph:jar:1.0.6:compile
[INFO] +- com.aliyun.opensearch:aliyun-sdk-opensearch:jar:2.1.3:compile
[INFO] |  +- org.apache.httpcomponents:httpmime:jar:4.3.1:compile
[INFO] |  +- org.json:json:jar:20131018:compile
[INFO] |  \- org.slf4j:slf4j-nop:jar:1.7.0:compile
```

***其中最后一行为找到冲突包的位置，也就是日志不输出的罪魁祸首，在`aliyun-sdk-opensearch:jar`包中依赖，所以在配置文件中把它干掉。***

```xml
<dependency>
    <groupId>com.aliyun.opensearch</groupId>
    <artifactId>aliyun-sdk-opensearch</artifactId>
    <version>2.1.3</version>
    <exclusions>
        <exclusion>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-nop</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

至此，问题完美解决，


### 遗留问题
1. tomcat7和tomcat8的`ParallelWebappClassLoader`类加载器的实现方式有何不同？为何加载顺序不同？（2018-2-26: 转一篇讲类加载器的 [文章](http://ifeve.com/classloader/)，讲的很不错）
2. 在加载同名类的时候，真正运行的是哪个类是如何选择的？为何执行第一个被加载的类？

### slf4j源码的两点个人感受
1. 羡慕其代码的命名规范、合理的代码抽象、模式的运用。
2. 程序员就要有工匠精神，代码也是一种艺术品。:)


end

