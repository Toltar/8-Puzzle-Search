/*
 * The State Class which holds the state
 * @author Matthew Dickens
 * @author Jairo Vera
 */

import java.util.LinkedList;

public class Board {
	/*index of the array represents the positions of the tiles on the board
	 * The contents of the array will be represented from numbers 1-9 and 0 for the blank position
	 */
	
	public final int SIZE=3;
	public final String NAME = "Board";
	
	private Tile[][] theBoard;
	private int rowOfZero, columnOfZero;
	private String stateKey;
	
	/*
	 * Constructs the newly made Board State
	 * @param {int[][]} newBoard Input of the new board
	 */
	public Board(Tile[][] newBoard){
		this.theBoard = newBoard;
		setKey();
		setMovability();
	}
	
	/*
	 * Constructs the newly made Board State
	 * @param {Tile[][]} newBoard Input of the new board
	 * @param {int} indexOfBlankRow
	 * @param {int} indexOfBlankColumn
	 */
	public Board(Tile[][] newBoard, int rowZero, int columnZero){
		this.theBoard = newBoard;
		this.rowOfZero=rowZero;
		this.columnOfZero=columnZero;
		setKey();
		setMovability1();
	}
	/*
	 * Generates the movability of the state
	 */
	public void setMovability(){
		for(Tile[] x: theBoard){
			for(Tile theTile: x){
				if(theTile.getValue() == 0){
					int row = theTile.getRowIndex();
					int column = theTile.getColumnIndex();
					//if the blank tile is in the corner
					if((row==0 && column==0) || (row==0 && column==2) || (row==2 && column==0) || (row==2 && column==2)){
						if(row==0){
							if(column==0){
								theBoard[(row)][(column+1)].setMovable(true);
								theBoard[(row+1)][(column)].setMovable(true);
							}else if(column==2){
								theBoard[(row)][(column-1)].setMovable(true);
								theBoard[(row+1)][(column)].setMovable(true);
							}
						}else if(row==2){
							if(column==0){
								theBoard[(row)][(column+1)].setMovable(true);
								theBoard[(row-1)][(column)].setMovable(true);
							}else if(column==2){
								theBoard[(row)][(column-1)].setMovable(true);
								theBoard[(row-1)][(column)].setMovable(true);
							}
						}
					}
					//if the blank tile is in the middle edges
					if((row==0 && column==1) || (row==1 && column==0) || (row==2 && column==1) || (row==1 && column==2)){
						if(row==0){
							theBoard[(row)][(column-1)].setMovable(true);
							theBoard[(row)][(column+1)].setMovable(true);
							theBoard[(row+1)][(column)].setMovable(true);
						}else if(row == 1){
							if(column==0){
								theBoard[(row)][(column+1)].setMovable(true);
								theBoard[(row+1)][(column)].setMovable(true);
								theBoard[(row-1)][(column)].setMovable(true);
							}else{
								theBoard[(row)][(column-1)].setMovable(true);
								theBoard[(row+1)][(column)].setMovable(true);
								theBoard[(row-1)][(column)].setMovable(true);
							}
						}else if(row == 2){
							theBoard[(row)][(column-1)].setMovable(true);
							theBoard[(row-1)][(column)].setMovable(true);
							theBoard[(row)][(column+1)].setMovable(true);
						}
					}
					//if the blank tile is in the middle
					if(row==1 && column==1){
						theBoard[(row+1)][(column)].setMovable(true);
						theBoard[(row-1)][(column)].setMovable(true);
						theBoard[(row)][(column+1)].setMovable(true);
						theBoard[(row)][(column-1)].setMovable(true);
					}
					rowOfZero=row;
					columnOfZero=column;
					break;
				}
			}
		}
	}
	
	public void setMovability1(){
		int row = rowOfZero;
		int column = columnOfZero;
		//if the blank tile is in the corner
		if((row==0 && column==0) || (row==0 && column==2) || (row==2 && column==0) || (row==2 && column==2)){
			if(row==0){
				if(column==0){
					theBoard[(row)][(column+1)].setMovable(true);
					theBoard[(row+1)][(column)].setMovable(true);
				}else if(column==2){
					theBoard[(row)][(column-1)].setMovable(true);
					theBoard[(row+1)][(column)].setMovable(true);
				}
			}else if(row==2){
				if(column==0){
					theBoard[(row)][(column+1)].setMovable(true);
					theBoard[(row-1)][(column)].setMovable(true);
							}else if(column==2){
								theBoard[(row)][(column-1)].setMovable(true);
								theBoard[(row-1)][(column)].setMovable(true);
							}
						}
					}
		//if the blank tile is in the middle edges
		if((row==0 && column==1) || (row==1 && column==0) || (row==2 && column==1) || (row==1 && column==2)){
			if(row==0){
				theBoard[(row)][(column-1)].setMovable(true);
				theBoard[(row)][(column+1)].setMovable(true);
				theBoard[(row+1)][(column)].setMovable(true);
			}else if(row == 1){
				if(column==0){
					theBoard[(row)][(column+1)].setMovable(true);
					theBoard[(row+1)][(column)].setMovable(true);
					theBoard[(row-1)][(column)].setMovable(true);
					}else{
					theBoard[(row)][(column-1)].setMovable(true);
					theBoard[(row+1)][(column)].setMovable(true);
					theBoard[(row-1)][(column)].setMovable(true);
					}
			}else if(row == 2){
				theBoard[(row)][(column-1)].setMovable(true);
				theBoard[(row-1)][(column)].setMovable(true);
				theBoard[(row)][(column+1)].setMovable(true);
			}
		}
		//if the blank tile is in the middle
		if(row==1 && column==1){
			theBoard[(row+1)][(column)].setMovable(true);
			theBoard[(row-1)][(column)].setMovable(true);
			theBoard[(row)][(column+1)].setMovable(true);
			theBoard[(row)][(column-1)].setMovable(true);
		}
	}
						
