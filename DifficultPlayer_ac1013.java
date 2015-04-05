public class DifficultPlayer_ac1013 implements Player{
	private Coordinate guess;
	char[][] BoardA = new char[10][10];
	char[][] BoardB = new char[10][10];
	
	//Implement your methods here:

	public char fireUpon(Coordinate x){
		char code=this.BoardA[x.x-1][x.y-1];
		if (code!='~'){
			this.BoardA[x.x-1][x.y-1]='!';
			return code;
		}
		else{
			this.BoardA[x.x-1][x.y-1]='!';
			return 'M';
		}
	}
	
	public void update(Coordinate x, char c){
		BoardB[x.x-1][x.y-1]=c;
	}

//	strategy: check if there are nearby misses within one coordinate (ignoring perimeter) and guess again if there are nearby misses.
	public Coordinate fire(){
		int x,y;
		do{
			x=(int) (Math.random()*100);
		} while (x>10||x<1);
		do{
			y=(int) (Math.random()*100);
		} while (y>10||y<1);
		if ((x!=10&&y!=10&&x!=1&&y!=1)&&(BoardB[x+1][y]!='M'||BoardB[x+1][y+1]!='M'||BoardB[x+1][y-1]!='M'||BoardB[x][y-1]!='M'||BoardB[x][y+1]!='M'||BoardB[x-1][y+1]!='M'||BoardB[x-1][y-1]!='M'||BoardB[x-1][y]!='M')){
			guess=new Coordinate(x,y);
			return guess;
		}
		else{
			do{
				x=(int) (Math.random()*100);
			} while (x>10||x<1);
			do{
				y=(int) (Math.random()*100);
			} while (y>10||y<1);
			guess=new Coordinate(x,y);
		}
		return guess;
	}
	
	public void fireResult(char result){
		if (result=='!'){
			printBoard();
			System.out.println("YOU ALREADY GUESSED THIS COORDINATE");
		}
		IO.outputCharAnswer(result);
	}
//	strategy: place ships away from center
	public void placeShips(){
		int number=0;
		int number2=0;
		int xA=0;
		int yA=0;
		String direction;
		boolean conditions=false;
		String placement;
		boolean repeat=false;
		char ship=' ';
		int s;
		int param1=0;
		int param2=0;
		int size=0;
		for (s=0; s<5; s++){
			//AIRCRAFT CARRIER 0
			if (s==0){
				ship='A';
				param1=4;
				param2=7;
				size=6;
			}
			
			//BATTLESHIP 1
			else if (s==1){
				ship='B';
				param1=3;
				param2=8;
				size=5;
			}
			
			//SUBMARINE 2
			else if (s==2){
				ship='S';
				param1=2;
				param2=9;
				size=4;
			}
			
			//DESTROYER 3
			else if (s==3){
				ship='D';
				param1=2;
				param2=9;
				size=4;
			}
			
			//PATROL BOAT 4
			else if (s==4){
				ship='P';
				param1=1;
				param2=10;
				size=3;
			}
			
			do{
				number=0;
				number2=0;
				do{
					number=(int)(Math.random()*100);
					number2=(int)(Math.random()*100);
				} while ((number>10||number<1)||(number2>10||number2<1));
				double a= Math.abs(number-5.5);
				double b= Math.abs(number2-5.5);
				if (a>b){
					xA=number;
				}
				else{
					xA=number2;
				}
				number=0;
				number2=0;
				do{
					number=(int)(Math.random()*100);
					number2=(int)(Math.random()*100);
				} while ((number>10||number<1)||(number2>10||number2<1));
				a= Math.abs(number-5.5);
				b= Math.abs(number2-5.5);
				if (a>b){
					yA=number;
				}
				else{
					yA=number2;
				}
				if (this.BoardA[xA-1][yA-1]!='~'){
//					Cannot start vessel on previous vessels...
					repeat=true;
					continue;
				}
				else{
					direction="";
//					Place ship 'down', 'up', 'left', or 'right'?
					number=0;
					do{
						number=(int)(Math.random()*10);
					} while (number>4||number<1);
					if (number==1)
						direction="left";
					else if (number==2)
						direction="right";
					else if (number==3)
						direction="up";
					else if (number==4)
						direction="down";
					conditions=(direction.compareTo("left")!=0&&direction.compareTo("right")!=0&&direction.compareTo("up")!=0&&direction.compareTo("down")!=0);
					if (conditions!=true){
						placement=direction;
						if (placement.compareTo("left")==0){
							if (xA>param1){
								for (int i=(xA-1); i>(xA-size); i--){
									if (this.BoardA[i][yA-1]=='~'){
										repeat=false;
										continue;
									}
									else{
										repeat=true;
										break;
									}
								}
								if (repeat==false){
									for (int i=(xA-1); i>(xA-size); i--){
										BoardA[i][yA-1]=ship;
									}
								}
							}
							else{
								repeat=true;
								continue;
							}
						}
						else if (placement.compareTo("right")==0){
							if (xA<param2){
								for (int i=(xA-1); i<(xA+param1); i++){
									if (this.BoardA[i][yA-1]=='~'){
										repeat=false;
										continue;
									}
									else{
										repeat=true;
										break;
									}
								}
								if (repeat==false){
									for (int i=(xA-1); i<(xA+param1); i++)
										this.BoardA[i][yA-1]=ship;
								}
							}
							else{
								repeat=true;
								continue;
							}
						}
						else if (placement.compareTo("up")==0){
							if (yA>param1){
								for (int i=(yA-1); i>(yA-size); i--){
									if (this.BoardA[xA-1][i]=='~'){
										repeat=false;
										continue;
									}
									else{
										repeat=true;
										break;
									}
								}
								if (repeat==false){
									for (int i=(yA-1); i>(yA-size); i--)
										this.BoardA[xA-1][i]=ship;
								}
							}
							else{
								repeat=true;
								continue;
							}
						}
						else if (placement.compareTo("down")==0){
							if (yA<param2){
								for (int i=(yA-1); i<(yA+param1); i++){
									if (this.BoardA[xA-1][i]=='~'){
										repeat=false;
										continue;
									}
									else{
										repeat=true;
										break;
									}
								}
								if (repeat==false){
									for (int i=(yA-1); i<(yA+param1); i++)
										this.BoardA[xA-1][i]=ship;
								}
							}
							else{
								repeat=true;
								continue;
							}
						}
					}
					
				}

				
			}while (conditions==true||repeat==true);
		}
	}	
	
	public boolean lost(){
		int count=0;
		for (int i=0; i<10; i++){
			for (int x=0; x<10; x++){
				char e= this.BoardA[i][x];
				if (e=='!'){
					count++;
				}
			}
		}
		if (count==17)
			return true;
		return false;
	}
	
	public void printBoard(){
		char[][] newBoard=new char[11][11];
		for (int titleRow=0;titleRow<11;titleRow++)
			newBoard[titleRow][0]=(char)(titleRow+48);
		for (int titleColumn=1;titleColumn<11;titleColumn++){
			newBoard[0][titleColumn]=(char)(titleColumn+48);
		}
		for (int newRow=1; newRow<11; newRow++){
			for (int newColumn=1; newColumn<11; newColumn++){
				newBoard[newRow][newColumn]=this.BoardB[newRow-1][newColumn-1];
			}
		}
		
		//printing first row
		for (int item=0; item<10; item++){
			String row=newBoard[item][0]+"\t";
				System.out.print(row);
		}
		System.out.print("10"+"\t");
		System.out.println();
		//printing next rows
		int column=1;
		while (column<10){
			for (int item=0; item<11; item++){
				String row=newBoard[item][column]+"\t";
				System.out.print(row);
			}
			System.out.println();
			column+=1;
		}
		//printing last row
		System.out.print("10"+"\t");
		for (int i=1; i<11; i++){
			String endingRow=newBoard[i][10]+"\t";
			System.out.print(endingRow);
		}
		System.out.println();
		System.out.println();
	}
	public void show(){
		char[][] newBoard=new char[11][11];
		for (int titleRow=0;titleRow<11;titleRow++)
			newBoard[titleRow][0]=(char)(titleRow+48);
		for (int titleColumn=1;titleColumn<11;titleColumn++){
			newBoard[0][titleColumn]=(char)(titleColumn+48);
		}
		for (int newRow=1; newRow<11; newRow++){
			for (int newColumn=1; newColumn<11; newColumn++){
				newBoard[newRow][newColumn]=this.BoardA[newRow-1][newColumn-1];
			}
		}
		
		//printing first row
		for (int item=0; item<10; item++){
			String row=newBoard[item][0]+"\t";
				System.out.print(row);
		}
		System.out.print("10"+"\t");
		System.out.println();
		//printing next rows
		int column=1;
		while (column<10){
			for (int item=0; item<11; item++){
				String row=newBoard[item][column]+"\t";
				System.out.print(row);
			}
			System.out.println();
			column+=1;
		}
		//printing last row
		System.out.print("10"+"\t");
		for (int i=1; i<11; i++){
			String endingRow=newBoard[i][10]+"\t";
			System.out.print(endingRow);
		}
		System.out.println();
		System.out.println();
	}	
	public void initBoard(){
		//initialize a board to all ~characters
		System.out.println("Mwhahahaa");
		for (int row=0; row<10; row++){
			for (int column=0; column<10; column++){
				BoardA[row][column]='~';
				BoardB[row][column]='~';
			}
		}
	}
}
