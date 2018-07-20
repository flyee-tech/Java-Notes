# ImJavaer(进阶篇)
本项目存放自己平时敲的示例代码，并会在`README.md`中记录，以便自己忘了的时候回来看。每个类中都有测试代码，直接运行可查看效果。

- JDK使用版本: 1.7
- 构建工具: Maven

## 项目结构及内容简介

#### Java And Concurrency
    |com.elong.concurrency  //并发编程模块
        |UnsafeSequence.java
        |-----------------  //非线程安全的数值生成器
        |Sequence.java
        |-----------------  //线程安全的数值生成器
        |NoVisibility.java
        |-----------------  //在没有同步的情况下共享变量
        |OneValueCache.java
        |-----------------  //对数值及其因数分解结果进行缓存的不可变容器类
        |PersonSet.java
        |-----------------  //通过实例封闭和加锁机制使一个类成为线程安全的(类的状态变量并不是线程安全的)
        |TestHarness.java
        |-----------------  //同步工具类: 闭锁: 使用CountDownLatch类启动和停止线程
        |BoundedHashSet.java
        |-----------------  //同步工具类: 使用Semaphore为容器设置边界
        |barrierTest.java
        |-----------------  //同步工具类: CyclicBarrier
        |custom.cache.*
        |-----------------  //构建高效的、可伸缩的结果缓存
        |BrokenPrimeProducer.java
        |-----------------  //素数阻塞队列示例
        |BlockingQueue.java
        |-----------------  //一个阻塞队列的简单实现
        |CASDemo.java
        |-----------------  //原子包boolean原子性示例
        |FairLock.java
        |Lock.java
        |Synchronizer.java
        |LockTest.java
        |-----------------  //锁(公平锁)的实现与测试
        |MyWaitNotify.java
        |-----------------  //测试notify和wait的执行流程
        |QueueObject.java
        |-----------------  //信号量对象
        |ReadWriteLock.java
        |-----------------  //可重入的读写锁
        |ThreadLocalTest.java
        |-----------------  //ThreadLocalTest
        |TreeNode.java
        |-----------------  //死锁示例
        
#### Design Patterns
    |com.elong.design.patterns  //设计模式
        |EnumSingleton.java
        |SingletonObject.java
        |-----------------  //单例模式
        |Service.java
        |Provider.java
        |Services.java
        |-----------------  //服务提供者模式
        |Student.java
        |-----------------  //Builder模式
        |SetObserver.java
        |ForwardingSet.java
        |ObservableSet.java
        |-----------------  //避免过度同步(观察者模式)
        
#### Java Best Practices
    |com.peierlong.best.practices   //最佳实践
        |PhoneNumber.java
        |-----------------  //覆盖equals的同时要覆盖hashCode
        |Complex.java
        |-----------------  //使可变性最小化
        |Figure.java
        |FigureTrue.java
        |-----------------  //类层次优于标签类
        |Favorites.java
        |-----------------  //类型安全的异构容器的实现
        |AnnotatedElement.java
        |-----------------  /使用asSubclass方法在编译时读取类型未知的注解
        |Operation.java
        |-----------------  //利用枚举类型来实现特定运算
        |ReflectiveTest.java
        |-----------------  //使用反射类获取对象的实例
        |Phase.java
        |-----------------  //使用嵌套的EnumMap来构建关联的一对枚举
        |Period.java
        |-----------------  //必要时进行保护性拷贝
        
#### Java NIO
    |com.peierlong.nio  //NIO使用示例
        |TiemServer.java
        |MultiplexerTimeServer.java
        |-----------------  //NIO创建的TimeServer服务端
        |TimeClient.java
        |TimeClientHandle.java
        |-----------------  //NIO创建的TimeClient客户端
    |com.peierlong.nio.ifeve
        |ReadFileExample.java //NIO读取文件示例
        
        
#### JVM
    |com.peierlong.jvm //JVM相关
        |FinalizeEscapeGC.java
        |----------------- //对象的自我拯救
        |TestAllocation.java
        |----------------- //当Eden空间不足，且无法放入Survivor空间时，通过担保机制提前转移到老年代
        |TestPretenureSizeThreshold.java
        |----------------- //使大对象直接进入老年代
        |TestTenuringThreshold.java
        |----------------- //长期存活的对象进入老年代
        |TestFullHeap.java
        |----------------- //使用jConsole可视化工具查看虚拟机内存状态
        |DeadLoopClass.java
        |----------------- //如果一个类在<clinit>()方法中耗时很长，则可能造成多个阻塞
        |MethodHandlerTest.java
        |----------------- //Java对动态类型语言支持
        |MethodHandlerTest2.java
        |----------------- //如何在子类方法中调用祖类方法
        
#### Spring
    |com.peierlong.spring
        |SpELTest.java
        |----------------- //Spring表达式语言使用示例
        |HelloworldTest.java
        |----------------- //HelloWorld示例
    |com.peierlong.spring.aop
        |HelloWorldService.java
        |HelloWorldServiceImpl.java
        |HelloWorldAspect.java
        |AopTest.java
        |resources/helloWorld.xml
        |----------------- //AOP HelloWorld示例
        
#### ZooKeeper
    |com.peierlong.zookeeper
        |testConnect.java //官方API连接zookeeper并创建一个根结点
        |testCurator.java //开源Zookeeper客户端Curator使用示例
#### Lambda表达式
    |com.peierlong.lambda
        |CreateThreadDemo.java //创建线程
        |SortLambda.java //使用Lambda表达式进行排序操作
        |UpperCase.java //使用Lambda表达式大写转换
