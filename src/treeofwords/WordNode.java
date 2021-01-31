package treeofwords;

//---------------------------------------------------------//
import java.util.ArrayList;

/**
 * @author Suhodolsky Oleg
 * <h1><strong>Class describing content of binary tree node</strong></h1>
 * <h2>
 * Class describe content node of binary tree.<br>
 * </h2>
 */
class WordNode {

    /**
     * Double word - signature of the word(string)<br>
     * ArrayList (Double) - ID's of all destinations this word in database.<br>
     */
    Double word;                                  
    ArrayList<Double> id = new ArrayList<>();     

   /**<h3><strong>
    * CONSTRUCTOR.</strong></h3><br>
    * @param word - signature word from database <br>
    * @param idWord - ID of word from databse
    */
    WordNode(Double word, Double idWord) {
        this.word = word;
        id.add(idWord);
    }

    /**
     * Method to insert ID of word in collection <br>
     * @param idWord - ID of word destination in database
     */
    void addWord(Double idWord) {                
        this.id.add(idWord);
    }
} // end class WordNode

