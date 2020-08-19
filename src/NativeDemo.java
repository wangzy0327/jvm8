/**
 *
 * Native Interface本地接口
 *
 *    本地接口的作用是融合不同的编程语言为Java所用，它的初衷是融合C/C++程序，Java诞生的时候是C/C++横行的时候，
 *    要想立足，必须有调用C/C++程序，于是就在内存中专门开辟了一块区域处理标记位native的代码，它的具体做法是
 *    Native Method Stack中登记native方法，在Execution Engine执行时加载native libraies。
 *    目前该方法使用的越来越少了，除非是与硬件有关的应用，比如通过Java程序驱动打印机或者Java系统管理生产设备，
 *    在企业级应用中已经比较少见。因为现在的异构领域间的通信很发达，比如可以使用Socket通信，也可以使用Web Service等等。
 *
 *Native Method Stack
 *    它的具体做法是Native Method Stack中登记native方法，在Execution Engine执行时加载本地方法库
 *
 */
public class NativeDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        t1.start();
        t1.start();

        //Exception in thread "main" java.lang.IllegalThreadStateException
    }
}
