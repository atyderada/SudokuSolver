
public class Node {
	
	private int number;
	private int row;
	private int collumn;
	private int[][] oldGameState;

	public Node(int value, int row, int collumn, int size) {
		this.number = value;
		this.row = row;
		this.collumn = collumn;
		this.oldGameState = new int[size][size];
	}
	
	public int[][] getOldGameState() {
		return oldGameState;
	}

	public void setOldGameState(int[][] gameState) {
		for (int i = 0; i < gameState.length; i++) {
			for (int j = 0; j < gameState[0].length; j++) {
				this.oldGameState[i][j] = gameState[i][j];
			}
		}
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCollumn() {
		return collumn;
	}

	public void setCollumn(int collumn) {
		this.collumn = collumn;
	}
}
