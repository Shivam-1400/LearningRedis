package threadRedis;

import redis.clients.jedis.Jedis;

class RThreadDequeue2 implements Runnable{
    Thread thread;
    RedisDe2 obj;
    RThreadDequeue2(RedisDe2 obj){
        this.obj= obj;
        thread= new Thread(this);
        thread.start();
    }
    public void run(){
        System.out.println(Thread.currentThread().getName()+" Started to dequeue 2:");
        obj.dequeue1();
    }
}
class RedisDe2 {
    Jedis js;
    String key;
    RedisDe2(String key){
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
public class ThreadDequeue2 {
    public static void main(String[] args) {
        System.out.println("==============Dequeue 2================");
        String key= "thread";

//        RedisDe1 obj= new RedisDe1(key);

        RThreadDequeue2 robj1= new RThreadDequeue2(new RedisDe2(key));
        RThreadDequeue2 robj2= new RThreadDequeue2(new RedisDe2(key));
    }

}

