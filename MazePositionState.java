public class MazePositionState implements State {

    private int x;
    private int y;
    private float gValue;

    public MazePositionState(String maze, int x, int y) {
        this.x = x;
        this.y = y;
        this.gValue = 0;
    }

    public MazePositionState(String maze, int x, int y, MazePositionState parent) {
        this.x = x;
        this.y = y;
    }

	public State[] getChildren() {

        return null;
    }
	
	public State getParent() {
        return null;
    }
	
	public boolean equals(State other) {
        return false;
    }
	
	public String toString() {
        return "(" + x + "," + y + ")";
    }

	public float gValue() {
        return this.gValue;
    }
	
	public int hashCode() {
        String strX = String.valueOf(x);
        String strY = String.valueOf(y);
        String xy = strX + strY;
        int code = Integer.parseInt(xy) % ClosedList.SIZE;
        return code;
    }
	
	public String solutionPath() {
        return null;
    }

	public String solutionPathExtended() {
        return null;
    }

	public float distanceToState(State otherState) {
        return 0;
    }

}