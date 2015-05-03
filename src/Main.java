/*
 * @author: Matthew Dickens
 * @author: Jairo Vera
 */

import java.util.LinkedList;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Main {

	public static DirectedGraph<Board, DefaultEdge> depthGraph = 
			new DefaultDirectedGraph <Board, DefaultEdge>(DefaultEdge.class);
	
	public static PrintWriter fout;
	public static Tile[][] goal = getGoalBoard();
	
	public static void main(String[] args) {
		try {
			fout = new PrintWriter(new FileWriter("output.txt"));
		} catch(Exception e) {
			System.out.println("Error. Could not find or create output.txt file");
			System.exit(1);
		}
		
		dfs();
	}
	
	public static void dfs(){
		Board initial = new Board(getIntialBoard(), 2, 1);
		fout.println("INTIAL STATE");
		fout.println(initial);
		
		depthGraph.addVertex(initial);
		depthBuild(initial);
		
		GraphIterator<Board, DefaultEdge> iterator = 
	            new DepthFirstIterator<Board, DefaultEdge>(depthGraph);
	    
	    while (iterator.hasNext()){
	    	Board result = iterator.next();
	    	if (isBoardTheGoal(result.getBoard())) {
	    		fout.println("DEPTH FIRST SEARCH RESULTS \n" + result);
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
			int tile = movableTiles.get(i);
			Board child = parent.move(tile);
			
			depthGraph.addVertex(child);
			depthGraph.addEdge(parent, child);
			
			if (isBoardTheGoal(child.getBoard())) {
				continue;
			}
			
			depthBuild(child);
		}
	}
	
	public static boolean isBoardTheGoal(Tile[][] board){
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
	
	public static Tile[][] getGoalBoard(){
		Tile[][] goalBoard = new Tile[3][3];
		
		// {1, 2, 3}
		// {4, 5, 6}
		// {7, 8, 0}
		goalBoard[0][0] = new Tile(1,0,0);
		goalBoard[0][1] = new Tile(2,0,1);
		goalBoard[0][2] = new Tile(3,0,2);
		
		goalBoard[1][0] = new Tile(4,1,0);
		goalBoard[1][1] = new Tile(5,1,1);
		goalBoard[1][2] = new Tile(6,1,2);
		
		goalBoard[2][0] = new Tile(7,2,0);
		goalBoard[2][1] = new Tile(8,2,1);
		goalBoard[2][2] = new Tile(0,2,2);
		
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
