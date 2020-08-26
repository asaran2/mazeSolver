import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* algorithm search
 * 
 * You should fill the search() method of this class.
 */
public class AStarSearcher extends Searcher {

	/**
	 * Calls the parent class constructor.
	 * 
	 * @see Searcher
	 * @param maze initial maze.
	 */
	public AStarSearcher(Maze maze) {
		super(maze);
	}

	/**
	 * Main a-star search algorithm.
	 * 
	 * @return true if the search finds a solution, false otherwise.
	 */
	public boolean search() {

		// FILL THIS METHOD

		// explored list is a Boolean array that indicates if a state associated with a given position in the maze has already been explored. 
		boolean[][] explored = new boolean[maze.getNoOfRows()][maze.getNoOfCols()];
		// ...

		PriorityQueue<StateFValuePair> frontier = new PriorityQueue<StateFValuePair>();

		// TODO initialize the root state and add
		// to frontier list
		// ...
		
		//get initial state
		State initState = new State(maze.getPlayerSquare(),null,0,0);
		//get goal sq
		Square goalSq = maze.getGoalSquare();
		//get h
		double EucliDist = Math.sqrt(((initState.getX() - goalSq.X) * (initState.getX() - goalSq.X) )
							+ ((initState.getY() - goalSq.Y) * (initState.getY() - goalSq.Y)));
		//f = g + h
		double fVal = initState.getGValue() + EucliDist;

		StateFValuePair initSf = new StateFValuePair(initState, fVal);
		
		frontier.add(initSf);
		maxSizeOfFrontier = frontier.size();
		while (!frontier.isEmpty()) {
			// TODO return true if a solution has been found
			// TODO maintain the cost, noOfNodesExpanded (a.k.a. noOfNodesExplored),
			// maxDepthSearched, maxSizeOfFrontier during
			// the search
			// TODO update the maze if a solution found

			// use frontier.poll() to extract the minimum stateFValuePair.
			// use frontier.add(...) to add stateFValue pairs
			
			//Removed node to be expanded
			
			StateFValuePair curr = frontier.poll();
			explored[curr.getState().getX()][curr.getState().getY()] = true;
			noOfNodesExpanded++;
			
			//if goal reached
//			if(curr.getState().getSquare() == maze.getGoalSquare()){
			if(curr.getState().isGoal(maze)){
			//solnFound = true;
				//noOfNodesExpanded++;
				cost = curr.getState().getDepth();		//get length of soln path
				maxDepthSearched = curr.getState().getDepth();		//get max length
				State currPath = curr.getState();
				while(currPath.getParent() != null){
					currPath = currPath.getParent();
					maze.setOneSquare(currPath.getSquare(), '.');
					
				}
				maze.setOneSquare(currPath.getSquare(), 'S');
				return true;
			}
			
			// Update the player square for generating differnet successor
			maze = new Maze(maze.getMazeMatrix(), curr.getState().getSquare(), maze.getGoalSquare());

			//get the successors
			ArrayList<State> successors = curr.getState().getSuccessors(explored, maze);
			//	
			for(int i = 0; i < successors.size(); i++){
				Iterator<StateFValuePair> itr = frontier.iterator();
				
				boolean containsState = false;
				
				double hPotential = Math.sqrt(((successors.get(i).getX() - goalSq.X) * (successors.get(i).getX() - goalSq.X) )
						+ ((successors.get(i).getY() - goalSq.Y) * (successors.get(i).getY() - goalSq.Y)));
				double gPotential = successors.get(i).getGValue();
				double fPotential = hPotential + gPotential;
				StateFValuePair potential = new StateFValuePair(successors.get(i), fPotential);
				
				while(itr.hasNext()){
					StateFValuePair currSfPair = itr.next();
					//if state present in frontier
			//		if(currSfPair.getState() == successors.get(i)){
					if((currSfPair.getState().getX() == successors.get(i).getX()) &&
							(currSfPair.getState().getY() == successors.get(i).getY())){
					containsState = true;
						//get g of existing state
						double gPresent = currSfPair.getState().getGValue();

						//if need to replace state
						if(gPotential < gPresent){
							frontier.remove(currSfPair);
							
							frontier.add(potential);
							break;
						}
					}
				}
				if(containsState == false){
					frontier.add(potential);
				}
				else{
					containsState = false;
				}
				//if frontier bigger after adding successors, update maxFrontierSize
				if(frontier.size() > maxSizeOfFrontier){
					maxSizeOfFrontier = frontier.size();
				}
			}
		}
		return false;

		// TODO return false if no solution
	}

}
