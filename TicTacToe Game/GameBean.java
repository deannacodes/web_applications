package lab6;



public class GameBean {
	
	private int currentPlayer;
	private String currentPlayerName;
	private String[][] gameBoard;
	private boolean gameOver;
	private int winningPlayer;
	private String winningPlayerName;
	private int moveCount;
	
	
	public GameBean() {
		//X player = 0 and O player = 1
		this.currentPlayer = 0;
		this.currentPlayerName = "Player 1 (X)";
		
		this.gameBoard = new String [3][3];
		for( int i = 0 ; i < 3 ; i++ ) {
			for ( int j = 0 ; j < 3 ; j++ ) {
				this.gameBoard[i][j] = "";
			}
		}
		
		this.winningPlayer = -1;
		this.winningPlayerName = "";
		this.gameOver = false;
		this.moveCount = 0;
	}

	public String getCurrentPlayerName() {
		return currentPlayerName;
	}

	public void setCurrentPlayerName() {
		if (getCurrentPlayer() == 0 ) {
			this.currentPlayerName = "Player 1 (X)";
		} else if (getCurrentPlayer() == 1 ){
			this.currentPlayerName = "Player 2 (O)";
		} 
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public String[][] getGameBoard() {
		return gameBoard;
	}

	public void setBoardVal(int row, int col) {
		if (gameBoard[row][col] == "" ) {
				
			if (currentPlayer == 0 ) {
				gameBoard[row][col] = "<i class=\"fas fa-times\"></i>";
			} else {
				gameBoard[row][col] = "<i class=\"far fa-circle\"></i>";
			}
			moveCount++;
			checkForWinner();
			
			if (!isGameOver()) {
				if (getCurrentPlayer() == 0) {
					setCurrentPlayer(1);
					setCurrentPlayerName();
				} else {
					setCurrentPlayer(0);
					setCurrentPlayerName();
				}
			}
			
		}
	}
	
	public void checkForWinner() {
		if ((gameBoard[0][0] == gameBoard[0][1]) && (gameBoard[0][0] == gameBoard[0][2]) && (gameBoard[0][0] != "")  ||
			(gameBoard[1][0] == gameBoard[1][1]) && (gameBoard[1][0] == gameBoard[1][2]) && (gameBoard[1][0] != "")  ||
			(gameBoard[2][0] == gameBoard[2][1]) && (gameBoard[2][0] == gameBoard[2][2]) && (gameBoard[2][0] != "")  ||
			(gameBoard[0][0] == gameBoard[1][0]) && (gameBoard[0][0] == gameBoard[2][0]) && (gameBoard[0][0] != "")  ||
			(gameBoard[0][1] == gameBoard[1][1]) && (gameBoard[0][1] == gameBoard[2][1]) && (gameBoard[0][1] != "")  ||
			(gameBoard[0][2] == gameBoard[1][2]) && (gameBoard[0][2] == gameBoard[2][2]) && (gameBoard[0][2] != "")  ||
			(gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[0][0] == gameBoard[2][2]) && (gameBoard[0][0] != "")  ||
			(gameBoard[0][2] == gameBoard[1][1]) && (gameBoard[0][2] == gameBoard[2][0]) && (gameBoard[0][2] != "")  ) {
			
			setGameOver(true);
			setWinningPlayer(currentPlayer);
			setWinningPlayerName();
			
		} else if (moveCount == 9) {
			setGameOver(true);
			setWinningPlayer(3);
			setWinningPlayerName();
		}
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getWinningPlayer() {
		return winningPlayer;
	}

	public void setWinningPlayer(int winningPlayer) {
		this.winningPlayer = winningPlayer;
		
	}
	
	public String getWinningPlayerName() {
		return this.winningPlayerName;
	}
	
	public void setWinningPlayerName() {
		if (getWinningPlayer() == 0 ) {
			this.winningPlayerName = "Player 1 (X)";
		} else if (getWinningPlayer() == 1 ){
			this.winningPlayerName = "Player 2 (O)";
		} else {
			this.winningPlayerName = "Cat's Game";
		}
	}


}
