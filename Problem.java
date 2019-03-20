import java.util.ArrayList;

public interface Problem {
	State init_State = null;
	int statecount = 0;
	ArrayList<Action> A = new ArrayList<>();
	
	int player(State s);
	ArrayList<Action> Action(State s);
	State Result(State s, Action a);
	boolean Terminal_test(State s);
	int Utility(State s);
}
