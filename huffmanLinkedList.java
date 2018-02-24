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