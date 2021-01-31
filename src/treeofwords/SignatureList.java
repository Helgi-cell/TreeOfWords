package treeofwords;

import java.util.ArrayList;
import java.util.Objects;


/**
 * @author SUHODOLSKY OLEG
 * Class normalize sorted indexes array to
 * collection of binary tree nodes. 
 */
class SignatureList {

    ArrayList<WordNode> listOfSignature;

 /**
  * Constructor
  * @param array - sorted indexes array to create binary tree nodes 
  */
    SignatureList(Double[][] array) {
        this.listOfSignature = createSignatureList(array);
    }

    /**
     * Method to creat binary tree nodes collection
     * @param array - sorted indexes array to create binary tree nodes 
     * @return list - collection ArrayList
     */
    private ArrayList<WordNode> createSignatureList(Double[][] array) {
        WordNode word;
        ArrayList<WordNode> list = new ArrayList<>();
        int i = 0;

        while (i < array[1].length) {
            word = new WordNode(array[1][i], array[0][i]);
            word.word = array[1][i];
            while ((i + 1) < array[1].length && Objects.equals(array[1][i], array[1][i + 1])) {
                i++;
                word.addWord(array[0][i]);
            }
            i++;
            list.add(word);
        }
        return list;
    } // end method

} // end class

