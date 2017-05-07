public class SlidingTileState implements State {

    private int width;
    private int height;
    private int[][] tiles;
    private SlidingTileState parent;

    /**
     * External Constructor
     */
    public SlidingTileState(int width, int height, int[] nums) {
        this.width = width;
        this.height = height;
        this.parent = null;

        tiles = new int[width][height];
        int i = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                tiles[w][h] = nums[i];
                i++;
            }
        }
    }

    /**
     * Internal Constructor
     */
    public SlidingTileState(int width, int height, int[][] tiles, SlidingTileState parent) {
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
        return this.toString().equals(other.toString());
    }
    
    public String toString() {
        String output = "";
        output = addBorder(output);
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (tiles[w][h] == 0) {
                    output += "|" + " ";
                }
                else {
                    output += "|" + tiles[w][h];
                }
            }
            output += "|\n";
            output = addBorder(output);
        }
        return output;
    }

    private String addBorder(String output) {
        for (int w = 0; w < width; w++) {
            output += "+-";
        }
        output += "+\n";
        return output;
    }

    public float gValue() {
        return 0;
    }
    
    public int hashCode() {
        int total = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                total += tiles[w][h];
            }
        }
        return total % ClosedList.SIZE;
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