
public class GameSolver {
	
	private int boardSize;
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
	                for (int k = 1; k <= this.getBoardSize(); k++) {
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
	    boolean[] row = new boolean[this.getBoardSize()];
	    for (int i = 0; i < this.getBoardSize(); i++) {
	        if (this.boardMatrix[r][i] >= 1 && this.boardMatrix[r][i] <= this.getBoardSize()) {
	            if (row[this.boardMatrix[r][i] - 1] == false) {
	                row[this.boardMatrix[r][i] - 1] = true;
	            } else {
	                return false;
	            }
	        }
	    }

	    //check column
	    boolean[] col = new boolean[this.getBoardSize()];
	    for (int i = 0; i < this.getBoardSize(); i++) {
	        if (this.boardMatrix[i][c] >= 1 && this.boardMatrix[i][c] <= this.getBoardSize()) {
	            if (col[this.boardMatrix[i][c] - 1] == false) {
	                col[this.boardMatrix[i][c] - 1] = true;
	            } else {
	                return false;
	            }
	        }
	    }

	    //check the sqrt(size)*sqrt(size) grid
	    boolean[] grid = new boolean[this.getBoardSize()];
	    int factor = (int) Math.sqrt(this.getBoardSize());
	    for (int i = (r / factor) * factor; i < (r / factor) * factor + factor; i++) {
	        for (int j = (c / factor) * factor; j < (c / factor) * factor + factor; j++) {
	            if (this.boardMatrix[i][j] >= 1 && this.boardMatrix[i][j] <= this.getBoardSize()) {
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
}
