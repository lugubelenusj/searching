public class SlidingTileState implements State {

    private int width;
    private int height;
    private int[][] tiles;
    private SlidingTileState parent;
    private float gValue;
    private int movedNum;

    /**
     * External Constructor
     */
    // All elements are put into tiles.
    public SlidingTileState(int width, int height, int[] nums) {
        this.width = width;
        this.height = height;
        this.parent = null;
        this.gValue = 0;
        this.movedNum = -1;

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
    public SlidingTileState(int width, int height, int[][] tiles, int movedNum, SlidingTileState parent) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.movedNum = movedNum;
        this.parent = parent;
        this.gValue = parent.gValue() + 1;
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
            int[][] afterMove = new int[width][height];
            make2dCopy(afterMove);
            afterMove[x][y] = tiles[x+1][y];
            afterMove[x+1][y] = 0;
            children[0] = new SlidingTileState(width, height, afterMove, tiles[x+1][y], this);
        }
        if (x-1 >= 0) {
            int[][] afterMove = new int[width][height];
            make2dCopy(afterMove);
            afterMove[x][y] = tiles[x-1][y];
            afterMove[x-1][y] = 0;
            children[1] = new SlidingTileState(width, height, afterMove, tiles[x-1][y], this);
        }
        if (y+1 < height) {
            int[][] afterMove = new int[width][height];
            make2dCopy(afterMove);
            afterMove[x][y] = tiles[x][y+1];
            afterMove[x][y+1] = 0;
            children[2] = new SlidingTileState(width, height, afterMove, tiles[x][y+1], this);
        }
        if (y-1 >= 0) {
            int[][] afterMove = new int[width][height];
            make2dCopy(afterMove);
            afterMove[x][y] = tiles[x][y-1];
            afterMove[x][y-1] = 0;
            children[3] = new SlidingTileState(width, height, afterMove, tiles[x][y-1], this);
        }

        return children;
    }

    private void make2dCopy(int[][] target) {
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                target[w][h] = tiles[w][h];
            }
        }
    }
    
    public State getParent() {
        return this.parent;
    }

    public boolean equals(State other) {
        int[][] otherTiles = ((SlidingTileState) other).getTiles();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (tiles[i][j] != otherTiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
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
        long num = Long.parseLong(total.substring(total.length()/2));
        int code = (int) (num % ClosedList.SIZE);
        return code;
    }
    
    public String solutionPath() {
        String output = solutionPath(this, "");
        // Remove trailing comma.
        output = output.replaceAll(",$", "");
        return output;
    }

    private String solutionPath(SlidingTileState state, String output) {
        if (state.getParent() == null) {
            output += state.getMovedNum() + ",";
            return output;
        }
        else {
            output += solutionPath((SlidingTileState) state.getParent(), output);
            output += state.getMovedNum() + ",";
            return output;
        }
    }

    public int getMovedNum() {
        return this.movedNum;
    }

    // Here already has multiple 0s.
    public String solutionPathExtended() {
        String output = solutionPathExtended(this, "");

        // Remove trailing border.
        String border = "";
        for (int i = 0; i < width; i++) {
            border += "--";
        }
        String regex = border + "$";
        output = output.replaceAll(regex, "");

        return output;
    }

    private String solutionPathExtended(SlidingTileState state, String output) {
        if (state.getParent() == null) {
            output += state.toString();
            for (int i = 0; i < width; i++) {
                output += "--";
            }
            output += "\n";
            return output;
        }
        else {
            output += solutionPathExtended((SlidingTileState) state.getParent(), output);
            output += state.toString();
            for (int i = 0; i < width; i++) {
                output += "--";
            }
            output += "\n";
            return output;
        }
    }
    
    public float distanceToState(State otherState) {
        int[][] otherTiles = ((SlidingTileState) otherState).getTiles();
        float total = 0;

        // Go through every num in this board.
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                if (this.tiles[w][h] != 0) {

                    // Get the corresponding num in the other board.
                    for (int h2 = 0; h2 < height; h2++) {
                        for (int w2 = 0; w2 < width; w2++) {
                            if (this.tiles[w][h] == otherTiles[w2][h2]) {

                                // Add the difference in width and height to the total;
                                total += Math.abs(w2 - w) + Math.abs(h2 - h);
                            }
                        }
                    }
                }
            }
        }

        return total;
    }

    public int[][] getTiles() {
        return this.tiles;
    }

}