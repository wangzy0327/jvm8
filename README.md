# java虚拟机

### java类装载器

- Jvm自带类加载器
  1. 启动类加载器（Bootstrap） C++ 也叫根加载器
  2. 扩展类加载器（Extension） Java
  3. 应用程序类加载器（AppClassLoader）Java  也叫系统类加载器，加载当前应用的classpath的所有类

- 双亲委派和沙箱安全（MyObject 、java.lang.String）

### java运行时数据区

1. 本地接口（NativeDemo）
2. PC寄存器（PCR）
3. 方法区（JVM Note）
4. 栈（JVM Note）
5. 堆（JVM Note）

### 堆参数调整

### GC收集日志信息

Exception in thread "main" java.lang.OutOfMemoryError: Java heap space（OutOfMem）

### 对象是否消亡算法：引用计数法、可达性分析算法

### 四个垃圾收集算法：复制算法、标记清除算法、标记整理算法、分代收集算法

### java内存模型（Java Memory Model）

JMM代码验证（T2）

### java加载代码块顺序

静态（1次）->main->代码块（多次）->构造方法（多次）<CodeBlock>