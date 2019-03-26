package Webmagic.BaozouManhua.com;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/***Created by moyongzhuo
 *On 2017/10/10  ***21:04.
 ******/
public class BaoZouPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        BaozouNews news = (BaozouNews) resultItems.get("news");
        Dao.insert(news);//将数据插入数据库
    }

}
