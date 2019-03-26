package Java2Store;

/***Created by moyongzhuo
 *On 2018/3/23  ***17:30.
 ******/

/**
 * Created by ietree
 * 2017/5/1
 */
public class ArrayBinTreeTest {

    public static void main(String[] args) {

        ArrayBinTree<String> binTree = new ArrayBinTree<String>(4, "根");
        binTree.add(0, "第二层右子节点", false);
        binTree.add(2, "第三层右子节点", false);
        binTree.add(6, "第四层右子节点", false);
        System.out.println(binTree);

    }

}
