#include <iostream>
#include <fstream>
#include <cmath>

using namespace std;

/*
*point class- point's properties- x: x-coordinate, y: y-coordinate, point's label
*/
class point{
public:
	int x, y, label;
	double distance;

/**
*printPoint- prints point's x, y and label
*/
void printPoint(point p, int counter){
		cout<<"point "<< counter<< " - "<< p.x<< " "<< p.y<< " "<< p.label<< endl;
	}
};

/*
*Kmean class- creates kmean clustering
*/
class Kmean{
	int K, numRow, numCol, num1, num2, numPts, groupNum;
	int count=0;
	point p;
	point *pointSet;

	int **imageArray;

	/*
	*xyCoord struct- contains variable x, y and label
	*/
	struct xyCoord{
		int x, y;
		int label;
	};

	xyCoord *Kcentroids;

public:
	/*
	*Kmean constructor- opens file and reads in input, and intialize 2D imageArray
	*/
Kmean(char* file){
	ifstream inFile;
	inFile.open(file);

	inFile>>num1>>num2;
	K=num1;
	numPts=num2;

	Kcentroids= new xyCoord[K];
	for(int i=0; i<K; i++){
		xyCoord c={0,0,i+1};
		Kcentroids[i]= c;
	}

	pointSet= new point[numPts];

	inFile>>num1>>num2;
	numRow=num1;
	numCol=num2;
	groupNum=numRow/K;

	imageArray=new int*[numRow];
	for(int i=0; i<numRow; i++){
		imageArray[i]=new int[numCol];
	}

	while(inFile>>num1>>num2){
		loadPointSet(num1, num2);
	}
	inFile.close();
}

	/*
	*loadPointSet- loads each point's x-coordinate, y-coordinate and assign its label
	*/
	void loadPointSet(int row, int col){
		p.x=row;
		p.y=col;
		
		assignLabel();
		mapPoint2Image(row, col);
		pointSet[count++]=p;
		p.printPoint(p, count);
	}

	/*
	*assignLabel- assigns point's label
	*/
	void assignLabel(){
			for(int i=0; i<K; i++){
				if(p.x>=groupNum*i && p.x<= groupNum*(i+1))
					p.label=i+1;
			}	
	}

	/*
	*mapPoint2Image- store point's label into imageArray
	*/
	void mapPoint2Image(int r, int c){
		imageArray[r][c]=p.label;
	}

	/*
	*KmeanClustering- computes every point to k centroids and resign point's label 
	*accoridng to the nearest centriod distance
	*/
	void kmeanClustering(ostream &output1, ostream &output2){
		int changeLabel=0;
		do{
		
			int pointCount1=0, pointCount2=0, pointCount3=0, pointCount4=0;
			changeLabel=0;
			int row=0, col=0;
			for(int i=0; i<numPts; i++){
				if(pointSet[i].label==1){
					Kcentroids[0].x+=pointSet[i].x;
					Kcentroids[0].y+=pointSet[i].y;
					pointCount1++;
					
				}
				else if(pointSet[i].label==2){
					Kcentroids[1].x+=pointSet[i].x;
					Kcentroids[1].y+=pointSet[i].y;
					pointCount2++;
				}
				else if(pointSet[i].label==3){
					Kcentroids[2].x+=pointSet[i].x;
					Kcentroids[2].y+=pointSet[i].y;
					pointCount3++;
				}
				else{
					Kcentroids[3].x+=pointSet[i].x;
					Kcentroids[3].y+=pointSet[i].y;
					pointCount4++;
				}
			}


			Kcentroids[0].x= Kcentroids[0].x/pointCount1;
			Kcentroids[0].y= Kcentroids[0].y/pointCount1;

			Kcentroids[1].x= Kcentroids[1].x/pointCount2;
			Kcentroids[1].y= Kcentroids[1].y/pointCount2;

			Kcentroids[2].x= Kcentroids[2].x/pointCount3;
			Kcentroids[2].y= Kcentroids[2].y/pointCount3;

			Kcentroids[3].x= Kcentroids[3].x/pointCount4;
			Kcentroids[3].y= Kcentroids[3].y/pointCount4;

			prettyPrint(output2);
			double minDistance=0.0;

			for(int j=0; j<numPts; j++){
				int centerPoint=0;
				for(int a=0; a<K;a++){
					int xs= pointSet[j].x- Kcentroids[a].x;
					int ys= pointSet[j].y- Kcentroids[a].y;
					p.distance= sqrt((xs*xs)+(ys*ys));
					if(a==0){
						minDistance=p.distance;
						centerPoint=a+1;
					}
					if(minDistance>p.distance){
						minDistance=p.distance;
						centerPoint=a+1;
					}
				}
					if(pointSet[j].label!=centerPoint){
						pointSet[j].label=centerPoint;
						row=pointSet[j].x;
						col=pointSet[j].y;
						imageArray[row][col]=pointSet[j].label;
						changeLabel++;
					}
			}

			cout<<"change  " <<changeLabel<< endl;

		}while(changeLabel>0);

		output1<<K<<endl;
		output1<<numPts<<endl;
		output1<<numRow<<" "<< numCol<<endl;

		for(int a=0; a<numPts; a++){
			output1<<pointSet[a].x<<" "<<pointSet[a].y<<" "<<pointSet[a].label<<endl;
		}
	 }  

	 /*
	 *prettyPrint - prints imageArray to output file
	 */
	 void prettyPrint(ostream &outFile2){
	 	outFile2<<*this;
	 }
	 
	 /*
	 *friend function- prints imageArray to ouput file
	 */
	 friend ostream& operator<<(ostream & os, const Kmean &k){	 	
	 	int row= k.numRow;
	 	int c=k.numCol;
	 	for(int i=0; i<row; i++){
	 		for(int j=0; j<c; j++){
	 			bool printC=false;
	 			for(int p=0; p<k.K; p++){
	 				if(k.Kcentroids[p].x==i && k.Kcentroids[p].y==j){
	 					os<<(char)(k.Kcentroids[p].label+'A'-1);
	 					printC=true;
	 					break;
	 				}
	 			}
		 		if(printC) {
		 			continue;
		 		}
	 			if(k.imageArray[i][j]>0){
	 				os<< k.imageArray[i][j];
	 			}
	 			else 
	 				os<<" ";	
	 		}
	 		os<<endl;
	 	}
	 	return os;
	 }
};

/*
*main- opens and closes files and calls Kmean class constructor
*/
int main(int args, char* argv[]){

	Kmean K(argv[1]);
	ofstream output1, output2;
	output1.open(argv[2]);
	output2.open(argv[3]);
	K.kmeanClustering(output1, output2);

	output1.close();
	output2.close();
	return 0;
}

