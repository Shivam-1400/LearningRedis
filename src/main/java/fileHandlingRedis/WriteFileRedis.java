package fileHandlingRedis;


import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WriteFileRedis {
    public static void main(String [] args) throws IOException {
        Jedis js = new Jedis();
//        Map<String, String> mp= js.hgetAll("Shivam");
//        System.out.println(mp);

        FileWriter writer = new FileWriter("data.txt");

        writer.write("Redis data\n");
//        writer.close();

        List<String > ls= new ArrayList<>();
        ls.add("Add");
        ls.add("Subtract");
        ls.add("Multiply");
        ls.add("Divide");


        Set<String > keys= js.keys("*");
        for(String key : keys){
            String type= js.type(key);
            System.out.println(key+"  ->  "+ type);
            writer.write(key+"  ->  "+ type+"\n");
            printKeyValue(js, key, type, writer);

            System.out.println("============================");
            writer.write("===========================\n");

        }
        writer.close();
//        js.lpush("Tasks", ls.toArray(new String[0]));





    }
    static void printKeyValue(Jedis js, String key, String type, FileWriter writer) throws IOException {
        switch (type){
            case "string": System.out.println(js.get(key));
                writer.write(js.get(key)+"\n");
            break;

            case "hash": System.out.println(js.hgetAll(key));
                writer.write(js.hgetAll(key)+"\n");
            break;

            case "list": System.out.println(js.lrange(key, 0, -1));
                writer.write(js.lrange(key, 0, -1)+"\n");
            break;

            default:System.out.println("Wrong key type");
        }
    }
}
