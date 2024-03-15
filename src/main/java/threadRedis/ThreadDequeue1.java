package threadRedis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class RThreadDequeue implements Runnable{

    RedisDe1 obj;
    RThreadDequeue(RedisDe1 obj){
        this.obj= obj;
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" Started to dequeue 1:");
        obj.dequeue1();
    }
}
class RedisDe1 {
    Jedis js;
    String key;
    RedisDe1(String key){
        js= new Jedis();
        this.key= key;
    }
    void dequeue1(){
        while (true){
            String a= js.rpop(key);
            if(a== null){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println(key + "  dequeued: " + a+" by thread "+ Thread.currentThread().getName() );
            }
        }
    }
}
public class ThreadDequeue1 {
    public static void main(String[] args) {
        System.out.println("==============Dequeue 1================");
        String key= "thread";


        ExecutorService pool= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        pool.submit(new RThreadDequeue(new RedisDe1(key)));
    }

}
