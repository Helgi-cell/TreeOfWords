package treeofwords;

//---------------------------------------------------------//
import java.util.ArrayList;
import java.util.Objects;

//---------------------------------------------------------//
// Класс создания дерева узлов , операций с ним            //
//---------------------------------------------------------//
class TreeOfNodes {

    ArrayList<WordNode> list;
    TreeNode treeNodeRoot = new TreeNode();

    TreeOfNodes(Double[][] array) {

        TreeNode treeNode = new TreeNode();
        SignatureList listSign = new SignatureList(array);
        this.list = listSign.listOfSignature;
        this.treeNodeRoot = treeNode;
        createTree(0, (list.size() - 1), (list.size() - 1) / 2, treeNode);
    } // end constructor

    /*--------------------------------
    // Метод создания бинарного дерева
    //--------------------------------
     */
    private void createTree(int min, int max, int middle, TreeNode treeNode) {

        WordNode word;
        TreeNode treeNodeRight = new TreeNode();
        TreeNode treeNodeLeft = new TreeNode();
        int left;
        int right;

        left = (middle + min) / 2;
        right = (middle + max) / 2;

        word = list.get(middle);
        treeNode.node = word;

        if (right > middle && right < max) {
            word = list.get(right);
            treeNodeRight.node = word;
            treeNode.right = treeNodeRight;
        }

        if (left < middle && left > min) {
            word = list.get(left);
            treeNodeLeft.node = word;
            treeNode.left = treeNodeLeft;
        }

        if (max - middle == 1 && (right == middle || right == max)) {
            word = list.get(max);
            treeNodeRight.node = word;
            treeNode.right = treeNodeRight;
        }

        if (middle - min == 1 && (left == middle || left == min)) {
            word = list.get(min);
            treeNodeLeft.node = word;
            treeNode.left = treeNodeLeft;
        }

        if (max == middle) {
            treeNode.right = null;
        }

        if (min == middle) {
            treeNode.left = null;
        }

        if (min == max) {
            word = list.get(max);
            treeNode.node = word;
            treeNode.left = null;
            treeNode.right = null;
            return;
        }

        if (treeNode.left == null && treeNode.right == null) {
            return;
        }

        createTree(min, middle - 1, left, treeNodeLeft);
        createTree(middle + 1, max, right, treeNodeRight);
    } // end createTree

    /* ------------------------------------
    // Метод поиска слова в бинарном дереве
    //-------------------------------------
     */
    public Double[] findWord(double find) {
        Double[] res = null;
        TreeNode tree;
        tree = treeNodeRoot;

        if (treeNodeRoot == null) {
            return null;
        }
        boolean bool = true;
        while (bool) {
            if (tree.node.word == find) {
                res = new Double[tree.node.id.size()];
                for (int i = 0; i < res.length; i++) {
                    res[i] = tree.node.id.get(i);
                }
                bool = false;

            } else {
                if (tree.left == null && tree.right == null) {
                    System.out.println("No find word.");
                    bool = false;
                }
            }
            if (bool != false) {
                if (tree.node.word > find) {
                    if (tree.left != null) {
                        tree = tree.left;
                    } else {
                        System.out.println("No find word.");
                        bool = false;
                    }
                }
                if (tree.node.word < find) {
                    if (tree.right != null) {
                        tree = tree.right;
                    } else {
                        System.out.println("No find word.");
                        bool = false;
                    }
                }
            }
        }
        if (res != null) {
            for (Double re : res) {
                System.out.println("Поиск слова ->" + re);
            }
        }
        return res;
    } // end findWord

