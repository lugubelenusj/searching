public class DummyState implements State {

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
        return -1;
    }
	
	public int hashCode() {
        return -1;
    }
	
	public String solutionPath() {
        return null;
    }
	
	public String solutionPathExtended() {
        return null;
    }
	
	public float distanceToState(State otherState) {
        return -1;
    }

}