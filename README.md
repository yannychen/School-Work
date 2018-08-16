School-Work

HuffmanCoding project(Java):
  Given a text file, compress the file by creating least bit for most frequent appeared characters and more bit for least frequent appeared characters.

Huffman Coding Algorithm:
Step 0: compute character counting/ probability
Step 1: construct a linked list in ascending order w.r.t count/probability
Step 2: construct Huffman Binary Tree
Step 3: construct Huffman Code Table
Step 4: compression by encoding the table

Construct Huffman Binary Tree Algorithm:
Step 0: oldListHead <- create a dummy listBinTreeNode and let oldListHead points to the dummy node
Step 2:oldListHead.next <- listHead.next 
Step 3:
3.1- newNode <- create a listBinTreeNode
newNode’s prob <- the sum of prob of the first and second node of the list        
newNode’s chStr <- concatenate chStr of the first node and chStr of the second node in the list
       newNode’s left <- the first node of the list
       newNode’s right <- the second node of the list
       listHead <- the third node of the list 
       printNode (newNode) to outFile5 
3.2: spot <- findSpot(newNode’prob)
       listInsert (spot, newNode)  // inserting newNode between spot and spot.next.
       printList to outFile5 //for debugging purpose
3.3: repeat step 3.1 – 3.2 until the list only has one node left which is the newNode
3.4: Root <- newNode


