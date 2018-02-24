import java.io.PrintWriter;

/**
 * ConstructHuffmanBinTree class- construct huffman binary tree and binary code 
 * for characters
 * @author Yan
 *
 */
public class HuffmanBinaryTree {

		        listBinTreeNode root;
		        
		        /**
		         * constructHuffmanBinTree method- construct huffman binary tree using linked list
		         * @param list- linked list
		         * @return the root of the linked list
		         */
		        listBinTreeNode constructHuffmanBinTree(huffmanLinkedList list) {
		                listBinTreeNode oldListHead;
		                listBinTreeNode spot;   
		                listBinTreeNode node;
		                listBinTreeNode listHead;
		                node = new listBinTreeNode();
		                oldListHead = node;
		                listHead = list.listHead.next;
		                oldListHead.next = listHead;
		                
		                while (listHead != null&& listHead.next != null) {
	                        listBinTreeNode newNode = new listBinTreeNode();
	                        newNode.chStr = listHead.chStr + listHead.next.chStr;
	                        newNode.prob = listHead.prob + listHead.next.prob;
	                        newNode.next = null;
	                        newNode.left = listHead;
	                        newNode.right = listHead.next;
	                        newNode.printNode(newNode);
	                        spot = list.findSpot(newNode.prob);
	                        list.listInsert(spot, newNode);
	                        listHead = listHead.next.next;
	                }

	                root = listHead;
	                return root;
		        }

		        /**
		         * isLeaf method- check if current node has children 
		         * @param r- current node
		         * @return true if the current node is a leaf node, else return false
		         */
		        boolean isLeaf(listBinTreeNode r) {
		                return r.left == null && r.right == null;
		        }

		        /**
		         * constructCharCode method- construct binary code for nodes
		         * @param T- root of the linked list
		         * @param code- empty string
		         * @param output- print binary code result to output file
		         */
		        void constructCharCode(listBinTreeNode T, String code, PrintWriter output) {
		                if (T == null) {
		                        return;
		                }
		                else if (isLeaf(T)) {
		                	output.println( T.chStr + "  " + code +"\n");
		                }
		                else
		                constructCharCode(T.left, code + "0", output);
		                constructCharCode(T.right, code + "1", output);
		        }
		        /**
		         * preOrderTraversal method- prints Huffman Binary Tree in preOrder 
		         * @param T- root of the binary tree
		         * @param output- prints to output file
		         */
		        void preOrderTraversal(listBinTreeNode T, PrintWriter output) {
		                if (T == null) {
		                        return;
		                }
		                else {
		                        output.print(T.chStr + " " + T.prob+ " ");
		                        if(T.next!=null)
		                                output.print("next- "+ T.next.chStr);
		                        else   
		                                output.print (" T's next- null");
		                
		                        if (T.left != null)
		                                output.print(" T's left- "+ T.left.chStr);
		                        else
		                                output.println(" T.Left- null");
		                        if (T.right != null)
		                                output.println(" T's right- "+ T.right.chStr);
		                        else
		                                output.println( " T's right-null");
		                        
		                        preOrderTraversal(T.left, output);
		                        preOrderTraversal(T.right, output);
		                }
		     }
		        
		        /**
		         * inOrderTraversal method- prints Huffman Binary Tree in inOrder 
		         * @param T- the root of binary tree	
		         * @param output- prints to output file
		         */
		        void inOrderTraversal(listBinTreeNode T, PrintWriter output) {
		                if (T == null) {
		                       return;
		                }
		                else {
		                        inOrderTraversal(T.left, output);
		                        output.print(T.chStr + " " + T.prob+" ");
		                        if(T.next!=null)
		                                output.print("next- " +T.next.chStr);
		                        else
		                                output.print(" T's next- null");
		                                
		                        if (T.left != null)
		                                output.print(" T's left- "+ T.left.chStr);
		                        else
		                                output.print( " T.Left- null");
		                        if (T.right != null)
		                                output.print(" T's right- "+ T.right.chStr);
		                        else
		                                output.print(" T's right-null");
		                 
		                
		                        inOrderTraversal(T.right, output);
		                }
		        }
		        
		        	/**
		        	 * postOrderTraversal method- prints Huffman Binary Tree in postOrder
		        	 * @param T- the root of binary tree
		        	 * @param output- prints to output file
		        	 */
		                void postOrderTraversal(listBinTreeNode T, PrintWriter output){
		                        if (T == null) {
		                                return;
		                        }
		                        else {
		                                postOrderTraversal(T.left, output);
		                                postOrderTraversal(T.right, output);
		                                output.print( T.chStr + " " + T.prob+ " ");
		                                if(T.next!=null)
		                                        output.print("next- " +T.next.chStr);
		                                else
		                                        output.print(" T's next- null");

		                                if (T.left != null)
		                                        output.print( " T's left- "+ T.left.chStr);
		                                else
		                                        output.print(" T.Left- null");
		                                if (T.right != null)
		                                        output.println(" T's right- "+ T.right.chStr);
		                                else
		                                        output.println( " T's right-null");

		                         }
		                }
}
