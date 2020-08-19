import java.util.Random;

public class OutOfMem {
    public static void main(String[] args) {
//        byte[] bytes = new byte[40 * 1024 * 1024];
        String str = "www.baidu.com";
        while(true){
            str += str + new Random().nextInt(88888888)+new Random().nextInt(99999999);
        }
    }
}
