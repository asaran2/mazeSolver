import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Breadth-First Search (BFS)
 * 
 * You should fill the search() method of this class.
 */
public class BreadthFirstSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public BreadthFirstSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main breadth first search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {
		// FILL THIS METHOD

		// explored list is a 2D Boolean array that indicates if a state associated with a given position in the maze has already been explored.
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];

		// ...

		// Queue implementing the Frontier list
		LinkedList<State> queue = new LinkedList<State>();

		//added the initial state of the player
		State initState = new State(maze.getPlayerSquare(),null,0,0);
		queue.add(initState);
		boolean solnFound = false;
		while (!queue.isEmpty()) {
			// TODO return true if find a solution
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			// TODO update the maze if a solution found
			
			//Removed node to be expanded
			State curr = queue.pop();
			
			explored[curr.getX()][curr.getY()] = true;
			noOfNodesExpanded++;	//increment # of nodes expanded
			//if goal reached
			//if(curr.getSquare() == maze.getGoalSquare()){
			if(curr.isGoal(maze)){
				//solnFound = true;
				//noOfNodesExpanded++;
				cost = curr.getGValue();		//get length of soln path
				maxDepthSearched = curr.getDepth();
				State currPath = curr;
				while(currPath.getParent() != null){
					currPath = currPath.getParent();
					maze.setOneSquare(currPath.getSquare(), '.');
					
				}
				maze.setOneSquare(currPath.getSquare(), 'S');
				return true;
			}
			

			maze = new Maze(maze.getMazeMatrix(), curr.getSquare(), maze.getGoalSquare());
			//Get the successors
			ArrayList<State> successors = curr.getSuccessors(explored, maze);
			//for each successor returned, check if already present in the frontier queue, else add it at the end
			for(int i = 0; i < successors.size(); i++){
				for(State j: queue){
					if (j.getX() == successors.get(i).getX() && j.getY() == successors.get(i).getY()) {
						solnFound = true;
					}
				}
				if (!solnFound) {
					queue.add(successors.get(i));

				}
				solnFound = false;
			}
			
			//if frontier bigger after adding successors, update maxFrontierSize
			if(queue.size() > maxSizeOfFrontier){
				maxSizeOfFrontier = queue.size();
			}
			
		}				
			
			// use queue.pop() to pop the queue.
			// use queue.add(...) to add elements to queue
	
		
		
		return false;	
		// TODO return false if no solution
	}
}

