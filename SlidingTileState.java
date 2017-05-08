public class SlidingTileState implements State {

    private int width;
    private int height;
    private int[][] tiles;
    private SlidingTileState parent;
    private float gValue;

    /**
     * External Constructor
     */
    public SlidingTileState(int width, int height, int[] nums) {
        this.width = width;
        this.height = height;
        this.parent = null;
        this.gValue = 0;

        // Fills a 2D array (tiles) with the given 1D array (nums).
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
        this.gValue = parent.gValue + 1;
    }

    public State[] getChildren() {
        State[] children = new State[4];

        // Finds the location of the empty tile.
        int x = 0;
        int y = 0;
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (tiles[w][h] == 0) {
                    x = w;
                    y = h;
                }
            }
        }

        // Adds all possible children to the array.
        if (x+1 < width) {
            int[][] afterMove = tiles;
            afterMove[x][y] = tiles[x+1][y];
            afterMove[x+1][y] = 0;
            children[0] = new SlidingTileState(width, height, afterMove, this);
        }
        if (x-1 >= 0) {
            int[][] afterMove = tiles;
            afterMove[x][y] = tiles[x-1][y];
            afterMove[x-1][y] = 0;
            children[1] = new SlidingTileState(width, height, afterMove, this);
        }
        if (y+1 < height) {
            int[][] afterMove = tiles;
            afterMove[x][y] = tiles[x][y+1];
            afterMove[x][y+1] = 0;
            children[2] = new SlidingTileState(width, height, afterMove, this);
        }
        if (y-1 >= 0) {
            int[][] afterMove = tiles;
            afterMove[x][y] = tiles[x][y-1];
            afterMove[x][y-1] = 0;
            children[3] = new SlidingTileState(width, height, afterMove, this);
        }

        return children;
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
        return gValue;
    }
    
    public int hashCode() {
        String total = "";
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                total += tiles[w][h];
            }
        }
        int num = Integer.parseInt(total);
        return num % ClosedList.SIZE;
    }
    
    public String solutionPath() {
        return null;
    }

    public String solutionPathExtended() {
        return solutionPathExtended(this, "");
    }

    private String solutionPathExtended(SlidingTileState state, String output) {
        if (state.getParent() == null) {
            output += state.toString();
            for (int i = 0; i < width; i++) {
                output += "-";
            }
            output += "\n";
            return output;
        }
        else {
            output += solutionPathExtended((SlidingTileState) state.getParent(), output);
            output += state.toString();
            for (int i = 0; i < width; i++) {
                output += "-";
            }
            output += "\n";
            return output;
        }
    }
    
    public float distanceToState(State otherState) {
        return 0;
    }

}