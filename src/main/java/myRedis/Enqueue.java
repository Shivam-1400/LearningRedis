package myRedis;

import redis.clients.jedis.Jedis;
import java.util.Scanner;

public class Enqueue {

    public static void main(String[] args) {
        Jedis js = new Jedis();
        if(js== null){
            System.out.println("Connection failed. Retry");
            return;
        }

        String key = "my_queue";
        System.out.println("Enter number of items to insert: ");
        int n= new Scanner(System.in).nextInt();

        for (int i = 0; i < n; i++) {
            String item = "Item " + i;
            js.rpush(key, item);
            System.out.println("Enqueued: " + item);
        }

        js.close();
    }
}
