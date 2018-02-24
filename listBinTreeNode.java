/**
 * listBinaryTreeNode class
 * @author Yan
 *
 */
class listBinTreeNode {  

        String chStr;
        int prob;
        listBinTreeNode next;
        listBinTreeNode left;
        listBinTreeNode right;
        
        /**
         * constructor- to initialize variables in the class
         */
        public listBinTreeNode() {
                chStr = "dummy";
                prob = 0; 
                next = null;
                left = null;
                right = null;
        }

        /**
         * constructor- assigns data to the class variables
         * @param s- strings with concatenated characters
         * @param p- number of count/probability of string
         * @param n- next node pointer of the current node
         * @param l- left child node pointer of the current node in HuffmanBinTree
         * @param r- right child node pointer of the current node in HuffmanBinTree
         */
        listBinTreeNode(String s, int p, listBinTreeNode n,listBinTreeNode l, listBinTreeNode r) {
                chStr = s;
                prob = p;
                next = n;
                left = l; 
                right = r;
        }

        /**
         * printNode method- prints current new node's next node and its left and right child node
         * in the HuffBinTree 
         * @param newNode- the current new node
         */
        void printNode(listBinTreeNode newNode) {
                if (newNode != null) {
                        System.out.print(newNode.chStr+ " "+ newNode.prob+ " newNode's next- null");
                        if (newNode.left != null) {   
                                System.out.print(" , Left-Child-" + newNode.left.chStr + "," + newNode.left.prob);
                        }
                        else System.out.print(" , Left-Child-null");
                        if (newNode.right != null) {
                                System.out.println(" , Right-Child-" + newNode.right.chStr + "," + newNode.right.prob);
                        }
                        else System.out.println(" , Right-Child-null");
                }
        }
}