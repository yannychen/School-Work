import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * huffmanCoding class- main class
 * @author Yan
 *
 */
public class huffmanCoding{
/**
 * 	main method- read input file and outputs 5 different output files
 * 			   - Creates huffmanLinkedList and listBinTreeNode objects
 */
	public static void main(String[] args) throws FileNotFoundException{
		int prob=0;
		String chStr = "";
		listBinTreeNode spot;
		listBinTreeNode newNode;
		huffmanLinkedList list = new huffmanLinkedList();
		try{
			File inputFile= new File(args[0]);
			Scanner scan= new Scanner(inputFile);
			
			while(scan.hasNext()){
				 chStr=scan.next();
				 prob=scan.nextInt();
				 spot = list.findSpot(prob);
		         newNode = new listBinTreeNode(chStr, prob, null,null,null);
		         list.listInsert(spot, newNode);   
				 }          	
			scan.close();
		}
		catch(FileNotFoundException e){
			System.out.println("File not found");			
		}
			
		   PrintWriter outFile5= new PrintWriter(args[5]);
		   list.printList(outFile5);
		   outFile5.close();
		   
		   HuffmanBinaryTree tree= new HuffmanBinaryTree();
		   listBinTreeNode root= tree.constructHuffmanBinTree(list);
		   
		   PrintWriter outFile1= new PrintWriter(args[1]);
		   tree.constructCharCode(root, "", outFile1);
		   outFile1.close();
		   
		   PrintWriter outFile2= new PrintWriter(args[2]);
		   outFile2.println("preOrderTraversal- ");
		   tree.preOrderTraversal(root, outFile2);
		   outFile2.close();
		   
		   PrintWriter outFile3= new PrintWriter(args[3]);
		   outFile3.println("inOrderTraversal- ");
		   tree.inOrderTraversal(root, outFile3);
		   outFile3.close();
		   
		   PrintWriter outFile4= new PrintWriter(args[4]);
		   outFile4.println("postOrderTraversal- ");
		   tree.postOrderTraversal(root, outFile4);
		   outFile4.close();
	}
}

import java.io.PrintWriter;

/**
 * huffmanLinkedList class- to create linked list
 * @author Yan
 *
 */
class huffmanLinkedList {

        listBinTreeNode listHead;
      /**
       * Constructor-create listHead node
       */
        public huffmanLinkedList() {
                listBinTreeNode node;
                node = new listBinTreeNode();
                listHead = node;
        }
        /**
         * Check if linked list is empty
         * @return true if list is empty, false if not
         */
        boolean isEmpty() {
                if (listHead.next != null)
                        return false;
                else
                        return true;
        }

      /**
       * findSpot method- finds the previous spot in the linked list where 
       * the current node with the prob(data) can be inserted after to 
       * @param prob is used to order the list in ascending order 
       * @return previous spot
       */
        listBinTreeNode findSpot(int prob) {
                listBinTreeNode spot;
                listBinTreeNode prevSpot;
                spot = listHead.next;
                prevSpot = listHead;  
                if (isEmpty()) {
                        return prevSpot;
                 }
               
                if (spot != null) {
                        if (spot.prob>prob) {
                                return prevSpot;
                        }
                        while (spot != null && spot.prob < prob) {
                                spot = spot.next;
                                prevSpot = prevSpot.next;
                        }
                }
                return prevSpot;
        }

        /**
         * listInsert method- insert a new node into the list
         * @param spot- the previous node spot
         * @param newNode- new node to be inserted after the previous spot
         */
        void listInsert(listBinTreeNode spot, listBinTreeNode newNode) {
                newNode.next = spot.next;
                spot.next = newNode; 
        }

        /**
         * printList- debugging purpose, to make sure the linked list is in correct form
         * @param output- print to output file for debugging 
         */
        void printList(PrintWriter output) {
                listBinTreeNode current;
                listBinTreeNode nextNode;
                output.print("debugging for linkedlist--- \n" + "listHead-->");
                current = listHead;
                nextNode = listHead;     
                while (current != null) {
                        nextNode = nextNode.next;
                        if (current.next != null) {
                                output.print( "(" + current.chStr + ","+ current.prob
                                        + "," + nextNode.chStr + ")-->" );
                        }
                        else 
                                output.print( "(" + current.chStr + "," + current.prob + ',' + "null)"+ "-->"+ "NULL");
                                current = current.next;
                }
        }
}

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

