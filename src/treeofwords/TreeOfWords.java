package treeofwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * <h2>
 * Class works with database specific string's binary tree 
 * @author Suhodolsky Oleg <br>
 * @version 1.0 <br> 
 * <strong>Class creat and use binary tree of words for finding, deleting <br>
 * and insert words, that store in database</strong> <br>
 * </h2>
 */

public class TreeOfWords implements SignatureInterface {

    /**
     * <h3><strong> Fields </strong></h3>
     * <h2>
     * @see FindBySignature class <br>
     * @see TreeOfNodes class     <br>
     * </h2>
     */
    FindBySignature stringToTree;
    TreeOfNodes treeWork;

    /**
     * <h2>
     * <strong>
     * Comstructor Class works with special binary tree of words and this ID <br>
     * from database
     * </strong> </h2>
     * @param string - array of words getting from database to dispose and use
     * in tree
     * @param identification - array of ID words, gettings from database
     */
    TreeOfWords(String[] string, Double[] identification) {
        this.stringToTree = new FindBySignature(string, identification);
        this.treeWork = new TreeOfNodes(stringToTree.indexArrayOfWordSignature);
    }

    /**
     * <h3><strong>Method find in tree ID words in database</strong></h3>
     * <h2>
     * @param str - word need to find in binary tree <br>
     * @return result- Words ID datdbase founded in tree <br>
     * </h2>
     */
    Double[] findWordInBinaryTree(String str) {

        Double dW = getSignatureWord(str);      // получаем сигнатуру слова поиска
        Double[] result = treeWork.findWord(dW);    // получаем результат найденных id слова поиска 
        System.out.println("Word: " + str + "-> " + dW + "|  id : -> " + Arrays.toString(result));
        return result;
    }

    /**
     * <h3><strong>Method to create signature of word</strong></h3>
     *<h2>
     * @param str - word need to create signature<br>
     * @return dw - result of signature<br>
     * </h2>
     */
    Double getSignatureWord(String str) {
        Double dW = stringToTree.createSignatureWord(str, ENG, COEFENG);
        return dW;
    }

    /**
     * <h3><strong>Method insert new word with ID in binary tree. </strong></h3>
     *
     * @param word - word need to insert <br>
     * @param idW- ID of word in database <br>
     */
    void insertWord(String word, Double idW) {
        Double dW = stringToTree.createSignatureWord(word, ENG, COEFENG);
        treeWork.insertNode(dW, idW);
    }

    /**
     * <h3><strong>Method delete word with ID in binary tree</strong></h3>
     *
     * @param word - word need to delete<br>
     * @param idW - ID of word in database<br>
     */
    void deleteWord(String word, Double idW) {
        Double dW = stringToTree.createSignatureWord(word, ENG, COEFENG);
        treeWork.deleteTreeNode(dW, idW);
    }

    /**
     * <h3><strong>
     * Method balancing binary tree
     * </strong></h3>
     */
    void balanceTree() {
        if (treeWork.possibilityBalance()) {
            treeWork.treeBalance();
        }
    }

    /**
     *  <h3><strong>
     * Method return deep of binary tree from the node.left or node.right of
     * currency node
     *  </strong></h3>
     * @param  treeNode -  TreeNode Link to needed node of tree <br>
     * @return deep -  int deeping o tree<br>
     */
    int deepTree(TreeNode treeNode) {
        int deep = treeWork.deep(treeNode, 0);
        return deep;
    }

    
    public static void main(String[] args) {

        TreeOfWords tree = new TreeOfWords(string, identification);
        tree.acrossTestDeletingInsertDinding(tree);
    }// end main

    
    
    /*-------------------------
            TESTING METHODS
    //-------------------------
    */
    
/**
 * Testing method
 * @param tree - link to tree of nodes 
 */   
void acrossTestDeletingInsertDinding(TreeOfWords tree){

System.out.println("Tree currency : ");
        tree.getIdFromTree(tree.treeWork.treeNodeRoot);
        System.out.println();
        System.out.println("Insert new words and ID's: " + "\ndebby ->" + 115.0 + "\njohn ->" + 72.0 + "\nalpha ->" + 52.0);
        tree.insertWord("debby", 115.0);
        tree.insertWord("john", 72.0);
        tree.insertWord("alpha", 52.0);
        System.out.println("Tree after insert new words : ");
        tree.getIdFromTree(tree.treeWork.treeNodeRoot);
        System.out.println();
        System.out.println("Find words in tree :");
        tree.findWordInBinaryTree("debby");
        tree.findWordInBinaryTree("john");
        tree.findWordInBinaryTree("alpha");
        tree.findWordInBinaryTree("red");
        tree.findWordInBinaryTree("real");

        System.out.println("Deleting word : ");
        tree.deleteWord("debby", 115.0);
        tree.deleteWord("alpha", 63.0);
        tree.deleteWord(string[13], identification[13]);
        System.out.println("debby -> " + 115.0 + "\n\t" + string[13] + " -> " + identification[13] + "\n\t\t"
                + "  alpha -> " + 63.0 + " Deleting correctable.");
        tree.deleteWord(string[1], identification[1]);
        tree.deleteWord(string[24], identification[24]);
        System.out.println(string[1] + " -> " + identification[1] + "\n\t\t"
                + string[24] + " -> " + identification[24]);
        System.out.println("End deleting . ");
        System.out.println("Finding words in tree 'debby', 'alpha', 'exodus', 'red', 'real' : ");
        tree.findWordInBinaryTree("debby");
        tree.findWordInBinaryTree("alpha");
        tree.findWordInBinaryTree("exodus");
        tree.findWordInBinaryTree("red");
        tree.findWordInBinaryTree("real");
        System.out.println("Tree after deleting words: ");
        tree.getIdFromTree(tree.treeWork.treeNodeRoot);
        System.out.println();
        System.out.println("Test terminated correctly.");


}
   

