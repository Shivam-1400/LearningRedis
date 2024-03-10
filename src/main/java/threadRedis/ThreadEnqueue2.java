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
            System.out.println(key+ " key Enqueued: "+ a );
        }
    }
}
public class ThreadEnqueue2 {
    public static void main(String[] args) {
        System.out.println("==============Enqueue 2================");
        String key= "thread";
        RedisEn1 obj= new RedisEn1(key);

        RThread robj= new RThread(obj);
    }
}
