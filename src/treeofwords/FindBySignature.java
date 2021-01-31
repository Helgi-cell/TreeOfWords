
package treeofwords;
/**
 * Class for sorting and create signatures data for binary tree.<br>
 * @author Oleg Suhodolsky
 */
class FindBySignature extends TableSort implements SignatureInterface {

/**
 * Double {} doubleArrayOfWords -Array of the input string signatures<br>
 * int stringLength - length of the input string array where must be operated <br>
 * Double[][] indexArrayOfWordSignature - index array of the signatures of the sorted strings array<br>
 * 
 */
    Double[] doubleArrayOfWords;
    Integer[] arrayIndex;
    int stringLength;
    Double[][] indexArrayOfWordSignature;
    //Object for the show of the sorting result
    String[][] data;
    // Service variable for the table show
    static int x = -450;
    //Object for tables columns names
    static String[] headers = {"СТРОКА ВХОДЯЩАЯ", "СТРОКА ОТСОРТИРОВАННАЯ"};

    /**
     * CONSTRUCTOR <br>
     * @param string - array of string [](words) from database to perform<br>
     * @param idWords - array Double [] of the ID's strings from database
     */
    FindBySignature(String string[], Double idWords[]) {
        stringLength = string.length;          
        doubleArrayOfWords = new Double[stringLength]; 
        
        data = new String[stringLength][2];
        for (int i = 0; i < stringLength; i++) {
            data[i][0] = string[i];
        }
        indexArrayOfWordSignature = new Double[2][stringLength];
        wordSortAlphabet(string, idWords, ENG, COEFENG);
    }

  

//Method get word signature
//...........................//  
    
    /**
     * Method to get word signature. <br>
     * @param str - word must be get signature <br>
     * @param language - language of word ENG -english ;  RUS - russian <br>
     * @param coef - COEFENG - english ; COEFRUS - russian <br>
     * @return  Double res - signature. <br>
     */
    double createSignatureWord(String str, int language, int coef) {
        char c;
        int i;
        double res;

        int[] charSymbol = new int[str.length()];

        for (i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            charSymbol[i] = (byte) c;
            charSymbol[i] = charSymbol[i] & 0b0000000011011111;
        }
        res = (double) charSymbol[0] * coef;

        for (i = 1; i < str.length(); i++) {
            res += ((double) charSymbol[i] * coef / Math.pow(language, i + 2));
        }

        return res; //возвращаем сигнатуру строки (слова)
    }

// Method  get signature array of the words array 
    
    /**
     * Method to get signature of String [] array. <br>
     * @param string1 - String [] array must be get signature <br>
     * @param coef - language of word ENG -english ;  RUS - russian <br>
     * @param cf - COEFENG - english ; COEFRUS - russian <br>
     * @return  Double [] doubleArrayOfWords - signature of String []. <br>
     */
    Double[] createSignature(String string1[], int coef, int cf) {
        char c;
        int i;
        double res;
        String str = "";

        for (int j = 0; j < stringLength; j++) {
            str += string1[j];
            int[] charSymbol = new int[str.length()];

            for (i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                charSymbol[i] = (byte) c;
                charSymbol[i] = charSymbol[i] & 0b0000000011011111;
            }
            res = (double) charSymbol[0] * cf;

            for (i = 1; i < str.length(); i++) {
                res += ((double) charSymbol[i] * cf / Math.pow(coef, i + 2));
            }

            doubleArrayOfWords[j] = res;
            str = "";
        }

        return doubleArrayOfWords; // возвращаем массив сигнатур массива строк (слов)
    }

    // Hoar's sorting. Sorting of the index'es array. Quick
   private void qSort(double minn, double maxx, Double[][] array1) {
        double min = minn;
        double max = maxx;
        double mid = array1[1][(int) (max + min) / 2];
        double ex = 0;
        double ex1 = 0;

        do {
            while (array1[1][(int) min] < mid) {
                min++;
            }
            while (array1[1][(int) max] > mid) {
                max--;
            }
            if (min <= max) {
                ex = array1[1][(int) min];
                ex1 = array1[0][(int) min];
                array1[1][(int) min] = array1[1][(int) max];
                array1[0][(int) min] = array1[0][(int) max];
                array1[1][(int) max] = ex;
                array1[0][(int) max] = ex1;
                min++;
                max--;
            }

        } while ((int) min < (int) max);

        if (minn < max) {
            qSort(minn, max, array1);
        }
        if (min < maxx) {
            qSort(min, maxx, array1);
        }
    } // end qSort

    // Method  sorting words array. Input : array of words
    // Result : index array of the signatures and this index'es.
    private void wordSortAlphabet(String string1[], Double idWords[], int language, int coef) {
        String[] str = string1;
        doubleArrayOfWords = createSignature(str, language, coef);

        // Инициализируем индексный массив [stringLength][2] 
        // double[][] indexArrayOfWordSignature = new double[2][stringLength];
        // Заполняем индексный массив длинами строк 
        //согласно их положению в искомом массиве
        for (int i = 0; i < stringLength; i++) {
            indexArrayOfWordSignature[1][i] = doubleArrayOfWords[i];
            indexArrayOfWordSignature[0][i] = idWords[i];
        }

        qSort(0.0, (double) indexArrayOfWordSignature[1].length - 1, indexArrayOfWordSignature);

        showTable(string1);
    } // end wordSortAlphabet
    // ..................................................................//

    
    // Show the sorting result. (Service action)
    // TEST table to see results sorting
    // ...........................//
    void showTable(String[] oldString) {
        // Создаем строку для размещения отсортированного массива строк 
        String[] string2 = new String[stringLength];
        double p;
// Переносим в новую строку из оригинала строки согласно отсортированных индексов
        for (int j = 0; j < stringLength; j++) {
            p = indexArrayOfWordSignature[0][j];
            int i = 0;
            while (p != identification[i]) {
                i++;
            }
            string2[j] = oldString[i];
        }
        for (int i = 0; i < stringLength; i++) {
            data[i][1] = string2[i];
        }
        x += 650;
        tableSort("ТАБЛИЦА ОТОБРАЖЕНИЯ РЕЗУЛЬТАТОВ СОРТИРОВКИ", x, 100, data);
    }

}
