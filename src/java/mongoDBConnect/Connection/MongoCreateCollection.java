package mongoDBConnect.Connection;

/***Created by moyongzhuo
 *On 2017/10/11  ***20:09.
 ******/

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoCreateCollection {
    public static void main( String args[] ){
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );


            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");
            mongoDatabase.createCollection("mongotest");
            System.out.println("集合创建成功");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}