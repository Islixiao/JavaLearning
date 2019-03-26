package JavaToArangodb;


import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.entity.BaseDocument;
import com.arangodb.util.MapBuilder;

import java.util.Collection;
import java.util.Map;

/***Created by moyongzhuo
 *On 2017/10/31  ***16:44.
 ******/
public class ArangoUtil {
    protected ArangoDB arangoDB;
   // protected ArangoConfig config;
    protected ArangoDatabase[] dbs;
    protected ArangoCollection[] colls;
    //private static final Logger log = LoggerFactory.getLogger(ArangoUtil.class);

    public void shutDown() {
        arangoDB.shutdown();
    }

    protected void initArango(String host,int port,String username,String password) {
        //连接Arangodb服务器
        arangoDB = new ArangoDB.Builder().host(host,port).user(username).password(password).build();
    }
    protected void initDbs(String dbname) {
        Collection<String> dbNames = arangoDB.getDatabases();
        //判断database是否已经存在，不存在就新创建
        int index =dbNames.size();
        dbs = new ArangoDatabase[index];
            if (!arangoDB.getDatabases().contains(dbname)) {
                arangoDB.createDatabase(dbname);
                System.out.println("数据库不存在，创建" + dbname);
                dbs = new ArangoDatabase[index++];
               System.out.println("数据库的个数: " + index);
            }
           else{System.out.println("数据库" + dbname+"存在");}
    }





    protected void insert(int dbIndex, String doc, BaseDocument insertDoc) {
        String insertCmmd = "insert @insertDoc into @@doc";
        dbs[dbIndex].query(insertCmmd, new MapBuilder().put("insertDoc", insertDoc).put("@doc", doc).get(), null, null);
    }

    protected void update(int dbIndex, String doc, String key, BaseDocument updateDoc) {
        String updateCmmd = "update {_key:@key} with @updateDoc into @@doc";
        dbs[dbIndex].query(updateCmmd, new MapBuilder().put("key", key).put("updateDoc", updateDoc).put("@doc", doc).get(), null, null);
    }

    protected void upsert(int dbIndex, String doc, BaseDocument upsertDoc, BaseDocument insertDoc, BaseDocument updateDoc) {
        String upsertCmmd = "upsert @upsertDoc insert @insertDoc update @updateDoc in @doc  OPTIONS { keepNull: false }";
        dbs[dbIndex].query(upsertCmmd, new MapBuilder().put("upsertDoc", upsertDoc).put("insertDoc", insertDoc).put("updateDoc", updateDoc).put("doc", doc).get(), null, null);
    }

/*    protected ArangoCursor relation(Class clazz, int dbIndex, String tableName, String tableName1, List<ArangoQueryParam> arangoQueryParamList, String relation) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
//        table : for o in table
        query.append(" for o in ").append(tableName);
//        filter  o.field=@field and o.field1=@field1
        if (!CollectionUtils.isEmpty(arangoQueryParamList)) {
            query.append(" filter ");
            int index = 0;
            for (ArangoQueryParam arangoQueryParam : arangoQueryParamList) {
                if (index++ == 0) {
                    query.append(arangoQueryParam.getFilterString().replace("and", ""));
                } else {
                    query.append(arangoQueryParam.getFilterString());
                }
                params.put(arangoQueryParam.getField(), arangoQueryParam.getValue());

            }
        }
        query.append(" for v,e in outbound o._id ").append(tableName1)
                .append(" filter  e.relation=='").append(relation).append("'");
//        return o
        query.append(" return v ");
        LogUtil.debug(query.toString());
        return dbs[dbIndex].query(query.toString(), params, null, clazz);
    }

    protected ArangoCursor<Entity> find(Class clazz, int dbIndex, String doc, List<ArangoQueryParam> arangoQueryParamList, int limit, int page) {
        StringBuilder query = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
//        table : for o in table
        query.append(" for o in ").append(doc);
//        filter  o.field=@field and o.field1=@field1
        if (!CollectionUtils.isEmpty(arangoQueryParamList)) {
            query.append(" filter ");
            int index = 0;
            for (ArangoQueryParam arangoQueryParam : arangoQueryParamList) {
                if (index++ == 0) {
                    query.append(arangoQueryParam.getFilterString().replace("and", ""));
                } else {
                    query.append(arangoQueryParam.getFilterString());
                }
                params.put(arangoQueryParam.getField(), arangoQueryParam.getValue());

            }
        }
//        limit offset,count
        if (page > 0 && limit > 0) {
            query.append(" limit ").append((page - 1) * limit).append(",").append(limit);
        }
//        return o
        query.append(" return o ");
        LogUtil.debug(query.toString());
        return dbs[dbIndex].query(query.toString(), params, null, clazz);
    }

    public ArangoConfig getConfig() {
        return config;
    }

    public void setConfig(ArangoConfig config) {
        this.config = config;
    }*/

    public ArangoCursor<BaseDocument> query(int dbIndex, String query, Map<String, Object> params) {
        return dbs[dbIndex].query(query, params, null, BaseDocument.class);
    }
}
