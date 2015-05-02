
public class Board {
	/*index of the array represents the positions of the tiles on the board
	 * The contents of the array will be represented from numbers 1-9 and 0 for the blank position
	 */
	public final int SIZE=3;
	private int[][] theBoard;
	
	public Board(int[][] newBoard){
		this.theBoard = newBoard;
	}
	
	public int[][] getBoard(){
		return this.theBoard;
	}
	public void setBoard(int[][] newBoard){
		this.theBoard = newBoard;
	}
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
