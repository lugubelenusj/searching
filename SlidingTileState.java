public class SlidingTileState implements State {

    private int width;
    private int height;
    private int[] tiles;
    private SlidingTileState parent;

    public SlidingTileState(int width, int height, int[] tiles) {
        this(width, height, tiles, null);
    }

    public SlidingTileState(int width, int height, int[] tiles, SlidingTileState parent) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.parent = parent;
    }

	public State[] getChildren() {
        return null;
    }
	
	public State getParent() {
        return this.parent;
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