package com.ksign.zzangdol.launcher;
import java.util.Scanner;

import com.ksign.zzangdol.player.Player;
import com.ksign.zzangdol.player.impl.Ai$1_0;
import com.ksign.zzangdol.player.impl.Ai$2_0;
import com.ksign.zzangdol.player.impl.Ai$2_1;
import com.ksign.zzangdol.player.impl.Ai$2_2;
import com.ksign.zzangdol.player.impl.Ai$2_3;
import com.ksign.zzangdol.player.impl.Human;

public class Test {
	public enum PLAYER {
		HUMAN(1),
		VERSION$1_0(2),
		VERSION$2_0(3),
		VERSION$2_1(4),
		VERSION$2_2(5),
		VERSION$2_3(6);

		private final int playerNum;
		PLAYER(int playerNum) { this.playerNum = playerNum; }
		public int getPlayerNum() { return playerNum; }
	}

	private static final int MINE = 1;
	private static final int OPPOSITE = 2;

	private static final int BOARD_SIZE = 19;

	private static void printOptionNum() {
		System.out.println("1 : Human");
		System.out.println("2 : Zzangdol Version 1.0");
		System.out.println("3 : Zzangdol Version 2.0");
		System.out.println("4 : Zzangdol Version 2.1");
		System.out.println("5 : Zzangdol Version 2.2");
		System.out.println("6 : Zzangdol Version 2.3");
	}

	public static void main(String[] args) {
		Scanner myScan = new Scanner(System.in);
		Player p1 = null;
		Player p2 = null;

		Test main = new Test();
		System.out.printf("Select first player:\n\n");
		printOptionNum();
		int optionNum = myScan.nextInt();
		p1 = main.getPlayer(optionNum, true);
		System.out.printf("Select second player:\n\n");
		printOptionNum();
		optionNum = myScan.nextInt();
		p2 = main.getPlayer(optionNum, false);

		main.playGame(p1, p2);
		System.out.printf("\n\nGoodbye!\n\n");
	}

	private Player getPlayer(int optionNum, boolean isFirst) {
		if(optionNum == PLAYER.HUMAN.getPlayerNum()) {
			return new Human();
		} else if(optionNum == PLAYER.VERSION$1_0.getPlayerNum()) {
			if(isFirst) return new Ai$1_0(MINE, OPPOSITE);
			else return new Ai$1_0(OPPOSITE, MINE);
		} else if(optionNum == PLAYER.VERSION$2_0.getPlayerNum()) {
			if(isFirst) return new Ai$2_0(MINE, OPPOSITE);
			else return new Ai$2_0(OPPOSITE, MINE);
		} else if(optionNum == PLAYER.VERSION$2_1.getPlayerNum()) {
			if(isFirst) return new Ai$2_1(MINE, OPPOSITE);
			else return new Ai$2_1(OPPOSITE, MINE);
		} else if(optionNum == PLAYER.VERSION$2_2.getPlayerNum()) {
			if(isFirst) return new Ai$2_2(MINE, OPPOSITE);
			else return new Ai$2_2(OPPOSITE, MINE);
		} else if(optionNum == PLAYER.VERSION$2_3.getPlayerNum()) {
			if(isFirst) return new Ai$2_3(MINE, OPPOSITE);
			else return new Ai$2_3(OPPOSITE, MINE);
		} else {
			return new Human();
		}
	}

	private void playGame(Player p1, Player p2) {
		int[][] store = createStore();
		int[] move = new int[2];
		int winner = 0;
		move[0] = -1;
		move[1] = -1;

		while(true) {
			printTable(store);
			move = p1.play(move[0], move[1]);
			store[move[0]][move[1]] = MINE;
			System.out.println("First Move X : " + move[0] + ", Y : " + move[1]);

			winner = checkWin(store);
			if(winner > 0) break;

			printTable(store);
			move = p2.play(move[0], move[1]);
			store[move[0]][move[1]] = OPPOSITE;
			System.out.println("Second Move X : " + move[0] + ", Y : " + move[1]);

			winner = checkWin(store);
			if(winner > 0) break;
			
		};
		printTable(store);
		System.out.println("Winnwe is " + winner);
	}

