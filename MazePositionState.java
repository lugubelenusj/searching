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
        return null;
    }

	public float gValue() {
        return this.gValue;
    }
	
	public int hashCode() {
        return 0;
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