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
