
public class GameBoard {
	int[][] board;
	private int row;
	private int column;
	private int winc;
	
	public GameBoard(int l, int w, int win){
		this.row = l;
		this.column = w;
		this.winc = win;
		this.board = new int[l][w];
	}
	
	public GameBoard copyboard(){
		GameBoard copy = new GameBoard(row,column,winc);
		for(int i =0; i < column; i++){
			for(int j = 0; j < row; j++){
				copy.board[j][i]=board[j][i];
			}
		}
		
		return copy;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public boolean checkWins(int player){
		int check = 0;
		if(player == 1) {check = 1;}
		else if(player == -1) {check = -1;}
		else {return false;}
		
		//checking vertically
		for(int i = 0; i < column; i++){
			for(int j = 0; j < row-winc+1;j++){
				//System.out.println(board[j][i]);
				if(board[j][i] == check){
					int count = 1;
					for(int n = 1; n < winc; n++){
						if(board[j+n][i] == check){
							count++;
						}
					}
					if(count == winc){
						return true;
					}
				}
			}
		}
		
		//checking horizontally
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column-winc+1;j++){
				//System.out.println(board[j][i]);
				if(board[i][j] == check){
					int count = 1;
					for(int n = 1; n < winc; n++){
						if(board[i][j+n] == check){
							count++;
						}
					}
					if(count == winc){
						return true;
					}
				}
			}
		}
		
		//checking diagonally
		for(int i = 0; i < row-winc+1; i++){
			for(int j = 0; j < column-winc+1;j++){
				if(board[i][j] == check){
					int count = 1;
					for(int n = 1; n < winc; n++){
						if(board[i+n][j+n] == check){
							count++;
						}
					}
					if(count == winc){
						return true;
					}
				}
			}
		}
		
		for(int i = 0; i < row-winc+1; i++){
			for(int j = winc-1; j < column;j++){
				if(board[i][j] == check){
					int count = 1;
					for(int n = 1; n < winc; n++){
						if(board[i+n][j-n] == check){
							count++;
						}
					}
					if(count == winc){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public boolean checkFull(int column){
		for(int i = 0; i < row; i++){
			if(board[i][column] == 0){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkDraw(){
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++){
				if(board[i][j] == 0){
					return false;
				}
			}
		}
		return true;
	}
	
	public void printBoard(){
		for(int i = 0; i < column+1; i++){
			if(i == 0){
				System.out.print("  ");
			}
			else if(i == column){
				System.out.println(i);
			}
			else{
				System.out.print(i+" ");
			}
		}
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++){
				if(j == 0){
					System.out.print((i+1)+" ");
				}
				if(board[i][j] == 1){
					System.out.print("R"+" ");
				}
				else if(board[i][j] == -1){
					System.out.print("Y"+" ");
				}
				else
					System.out.print("  ");
				if(j == column-1){
					System.out.print(i+1);
				}
			}
			System.out.println();
		}
		for(int i = 0; i < column+1; i++){
			if(i == 0){
				System.out.print("  ");
			}
			else if(i == column){
				System.out.println(i);
			}
			else{
				System.out.print(i+" ");
			}
		}
	}
	
	public void drop(int column,int player){
		for(int i = row-1; i >= 0; i--){
			if(board[i][column] == 0){
				board[i][column] = player;
				break;
			}
		}
	}
	
//	public static void main(String args[]){
//		GameBoard g = new GameBoard(3,3,3);
//		//System.out.println(g.board.length);
//		g.drop(0, -1);
////		g.drop(0, 1);
////		g.drop(0, -1);
////		g.drop(0, -1);
////		g.drop(0, -1);
////		g.drop(0, -1);
////		g.drop(1, 1);
////		g.drop(1, 1);
////		g.drop(3, 1);
////		g.drop(2, 1);
////		g.drop(4, -1);
//		g.printBoard();
//		//System.out.println(g.checkFull(2));
//		System.out.println(g.checkWins(1));
//		
////		GameBoard copy = g.copyboard();
////		copy.drop(0, 1);
////		copy.printBoard();
////		System.out.println(copy.checkWins(1));
////		
////		g.printBoard();
//		
//		
//		Connect4Player c = new Connect4Player(1,3,3,3);
////		for(int i = 0; i < c.A.size();i++){
////			Action temp = c.A.get(i);
////			System.out.println(temp.getColumn());
////		}
//		State temp = c.getInit_S();
//		//temp.setBoard(g);
//		
//		Action a = c.minimax(temp);
//		System.out.println(a.column);
//	}
}
