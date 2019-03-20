
public class State{
	private GameBoard current;
	private int nextmove;
	private int mymove;
	private int enemymove;
	private int value;
	String mycolor;
	
	public State copy(){
		State copy = new State(nextmove,mymove);
		copy.current = this.current.copyboard();
		copy.value = this.value;
		
		return copy;
	}
	
	public State(int nextmove,int mymove){
		this.nextmove = nextmove;
		this.mymove = mymove;
		this.enemymove = (mymove > -1)? -1: 1;
		if(mymove == 1){
			mycolor = "RED";
		}
		else{
			mycolor = "YELLOW";
		}
	}
	
	public void createBoard(int row, int column, int winc){
		this.current = new GameBoard(row,column,winc);
	}
	
	public GameBoard getBoard(){
		return this.current;
	}
	
	public void setBoard(GameBoard gb){
		this.current = gb;
	}
	
	public int getNextmove(){
		return nextmove;
	}
	
	public void setMymove(int mymove){
		this.mymove = mymove;
	}
	
	public int getMymove(){
		return this.mymove;
	}
	
	public int getEnemymove(){
		return this.enemymove;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(){
		if(current.checkWins(mymove)){
			this.value = 1;
		}
		else if(current.checkWins(enemymove)){
			this.value = -1;
		}
		else
			this.value = 0;
	}
}