	/*
	 * gets the Board State of this instance
	 * @return The Instance of the BoardState of the Class
	 */
	public Tile[][] getBoard(){
		return this.theBoard;
	}
	
	/*
	 * Sets the Board State with a new Board State
	 * @param {Tile[][]} newBoard;
	 */
	public void setBoard(Tile[][] newBoard){
		this.theBoard = newBoard;
		this.setMovability();
	}
	
	/*
	 * Modified toString method for the BoardState
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @return the Board State in a String Format
	 */
	@Override
	public String toString(){
		String boardString="";
		for(int x=0; x<SIZE; x++){
			if(x==0){
				boardString+= "---------"+"\n";
			}
			for(int y=0; y<SIZE; y++){
				boardString+= "|"+theBoard[x][y].getValue()+"|";
			}
			if(x==2){
				boardString+= "\n"+"---------";
			}else{
				boardString+= "\n";
			}
		}
		
		return boardString;
	}
	
	// gets the key
	public String getKey() {
		return this.stateKey;
	}

	// sets the key based on tile position
	private void setKey() {
		String key = "";
		for (Tile[] x : theBoard) {
			for (Tile y : x) {
				key += Integer.toString(y.getValue());
			}
		}
		stateKey = key;
	}
	/*
	 * Tile Movement Function
	 * @param {int} tileToBeMoved
	 */
	public Board move(int tileToBeMoved){
		Tile[][] childBoard;
		childBoard = clone_board();
		int row=0, column=0;
		boolean moveSuccess = false;
		
		mainloop:
		for(int x=0; x<SIZE; x++){
			for(int y=0; y<SIZE; y++){
				if(childBoard[x][y].getValue()==tileToBeMoved && childBoard[x][y].isMovable()){
					fix_movability(childBoard);
					
					childBoard[rowOfZero][columnOfZero].setValue(tileToBeMoved);
					childBoard[x][y].setValue(0);
					childBoard[rowOfZero][columnOfZero].setMovable(false);
					
					row=x;
					column=y;
					moveSuccess = true;
					break mainloop;
				}
			}
		}
		if (moveSuccess == false) {
			System.out.println("Error. Tried to move unmovable tile.");
			System.exit(1);
		}
		
		return new Board(childBoard,row,column);	// returns child state
	}
	
	/**
	 * Takes board of Tiles. Sets all movability to false
	 * 
	 * @param a 3x3 array of Tile objects
	 */
	private void fix_movability(Tile[][] board){
		for(int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				board[i][j].setMovable(false);
	}
	
	/**
	 * @return a linked list of integers that represent
	 * the movable tiles of the board
	 */
	public LinkedList<Integer> getMovableSpaces(){
		LinkedList<Integer> movableTiles = new LinkedList<Integer>();
		int row   = rowOfZero;
		int column = columnOfZero;
		
		//if the blank is in a corner
		if((row==0 && column==0) || (row==0 && column==2) || (row==2 && column==0) || (row==2 && column==2)){
			if (row == 0){
				movableTiles.add(theBoard[0][1].getValue());
				movableTiles.add(theBoard[1][column].getValue());
			}
			else if (row == 2){
				movableTiles.add(theBoard[1][column].getValue());
				movableTiles.add(theBoard[2][1].getValue());
			}
		}
		//if the blank tile is in a middle edge
		if((row==0 && column==1) || (row==1 && column==0) || (row==2 && column==1) || (row==1 && column==2)){
			if (row == 0) {
				movableTiles.add(theBoard[row][0].getValue());
				movableTiles.add(theBoard[row][2].getValue());
				movableTiles.add(theBoard[1][1].getValue());
			} else if (row == 2) { 
				movableTiles.add(theBoard[1][1].getValue());
				movableTiles.add(theBoard[row][0].getValue());
				movableTiles.add(theBoard[row][2].getValue());
			} else { // row == 1
				movableTiles.add(theBoard[0][column].getValue());
				movableTiles.add(theBoard[1][1].getValue());
				movableTiles.add(theBoard[2][column].getValue());
			}
		}
		//if the blank tile is in the middle
		if(row==1 && column==1){
			movableTiles.add(theBoard[0][1].getValue());
			movableTiles.add(theBoard[1][0].getValue());
			movableTiles.add(theBoard[1][2].getValue());
			movableTiles.add(theBoard[2][1].getValue());
		}
		
		return movableTiles;
	}
	
	public Tile[][] clone_board(){
		Tile[][] newboard = new Tile[3][3]; 
		for (int i=0; i < 3; i++)
			for (int j=0; j < 3; j++) 
				newboard[i][j] = theBoard[i][j].getCopy();
		return newboard;
	}
}