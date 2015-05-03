
public class Tile {
	private int value;
	private int rowIndex;
	private int columnIndex;
	private boolean isMovable;
	public Tile(int theValue,int theRowIndex, int theColumnIndex, boolean movability){
		this.value = theValue;
		this.rowIndex = theRowIndex;
		this.columnIndex = theColumnIndex;
		this.isMovable= movability;
	}
	
	public Tile(int theValue,int theRowIndex, int theColumnIndex){
		this.value = theValue;
		this.rowIndex = theRowIndex;
		this.columnIndex = theColumnIndex;
	}
	public Tile(int theValue){
		this.value = theValue;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getColumnIndex() {
		return columnIndex;
	}
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	public boolean isMovable() {
		return isMovable;
	}
	public void setMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}
	
}
