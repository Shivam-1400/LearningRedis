package myRedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

public class Dequeue {

    public static void main(String[] args) {
        Jedis js = new Jedis();

        if(js== null){
            System.out.println("Connection failed. Retry");
            return;
        }

            String key = "my_queue";

        try {
            while (true) {
                String item = js.lpop(key);
                if (item == null) {
                    Thread.sleep(1000);
                } else {
                    System.out.println("Dequeued: " + item);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JedisDataException e) {
            System.out.println("Queue is empty. Exiting...");
        } finally {
            js.close();
        }
    }
}
