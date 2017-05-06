public class MazePositionState implements State {

    public MazePositionState(String maze, int x, int y) {

    }

    public MazePositionState(String maze, int x, int y, MazePositionState parent) {

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
        return 0;
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