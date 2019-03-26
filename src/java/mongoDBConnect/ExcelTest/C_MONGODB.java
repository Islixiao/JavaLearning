package mongoDBConnect.ExcelTest;

/***Created by moyongzhuo
 *On 2017/10/12  ***11:44.
 ******/

import com.alibaba.fastjson.JSON;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * 对于MongoDB 数据库操作的方法
 */
public class C_MONGODB  {

    /**
     * 将数据存储到MongoDB中。
     *
     * @param json       要存储到MongoDB数据库的字符串
     * @param collection MongoDB中集合的名称
     * @return 插入数据的主键
     * @throws Exception
     */
    @Test
    public static String saveObjectByJson(String json, String collection) throws Exception {
       // DBCollection coll = MongoDBUtil.getCollection(collection);
        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> coll = mongoDatabase.getCollection("errorlog");
        System.out.println("集合 errorlog 选择成功");
        DBObject dbobject = (DBObject) JSON.parse(json);
       // coll.save(dbobject);
        String oid = dbobject.get("_id").toString();
        return oid;
    }
}