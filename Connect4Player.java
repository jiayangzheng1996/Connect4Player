import java.util.ArrayList;
import java.util.Random;

public class Connect4Player implements Problem {
	private int Statecount;
	private ArrayList<Action> A;//need to incapsulate
	
	public Connect4Player(int l, int w, int win){
		//this.init_S = init_S;
		A = new ArrayList<>();
		for(int i = 0; i < w; i++){
			A.add(new Action(i));
		}
	}

	@Override
	public State Result(State s, Action a) {
		return a.act(s);
	}

	@Override
	public ArrayList<Action> Action(State s) {
		ArrayList<Action> temp = new ArrayList<>();
		for(int i = 0; i < A.size(); i++){
			Action a = A.get(i);
			if(a.checkApplicable(s)){
				temp.add(a);
			}
		}
		return temp;
	}
	
	public int getStatecount(){
		return Statecount;
	}
	
	public double heuristic(State s){
		double value = 0;
		double wincount = 0;
		Random testcase = new Random();
		int ntest = testcase.nextInt(91)+10;
		GameBoard board = s.getBoard().copyboard();
		Random column = new Random();
		for(int i = 0; i < ntest;i++){
			int nextmove = s.getNextmove();
			board = s.getBoard().copyboard();
			while(true){
				int nextcolumn = 0;
				do{
					nextcolumn = column.nextInt(board.getColumn());
				}while(board.checkFull(nextcolumn));
				board.drop(nextcolumn, nextmove);
				if(board.checkWins(s.getMymove())){
					wincount++;
					break;
				}
				else if(board.checkWins(s.getEnemymove())){
					break;
				}
				else if(board.checkDraw()){
					wincount = wincount+0.5;
					break;
				}
				nextmove = nextmove*(-1);
				
				do{
					nextcolumn = column.nextInt(board.getColumn());
				}while(board.checkFull(nextcolumn));
				board.drop(nextcolumn, nextmove);
				if(board.checkWins(s.getMymove())){
					wincount++;
					break;
				}
				else if(board.checkWins(s.getEnemymove())){
					break;
				}
				else if(board.checkDraw()){
					wincount = wincount+0.5;
					break;
				}
				nextmove = nextmove*(-1);
			}
//			board.printBoard();
//			System.out.println("current win count: "+wincount);
//			System.out.println();
		}
		
		value = wincount/ntest;
		
		
		
		return value;
	}
	
	public Action minimax(State s){
		int max = Integer.MIN_VALUE;
		Action max_action = null;
		State tempstate = s.copy();
		Statecount = 0;
		
		for(int i = 0; i < A.size(); i++){
			Action temp = A.get(i);
			//System.out.println(i+" "+temp.checkApplicable(s));
			//s.getBoard().printBoard();
			//System.out.println();
			if(temp.checkApplicable(s)){
				int n = min(Result(tempstate,temp));
				//System.out.println(i+" "+n);
				if(max<n){
					max = n;
					max_action = temp;
				}
			}
			tempstate = s.copy();
		}
		
		return max_action;
	}
	
	public int max(State s){
		//State tempstate = s.copy();
//		tempstate.getBoard().printBoard();
//		System.out.println("value "+s.getValue());
//		System.out.println();
		
		int max = Integer.MIN_VALUE;
		int s_value = s.getValue();
		if(s_value == 1 || s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp = A.get(i);
				if(temp.checkApplicable(s)){
					Statecount++;
					int n = min(Result(s,temp));
					if(max<n){
						max = n;
					}
				}
				//tempstate = s.copy();
			}
		}
		
