/*
 * The State Class which holds the state
 * @author Matthew Dickens
 * @author Jairo Vera
 */


public class Board {
	/*index of the array represents the positions of the tiles on the board
	 * The contents of the array will be represented from numbers 1-9 and 0 for the blank position
	 */
	
	public final int SIZE=3;
	public final String NAME = "Board";
	private Tile[][] theBoard;
	private int rowOfZero, columnOfZero;
	private double stateID;
	/*
	 * Constructs the newly made Board State
	 * @param {int[][]} newBoard Input of the new board
	 */
	public Board(Tile[][] newBoard){
		setBoard(newBoard.clone());
		setMovability();
		setStateID();
	}
	/*
	 * Constructs the newly made Board State
	 * @param {Tile[][]} newBoard Input of the new board
	 * @param {int} indexOfBlankRow
	 * @param {int} indexOfBlankColumn
	 */
	public Board(Tile[][] newBoard, int rowZero, int columnZero){
		setBoard(newBoard.clone());
		this.rowOfZero=rowZero;
		this.columnOfZero=columnZero;
		setMovability1();
		setStateID();
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
	}
	/*
	 * gets the Board State ID for the Hash functions
	 * @return The Instance of the stateID of the Class
	 */
	public double getStateID(){
		return this.stateID;
	}
	/*
	 * sets the stateID of the particularState
	 */
	private void setStateID(){
		double newID = 0;
		int rowProduct=1;
		int multiplier=1;
		for(int x=0; x<SIZE; x++){
			rowProduct = 1;
			for(int y=0; y<SIZE; y++){
				rowProduct *= multiplier*theBoard[x][y].getValue();
				multiplier++;
			}
			System.out.println(rowProduct);
			newID+=(double)rowProduct;
		}

		stateID = newID/9;
		
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
	public Board move(int tileToBeMoved){
		Tile[][] childBoard;
		childBoard = theBoard.clone();
		int row=0, column=0;
		for(int x=0; x<SIZE; x++){
			for(int y=9; y<SIZE; y++){
				if(childBoard[x][y].getValue()==tileToBeMoved && childBoard[x][y].isMovable()){
					childBoard[rowOfZero][columnOfZero].setValue(tileToBeMoved);
					childBoard[x][y].setValue(0);
					row=x;
					column=y;
				}
			}
		}
		return new Board(childBoard,row,column);
	}
}