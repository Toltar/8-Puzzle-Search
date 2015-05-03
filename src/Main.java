/*
 * @author: Matthew Dickens
 * @author: Jairo Vera
 */

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

public class Main {

	public static void main(String[] args) {
		Board    intialState = new Board(getIntialBoard()); 
		System.out.println("Intial State");
		System.out.println(intialState.toString());
		
		Board move1 = intialState.move(6);
		System.out.println("\nMoving 6 down");
		System.out.println(move1.toString());
		
		Board move2 = move1.move(1);
		System.out.println("\nMoving 1 right");
		System.out.println(move2.toString());
		
		Board move3 = move2.move(2);
		System.out.println("\nMoving 2 down");
		System.out.println(move3.toString());
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
}
