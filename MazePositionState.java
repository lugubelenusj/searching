public class MazePositionState implements State {

    private char[][] maze;
    private int x;
    private int y;
    private float gValue;
    private MazePositionState parent;
    public static final float ROOTTWO = 1.4142f;

    public MazePositionState(String strMaze, int x, int y) {
        this.x = x;
        this.y = y;
        this.gValue = 0;
        this.parent = null;

        // Convert the string maze into a 2D char array.
        String[] rows = strMaze.split("\n");
        maze = new char[rows[0].length()][rows.length];
        for (int i = 0; i < rows.length; i++) {
            maze[i] = rows[i].toCharArray();
        }
    }

    public MazePositionState(char[][] maze, int x, int y, float distanceMoved, MazePositionState parent) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.gValue = parent.gValue + distanceMoved;
    }

    public State[] getChildren() {
        State[] children = new State[8];

        // Adds all possible children to the array.
        int n = 0;
        float distanceMoved = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                // This if-statement checks that it's not the same vertex (x,y), and that the space moved onto is valid (" ").
                if ((!(i == 0 && j == 0)) && maze[x+i][y+j] == ' ') {

                    if (Math.abs(i) == 1 && Math.abs(j) == 1) {
                        distanceMoved = ROOTTWO;
                    }
                    else {
                        distanceMoved = 1;
                    }

                    children[n] = new MazePositionState(maze, x+i, y+j, distanceMoved, this);
                }
                n++;
            }
        }

        return children;
    }
    
    public State getParent() {
        return this.parent;
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