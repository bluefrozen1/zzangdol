package com.ksign.zzangdol.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Player {

	protected int empty = 0;
	protected int myStone = 1;
	protected int oppositeStone = 2;

	protected int boardSize = 19; // default 19

	protected boolean isBlack = false;
	protected int[][] mBoard = null;
	protected List<int[]> playStoneList;

	public Player(int myStone, int oppositeStone) {
		this.myStone = myStone;
		this.oppositeStone = oppositeStone;
		this.playStoneList = new ArrayList<>();
		initBoard();
	}

	private void initBoard() {
		int x = 0;
		int y = 0;
		
		mBoard = new int[boardSize][boardSize];
		
		for (x = 0 ; x < boardSize ; x++) {
			for (y = 0 ; y < boardSize ; y++) {
				mBoard[x][y] = empty;
			}
		}
	}

	protected void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public int[] play(int x, int y) {
		int[] oppositeNewStone = {x, y};
		if(playStoneList.size() == 0 && x == -1 && y == -1) {
			this.isBlack = true;
		} else {
			playStoneList.add(oppositeNewStone);
			mBoard[x][y] = this.oppositeStone;
		}
		int[] myNewStone = this.getBestPosition(x, y);
		playStoneList.add(myNewStone);
		mBoard[myNewStone[0]][myNewStone[1]] = this.myStone;
		return myNewStone;
	}

	public abstract int[] getBestPosition(int x, int y);
}