		return max;
	}
	
	public int min(State s){
		//State tempstate = s.copy();
//		tempstate.getBoard().printBoard();
		
		
		int min = Integer.MAX_VALUE;
		int s_value = s.getValue();
		if(s_value == 1 || s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp = A.get(i);
				if(temp.checkApplicable(s)){
					Statecount++;
					int n = max(Result(s,temp));
					if(min>n){
						min = n;
					}
				}
				//tempstate = s.copy();
			}
		}
		
		return min;
	}
	
	public Action alpha_beta_search(State s){
		int max = Integer.MIN_VALUE;
		Action max_action = null;
		State tempstate = s.copy();
		Statecount = 0;
		
		for(int i = 0; i < A.size(); i++){
			Action temp = A.get(i);
			//System.out.println(i+" "+temp.checkApplicable(s));
			//s.getBoard().printBoard();
			//System.out.println();
			if(temp.checkApplicable(s)){
				int n = alpha_beta_min(Result(tempstate,temp),Integer.MIN_VALUE,Integer.MAX_VALUE);
				//System.out.println(i+" "+n);
				if(max<n){
					max = n;
					max_action = temp;
				}
			}
			tempstate = s.copy();
		}
		
		return max_action;
	}
	
	public int alpha_beta_max(State s, int alpha, int beta){
		int max = Integer.MIN_VALUE;
		int s_value = s.getValue();
		int a = alpha;
		int b = beta;
		if(s_value == 1 || s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp = A.get(i);
				if(temp.checkApplicable(s)){
					Statecount++;
					int n = alpha_beta_min(Result(s,temp),a,b);
					if(max<n){
						max = n;
					}
				}
				if(max >= beta){
					return max;
				}
				else
					a = Integer.max(a, max);
			}
		}
		
		return max;
	}
	
	public int alpha_beta_min(State s, int alpha, int beta){
		int min = Integer.MAX_VALUE;
		int s_value = s.getValue();
		int a = alpha;
		int b = beta;
		if(s_value == 1 || s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp = A.get(i);
				if(temp.checkApplicable(s)){
					Statecount++;
					int n = alpha_beta_max(Result(s,temp),a,b);
					if(min>n){
						min = n;
					}
				}
				if(min <= alpha){
					return min;
				}
				else
					b = Integer.min(b, min);
			}
		}
		
		return min;
	}
	
	public Action h_minimax(State s,int depth){
		double max = Integer.MIN_VALUE;
		Action max_action = null;
		State tempstate = s.copy();
		Statecount = 0;
		
		for(int i = 0; i < A.size(); i++){
			Action temp = A.get(i);
			temp.setWincount(Integer.MAX_VALUE);
			//System.out.println(i+" "+temp.checkApplicable(s));
			//s.getBoard().printBoard();
			//System.out.println();
			if(temp.checkApplicable(s)){
				double n = h_min(Result(tempstate,temp),temp,depth-1,0);
				//System.out.println(i+" "+n+" "+temp.getWincount());
				//System.out.println();
				if(max<n){
					max = n;
					max_action = temp;
				}
				else if(max == n){
					if(max_action.getWincount()>temp.getWincount()){
						max_action = temp;
					}
				}
			}
			tempstate = s.copy();
		}
		
		return max_action;
	}
	
	public double h_max(State s, Action temp, int depth, int winc){
		double max = Integer.MIN_VALUE;
		int s_value = s.getValue();
		if(s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else if(s_value == 1){
			temp.setWincount(winc);
			return s_value;
		}
		else if(depth == 0){
			return heuristic(s);
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp_max = A.get(i);
				if(temp_max.checkApplicable(s)){
					Statecount++;
					double n = h_min(Result(s,temp_max),temp,depth-1,winc+1);
					if(max<n){
						max = n;
					}
				}
				//tempstate = s.copy();
			}
		}
		
		return max;
		
	}
	
	public double h_min(State s, Action temp, int depth, int winc){
		double min = Integer.MAX_VALUE;
		int s_value = s.getValue();
		if(s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else if(s_value == 1){
			temp.setWincount(winc);
			return s_value;
		}
		else if(depth == 0){
			//System.out.println(1);
			return heuristic(s);
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp_min = A.get(i);
				if(temp_min.checkApplicable(s)){
					//System.out.println(2);
					Statecount++;
					double n = h_max(Result(s,temp_min),temp,depth-1,winc+1);
					if(min>n){
						min = n;
					}
				}
				//tempstate = s.copy();
			}
		}
		
		return min;
		
	}
	
	public Action alpha_beta_h_minimax(State s,int depth){
		double max = Integer.MIN_VALUE;
		Action max_action = null;
		State tempstate = s.copy();
		Statecount = 0;
		
		for(int i = 0; i < A.size(); i++){
			Action temp = A.get(i);
			temp.setWincount(Integer.MAX_VALUE);
			//System.out.println(i+" "+temp.checkApplicable(s));
			//s.getBoard().printBoard();
			//System.out.println();
			if(temp.checkApplicable(s)){
				double n = alpha_beta_h_min(Result(tempstate,temp),temp,depth-1,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
				//System.out.println(i+" "+n+" "+temp.getWincount());
				//System.out.println();
				if(max<n){
					max = n;
					max_action = temp;
				}
				else if(max == n){
					if(max_action.getWincount()>temp.getWincount()){
						max_action = temp;
					}
				}
			}
			tempstate = s.copy();
		}
		
		return max_action;
	}
	
	public double alpha_beta_h_max(State s, Action temp, int depth, int winc, double alpha, double beta){
		double max = Integer.MIN_VALUE;
		int s_value = s.getValue();
		double a = alpha;
		double b = beta;
		if(s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else if(s_value == 1){
			temp.setWincount(winc);
			return s_value;
		}
		else if(depth == 0){
			return heuristic(s);
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp_max = A.get(i);
				if(temp_max.checkApplicable(s)){
					Statecount++;
					double n = alpha_beta_h_min(Result(s,temp_max),temp,depth-1,winc+1,a,b);
					if(max<n){
						max = n;
					}
				}
				if(max >= beta){
					return max;
				}
				else
					a = Double.max(a, max);
			}
		}
		
		return max;
		
	}
	
	public double alpha_beta_h_min(State s, Action temp, int depth, int winc, double alpha, double beta){
		double min = Integer.MAX_VALUE;
		int s_value = s.getValue();
		double a = alpha;
		double b = beta;
		if(s_value == -1||s.getBoard().checkDraw()){
			return s_value;
		}
		else if(s_value == 1){
			temp.setWincount(winc);
			return s_value;
		}
		else if(depth == 0){
			//System.out.println(1);
			return heuristic(s);
		}
		else{
			for(int i = 0; i < A.size(); i++){
				Action temp_min = A.get(i);
				if(temp_min.checkApplicable(s)){
					//System.out.println(2);
					Statecount++;
					double n = alpha_beta_h_max(Result(s,temp_min),temp,depth-1,winc+1,a,b);
					if(min>n){
						min = n;
					}
				}
				if(min <= alpha){
					return min;
				}
				else
					b = Double.min(b, min);
			}
		}
		
		return min;
		
	}

	@Override
	public int player(State s) {
		return s.getNextmove();
	}

	@Override
	public boolean Terminal_test(State s) {
		return s.getBoard().checkWins(1)||s.getBoard().checkWins(-1)||s.getBoard().checkDraw();
	}

	@Override
	public int Utility(State s) {
		return s.getValue();
	}
}
