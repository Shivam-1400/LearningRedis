package threadRedis;


import redis.clients.jedis.Jedis;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RThread implements Runnable{
    RedisEn1 obj;
    RThread(RedisEn1 obj){
        this.obj= obj;
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" Started to enqueue 1:");
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
            System.out.println(key+ " key Enqueued: "+ a+" by thread "+ Thread.currentThread().getName() );
        }
    }
}
public class ThreadEnqueue1 {
    public static void main(String[] args) {
        System.out.println("==============Enqueue 1================");
        String key= "thread";

        int p= Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: "+ p);
        ExecutorService pool= Executors.newFixedThreadPool(p);
        pool.submit(new RThread(new RedisEn1(key)));
    }
}
