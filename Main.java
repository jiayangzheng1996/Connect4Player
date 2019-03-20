import java.util.Scanner;
public class Main {
	
public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Connect 4 by Shidi Yin and Jiayang Zheng");
		while(true){
		System.out.println("Choose your game: ");
		System.out.println("1. 3*3*3 Connect 4;");
		System.out.println("2. 6*7*4 Connect 4;");
		System.out.println("3. Quit game.");
		int in = input.nextInt();
		if(in == 1){
			gameplay1(input);
		}
		else if(in == 2){
			System.out.println("Please select the agent that will play you: ");
			System.out.println("1. An agent that uses H-minimax algorithm;");
			System.out.println("2. An agent that uses alpha-beta pruning algorithm;");
			System.out.println("3. An agent that uses H-minimax with alpha-beta pruning algorithm.");
			in=input.nextInt();
			while (in!=1&&in!=2&&in!=3){
				System.out.println("Type 1, 2 or 3!");
				in = input.nextInt();
			}
			if(in==1){
				gameplay2(input);
			}
			else if(in == 2) {
				gameplay3(input);
			}
			else
				gameplay4(input);
		}
		else if(in == 3){
			System.out.println("See you!");
			break;
		}
		else
			System.out.println("Please choose valid option!");
		}
	}
	
	static void gameplay1(Scanner input){
		System.out.println("You are playing 3*3*3 Connect 4. Red(1) or Yellow(2) Red goes first.");
		int in = input.nextInt();
		int enemymove = (in > 1)? -1: 1;
		int mymove = (in > 1)? 1: -1;
		GameBoard board = new GameBoard(3,3,3);
		Connect4Player ai = new Connect4Player(3,3,3);
		
		if(enemymove == 1){
			board.printBoard();
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			board.drop(in, 1);
			System.out.println();
			board.printBoard();
			System.out.println();
		}
		while(true){
			State S = new State(mymove,mymove);
			S.setBoard(board.copyboard());
			System.out.println("I am thinking...");
			System.out.println();
			double start = System.currentTimeMillis();
			int myturn = ai.minimax(S).getColumn();
			board.drop(myturn, mymove);
			double time = System.currentTimeMillis()-start;
			System.out.println("I'm done!");
			board.printBoard();
			System.out.println("I visited "+ai.getStatecount()+" nodes in " + time/1000 +" secs!");
			System.out.println("My best move is: "+S.mycolor+"@"+(myturn+1)+".");
			System.out.println();
			if(board.checkWins(mymove)){
				System.out.println("I won!");
				break;
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
				break;
			}
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			while(board.checkFull(in)){
				System.out.println("That column is full!");
				System.out.println("Please rechoose a column: ");
				in = input.nextInt()-1;
			}
			board.drop(in, enemymove);
			System.out.println();
			if(board.checkWins(enemymove)){
				System.out.println("You won! You have bested me!");
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
			}
		}
	}
	
	static void gameplay2(Scanner input){
		System.out.println("You are playing 6*7*4 Connect 4. Red(1) or Yellow(2) Red goes first.");
		int in = input.nextInt();
		int enemymove = (in > 1)? -1: 1;
		int mymove = (in > 1)? 1: -1;
		GameBoard board = new GameBoard(6,7,4);
		Connect4Player ai = new Connect4Player(6,7,4);
		
		if(enemymove == 1){
			board.printBoard();
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			board.drop(in, 1);
			System.out.println();
			board.printBoard();
			System.out.println();
		}
		while(true){
			State S = new State(mymove,mymove);
			S.setBoard(board.copyboard());
			System.out.println("I am thinking...");
			System.out.println();
			double start = System.currentTimeMillis();
			int myturn = ai.h_minimax(S,5).getColumn();
			board.drop(myturn, mymove);
			double time = System.currentTimeMillis()-start;
			board.printBoard();
			System.out.println("I'm done!");
			System.out.println("I visited "+ai.getStatecount()+" nodes in " + time/1000 +" secs!");
			System.out.println("My best move is: "+S.mycolor+"@"+(myturn+1)+".");
			System.out.println();
			if(board.checkWins(mymove)){
				System.out.println("I won!");
				break;
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
				break;
			}
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			while(board.checkFull(in)){
				System.out.println("That column is full!");
				System.out.println("Please rechoose a column: ");
				in = input.nextInt()-1;
			}
			board.drop(in, enemymove);
			board.printBoard();
			System.out.println();
			if(board.checkWins(enemymove)){
				System.out.println("You won! You have bested me!");
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
			}
		}
	}
	
	public static void gameplay3(Scanner input){
		System.out.println("You are playing 6*7*4 Connect 4. Red(1) or Yellow(2) Red goes first.");
		int in = input.nextInt();
		int enemymove = (in > 1)? -1: 1;
		int mymove = (in > 1)? 1: -1;
		GameBoard board = new GameBoard(6,7,4);
		Connect4Player ai = new Connect4Player(6,7,4);
		
		if(enemymove == 1){
			board.printBoard();
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			board.drop(in, 1);
			System.out.println();
			board.printBoard();
			System.out.println();
		}
		while(true){
			State S = new State(mymove,mymove);
			S.setBoard(board.copyboard());
			System.out.println("I am thinking...");
			System.out.println();
			double start = System.currentTimeMillis();
			int myturn = ai.alpha_beta_search(S).getColumn();
			board.drop(myturn, mymove);
			double time = System.currentTimeMillis()-start;
			board.printBoard();
			System.out.println("I'm done!");
			System.out.println("I visited "+ai.getStatecount()+" nodes in " + time/1000 +" secs!");
			System.out.println("My best move is: "+S.mycolor+"@"+(myturn+1)+".");
			System.out.println();
			if(board.checkWins(mymove)){
				System.out.println("I won!");
				break;
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
				break;
			}
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			while(board.checkFull(in)){
				System.out.println("That column is full!");
				System.out.println("Please rechoose a column: ");
				in = input.nextInt()-1;
			}
			board.drop(in, enemymove);
			board.printBoard();
			System.out.println();
			if(board.checkWins(enemymove)){
				System.out.println("You won! You have bested me!");
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
			}
		}
	}
	
	public static void gameplay4(Scanner input){
		System.out.println("You are playing 6*7*4 Connect 4. Red(1) or Yellow(2) Red goes first.");
		int in = input.nextInt();
		int enemymove = (in > 1)? -1: 1;
		int mymove = (in > 1)? 1: -1;
		GameBoard board = new GameBoard(6,7,4);
		Connect4Player ai = new Connect4Player(6,7,4);
		
		if(enemymove == 1){
			board.printBoard();
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			board.drop(in, 1);
			System.out.println();
			board.printBoard();
			System.out.println();
		}
		while(true){
			State S = new State(mymove,mymove);
			S.setBoard(board.copyboard());
			System.out.println("I am thinking...");
			System.out.println();
			double start = System.currentTimeMillis();
			int myturn = ai.alpha_beta_h_minimax(S,6).getColumn();
			board.drop(myturn, mymove);
			double time = System.currentTimeMillis()-start;
			board.printBoard();
			System.out.println("I'm done!");
			System.out.println("I visited "+ai.getStatecount()+" nodes in " + time/1000 +" secs!");
			System.out.println("My best move is: "+S.mycolor+"@"+(myturn+1)+".");
			System.out.println();
			if(board.checkWins(mymove)){
				System.out.println("I won!");
				break;
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
				break;
			}
			System.out.println("where do you want to drop?");
			in = input.nextInt()-1;
			while(board.checkFull(in)){
				System.out.println("That column is full!");
				System.out.println("Please rechoose a column: ");
				in = input.nextInt()-1;
			}
			board.drop(in, enemymove);
			board.printBoard();
			System.out.println();
			if(board.checkWins(enemymove)){
				System.out.println("You won! You have bested me!");
			}
			if(board.checkDraw()){
				System.out.println("Draw!");
			}
		}
	}
}
