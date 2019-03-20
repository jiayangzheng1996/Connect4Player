
public class Action {
	private int column;//need to incapsulate
	private int wincount;
	
	public Action(int column){
		this.column = column;
	}
	
	public void setWincount(int wincount){
		this.wincount = wincount;
	}
	
	public int getWincount(){
		return this.wincount;
	}
	
	public boolean checkApplicable(State s){
		GameBoard temp = s.getBoard();
		//temp.printBoard();
		//System.out.println();
		return !temp.checkFull(column);
	}
	
	public State act(State s){
		int nextmove = (s.getNextmove() > -1)? -1: 1;
		State next = new State(nextmove,s.getMymove());
		GameBoard temp = s.getBoard().copyboard();
		temp.drop(column, s.getNextmove());
		next.setBoard(temp);
		next.setValue();
		return next;
	}
	
	public int getColumn(){
		return column;
	}
	
	
	
}
