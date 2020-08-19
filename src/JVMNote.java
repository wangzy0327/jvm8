/***
 *
 * 主题：JVM
 *
 * 1 JVM系统架构图
 *
 * 2 类加载器
 *      System.out.println(myObject.getClass().getClassLoader().getParent().getParent())
 *      System.out.println(myObject.getClass().getClassLoader().getParent());
 *      System.out.println(myObject.getClass().getClassLoader());
 *
 * 3 Native
 *   3.1 native是一个关键字
 *   3.2 声明有，实现无，why？
 *
 * 4 PC寄存器
 *   4.1 （指针）记录了方法之间的调用和执行情况，类似排班值日表
 *        用来存储指向下一条指令的地址，也即将要执行的指令代码
 *        它是当前线程所执行的字节码的行号指示器
 *
 * 5 方法区
 *   供各线程共享的运行时内存区域。它存储了每一个类的结构信息，例如运行时常量池（Runtime Constant Pool）、
 *   字段和方法数据、构造函数和普通方法的字节码内容。But
 *   实例变量存在堆内存中，和方法区无关
 *
 *   5.1 它存储了每一个类的结构信息（模板）
 *   5.2 方法区是规范，在不同的虚拟机里实现是不一样的，最典型的就是永久代（PermGen space）和元空间（Metaspace）
 *
 * 1.7
 * 方法区 f = new 永久代
 *
 * 1.8
 * 方法区 f = new 元空间
 *
 * 6 stack(栈)
 *   也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命周期是跟随线程的声明周期，线程结束栈内存也就释放，
 *   对于栈来说不存在垃圾回收问题，只要线程一结束该栈就over，声明周期和线程一致，是线程私有的。
 *   8种基本类型的变量+对象的引用变量+实例方法都是在函数的栈内存中分配。
 *
 *   6.1 栈管运行，堆管存储
 *   6.2 栈保存哪些东西
 *      栈帧主要保存3类数据：
 *     本地变量：输入参数和输出参数以及方法内的变量
 *     栈操作：记录出栈、入栈的操作
 *     栈帧数据：包括类文件、方法等等。
 *
 *     运行原理：遵循（FILO）
 *     每个方法执行的同时都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接、方法出口等信息，每一个方法从调用直至执行完毕的过程，
 *     就对应着一个栈帧在虚拟机中入栈到出栈的过程。栈的大小和具体JVM的实现有关，通常在256K~756K之间，约等于1Mb左右。
 *
 *   java.lang.Object
 *        java.lang.Throwable
 *             java.lang.Error
 *                  java.lang.VirtualMachineError
 *                       java.lang.StackOverflowError
 *   Exception in thread "main" java.lang.StackOverflowError（是错误不是异常）
 *
 *   7 heap(堆)
 *   堆内存逻辑上分为三部分：新生+老年+元空间  From(Survivor0) To(Survivor1)
 *   -----------------------------------------------------------------
 *   |                           heap                                 |
 *   | -----------------------           ---------------------------  |
 *   | |       Young          |          |            Old           | |
 *   | |  Eden   From   To    |          |                          | |
 *   | |  (8/10) (1/10)(1/10) |          |                          | |
 *   | |         1/3          |          |           2/3            | |
 *   | ------------------------          ---------------------------- |
 *   |                                                                |
 *   ------------------------------------------------------------------
 *             MinorGC过程（复制->清空->互换）
 *             from区与to区不是固定的，每次GC后会进行交换，谁空谁是To
 *
 *    元空间（永久区7之前）
 *    是一个常驻内存区域，用于存放JDK自身所携带的Class，Interface的元数据，也就是说它存储的是运行环境必须的类信息，
 *    被装载进此区域的数据是不会被垃圾回收器回收掉的，关闭JVM才会释放此区域所占用的内存
 *
 *    8 堆参数调整
 *    在java8中，永久代已经被移除，被一个称为元空间的区域所取代。元空间的本质跟永久代类似。
 *
 *    元空间与永久代之间最大的区别在于：
 *    永久代使用的JVM的堆内存，但是java8以后的元空间并不在虚拟机中而是使用本机物理内存。
 *    因此，默认情况下，元空间的大小经手本地内存限制。类的元数据放入native memory，字符串池和类的静态变量放入java堆中，
 *    这样可以加载多少类的元数据就不再由MaxPermSize控制，而由系统的实际可用空间来控制。
 *
 *    ------------------------------------------------------------------------
 *          -Xms             |  设置初始分配大小，默认为物理内存的1/64
 *    ------------------------------------------------------------------------
 *          -Xmx             |  最大分配内存，默认为物理内存的1/4
 *    ------------------------------------------------------------------------
 *      -XX:+PrintGCDetails  |  输出详细的GC处理日志
 *    ------------------------------------------------------------------------
 *    8.1 heap结构
 *    Heap
         PSYoungGen      total 305664K, used 20971K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
             eden space 262144K, 8% used [0x00000000eab00000,0x00000000ebf7afb8,0x00000000fab00000)
             from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
             to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
         ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
            object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
         Metaspace       used 3491K, capacity 4496K, committed 4864K, reserved 1056768K
            class space    used 382K, capacity 388K, committed 512K, reserved 1048576K
 *    8.2
 *
 *  -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 *
 *  Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 *
 *  [GC (Allocation Failure)
 *  [PSYoungGen: 2446K->504K(2560K)] 8068K->7054K(9728K), 0.0012757 secs]
 *  [Times: user=0.00 sys=0.00, real=0.00 secs]
 *
    [Full GC (Ergonomics)
     [PSYoungGen: 504K->0K(2560K)]
     [ParOldGen: 6550K->3423K(7168K)] 7054K->3423K(9728K),
     [Metaspace: 3436K->3436K(1056768K)], 0.0066876 secs]
     [Times: user=0.02 sys=0.00, real=0.01 secs]
 *
 *  [GC类型 ]:(YoungGC前新生代内存占用)->(YoungGC后新生代内存占用)（新生代总共大小） (YoungGC前JVM堆内存占用)->(YoungGC后JVM堆内存使用)（JVM堆内存总大小）
 *
 *  [GC类型]：GC前内存占用->GC后内存占用（该区内存总大小）
 *
 *  9.垃圾回收（GC）<分代收集算法>
 *    JVM在进行GC时，并非每次都对上面三个内存区域一起回收的，大部分时候回收的都是指新生代。
 *    因此GC按照回收的区域又分了两种类型，一种是普通GC（minor GC），一种是全局GC（Full GC or major GC）
 *
 *    Minor GC 和 Full GC的区别
 *    普通GC（minor GC）：只针对新生代区域的GC，指发生在新生代的垃圾回收动作，因为大多数Java对象存活率都不高，所以
 *        Minor GC非常频繁，一般回收速度也比较快。
 *    全局GC（major GC or Full GC）：指发生在老年代的垃圾收集动作，出现了Major GC，经常会伴随至少一次的Minor GC（但并不是绝对的）。
 *       Major GC的速度一般要比Minor GC慢上10倍以上(新生代占用内存空间少，且采用效率高的复制回收算法)
 *    9.1 引用计数法 [几乎不用] <严格来说用来判断对象死了没有，同样的还有可达性分析算法GC Root>
 *         JVM 的实现一般不采用这种方式
 *          缺点：
 *             1. 每次对对象赋值时均要维护引用计数器，且计数器本身也有一定的消耗；
 *             2. 较难处理循环引用 （objectA.data = B; objectB.data = A）
 *    9.2 复制算法（Copying）<新生代使用>
 *           原理：从根集合出发（GC Root）开始，通过Tracing从From中找到存活对象，拷贝到To中；
 *                From、TO交换身份，下次内存分配从To开始
 *           优点：不会产生垃圾碎片
 *                 没有标记和清除的过程，效率高
 *           缺点：浪费一半的内存空间（因为要拷贝释放）
 *                极端情况下，当对象存活率很高时，花费代价高（对象的存活率要非常低才行）
 *
 *    ***老年代一般是由标记清除或者是标记清除与标记整理的混合实现****
 *    9.3 标记清除（Mark-Sweep）<老年代使用>
 *        原理：算法分成标记和清除两个阶段，先标记处要回收的对象，然后统一回收这些对象。
 *        优点：不需要额外空间
 *        缺点：两次扫描，耗时严重
 *             会产生内存碎片
 *    9.4 标记整理<标记压缩>（Mark-Compact）<老年代使用>
 *        原理：1.标记（Mark）与标记-清除一样
 *             2.压缩（Compact）再次扫描，并往一端滑动存活对象
 *        优点：没有内存碎片
 *        缺点：需要移动对象的成本
 *             耗时严重
 *     一般：标记-清除-压缩 联合使用：
 *         1.Mark-Sweep和Mark-Compact的结合
 *         2.和Mark-Sweep一致，当进行多次GC后才Compact<减少移动对象成本>
 *
 *
 *   年轻代（Young Gen）
 *    年轻代特点是区域相对老年代较小，对象存活率低（复制算法效率最高，复制算法的效率只和当前存活对象大小有关，因此很适用）
 *
 *   老年代（Tenure Gen）
 *   老年代的特点是区域较大，对象存活率高（一般由标记清除或者标记清除与标记整理的混合实现）
 *
 *
 *   10.JMM(java 内存模型)
 *     volatile是jvm提供的轻量级的同步机制（1、可见性  2、禁止重排序 3、不保证原子性）
 *
 *     JMM特征 线程安全性的保证（1、原子性 2、可见性 3、有序性）
 *
 *     JMM (Java内存模型Java memory model，简称JMM)本身是一种抽象的概念并不真实存在，它描述的是一组规则或规范，
 *     通过这组规范定义了程序中的各个变量（包括实例字段，静态字段和构成数组对象的元素）的访问方式
 *
 *     JMM关于同步的规定：
 *     1.线程解锁前，必须把共享变量的值刷新回主内存
 *     2.线程加锁前，必须读取主内存的最新值到自己的工作内存
 *     3.加锁解锁是同一把锁
 *
 *     由于JVM运行程序的实体是线程，而每个线程创建时JVM都会为其创建一个工作内存（有些地方称为栈空间），工作内存是每个线程的私有
 *     数据区域，而Java内存模型中规定所有变量都存储在主内存，主内存是共享内存区域，所有线程都可以访问，但线程对变量的操作（读取赋值等）
 *     必须在工作内存中进行，首先要将变量从主内存拷贝到线程自己的工作内存空间，然后对变量进行操作，操作完成后再将变量写回主内存，
 *     不能直接操作主内存中的变量，各个线程中的工作内存中存储着主内存中的变量副本拷贝，因此不同的线程间无法访问对方的工作内存，
 *     线程间的通信（传值）必须通过主内存来完成。
 *
 *          线程A              线程B
 *           ↕                  ↕
 *         本地内存A          本地内存B
 *       共享变量的副本      共享变量的副本
 *           ↕      ←JMM控制→   ↕
 *      ---------------------------------
 *      |            主内存              |
 *      |    共享变量  共享变量  共享变量   |
 *      ---------------------------------
 *
 *
 */
public class JVMNote {
    public void sayHello(){

    }
    public static void main(String[] args){
        JVMNote j1 = new JVMNote();
        //返回java虚拟机试图使用的最大内存量
        long maxMemory = Runtime.getRuntime().maxMemory();
        //返回java虚拟机中的内存总量
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("-Xmx:MAX_MEMORY = "+ maxMemory/(double)1024/1024+" MB");
        System.out.println("-Xms:TOTAL_MEMORY = "+totalMemory/(double)1024/1024+" MB");
    }
}