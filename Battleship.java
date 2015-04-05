public class Battleship {

	public static void main(String[] args){
		//input
		//boardA holds own ships, boardB shows hits & misses
		//Player 1 places ships
		System.out.println("BATTLESHIP");
		System.out.println("Welcome Player 1");
		int option;
		do{
			System.out.println("Enter 1 to play against computer...");
			System.out.println("Enter 2 for two-player game...");
			option=IO.readInt();
			if (option>2||option<1)
				System.out.println("Invalid");
		}while(option>2||option<1);
		
		Player Player1=new Player_ac1013();
		Player Player2 = null;
		
		if (option==1){
			option=0;
			do{
				System.out.println("Enter 1 for easy...");
				System.out.println("Enter 2 for difficult...");
//				System.out.println("Enter 3 for impossible...");
				option=IO.readInt();
				if(option>2||option<1)
					System.out.println("Invalid");
			}while (option>2||option<1);
			if (option==1)
				Player2=new AIPlayer_ac1013();
			else if (option==2)
				Player2=new DifficultPlayer_ac1013();
//			else
//				Player2=new ImpossiblePlayer_ac1013();
		}
		else{
			Player2=new Player_ac1013();
		}
		
		Player1.initBoard();
		Player2.initBoard();
		System.out.println("PLAYER 1: place ships!");
		Player1.placeShips();
		erase();
		System.out.println("PLAYER 2: place ships!");
		Player2.placeShips();
		erase();
		
		char result;
		while (Player1.lost()==false&&Player2.lost()==false){
			System.out.println("PLAYER 1: fire!");
			Player1.printBoard();
			do{
				Coordinate f=Player1.fire();
				result = Player2.fireUpon(f);
				if (result!='!'){
					Player1.update(f, result);
					Player1.printBoard();
				}
				Player1.fireResult(result);
				if (result=='M'){
					System.out.println("Miss!");
					break;
				}
				else if (Player2.lost()!=true){
					System.out.println("Fire again...");
				}
				if (Player2.lost()==true)
					break;
			}while (result!='M');
			if (Player2.lost()==true){
				System.out.println("PLAYER 1 WINS");
				break;
			}
			erase();
			System.out.println("PLAYER 2: fire!");
			Player2.printBoard();
			do{
				Coordinate f=Player2.fire();
				result=Player1.fireUpon(f);
				if (result!='!'){
					Player2.update(f, result);
					Player2.printBoard();
				}
				Player2.fireResult(result);
				if (result=='M'){
					System.out.println("Miss!");
					break;
				}
				else if (Player1.lost()!=true)
					System.out.println("Fire again...");
				if (Player1.lost()==true)
					break;
			}while (result!='M');
			if (Player1.lost()==true){
				System.out.println("PLAYER 2 WINS");
				break;
			}
			erase();
		}
		System.out.println("GAME OVER!!!!!!!!!!!!");
		
	}
	public static void erase(){
		System.out.println("Enter to continue");
		String enter=IO.readString();
		enter="";
		for (int i=0; i<100; i++){
			System.out.println(enter);
		}
	}

}
