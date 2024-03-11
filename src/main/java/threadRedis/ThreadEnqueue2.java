package threadRedis;

import redis.clients.jedis.Jedis;
import java.util.Scanner;

class RThread2 implements Runnable{
    RedisEn2 obj;
    Thread thread;
    RThread2(RedisEn2 obj){
        this.obj= obj;
        thread= new Thread(this);
        thread.start();
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" Started to enqueue 2:");
        obj.enqueue();
    }
}
class RedisEn2{
    Jedis js;
    String key;
    RedisEn2(String key){
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
            System.out.println(key+ " key Enqueued: "+ a +" by thread "+ Thread.currentThread().getName() );
        }
    }
}
public class ThreadEnqueue2 {
    public static void main(String[] args) {
        System.out.println("==============Enqueue 2================");
        String key= "thread";
//        RedisEn2 obj= new RedisEn2(key);

        RThread2 robj1= new RThread2(new RedisEn2(key));
        RThread2 robj2= new RThread2(new RedisEn2(key));
    }
}
