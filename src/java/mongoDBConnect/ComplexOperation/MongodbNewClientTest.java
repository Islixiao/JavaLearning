package mongoDBConnect.ComplexOperation;

/***Created by moyongzhuo
 *On 2017/10/12  ***10:17.
 ******/
import java.util.List;

        import org.bson.Document;
        import org.junit.Before;
        import org.junit.Test;

        import com.mongodb.client.result.UpdateResult;

public class MongodbNewClientTest
{
    @Before
    public void before(){
        MongodbNewClient.connect("test", "javadb", "127.0.0.1", 27017);
    }

    @Test
    public void testInsert(){
        Document document = new Document();
        document.append("name", "wang").append("gender", "female");
        MongodbNewClient.insert(document);
    }

    @Test
    public void testFindAll(){
        List<Document> results = MongodbNewClient.findAll();
        for(Document doc : results){
            System.out.println(doc.toJson());
        }
    }

    @Test
    public void testFindBy(){
        Document filter = new Document();
        filter.append("name", "wang");
        List<Document> results = MongodbNewClient.findBy(filter);
        for(Document doc : results){
            System.out.println(doc.toJson());
        }
    }

    @Test
    public void testUpdateOne(){
        Document filter = new Document();
        filter.append("gender", "male");

        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender1", "female"));
        UpdateResult result = MongodbNewClient.updateOne(filter, update);
        System.out.println("matched count = " + result.getMatchedCount());
    }

    @Test
    public void testUpdateMany(){
        Document filter = new Document();
        filter.append("gender", "female");

        //注意update文档里要包含"$set"字段
        Document update = new Document();
        update.append("$set", new Document("gender", "male"));
        UpdateResult result = MongodbNewClient.updateMany(filter, update);
        System.out.println("matched count = " + result.getMatchedCount());
    }

    @Test//替换
    public void testReplace(){
        Document filter = new Document();
        filter.append("name", "wang");

        //注意：更新文档时，不需要使用"$set"
        Document replacement = new Document();
        replacement.append("name", "li");
        MongodbNewClient.replace(filter, replacement);
    }

    @Test
    public void testDeleteOne(){
        Document filter = new Document();
        filter.append("name", "li");
        MongodbNewClient.deleteOne(filter);
    }

    @Test
    public void testDeleteMany(){
        Document filter = new Document();
        filter.append("gender", "male");
        MongodbNewClient.deleteMany(filter);
    }
}