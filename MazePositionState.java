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
        int rowLen = rows.length;
        int colLen = rows[0].length();

        maze = new char[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
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
        State[] children = new State[9];

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
        if (this.x == ((MazePositionState) other).getX() && this.y == ((MazePositionState) other).getY()) {
            return true;
        }
        return false;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
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
        String output = solutionPath(this, "");
        // Remove trailing comma.
        output = output.replaceAll(",$", "");
        return output;
    }

    private String solutionPath(MazePositionState state, String output) {
        if (state.getParent() == null) {
            output += state.toString() + ",";
            return output;
        }
        else {
            output += solutionPath((MazePositionState) state.getParent(), output);
            output += state.toString() + ",";
            return output;
        }
    }

    public String solutionPathExtended() {
        char[][] graph = solutionPathExtended(this, maze);
        
        String output = "";
        int rows = graph.length;
        int cols = graph[0].length;

        // Add every piece of the graph to the string one-by-one.
        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                output += maze[j][i];
            }
            output += "\n";
        }

        return output;
    }

    private char[][] solutionPathExtended(MazePositionState state, char[][] graph) {
        if (state.getParent() == null) {
            int w = state.getX();
            int h = state.getY();
            graph[w][h] = '.';
            return graph;
        }
        else {
            graph = solutionPathExtended((MazePositionState) state.getParent(), graph);
            int w = state.getX();
            int h = state.getY();
            graph[w][h] = '.';
            return graph;
        }
    }

    public float distanceToState(State otherState) {
        MazePositionState other = (MazePositionState) otherState;
        int distance = Math.abs(this.x - other.getX()) + Math.abs(this.y - other.getY());
        return distance;
    }

}