## HuffmanCoding project (Java):
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



## K-Mean Clustering (Java):
   popular for cluster analysis in data mining. Given data points and k number of clusters, randomly partition data points into k groups and assign labels to points with 1-k depends on which cluster they are in. Compute centriods for each group, then compute minimum distance between each point to all the centroids. Resigns point's label to minimum centroid's label if its label is different, keep the same label otherwise. 

step 0:  

- inFile  Open the input file

- K(k groups), numRow, numCol numPts  get from inFile.

- imageArray  Dynamically allocate a 2-D arrays, size numRows X numCols.

- pointSet  Dynamically allocate the point set, size of numPts  

- Kcentroids[K]  Dynamically allocate the K centroids struct.
 	
Step 1: call loadPointSet 

Step 2: call assignLabel	   

Step 3: call mapPoint2Image 

Step 4: call displayImage // output to output-2

step 5: 5.1:  changeLabel  0

5.2:  Go thru the entire pointSet struct array to compute the centroids of each of the K clusters. Store the computed centroids in each Kcentroids[i], i from 1 to K.

step 6: 6.1: for each point, p, in the pointSet array compute the distance, dist(p,ci), from p to the centroids of each Kcentroids[i], i = 1 to K
	
	6.2: min_i <-- determine which dist(p,ci) is minimum		
        
	6.3: if min_i’s Label is not the same as p's Label change p's label to min_i’s label and increment changeLabel ++ 

step 7: repeat step 6 until all points in pointSet are processed.

Step 8: repeat step 3 to step 7 while changeLabel > 0 

Step 9: Output the info of pointSet to Output-1 file.

Step 10: close all files.

