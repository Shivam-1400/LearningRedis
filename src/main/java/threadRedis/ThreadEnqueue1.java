package threadRedis;


import redis.clients.jedis.Jedis;
import java.util.Scanner;

class RThread implements Runnable{
    RedisEn1 obj;
    Thread thread;
    RThread(RedisEn1 obj){
        this.obj= obj;
        thread= new Thread(this);
        thread.start();
    }
    public void run(){
        obj.enqueue();
    }
}
class RedisEn1{
    Jedis js;
    String key;
    RedisEn1(String key){
        js= new Jedis();
        this.key= key;
    }
    void enqueue(){
        Scanner cin= new Scanner(System.in);
        System.out.println("Enter the integer values to enqueue: ");
        String a;
        while(true){
            a= cin.nextLine();
            js.rpush(key, a);
            System.out.println(key+ " key Enqueued: "+ a );
        }
    }
}
public class ThreadEnqueue1 {
    public static void main(String[] args) {
        System.out.println("==============Enqueue 1================");
        String key= "thread";
        RedisEn1 obj= new RedisEn1(key);

        RThread robj= new RThread(obj);
    }
}
