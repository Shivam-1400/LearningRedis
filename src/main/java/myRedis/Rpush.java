package myRedis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class Rpush {
    public static void main(String [] args){
        Jedis j= new Jedis();

        //rpush
        j.rpush("rpush1", "Shivam", "Keshri", "is", "my", "name");
        j.rpush("rpush2",  "netcore");

        //rpop
        String result= j.rpop("rpush1");
        System.out.println(result);

        //rpop
        String res2= j.rpop("rpush2");
        System.out.println(res2);

        //lrange
        String listName = "rpush3";
        String[] dataToPush = {"my", "name", "is", "Shivam", "Keshri"};
        j.rpush(listName, dataToPush);
        List<String> dataList = j.lrange("rpush3", 0, -1);
        System.out.println("Data read from the list:");
        for (String item : dataList) {
            System.out.println(item);
        }
    }
}
