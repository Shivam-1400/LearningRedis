package myRedis;

import redis.clients.jedis.Jedis;

import java.util.*;

public class Redis1 {
    public static void main(String [] args){
        System.out.println("Hello world");
        Jedis jedis = new Jedis();

        jedis.set("shivam", "keshri");
        String cachedResponse2 = jedis.get("shivam");
        System.out.println(cachedResponse2);


        //map1
        Map<String, String> mp= new HashMap<>();

        List<Object> details= new ArrayList<>();
        details.add("Keshri");;
        details.add(23);
        details.add("BE- CSE");
        details.add(2793);
        mp.put("Shivam", details.toString());

        details= new ArrayList<>();
        details.add("Guglani");;
        details.add(20);
        details.add("BE- ECE");
        details.add(1234);
        mp.put("Paras", details.toString());

        jedis.hset("NewJoinee", mp);

        Map<String, String> res= jedis.hgetAll("NewJoinee");
        System.out.println(res);

        //map2 hgetAll toString()
        Map<String, String > mp2= new HashMap<>();
        mp2.put("name", "Shivam");
        mp2.put("surname", "Keshri");
        mp2.put("age", 23+"");
        mp2.put("emp id", "2793");
        jedis.hset("Shivam", mp2);

        String res2= jedis.hgetAll("Shivam").toString();
        System.out.println(res2);

        String res3= jedis.hgetAll("Shivam").get("age").toString();
        System.out.println(res3);

        System.out.println(jedis.getClient());
        System.out.println(jedis.getDB());


        //hash
        jedis.hset("2793", "name", "Shivam Keshri");
        jedis.hset("2793", "age", "23");
        jedis.hset("2793", "course", "BE-CSE");

        String name = jedis.hget("2793", "name");
        System.out.println(name);

        Map<String, String> fields = jedis.hgetAll("2793");
        String job = fields.get("age");
        System.out.println(job);
        System.out.println(fields.get("course"));
    }
}

