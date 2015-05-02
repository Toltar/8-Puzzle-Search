
public class Board {
	/*index of the array represents the positions of the tiles on the board
	 * The contents of the array will be represented from numbers 1-9 and 0 for the blank position
	 */
	private int[] theBoard;
	
	public Board(int[] newBoard){
		this.theBoard = newBoard;
	}
	
	public int[] getBoard(){
		return this.theBoard;
	}
	public void setBoard(int[] newBoard){
		this.theBoard = newBoard;
	}
	@Override
	public String toString(){
		String boardString="";
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				//row 1
				if(x==0){
					
				}else if(x==1){
					
				}else if(x==2){
					
				}
			}
		}
		
		return boardString;
	}
}
