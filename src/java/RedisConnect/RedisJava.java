package RedisConnect;

import redis.clients.jedis.Jedis;

/***Created by moyongzhuo
 *On 2017/10/16  ***17:21.
 ******/
public class RedisJava {
    public static void main(String[] args) {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("localhost",6379);
        jedis.auth("xiemo");
        jedis.set("lsf","lingshufeng");
        System.out.println(jedis.get("lsf"));
        System.out.println("Connection to server sucessfully");
        //check whether server is running or not
        System.out.println("Server is running: "+jedis.ping());
    }
}
