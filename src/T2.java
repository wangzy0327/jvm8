import java.util.concurrent.TimeUnit;

class MyNumber{
    volatile int number = 10;
    public void addTo1205(){
        this.number = 1205;
    }
}

/**
 *
 * JMM = 可见性（通知机制）
 *
 */
public class T2 {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();
        new Thread(()->{
            //暂停一会儿线程
            System.out.println(Thread.currentThread().getName()+"  *********  come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (Exception e){
                e.printStackTrace();
            }
            myNumber.addTo1205();
            System.out.println(Thread.currentThread().getName()+"\t  update number,number value :"+myNumber.number);
        },"AAA").start();

        while(myNumber.number == 10){
            //需要有一种通知机制告诉main线程，number已经修改为1205，跳出while
            //如果什么都不加，main线程读不到共享变量修改后的值

        }
        System.out.println(Thread.currentThread().getName()+"\t mission is over");
    }
}