	public static void printTable(int [][] store) {
		for (int i = 0; i < BOARD_SIZE + 2; i++) {
			if ((i > 0) && (i <= BOARD_SIZE)) {
				System.out.printf(" %d ", new Object[] { Integer.valueOf((i - 1) % 10) });
			} else {
				System.out.printf(" # ", new Object[0]);
			}
		}
		System.out.printf("\n", new Object[0]);
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int z = 0; z < BOARD_SIZE; z++)
			{
				if (z == 0) {
					System.out.printf(" %d ", new Object[] { Integer.valueOf(i % 10) });
				}
				if (store[z][i] == 0) {
					System.out.printf(" _ ", new Object[0]);
				} else {
					System.out.printf(" %s ", new Object[] { Integer.valueOf(store[z][i]) == 1 ? "●" : "○" });
				}
				if (z == BOARD_SIZE - 1) {
					System.out.printf(" %d\n", new Object[] { Integer.valueOf(i % 10) });
				}
			}
		}
		for (int i = 0; i < BOARD_SIZE + 2; i++) {
			if ((i > 0) && (i <= BOARD_SIZE)) {
				System.out.printf(" %d ", new Object[] { Integer.valueOf((i - 1) % 10) });
			} else {
				System.out.printf(" # ", new Object[0]);
			}
		}
		System.out.printf("\n", new Object[0]);
	}

	public static int[][] createStore() {
		int[][] store = new int[BOARD_SIZE][BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int z = 0; z < BOARD_SIZE; z++) {
				store[i][z] = 0;
			}
		}
		return store;
	}

	public static int checkWin(int[][] store) {
		int playerOneCount = 0;
		int playerTwoCount = 0;
		
		for (int z = 0; z < BOARD_SIZE; z++) {
			playerOneCount = 0;
			playerTwoCount = 0;
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (store[z][i] == 0) {
					playerOneCount = 0;
					playerTwoCount = 0;
				} else if (store[z][i] == 1) {
					playerOneCount++;
					playerTwoCount = 0;
				} else if (store[z][i] == 2) {
					playerOneCount = 0;
					playerTwoCount++;
				} 

				if (playerOneCount == 5) {
					System.out.println("N to S");
					return 1;
				} 
				if (playerTwoCount == 5) {
					System.out.println("N to S");
					return 2;
				}
			}
		}

		for (int z = 0; z < BOARD_SIZE; z++) {
			playerOneCount = 0;
			playerTwoCount = 0;
			for (int i = 0; i < BOARD_SIZE; i++) {
				if (store[i][z] == 0) {
					playerOneCount = 0;
					playerTwoCount = 0;
				}
				else if (store[i][z] == 1) {
					playerOneCount++;
					playerTwoCount = 0;
				} else if (store[i][z] == 2) {
					playerOneCount = 0;
					playerTwoCount++;
				}

				if (playerOneCount == 5) {
					System.out.println("W to E");
					return 1;
				}
				if (playerTwoCount == 5) {
					System.out.println("W to E");
					return 2;
				}
			}
		}
		for (int h = 0; h < BOARD_SIZE - 5; h++) {
			for (int l = 0; l < BOARD_SIZE - 5; l++) {
				playerOneCount = 0;
				playerTwoCount = 0;
				int z = h;
				for (int i = l; (z <= h + 5) && (i <= l + 5); z++) {
					if (store[z][i] == 0) {
						playerOneCount = 0;
						playerTwoCount = 0;
					}
					else if (store[z][i] == 1) {
						playerOneCount++;
						playerTwoCount = 0;
					}
					else if (store[z][i] == 2) {
						playerOneCount = 0;
						playerTwoCount++;
					}

					if (playerOneCount == 5) {
						System.out.println("NW to SE");
						return 1;
					}
					if (playerTwoCount == 5) {
						System.out.println("NW to SE");
						return 2;
					}
					i++;
				}
			}
		}

		for (int h = 0; h < BOARD_SIZE - 5; h++) {
			for (int l = 0; l < BOARD_SIZE - 5; l++) {
				playerOneCount = 0;
				playerTwoCount = 0;
				int z = h;
				for (int i = l + 5; (z <= h + 5) && (i >= l - 5); z++) {
					if (store[z][i] == 0) {
						playerOneCount = 0;
						playerTwoCount = 0;
					} else if (store[z][i] == 1) {
						playerOneCount++;
						playerTwoCount = 0;
					} else if (store[z][i] == 2) {
						playerOneCount = 0;
						playerTwoCount++;
					}

					if (playerOneCount == 5) {
						System.out.println("SW to NE");
						return 1;
					}
					if (playerTwoCount == 5) {
						System.out.println("SW to NE");
						return 2;
					}
					i--;
				}
			}
		}
		return 0;
	}

	private static boolean isValidMove(int[][] store, int x, int y, int stone) {
		try
		{
			if (store[x][y] != 0) {
				return false;
			}
			store[x][y] = stone;
			return true;
		}
		catch (Exception e) {}
		return false;
	}
}