    /**
     * <h3><strong>
     * Testing method
     * </strong></h3>
     */
   private void showResultOfFind(Double[] result, String str) {
        double r;
        if (result != null) {
            for (Double result1 : result) {
                r = result1;
                System.out.println("ID - " + (int) r + " ->  " + string[(int) r]);
            }
        } else {
            System.out.println("Not to find word - " + str);
        }
    }

   
    
    /**
     * <h3><strong>
     * Testing method
     * </strong></h3>
     */
   private void testDeleteTree() {
        System.out.println("String len = " + stringToTree.indexArrayOfWordSignature[1].length);
        for (int i = stringToTree.indexArrayOfWordSignature[1].length - 1; i >= 0; i--) {
            System.out.println(i + 1 + ":  ");
            treeWork.deleteTreeNode(stringToTree.indexArrayOfWordSignature[1][i], stringToTree.indexArrayOfWordSignature[0][i]);
        }
        System.out.println("Deleting correct.");
    }

   

    /**
     * <h3><strong>
     * Testing method
     * </strong></h3>
     */
   private void testBalanceTree(TreeOfWords tree) {

        System.out.println("Need to balance is " + tree.treeWork.possibilityBalance());

    }

    /**
     * <h3><strong>
     * Testing method
     * </strong></h3>
     */
   private void testTreeAfterBalance() {
        ArrayList<Double[]> list;
        list = new ArrayList();
        Random rand = new Random();
        int member;
        for (int i = 0; i < stringToTree.indexArrayOfWordSignature[1].length - 1; i++) {
            Double[] container = new Double[2];
            container[0] = stringToTree.indexArrayOfWordSignature[0][i];
            container[1] = stringToTree.indexArrayOfWordSignature[1][i];
            list.add(container);
        }
        System.out.println("Size tree = " + list.size());

        for (int i = 0; i < 20; i++) {
            //member = i;
            member = rand.nextInt(list.size());
            Double[] container = new Double[2];
            container = list.get(member);
            list.remove(member);
            System.out.println(i + " element delete:  " + container[0]);
            treeWork.deleteTreeNode(container[1], container[0]);
        }
        if (treeWork.treeNodeRoot == null) {
            System.out.println("Deleting correct.");
        }

        for (int i = 0; i < list.size(); i++) {
            Double[] container = new Double[2];
            container = list.get(i);
            System.out.println("Id = " + Arrays.toString(treeWork.findWord(container[1])));

        }

    }

    /**
     * <h3><strong>
     * Testing method
     * Разбалансировка дерева (одной ветви)
     * </strong></h3>
     */
   private void disBalanceTree() {
        for (int i = 0; i < 19; i++) {
            TreeNode treeNode;
            TreeNode treeNodePrev = null;

            treeNode = treeWork.treeNodeRoot;
            treeNode = treeNode.left;

            if (treeNode == null) {
                return;
            }

            while (treeNode.left != null || treeNode.right != null) {
                if (treeNode.left != null) {
                    treeNodePrev = treeNode;
                    treeNode = treeNode.left;
                } else {
                    if (treeNode.left == null && treeNode.right != null) {
                        treeNodePrev = treeNode;
                        treeNode = treeNode.right;

                    }

                }

            }

            if (treeNodePrev.left == treeNode && treeNode.left == null && treeNode.right == null) {
                treeNodePrev.left = null;
            }
            if (treeNodePrev.right == treeNode && treeNode.left == null && treeNode.right == null) {
                treeNodePrev.right = null;
            }

        }
    }

    /**
     * <h3><strong>
     * Testing method
     * Получаем идентификаторы из дерева, выводим их на консоль
     * </strong></h3>
     */
   private void getIdFromTree(TreeNode treeNode) {

        if (treeNode != null) {
            System.out.print(treeNode.node.id + ", ");
        }
        if (treeNode.left != null) {
            getIdFromTree(treeNode.left);
        }
        if (treeNode.right != null) {
            getIdFromTree(treeNode.right);
        }
    }

} // end class

