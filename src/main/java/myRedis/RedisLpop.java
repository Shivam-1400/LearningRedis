package myRedis;


import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisLpop {
    public static void main(String [] args){
        Jedis js= new Jedis();

        //all keys
        Set<String> keys= js.keys("*");
        System.out.println("Keys : "+ keys);

        //lpush
        String taskKey= "taskQueue";
        js.lpush(taskKey, "task1", "Add", "two");
        js.lpush(taskKey, "task2", "Subtract");
        js.lpush(taskKey, "task3", "Multiply");
        js.lpush(taskKey, "task4", "Divide");

        //lpop
        while(true){
            String task= js.lpop(taskKey);
            if(task== null){
                System.out.println("All task finished. Exiting...");
                break;
            }
            System.out.println("doing Task :"+ task+" from list");
        }


//        js.lpush(taskKey, "task1", "Add", "two");
//        js.lpush(taskKey, "task2", "Subtract");
//        js.lpush(taskKey, "task3", "Multiply");
//        js.lpush(taskKey, "task4", "Divide");
    }
}
