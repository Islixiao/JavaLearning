package Java2Store;

import java.util.Comparator;
import java.util.Map;

/***Created by moyongzhuo
 *On 2018/5/9  ***10:11.
 ******/
class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {

        return me1.getValue().compareTo(me2.getValue());
    }
}
