package threadRedis;

import redis.clients.jedis.Jedis;

class RThreadDequeue implements Runnable{
    Thread thread;
    RedisDe1 obj;
    RThreadDequeue(RedisDe1 obj){
        this.obj= obj;
        thread= new Thread(this);
        thread.start();
    }
    public void run(){
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
                System.out.println(key + "  dequeued: " + a);
            }
        }
    }


}
public class ThreadDequeue1 {
    public static void main(String[] args) {
        System.out.println("==============Dequeue 1================");
        String key= "thread";
        RedisDe1 obj= new RedisDe1(key);

        RThreadDequeue robj= new RThreadDequeue(obj);
    }

}
