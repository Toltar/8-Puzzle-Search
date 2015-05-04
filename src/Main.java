/*
 * @author: Matthew Dickens
 * @author: Jairo Vera
 */

import java.util.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.Hashtable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Main {
	public static Hashtable<String, Tile[][]> hashTable = new Hashtable<String, Tile[][]>();
	public static int count = 0;
	public static PrintWriter fout;
	
	public static DirectedGraph<Board, DefaultEdge> depthGraph = 
			new DefaultDirectedGraph <Board, DefaultEdge>(DefaultEdge.class);
	
	public static DirectedGraph<Board, DefaultEdge> breadthGraph = 
			new DefaultDirectedGraph <Board, DefaultEdge>(DefaultEdge.class);
	
	public static Tile[][] goalA = getGoalBoard(new int[]{1,2,3,
														  8,0,4,
														  7,6,5});
	public static Tile[][] goalB = getGoalBoard(new int[]{0,1,2,
														  3,4,5,
														  6,7,8});
	public static Tile[][] goalC = getGoalBoard(new int[]{1,2,3,
														  4,5,6,
														  7,8,0});
	
	public static Board goalStateA;
	public static Board goalStateB;
	public static Board goalStateC;
	public static void main(String[] args) {
		goalStateA = new Board(goalA);
		goalStateB = new Board(goalB);
		goalStateC = new Board(goalC);
		bfs();
	}
	
	public static void bfs(){
		hashTable.put(goalStateA.getKey(), goalStateA.getBoard());
		hashTable.put(goalStateB.getKey(), goalStateB.getBoard());
		hashTable.put(goalStateC.getKey(), goalStateC.getBoard());
		Board initial = new Board(getIntialBoard(), 2, 1);
		System.out.println("INTIAL STATE");
		System.out.println(initial);
		
		breadthGraph.addVertex(initial);
		breadthBuild(initial);
		
		GraphIterator<Board, DefaultEdge> iterator = 
	            new BreadthFirstIterator<Board, DefaultEdge>(breadthGraph);
	    
	    while (iterator.hasNext()){
	    	Board result = iterator.next();
	    	boolean isGoalA, isGoalB, isGoalC;
	    	
			if ((isGoalA = isBoardGoal(result.getBoard(), goalA)) == true
					|| (isGoalB = isBoardGoal(result.getBoard(), goalB)) == true
					|| (isGoalC = isBoardGoal(result.getBoard(), goalC)) == true)
			{
	    		System.out.println("BREADTH FIRST SEARCH RESULTS \n" + result);
	    		return;
	    	}
	    }
	}
	
	/**
	 * Build a tree sutable for breadth first search
	 * @param parent	the parent board state
	 */
	public static void breadthBuild(Board parent){
		LinkedList<Integer> movableTiles = parent.getMovableSpaces();
		hashTable.put(parent.getKey(), parent.getBoard());
		int numKids = movableTiles.size();
		
		for (int i = 0; i < numKids; i++) {
			System.out.println(++count);
			int tile = movableTiles.get(i);
			Board child = parent.move(tile);
			if(!hashTable.containsKey(child.getKey())){
				System.out.println("Makin Babies!!");
				breadthGraph.addVertex(child);
				breadthGraph.addEdge(parent, child);
			}
			boolean isGoalA, isGoalB, isGoalC;
			if ((isGoalA = isBoardGoal(child.getBoard(), goalA)) == true
					|| (isGoalB = isBoardGoal(child.getBoard(), goalB)) == true
					|| (isGoalC = isBoardGoal(child.getBoard(), goalC)) == true)
			{
				continue;
			}
			count=0;
			breadthBuild(child);
		}
	}
	
	public static void dfs(){
		Board initial = new Board(getIntialBoard(), 2, 1);
		System.out.println("INTIAL STATE");
		System.out.println(initial);
		
		depthGraph.addVertex(initial);
		depthBuild(initial);
		
		GraphIterator<Board, DefaultEdge> iterator = 
	            new DepthFirstIterator<Board, DefaultEdge>(depthGraph);
	    
	    while (iterator.hasNext()){
	    	Board result = iterator.next();
	    	
	    	boolean isGoalA, isGoalB, isGoalC;
			if ((isGoalA = isBoardGoal(result.getBoard(), goalA)) == true
					|| (isGoalB = isBoardGoal(result.getBoard(), goalB)) == true
					|| (isGoalC = isBoardGoal(result.getBoard(), goalC)) == true)
			{
	    		System.out.println("DEPTH FIRST SEARCH RESULTS \n" + result);
	    		return;
	    	}
	    }
	}
	
	/**
	 * Build a tree sutable for depth first search
	 * @param parent	the parent board state
	 */
	public static void depthBuild(Board parent){
		LinkedList<Integer> movableTiles = parent.getMovableSpaces();
		int numKids = movableTiles.size();
		
		for (int i = numKids-1; i >=0; i--) {
			System.out.println(++count);
			int tile = movableTiles.get(i);
			Board child = parent.move(tile);
			
			depthGraph.addVertex(child);
			depthGraph.addEdge(parent, child);
			
			boolean isGoalA, isGoalB, isGoalC;
			if ((isGoalA = isBoardGoal(child.getBoard(), goalA)) == true
					|| (isGoalB = isBoardGoal(child.getBoard(), goalB)) == true
					|| (isGoalC = isBoardGoal(child.getBoard(), goalC)) == true)
			{
				continue;
			}
			
			depthBuild(child);
		}
	}
	
	public static boolean isBoardGoal(Tile[][] board, Tile[][] goal){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++){
				if (board[i][j].getValue() != goal[i][j].getValue())
					return false;
			}
		}
		return true;
	}

	public static Tile[][] getIntialBoard(){
		Tile[][] intialBoard = new Tile[3][3];
		
		// {2, 8, 3}
		// {1, 6, 4}
		// {7, 0, 5}
		intialBoard[0][0] = new Tile(2,0,0);
		intialBoard[0][1] = new Tile(8,0,1);
		intialBoard[0][2] = new Tile(3,0,2);
		
		intialBoard[1][0] = new Tile(1,1,0);
		intialBoard[1][1] = new Tile(6,1,1);
		intialBoard[1][2] = new Tile(4,1,2);
		
		intialBoard[2][0] = new Tile(7,2,0);
		intialBoard[2][1] = new Tile(0,2,1);
		intialBoard[2][2] = new Tile(5,2,2);
		
		return intialBoard;
	}
	
	public static Tile[][] getGoalBoard(int[] nums){
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

	public static void testBoardClass(){
		Board    intialState = new Board(getIntialBoard(), 2, 1); 
		System.out.println("Intial State");
		System.out.println(intialState.toString());
		System.out.println(intialState.getMovableSpaces());
		
		Board move1 = intialState.move(6);
		System.out.println("\nMoving 6 down");
		System.out.println(move1.toString());
		System.out.println(move1.getMovableSpaces());
		
		Board move2 = move1.move(1);
		System.out.println("\nMoving 1 right");
		System.out.println(move2.toString());
		System.out.println(move2.getMovableSpaces());
		
		Board move3 = move2.move(2);
		System.out.println("\nMoving 2 down");
		System.out.println(move3.toString());
		System.out.println(move3.getMovableSpaces());
	}
}
