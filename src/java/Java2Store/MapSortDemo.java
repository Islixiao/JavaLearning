package Java2Store;

import java.util.*;

import static BigDataOperation.dataThread.MacAddressThreadUtil.sortMapByKey;

/***Created by moyongzhuo
 *On 2018/5/9  ***10:09.
 ******/
public class MapSortDemo {

    public static void main(String[] args) {

        Map<String, String> map = new TreeMap<String, String>();

        map.put("KFC", "1");
        map.put("WNBA", "2");
        map.put("NBA", "5");
        map.put("CBA", "9");
        map.put("CBB", "2");

        //Map<String, String> resultMap = sortMapByKey(map);  //按Key进行排序
      Map<String, String> resultMap = sortMapByValue(map); //按Value进行排序

        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    /**
     * 使用 Map按value进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}
