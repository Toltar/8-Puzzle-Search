/**
 * The 8 Puzzle Problem: DFS & BFS
 * 
 * @author: Matthew Dickens
 * @author: Jairo Vera
 * 
 * We give credit and acknowledgement to Barak Naveh his Contributors.
 * The ones who built the JGraphT Library. Thank you.
 */

import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Scanner;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Main {
	public static Hashtable<String, Board> hashTable = new Hashtable<String, Board>();
	
	public static DirectedGraph<Board, DefaultEdge> graph = 
			new DefaultDirectedGraph <Board, DefaultEdge>(DefaultEdge.class);
	
	public static Tile[][] initialA = get2DTiles(new int[]{2,8,3,
														   1,6,4,
														   7,0,5});
	
	public static Tile[][] initialB = get2DTiles(new int[]{1,3,4,
			   											   8,6,2,
			   											   7,0,5});
	
	public static Tile[][] initialC = get2DTiles(new int[]{2,8,1,
			   											   0,4,3,
			   											   7,6,5});
	
	public static Tile[][] initialD = get2DTiles(new int[]{2,8,1,
			   											   4,6,3,
			   											   0,7,5});
	
	public static Tile[][] initialE = get2DTiles(new int[]{5,6,7,
			  											   4,0,8,
			  											   3,2,1});
	public static int rowZero = 0;
	public static int colZero = 0;
	
	public static Tile[][] goalA = get2DTiles(new int[]{1,2,3,8,0,4,7,6,5});
	public static Tile[][] goalB = get2DTiles(new int[]{0,1,2,3,4,5,6,7,8});
	public static Tile[][] goalC = get2DTiles(new int[]{1,2,3,4,5,6,7,8,0});
	
	public static Board initialState;
	public static Board goalStateA;
	public static Board goalStateB;
	public static Board goalStateC;
	
	public static int visitedNodes = 0;
	
	public static void main(String[] args) {
		goalStateA = new Board(goalA, 1, 1);
		goalStateB = new Board(goalB, 0, 0);
		goalStateC = new Board(goalC, 2, 2);
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Matt & Jairo's 8-Puzzle Program!");
		System.out.print("Type the capital letter for the initial state: ");
		String initialLetter  = in.next();
		
		if (initialLetter.equalsIgnoreCase("A")){
			rowZero = 2;
			colZero = 1;
			initialState = new Board(initialA, rowZero, colZero);
		} else if (initialLetter.equalsIgnoreCase("B")) {
			rowZero = 2;
			colZero = 1;
			initialState = new Board(initialB, rowZero, colZero);
		} else if (initialLetter.equalsIgnoreCase("C")) {
			rowZero = 1;
			colZero = 0;
			initialState = new Board(initialC, rowZero, colZero);
		} else if (initialLetter.equalsIgnoreCase("D")) {
			rowZero = 2;
			colZero = 0;
			initialState = new Board(initialD, rowZero, colZero);
		} else if (initialLetter.equalsIgnoreCase("E")) {
			rowZero = 1;
			colZero = 1;
			initialState = new Board(initialE, rowZero, colZero);
		} else
			System.out.println("Incorrect input. Restart and try again . . .");
		
		System.out.println("You chose\n" + initialState);
		
		System.out.print("Now, enter 1 for BFS or 2 for DFS: ");
		int input = in.nextInt();
		
		long execTime = 0;
		if (input == 1)
			execTime = bfs();
		else if (input == 2)
			execTime = dfs();
		else
			System.out.println("Goodbye :p");
		
		System.out.println("Your search performance:");
		System.out.println("Execution Time:     " + execTime + " nanoseconds");
		System.out.println("Size of Tree:       " + graph.vertexSet().size());
		System.out.println("States encountered: " + visitedNodes);
		
		System.exit(0);
	}
	
	public static long bfs(){
		graph.addVertex(initialState);
		breadthBuild(initialState);
		
		long start, end;
		GraphIterator<Board, DefaultEdge> iterator = 
	            new BreadthFirstIterator<Board, DefaultEdge>(graph);
	    
		start = System.nanoTime();
	    while (iterator.hasNext()){
	    	visitedNodes++;
	    	Board result = iterator.next();
	    	boolean isGoalA, isGoalB, isGoalC;
	    	
			if ((isGoalA = compareBoards(result.getBoard(), goalA)) == true
					|| (isGoalB = compareBoards(result.getBoard(), goalB)) == true
					|| (isGoalC = compareBoards(result.getBoard(), goalC)) == true)
			{
				end = System.nanoTime();
				System.out.println("\nBREADTH FIRST SEARCH RESULTS \n" + result);
	    		return (end - start);
	    	}
	    }
	    
	    return -1;
	}
	
	/**
	 * Build a tree sutable for breadth first search
	 * @param parent	the parent board state
	 */
	public static void breadthBuild(Board parent){
		LinkedList<Integer> movableTiles = parent.getMovableSpaces();
		
		hashTable.put(parent.getKey(), parent);
		int numKids = movableTiles.size();
		
		for (int i = 0; i < numKids; i++) {
			int tile = movableTiles.get(i);
			Board child = parent.move(tile);
			
			if(!hashTable.containsKey(child.getKey())){
				graph.addVertex(child);
				graph.addEdge(parent, child);
				
				boolean isGoalA, isGoalB, isGoalC;
				if ((isGoalA = compareBoards(child.getBoard(), goalA)) == true
						|| (isGoalB = compareBoards(child.getBoard(), goalB)) == true
						|| (isGoalC = compareBoards(child.getBoard(), goalC)) == true)
				{
					continue;
				}
				breadthBuild(child);
			}
			else {	// this child exists somewhere in the graph
				Board existingChild = hashTable.get(child.getKey());
				graph.addEdge(parent, existingChild);
			}
		}
	}
	
	public static long dfs(){
		graph.addVertex(initialState);
		depthBuild(initialState);
		
		long start, end;
		GraphIterator<Board, DefaultEdge> iterator = 
	            new DepthFirstIterator<Board, DefaultEdge>(graph);
	    
		start = System.nanoTime();
	    while (iterator.hasNext()){
	    	visitedNodes++;
	    	Board result = iterator.next();
	    	
	    	boolean isGoalA, isGoalB, isGoalC;
			if ((isGoalA = compareBoards(result.getBoard(), goalA)) == true
					|| (isGoalB = compareBoards(result.getBoard(), goalB)) == true
					|| (isGoalC = compareBoards(result.getBoard(), goalC)) == true)
			{
				
				end = System.nanoTime();
	    		System.out.println("\nDEPTH FIRST SEARCH RESULTS \n" + result);
	    		return (end - start);
	    	}
	    }
	    
	    return -1;
	}
	
	/**
	 * Build a tree sutable for depth first search
	 * @param parent	the parent board state
	 */
	public static void depthBuild(Board parent){
		LinkedList<Integer> movableTiles = parent.getMovableSpaces();
		
		hashTable.put(parent.getKey(), parent);
		int numKids = movableTiles.size();
		
		for (int i = numKids-1; i >=0; i--) {
			int tile = movableTiles.get(i);
			Board child = parent.move(tile);
			
			if(!hashTable.containsKey(child.getKey())){
				graph.addVertex(child);
				graph.addEdge(parent, child);
			
				boolean isGoalA, isGoalB, isGoalC;
				if ((isGoalA = compareBoards(child.getBoard(), goalA)) == true
						|| (isGoalB = compareBoards(child.getBoard(), goalB)) == true
						|| (isGoalC = compareBoards(child.getBoard(), goalC)) == true)
				{
					continue;
				}
			
				depthBuild(child);
			} else {  // this child exists somewhere in the graph
                Board existingChild = hashTable.get(child.getKey());
                graph.addEdge(parent, existingChild);
			}
		}
	}
	
	public static boolean compareBoards(Tile[][] boardA, Tile[][] baordB){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (boardA[i][j].getValue() != baordB[i][j].getValue())
					return false;
			}
		}
		return true;
	}
	
	public static Tile[][] get2DTiles(int[] nums){
		Tile[][] goalBoard = new Tile[3][3];
		
		goalBoard[0][0] = new Tile(nums[0],0,0);
		goalBoard[0][1] = new Tile(nums[1],0,1);
		goalBoard[0][2] = new Tile(nums[2],0,2);
		
		goalBoard[1][0] = new Tile(nums[3],1,0);
		goalBoard[1][1] = new Tile(nums[4],1,1);
		goalBoard[1][2] = new Tile(nums[5],1,2);
		
		goalBoard[2][0] = new Tile(nums[6],2,0);
		goalBoard[2][1] = new Tile(nums[7],2,1);
		goalBoard[2][2] = new Tile(nums[8],2,2);
		
		return goalBoard;
	}
}