    //=======================================================================//
    // Метод удаления слова из дерева поиска                                 //
    //=======================================================================//
    void deleteTreeNode(Double signatureWord, Double idWord) {

        if (treeNodeRoot == null) {
            System.out.println("Tree of words is null.");
            return;
        }

        TreeNode treeNode1 = null;
        TreeNode treeNodeX = null;
        TreeNode treeNode = treeNodeRoot;
        TreeNode prev = null;

        // 1. Ищем узел treeNode.node.word == signatureWord
        boolean bool = true;

        while (bool) {
            if (treeNode == null) {
                System.out.println("Null point result. Deleting impossible.");
                return;
            }
            if (Objects.equals(treeNode.node.word, signatureWord)) {
                bool = false;
            } else {
                if (treeNode.node.word < signatureWord) {
                    treeNode1 = treeNode;
                    treeNode = treeNode.right;
                } else {
                    treeNode1 = treeNode;
                    treeNode = treeNode.left;
                }
            }
        }
        int left = deep(treeNode.left, 0);
        int right = deep(treeNode.right, 0);

        if (treeNode.node.id.size() > 1) {
            Double num;
            for (int i = 0; i < treeNode.node.id.size(); i++) {
                num = treeNode.node.id.get(i);
                if (Objects.equals(num, idWord)) {
                    treeNode.node.id.remove(i);
                }
            }
        } else {
            //========================================
            // Удаляем корневой узел. Дерево вырождено
            //========================================
            if (treeNode == treeNodeRoot && treeNode.left == null && treeNode.right == null) {
                treeNodeRoot = null;
                System.out.println("Root node deleted. Tree is null.  End of deleting.");
            } else {
                //===============
                // Удаляем листья
                //===============
                if (treeNode.left == null && treeNode.right == null && treeNode1.left == treeNode) {
                    treeNode1.left = null;
                    //System.out.println("Node deleted." + treeNode.node.id);
                    // return;
                } else {
                    if (treeNode.left == null && treeNode.right == null && treeNode1.right == treeNode) {
                        treeNode1.right = null;
                    } else {
                        if (treeNode == treeNodeRoot) {
                            treeNodeX = treeNode;
                            if (left >= right && treeNode.left != null) {
                                treeNode = getDeepNodeLeft(treeNode.left, prev, treeNodeX);
                                treeNode.left = treeNodeX.left;
                                treeNode.right = treeNodeX.right;
                                treeNodeRoot = treeNode;
                            } else {
                                treeNode = getDeepNodeRight(treeNode.right, prev, treeNodeX);
                                treeNode.left = treeNodeX.left;
                                treeNode.right = treeNodeX.right;
                                treeNodeRoot = treeNode;
                            }
                        } else {
                            if (treeNode != treeNodeRoot) {
                                boolean way;
                                if (treeNode1.left == treeNode) {
                                    way = true;
                                } else {
                                    way = false;
                                }
                                treeNodeX = treeNode;
                                if (left >= right && treeNode.left != null) {
                                    treeNode = getDeepNodeLeft(treeNode.left, prev, treeNodeX);
                                    treeNode.left = treeNodeX.left;
                                    treeNode.right = treeNodeX.right;

                                    if (!way) {
                                        treeNode1.right = treeNode;
                                    } else {
                                        treeNode1.left = treeNode;
                                    }
                                } else {
                                    treeNode = getDeepNodeRight(treeNode.right, prev, treeNodeX);
                                    treeNode.left = treeNodeX.left;
                                    treeNode.right = treeNodeX.right;
                                    if (!way) {
                                        treeNode1.right = treeNode;
                                    } else {
                                        treeNode1.left = treeNode;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //  нормализация дерева  //
        if (treeNodeRoot != null) {
            makeLeafLeft(treeNodeRoot.left);
        }
        if (treeNodeRoot != null) {
            makeLeafLeft(treeNodeRoot.right);
        }
    } // end method

    //============================================================================//
//  -------------------------------- //
//  МЕТОД ДЛЯ ДОБАВЛЕНИЯ УЗЛА        //    
//  -------------------------------- //         
    void insertNode(Double dW, Double idW) {
        boolean isTrue = true;
        WordNode newNode = new WordNode(dW, idW);
        TreeNode treeNode = treeNodeRoot;
        TreeNode treeNodeNew = new TreeNode();
        treeNodeNew.node = newNode;
        treeNodeNew.left = null;
        treeNodeNew.right = null;
        if (treeNode == null) {
            treeNodeRoot = treeNodeNew;
            return;
        }
        while (isTrue) {
            if (Objects.equals(treeNode.node.word, dW)) {
                treeNode.node.id.add(idW);
                isTrue = false;
            }
            if (treeNode.node.word > dW) {
                if (treeNode.left != null) {
                    treeNode = treeNode.left;
                } else {
                    treeNode.left = treeNodeNew;
                    isTrue = false;
                }
            }
            if (treeNode.node.word < dW) {
                if (treeNode.right != null) {
                    treeNode = treeNode.right;
                } else {
                    treeNode.right = treeNodeNew;
                    isTrue = false;
                }
            }
        }
    }  // end insertNode

// ==================================//
//  -------------------------------- //
//  МЕТОД ДЛЯ ПОЛУЧЕНИЯ ДЛИНЫ ВЕТОК //    
//  -------------------------------- //     
    public int deep(TreeNode treeNode, int count) {

        if (treeNode == null) {
            return count++;
        }
        count++;
        if (treeNode.left != null) {
            count = deep(treeNode.left, count);
        } else {
            if (treeNode.right != null) {
                count = deep(treeNode.right, count);
            }
        }
        return count;
    }  // end deep

    //  ---------------------------------- //
    //  МЕТОДЫ ДЛЯ ПОЛУЧЕНИЯ УЗЛА          //    
    //  ---------------------------------- // 
    private TreeNode getDeepNodeLeft(TreeNode treeNode, TreeNode prev, TreeNode treeNodeX) {

        while (treeNode.right != null) {
            prev = treeNode;
            treeNode = treeNode.right;
        }
        if (prev != null) {
            prev.right = treeNode.left;
        } else {
            treeNodeX.left = treeNode.left;
        }
        return treeNode;
    } // end method

    private TreeNode getDeepNodeRight(TreeNode treeNode, TreeNode prev, TreeNode treeNodeX) {

        while (treeNode.left != null) {
            prev = treeNode;
            treeNode = treeNode.left;
        }
        if (prev != null) {
            prev.left = treeNode.right;
        } else {
            treeNodeX.right = treeNode.right;
        }
        return treeNode;
    } // end method
//============================================================================//
//============================================================================//
//  ------------------------------ //
//  МЕТОД БАЛАНСИРОВКИ ДЕРЕВА      //    
//  ------------------------------ //  

    void treeBalance() {

        TreeNode treeNode;
        TreeNode treeNodeX = treeNodeRoot;
        TreeNode prev = null;
        int left = deep(treeNodeRoot.left, 0);
        int right = deep(treeNodeRoot.right, 0);
        if ((left - right) >= 2) {
            treeNode = treeNodeRoot.left;
            treeNode = getDeepNodeLeft(treeNode, prev, treeNodeX);
            treeNode.left = treeNodeX.left;
            treeNode.right = treeNodeX;
            treeNodeX.left = null;
            treeNodeRoot = treeNode;
        } else {
            if ((right - left) >= 2) {
                treeNode = treeNodeRoot.right;
                treeNode = getDeepNodeRight(treeNode, prev, treeNodeX);
                treeNode.right = treeNodeX.right;
                treeNode.left = treeNodeX;
                treeNodeX.right = null;
                treeNodeRoot = treeNode;
            }
        }
        if (possibilityBalance()) {
            treeBalance();
        }
    } // end method

    boolean possibilityBalance() {
        boolean possible = false;
        int left = deep(treeNodeRoot.left, 0);
        int right = deep(treeNodeRoot.right, 0);

        if ((left - right) >= 2 || (right - left) >= 2) {
            possible = true;
        }
        return possible;
    }  // end method

//============================================================================//
//============================================================================//
//  --------------------------------- //
//  МЕТОД ДЛЯ СОЗДАНИЯ ЛИСТЬЕВ ДЕРЕВА // 
//    (нормализация ветвей дерева)    //    
//  --------------------------------- //  
    void makeLeafLeft(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode nextNode;
        TreeNode prevNode;
        if (treeNode.left != null) {
            nextNode = treeNode.left;
        } else {
            return;
        }
        if (nextNode.left != null) {
            prevNode = nextNode.left;
        } else {
            return;
        }
        if (treeNode.right == null && nextNode.right == null && prevNode.right == null) {
            treeNode.left = prevNode;
            prevNode.right = nextNode;
            nextNode.left = null;
            nextNode.right = null;
        }
        if (prevNode.left != null) {
            makeLeafLeft(treeNode.left);
        }
        if (prevNode.right != null) {
            makeLeafRight(treeNode.right);
        }
    } // end method

    void makeLeafRight(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode nextNode;
        TreeNode prevNode;
        if (treeNode.right != null) {
            nextNode = treeNode.right;
        } else {
            return;
        }
        if (nextNode.right != null) {
            prevNode = nextNode.right;
        } else {
            return;
        }
        if (treeNode.left == null && nextNode.left == null && prevNode.left == null) {
            treeNode.right = prevNode;
            prevNode.left = nextNode;
            nextNode.left = null;
            nextNode.right = null;
        }
        if (prevNode.left != null) {
            makeLeafLeft(prevNode.left);
        }
        if (prevNode.right != null) {
            makeLeafRight(prevNode.right);
        }
    }  // end method

//============================================================================//
//============================================================================//
} // end  class TreeOfNodes
