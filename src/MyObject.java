/**
 * 类加载器
 *
 *     负责加载class文件，class文件在文件开头有特定的文件标示，将class文件字节码内容加载到内存中，
 *     并将这些内容转换成方法区中的  运行时数据结构 并且ClassLoader只负责class文件的加载，
 *     至于它是否可以运行，则由Execution Engine决定
 *
 * 包括 三个JVM自带的类加载器 和 一个用户自定义类加载器
 *
 *  Jvm自带类加载器 ：
 *    1.启动类加载器(Bootstrap) C++ 也叫根加载器
 *    2.扩展类加载器(Extension) Java
 *    3.应用程序类加载器(AppClassLoader)  Java  也叫系统类加载器，加载当前应用的classpath的所有类
 *
 *  用户自定义加载器
 *    Java.lang.ClassLoader的子类，用户可以定制类的加载方式
 *
 *
 *             Bootstrap Class Loader   $JAVA_HOME/jre/lib/rt.jar
 *                         ↑
 *             Extension Class Loader  $JAVA_HOME/jre/lib/ext/*.jar
 *                        ↑
 *             System Class Loader     $CLASSPATH
 *   ---------------------------------------------
 *         ↗                        ↖
 *    User-Defined            User-Defined
 *    Class Loader            Class Loader
 *         ↑
 *    User-Defined
 *    Class Loader
 *
 *    类加载机制：双亲委派机制(往上找) + 沙箱安全机制
 *
 *    双亲委派：
 *    当一个类收到了类加载请求，它首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成，
 *    每一个层次类加载器都是如此，因此所有的加载请求都应该传送到启动类加载器中，
 *    只有当父类加载器反馈自己无法完成这个请求的时候（在它的加载路径下没有找到所需加载的Class），
 *    子类加载器才会尝试自己去加载
 *
 *
 *    采用双亲委派的一个好处是  比如加载位于rt.jar包中的类java.lang.Object，不管是哪个加载器加载这个类，
 *    最终都是委托给顶层的启动类加载器进行加载，这样就保证了使用不同的类加载器最终得到的都是同样一个Object对象
 *
 *    沙箱安全：
 *    防止恶意代码污染java源代码
 *
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
//        System.out.println(object.getClass().getClassLoader().getParent().getParent());
//        System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());

        System.out.println();
        System.out.println();
        System.out.println();


        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
    }
}
