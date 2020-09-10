class CodeZY{
    public CodeZY(){
        System.out.println("Code的构造方法1111");
    }
    {
        System.out.println("Code的构造块2222");
    }
    static {
        System.out.println("Code的静态代码块3333");
    }
    static void printHello(){
        System.out.println("Code的静态方法4444");
    }
}
class Parent{
    public Parent(){
        System.out.println("Parent的构造方法1111");
    }
    {
        System.out.println("Parent的构造块2222");
    }
    static {
        System.out.println("Parent的静态代码块3333");
    }
}
class Son extends Parent{
    public Son(){
        System.out.println("Son的构造方法11111");
    }
    {
        System.out.println("Son的构造块22222");
    }
    static {
        System.out.println("Son的静态代码块33333");
    }
}
public class CodeBlock { //主类  CodeBlock.class --- main
    {
        System.out.println("CodeBlock03的构造块444");
    }
    static {
        System.out.println("CodeBlock03的静态代码块555");
    }
    public CodeBlock(){
        System.out.println("CodeBlock03的构造方法666");
    }

    public static void main(String[] args) {
        System.out.println("--我是美丽分割线-----------CodeBlock03的main方法777");

        new Son();

        /***
         CodeBlock03的静态代码块555
         --我是美丽分割线-----------CodeBlock03的main方法777
         Parent的静态代码块3333
         Son的静态代码块33333
         Parent的构造块2222
         Parent的构造方法1111
         Son的构造块22222
         Son的构造方法11111

         */

//        CodeZY.printHello();
//        System.out.println("------------------------------");
//        new CodeZY();
//        System.out.println("------------------------------");
//        new CodeZY();
//        System.out.println("------------------------------");
//        new CodeBlock();

        /**
         *
         CodeBlock03的静态代码块555
         --我是美丽分割线-----------CodeBlock03的main方法777
         Code的静态代码块3333
         Code的静态方法4444
         ------------------------------
         Code的构造块2222
         Code的构造方法1111
         ------------------------------
         Code的构造块2222
         Code的构造方法1111
         ------------------------------
         CodeBlock03的构造块444
         CodeBlock03的构造方法666
         *
         */
    }
}