import java.util.ArrayList;
import java.util.Stack;

public class Game {
	
	private Node[][] gameBoard;
	private int[][] gameMatrix;
	private int boardSize; 
	private int[] numCounts;

	public Game(int[][] board, int size) {
		this.boardSize = size;
		this.numCounts = new int[size + 1];
		this.gameBoard = new Node[size][size];
		this.gameMatrix = board;
		int val;
		this.fillNumCounts();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				val = board[i][j];
				this.addNumberCount(val);
				Node node = new Node(val, i, j, size);
				gameBoard[i][j] = node;
			}
		}
	}
	
	private void fillNumCounts(){
		for (int i = 0; i < this.boardSize; i++) {
			this.numCounts[i] = 0;
		}
	}
	
	private void addNumberCount(int val) {
		this.numCounts[val]++;
	}
	
	private void decreaseNumberCount(int val) {
		this.numCounts[val]--;
	}

	public int[][] backtrackingSearch() {
		return recursiveBacktracking();
	}
	
	private int[][] recursiveBacktracking() {
		
		int[][] falseResult = new int[1][1];
		falseResult[0][0] = -1;
		
		if (this.isCompleted()) return this.getGameMatrix();
		Node currentNode;
		if ((currentNode = this.starterNode()) != null) {
			currentNode.setOldGameState(this.getGameMatrix());
			for (int var : this.getDomainValues()) {
				currentNode.setNumber(var);
				this.gameMatrix[currentNode.getRow()][currentNode.getCollumn()] = var;
				this.addNumberCount(var);
				if (this.isValidState()) {
					int[][] result = this.recursiveBacktracking();
					if (result[0][0] != -1) return result;
					this.setGameMatrix(currentNode.getOldGameState());
				}
				currentNode.setNumber(0);
				this.gameMatrix[currentNode.getRow()][currentNode.getCollumn()] = 0;
				this.decreaseNumberCount(var);
			}
			return falseResult;
		} else {
			return this.getGameMatrix();
		}
	}
	
	private int[] getDomainValues() {
		int[] domain = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		return domain;
	}

	private Node starterNode() {
		int[][]values = this.getGameMatrix();
		for (int i = 0; i < this.getBoardSize(); i++) {
			for (int j = 0; j < this.getBoardSize(); j++) {
				if (values[i][j] == 0) return this.getGameBoard()[i][j];
			}
		}
		return null;
	}
	
	private boolean isCompleted() {
		
		Node[][] board = this.getGameBoard();
		int[] counts = this.getNumCounts();
		int checkCount = 0;
		int blockCount = 0;
		
		for (int a = 1; a < this.getBoardSize() + 1; a++) {
			if (counts[a] != 9) return false;
		}
		
		ArrayList<Integer> rowCount = new ArrayList<>();
		for (int i = 0; i < this.getBoardSize(); i++) {
			for (int j = 0; j < this.getBoardSize(); j++) {
				Node current = board[i][j];
				if (rowCount.contains(current.getNumber())) return false;
				rowCount.add(current.getNumber());
				checkCount+= current.getNumber();
			}
			if (checkCount != 45) return false;
			checkCount = 0;
			rowCount = new ArrayList<>();
		}
		
		ArrayList<Integer> colCount = new ArrayList<>();
		for (int i = 0; i < this.getBoardSize(); i++) {
			for (int j = 0; j < this.getBoardSize(); j++) {
				Node current = board[j][i];
				if (colCount.contains(current.getNumber())) return false;
				colCount.add(current.getNumber());
				checkCount+= current.getNumber();
			}
			if (checkCount != 45) return false;
			checkCount = 0;
			colCount = new ArrayList<>();
		}
		
		int iOffset = 0;
		int jOffset = 0;
		
		ArrayList<Integer> blCount= new ArrayList<>();
		for (int l = 0; l < (this.getBoardSize() / 3); l++) {
			for (int k = 0; k < (this.getBoardSize() / 3); k++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						Node current = board[i+iOffset][j+jOffset];
						if (blCount.contains(current.getNumber())) return false;
						blCount.add(current.getNumber());
						blockCount+= current.getNumber();
					}
				}
				if (blockCount != 45) return false;
				blockCount = 0;
				blCount= new ArrayList<>();
				
				jOffset += 3;
			}
			jOffset = 0;
			iOffset += 3;
		}
		
		return true;
	}
	
	private boolean isValidState() {
		int[][] matrix = this.getGameMatrix();
		int[] counts = this.getNumCounts();
		int checkCount = 0;
		int blockCount = 0;
		
		for (int a = 1; a < this.getBoardSize() + 1; a++) {
			if (counts[a] > 9) return false;
		}
		
		ArrayList<Integer> rowCount = new ArrayList<>();
		for (int i = 0; i < this.getBoardSize(); i++) {
			for (int j = 0; j < this.getBoardSize(); j++) {
				int current = matrix[i][j];
				if ((current != 0) && rowCount.contains(current)) return false;
				rowCount.add(current);
				checkCount += current;
			}
			if (checkCount > 45) return false;
			checkCount = 0;
			rowCount = new ArrayList<>();
		}
		
		ArrayList<Integer> colCount = new ArrayList<>();
		for (int i = 0; i < this.getBoardSize(); i++) {
			for (int j = 0; j < this.getBoardSize(); j++) {
				int current = matrix[j][i];
				if ((current != 0) && colCount.contains(current)) return false;
				colCount.add(current);
				checkCount += current;
			}
			if (checkCount > 45) return false;
			checkCount = 0;
			colCount = new ArrayList<>();
		}
		
		int iOffset = 0;
		int jOffset = 0;
		
		ArrayList<Integer> blCount = new ArrayList<>();
		for (int l = 0; l < (this.getBoardSize() / 3); l++) {
			for (int k = 0; k < (this.getBoardSize() / 3); k++) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						int current = matrix[i+iOffset][j+jOffset];
						if ((current != 0) && blCount.contains(current)) return false;
						blCount.add(current);
						blockCount += current;
					}
				}
				if (blockCount > 45) return false;
				blockCount = 0;
				blCount = new ArrayList<>();
				jOffset += 3;
			}
			jOffset = 0;
			iOffset += 3;
		}
		
		return true;
	}
	
	public int[][] getGameMatrix() {
		return gameMatrix;
	}

	public void setGameMatrix(int[][] gameMatrix) {
		this.gameMatrix = gameMatrix;
	}
	
	public int[] getNumCounts() {
		return numCounts;
	}

	public void setNumCounts(int[] numCounts) {
		this.numCounts = numCounts;
	}
	
	public Node[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Node[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
}
