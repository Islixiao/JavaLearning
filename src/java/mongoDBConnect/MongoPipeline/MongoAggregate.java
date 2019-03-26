package mongoDBConnect.MongoPipeline;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.bson.BsonDocument;

/***Created by moyongzhuo
 *On 2017/10/12  ***11:01.
 ******/
/***
public class MongoAggregate {
    var connectionString = ConfigurationManager.AppSettings["MongodbConnection"];


    var client = new MongoClient(connectionString);

    var DatabaseName = ConfigurationManager.AppSettings["DatabaseName"];

    string collName = ConfigurationManager.AppSettings["collName"];

    MongoServer mongoDBConn = client.GetServer();

    MongoDatabase db = mongoDBConn.GetDatabase(DatabaseName);

    MongoCollection<BsonDocument> table = db[collName];

    var group = new BsonDocument{
        {"$group", new BsonDocument{
                      {

                    "_id","$state"

                };

                {

                    "totalPop", new BsonDocument

                    {

                        { "$sum","$pop" }

                    }

                }

            }

        }

    };

    var sort = new BsonDocument

    {

        {"$sort", new BsonDocument{ { "_id",1 }}}

    };

    var pipeline = new[] { group, sort };

    var result = table.Aggregate(pipeline);

    var matchingExamples = result.ResultDocuments.Select(x => x.ToDynamic()).ToList();

    foreach (var example in matchingExamples)

    {

        var message = string.Format("{0}- {1}", example["_id"], example["totalPop"]);

        Console.WriteLine(message);

    }
}
***/