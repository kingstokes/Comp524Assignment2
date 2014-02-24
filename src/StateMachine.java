
/*
 * State Machine for Calculator Language Scanner
 * 
 */


public class StateMachine {
	
	//this class should be instantiated and allow transitions by helper methods
	//from one state to another.
	
	//data member
	private int state = 0;
	
	
	public void setState(int x){
		if (x > 19 || x < 0){
			System.out.println("Invalid State Attempted.");
			System.exit(4);
		}
		state = x;
	}
	
	public int currentState(){
		return state;
	}
	
	public boolean finalState(){
		switch (state){
		case 2: case 3: case 4: case 5: case 6: case 7:
			return true;
			default: break;
		}
		return false;
	}
	
	

}
