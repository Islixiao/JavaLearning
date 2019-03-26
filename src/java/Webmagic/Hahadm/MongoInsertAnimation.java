package Webmagic.Hahadm;

/***Created by moyongzhuo
 *On 2017/10/23  ***9:57.
 ******/
        import java.math.BigDecimal;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

        import mongoDBConnect.ExcelTest.ExcelUtil;
        import mongoDBConnect.ExcelTest.StringUtils;
        import org.bson.Document;

        import com.mongodb.MongoClient;
        import com.mongodb.client.MongoCollection;
        import com.mongodb.client.MongoDatabase;
        import org.junit.Before;
        import org.junit.Test;

public class MongoInsertAnimation{
    public static String excel_source_path = "D:\\workspace\\java\\myOwnModlelearing\\WebHTTPInterface\\src\\main\\java\\Webmagic\\Hahadm\\allanimation总爬取3000多数据.xls";
    public List<List<String>> content_source;
    @Before
    public void init() {
        Calendar now = Calendar.getInstance();
        //excel_source_path+="测试角色-0706.xls";
        content_source= ExcelUtil.readOneBySheetName("Sheet1", excel_source_path, 1, 0, 0);

    }

    @Test
    public void testread(){
        List<List<String>> contents = new ArrayList<>();
        for (List<String> line:content_source){
            if (StringUtils.isNotBlank(line.get(1))){
                contents.add(line);
                System.out.println(line.get(1));
            }
        }
        System.out.println(contents);
    }
@Test//hahadm_animation_all3000_20171023
    public  void InsertAnimation() throws Exception{
        try{
            // 连接到 mongodb 服务
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("spider_test");
            System.out.println("Connect to database successfully");

            MongoCollection<Document> collection = mongoDatabase.getCollection("hahadm_animation_all3000_20171023");
            System.out.println("集合 test 选择成功");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            List<List<String>> contents = new ArrayList<>();
            int aa = 0;
            for (List<String> line:content_source){
                aa++;
               //if (StringUtils.isNotBlank(line.get(1))&&aa<=5) {
                if (StringUtils.isNotBlank(line.get(1))) {
                    String myid = line.get(0);
                    String hahawebname = line.get(1);
                    String name11 = hahawebname.replaceAll( "[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]" , "");
                    String name1111 = hahawebname.replaceAll( " " , "");
                    String clearName = name1111;
                    String tagold = line.get(2);
                    String tag = tagold.replaceAll(","," / ");
                    String yearold = line.get(3);
                    int year = Integer.valueOf(yearold).intValue();
                    String directorold = line.get(4);
                    String director = directorold.replaceAll(","," / ");
                    String content = line.get(5);
                    //得分
                    String goalold = line.get(6);
                    Float  goal11 = Float.valueOf(goalold).floatValue();
                    BigDecimal   goalbb   =   new   BigDecimal(goal11);
                    float   goalff   =   goalbb.setScale(1,   BigDecimal.ROUND_HALF_UP).floatValue();
                    String goal = Float.toString(goalff);
              /*      Float  goal = Float.valueOf(goalold).floatValue();
                    int   scale  =   2;//设置位数
                    int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
                    BigDecimal bd  =   new  BigDecimal((double)goal);
                    bd   =  bd.setScale(scale,roundingMode);
                    goal   =  bd.floatValue();
                    System.out.println("goal:" + goal);*/

                    String mentotalold = line.get(7);
                    int  mentotal = Integer.valueOf(mentotalold).intValue();
                    String originalold = line.get(8);
                    String original = originalold.replaceAll(","," / ");

                    String screenwriterold = line.get(9);
                    String screenwriter = screenwriterold.replaceAll(","," / ");

                    String  allname11 = line.get(10);
                    String  allname1111 = allname11.replaceAll("别名[: ：]","");
                    String  allname = allname1111.replaceAll(","," / ");

                    String  dubbingold = line.get(11);
                    String  dubbing = dubbingold.replaceAll(","," / ");
                    String  roleold = line.get(12);
                    String  role = roleold.replaceAll(","," / ");
                    String  area = line.get(13);
                    String  languge = line.get(14);
                    String  url = line.get(15);
                    String  vediourl = line.get(16);
                    String  hhimageurl = line.get(17);
                    String  contentdetail = line.get(18);
                    Document document = new Document("myid", myid).
                            append("hahawebName", hahawebname).
                            append("role", role).
                            append("dubbing", dubbing).
                            append("clearName", clearName).
                            append("allname", allname).
                            append("tag", tag).
                            append("year", year).
                            append("director", director).
                            append("goal", goal).
                            append("mentotal",  mentotal).
                            append("original", original).
                            append("screenwriter", screenwriter).
                            append("area", area).
                            append("languge",  languge).
                            append("url", url).
                            append("vediourl", vediourl).
                            append("hhimageurl", hhimageurl).
                            append("content", content).
                            append("contentdetail", contentdetail)
                            ;
                    List<Document> documents = new ArrayList<Document>();
                    documents.add(document);
                    collection.insertMany(documents);
                }
            }
            System.out.println("文档插入成功");
        }catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}