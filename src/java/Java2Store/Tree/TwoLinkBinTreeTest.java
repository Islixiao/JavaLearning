package Java2Store.Tree;

/***Created by moyongzhuo
 *On 2018/3/23  ***17:40.
 ******/
/**
 * Created by ietree
 * 2017/5/1
 */
public class TwoLinkBinTreeTest {

    public static void main(String[] args) {

        TwoLinkBinTree<String> binTree = new TwoLinkBinTree<String>("根节点");
        // 依次添加节点
        TwoLinkBinTree.TreeNode tn1 = binTree.addNode(binTree.root(), "第二层左节点", true);
        TwoLinkBinTree.TreeNode tn2 = binTree.addNode(binTree.root(), "第二层右节点", false);
        TwoLinkBinTree.TreeNode tn3 = binTree.addNode(tn2, "第三层左节点", true);
        TwoLinkBinTree.TreeNode tn4 = binTree.addNode(tn2, "第三层右节点", false);
        TwoLinkBinTree.TreeNode tn5 = binTree.addNode(tn3, "第四层左节点", true);

        System.out.println("tn2的左子节点：" + binTree.leftChild(tn2));
        System.out.println("tn2的右子节点：" + binTree.rightChild(tn2));
        System.out.println(binTree.deep());

    }

}
