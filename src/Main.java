/*
 * @author: Matthew Dickens
 * @author: Jairo Vera
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = new int[3][3];
		int n=0;
		for(int x=0; x<3; x++){
			for(int y=0; y<3; y++){
				test[x][y]= n++;
			}
		}
		Board state = new Board(test);
		System.out.println(state.toString());
	}

}
