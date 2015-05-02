
public class Board {
	/*index of the array represents the positions of the tiles on the board
	 * The contents of the array will be represented from numbers 1-9 and 0 for the blank position
	 */
	public final int SIZE=3;
	public final String NAME = "Board";
	private int[][] theBoard;
	private double stateID;
	/*
	 * Constructs the newly made Board State
	 * @param {int[][]} newBoard Input of the new board
	 */
	public Board(int[][] newBoard){
		this.theBoard = newBoard;
		setStateID();
	}
	/*
	 * gets the Board State of this instance
	 */
	public int[][] getBoard(){
		return this.theBoard;
	}
	/*
	 * Sets the Board State with a new Board State
	 * @param {int[][]} newBoard;
	 */
	public void setBoard(int[][] newBoard){
		this.theBoard = newBoard;
	}
	/*
	 * gets the Board State ID for the Hash functions
	 */
	public double getStateID(){
		return this.stateID;
	}
	/*
	 * sets the stateID of the particularState
	 */
	@SuppressWarnings("null")
	private void setStateID(){
		double newID = 0;
		int rowProduct=1;
		int multiplier=1;
		for(int x=0; x<SIZE; x++){
			rowProduct = 1;
			for(int y=0; y<SIZE; y++){
				rowProduct*=(theBoard[x][y]*multiplier);
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
	 */
	@Override
	public String toString(){
		String boardString="";
		for(int x=0; x<SIZE; x++){
			if(x==0){
				boardString+= "---------"+"\n";
			}
			for(int y=0; y<SIZE; y++){
				boardString+= "|"+theBoard[x][y]+"|";
			}
			if(x==2){
				boardString+= "\n"+"---------";
			}else{
				boardString+= "\n";
			}
		}
		
		return boardString;
	}
}
