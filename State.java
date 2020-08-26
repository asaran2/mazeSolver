import java.util.ArrayList;

/**
 * A state in the search represented by the (x,y) coordinates of the square and
 * the parent. In other words a (square,parent) pair where square is a Square,
 * parent is a State.
 * 
 * You should fill the getSuccessors(...) method of this class.
 * 
 */
public class State {

	private Square square;
	private State parent;

	// Maintain the gValue (the distance from start)
	// You may not need it for the BFS but you will
	// definitely need it for AStar
	private int gValue;

	// States are nodes in the search tree, therefore each has a depth.
	private int depth;

	/**
	 * @param square
	 *            current square
	 * @param parent
	 *            parent state
	 * @param gValue
	 *            total distance from start
	 */
	public State(Square square, State parent, int gValue, int depth) {
		this.square = square;
		this.parent = parent;
		this.gValue = gValue;
		this.depth = depth;
	}

	/**
	 * @param visited
	 *            explored[i][j] is true if (i,j) is already explored
	 * @param maze
	 *            initial maze to get find the neighbors
	 * @return all the successors of the current state
	 */
	public ArrayList<State> getSuccessors(boolean[][] explored, Maze maze) {
		// FILL THIS METHOD
		
		// TODO check all four neighbors in left, down, right, up order
		// TODO remember that each successor's depth and gValue are
		// +1 of this object.
		char[][] matrix = maze.getMazeMatrix();
		ArrayList<State> successors = new ArrayList<State>();
		Square currState = square;
		
		//check left neighbor
		Square leftNeighbor = new Square((currState.X), (currState.Y - 1));	
		if((explored[leftNeighbor.X][leftNeighbor.Y]!= true) && (leftNeighbor.Y >= 0) &&
				(matrix[leftNeighbor.X][leftNeighbor.Y] != '%')){
			State L = new State(leftNeighbor,this,this.gValue + 1, this.depth + 1);
			
			successors.add(L);
		}
			
		//check down neighbor
		Square downNeighbor = new Square((currState.X + 1), (currState.Y));
		if((explored[downNeighbor.X][downNeighbor.Y]   != true) && (downNeighbor.X < maze.getNoOfRows()) &&
		(matrix[downNeighbor.X][downNeighbor.Y] != '%')){
			State D = new State(downNeighbor,this,this.gValue + 1, this.depth + 1);
			successors.add(D);
		}
		
		//check right neighbor
		Square rightNeighbor = new Square((currState.X), (currState.Y + 1));
		if((explored[rightNeighbor.X][rightNeighbor.Y]   != true) && (rightNeighbor.Y < maze.getNoOfCols() ) &&
		(matrix[rightNeighbor.X][rightNeighbor.Y] != '%')){
			State R = new State(rightNeighbor,this,this.gValue + 1, this.depth + 1);
			successors.add(R);
		}
		//check up neighbor
		Square upNeighbor = new Square((currState.X - 1), (currState.Y));
		if((explored[upNeighbor.X][upNeighbor.Y]   != true) && (upNeighbor.X >= 0) &&
		(matrix[upNeighbor.X][upNeighbor.Y] != '%')){
			State U = new State(upNeighbor,this,this.gValue + 1, this.depth + 1);
			successors.add(U);
		}
		
		return successors;
		
		}

	/**
	 * @return x coordinate of the current state
	 */
	public int getX() {
		return square.X;
	}

	/**
	 * @return y coordinate of the current state
	 */
	public int getY() {
		return square.Y;
	}

	/**
	 * @param maze initial maze
	 * @return true is the current state is a goal state
	 */
	public boolean isGoal(Maze maze) {
		if (square.X == maze.getGoalSquare().X
				&& square.Y == maze.getGoalSquare().Y)
			return true;

		return false;
	}

	/**
	 * @return the current state's square representation
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @return parent of the current state
	 */
	public State getParent() {
		return parent;
	}

	/**
	 * You may not need g() value in the BFS but you will need it in A-star
	 * search.
	 * 
	 * @return g() value of the current state
	 */
	public int getGValue() {
		return gValue;
	}

	/**
	 * @return depth of the state (node)
	 */
	public int getDepth() {
		return depth;
	}
}
