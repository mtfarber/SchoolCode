package edu.wm.cs.cs301.game2048;

import java.util.Random;

public class State implements GameState {
	
	Random random = new Random();
	int[][] board = new int[4][4];
	

	public State(GameState original) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = original.getValue(i,j);
				}
			}
	}

	public State() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean equals(Object newBoard) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(board[i][j] != ((State) newBoard).getValue(i,j)){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int getValue(int xCoordinate, int yCoordinate) {
		int value = 0;
		value = board[xCoordinate][yCoordinate];
		return value;
	}

	@Override
	public void setValue(int xCoordinate, int yCoordinate, int value) {
		board[xCoordinate][yCoordinate] = value;
	}

	@Override
	public void setEmptyBoard() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = 0;
			}
		}
	}

	@Override
	public boolean addTile() {
		int randomx;
		int randomy;
		if(!this.isFull()) {
			do {
				randomx = random.nextInt(4);
				randomy = random.nextInt(4);
			} while(board[randomx][randomy] != 0);
			board[randomx][randomy] = random.nextBoolean() ? 2 : 4;
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean canMerge() {
		for (int i = 1; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				if ((board[i][j] == board[i-1][j]) || (board[i][j] == board[i][j-1])){
					return true;
				}
			}
		}
		if ((board[0][0] == board[1][0]) || (board[0][0] == board[0][1])) {
			return true;
		}
		return false;
	}

	@Override
	public boolean reachedThreshold() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] >= 2048) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int left() {
		int sum = 0;
		int currentPos;
		boolean firstRun;
		for (int i = 1; i < 4; i++) {
			for (int j = 0;j < 4; j++) {
					currentPos = i;
					firstRun = true;
				while(currentPos != 0 && (board[currentPos][j] == board[currentPos-1][j] || board[currentPos-1][j] == 0)) {
					
					if (firstRun == true && board[currentPos][j] == board[currentPos-1][j]) {
						board[currentPos-1][j] = 2*(board[currentPos][j]);
						sum += board[currentPos-1][j];
						board[currentPos][j] = 0;
						firstRun = false;
					}
					else if(board[currentPos-1][j] == 0 ) {
						board[currentPos-1][j] = (board[currentPos][j]);
						board[currentPos][j] = 0; 
					}
					currentPos -= 1;
					if(currentPos < 1) {
						break;
					}
				}
			}
		}
		return sum;
	}

	@Override
	public int right() {
		int sum = 0;
		int currentPos;
		boolean firstRun;
		for (int i = 2; i >= 0; i--) {
			for (int j = 0;j < 4; j++) {
					currentPos = i;
					firstRun = true;
				while((board[currentPos][j] == board[currentPos+1][j] || board[currentPos+1][j] == 0)) {
					
					if (firstRun == true && board[currentPos][j] == board[currentPos+1][j]) {
						board[currentPos+1][j] = 2*(board[currentPos][j]);
						sum += board[currentPos+1][j];
						board[currentPos][j] = 0;
						firstRun = false;
					}
					else if(board[currentPos+1][j] == 0 ){
						board[currentPos+1][j] = (board[currentPos][j]);
						board[currentPos][j] = 0; 
					}
					currentPos += 1;
					if(currentPos > 2) {
						break;
					}
				}
			}
		}
		return sum;
	}

	@Override
	public int down() {
		int sum = 0;
		int currentPos;
		boolean firstRun;
		for (int i = 0; i < 4; i++) {
			for (int j = 2;j >= 0; j--) {
					currentPos = j;
					firstRun = true;
				while((board[i][currentPos] == board[i][currentPos+1] || board[i][currentPos+1] == 0)) {
					
					if (firstRun == true && board[i][currentPos] == board[i][currentPos+1]) {
						board[i][currentPos+1] = 2*(board[i][currentPos+1]);
						sum += board[i][currentPos+1];
						board[i][currentPos] = 0;
						firstRun = false;
					}
					else if(board[i][currentPos+1] == 0){
						board[i][currentPos+1] = (board[i][currentPos]);
						board[i][currentPos] = 0; 
					}
					currentPos += 1;
					if(currentPos > 2) {
						break;
					}
				}
			}
		}
		return sum;
	}

	@Override
	public int up() {
		int sum = 0;
		int currentPos;
		boolean firstRun;
		for (int i = 0; i < 4; i++) {
			for (int j = 1;j < 4; j++) {
					currentPos = j;
					firstRun = true;
				while(currentPos != 0 && (board[i][currentPos] == board[i][currentPos-1] || board[i][currentPos-1] == 0)) {
					
					if (firstRun == true && board[i][currentPos] == board[i][currentPos-1]) {
						board[i][currentPos-1] = 2*(board[i][currentPos-1]);
						sum += board[i][currentPos-1];
						board[i][currentPos] = 0;
						firstRun = false;
					}
					else if(board[i][currentPos-1]==0){
						board[i][currentPos-1] = (board[i][currentPos]);
						board[i][currentPos] = 0; 
					}
					currentPos -= 1;
					if(currentPos < 1) {
						break;
					}
				}
			}
		}
		return sum;
	}

}
