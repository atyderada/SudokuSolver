
public class GameSolver {
	
	private int boardSize;
	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public int[][] getBoardMatrix() {
		return boardMatrix;
	}

	public void setBoardMatrix(int[][] boardMatrix) {
		this.boardMatrix = boardMatrix;
	}

	private int[][] boardMatrix;
	
	public GameSolver(int[][] board, int size) {
		this.boardSize = size;
		this.boardMatrix = new int[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.boardMatrix[i][j] = board[i][j];
			}
		}
	}
	
	public boolean solver() {
	    for (int r = 0; r < this.boardMatrix.length; r++) {
	        for (int c = 0; c < this.boardMatrix[0].length; c++) {
	            if (this.boardMatrix[r][c] == 0) {
	                for (int k = 1; k <= 9; k++) {
	                	this.boardMatrix[r][c] = k;
	                    if (isValid(r, c) && solver()) {
	                        return true;
	                    } else {
	                    	this.boardMatrix[r][c] = 0;
	                    }
	                 }
	                return false;
	             }
	         }
	     }
	    return true;
	}

	public boolean isValid(int r, int c) {
	    //check row
	    boolean[] row = new boolean[9];
	    for (int i = 0; i < 9; i++) {
	        if (this.boardMatrix[r][i] >= 1 && this.boardMatrix[r][i] <= 9) {
	            if (row[this.boardMatrix[r][i] - 1] == false) {
	                row[this.boardMatrix[r][i] - 1] = true;
	            } else {
	                return false;
	            }
	        }
	    }

	    //check column
	    boolean[] col = new boolean[9];
	    for (int i = 0; i < 9; i++) {
	        if (this.boardMatrix[i][c] >= 1 && this.boardMatrix[i][c] <= 9) {
	            if (col[this.boardMatrix[i][c] - 1] == false) {
	                col[this.boardMatrix[i][c] - 1] = true;
	            } else {
	                return false;
	            }
	        }
	    }

	    //check the 3*3 grid
	    boolean[] grid = new boolean[9];
	    for (int i = (r / 3) * 3; i < (r / 3) * 3 + 3; i++) {
	        for (int j = (c / 3) * 3; j < (c / 3) * 3 + 3; j++) {
	            if (this.boardMatrix[i][j] >= 1 && this.boardMatrix[i][j] <= 9) {
	                if (grid[this.boardMatrix[i][j] - 1] == false) {
	                    grid[this.boardMatrix[i][j] - 1] = true;
	                } else {
	                    return false;
	                }
	            }
	         }
	    }

	    return true;
	}
}
