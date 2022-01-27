package com.ksign.zzangdol.player.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.ksign.zzangdol.player.Player;

public class Ai$1_0 extends Player{
	private static int[] scoreOwn = { 21, 29, 37, 100, 100 };
	private static int[] scoreOther = { 10, 20, 30, 50, 50 };
	
	public Ai$1_0(int mine, int opposite) {
		super(mine, opposite);
	}

	@Override
	public int[] getBestPosition(int x, int y) {
		List<Integer> currentScore = null;
		int[] bestMove = new int[3];
		int[] position = new int[2];
		for (int w = 0; w < 19; w++) {
			for (int h = 0; h < 19; h++) {
				List<Integer> newScore = scoreEvaluation(w, h);
				if(currentScore == null) {
					currentScore = newScore;
					System.out.println("x : " + w + ", y : " + h + ", score : " + newScore);
				} else {
					if(!compareScore(currentScore, newScore)) {
						currentScore = newScore;
						position[0] = w;
						position[1] = h;
						System.out.println("x : " + w + ", y : " + h + ", score : " + newScore);
					}
				}
			}
		}
		bestMove[0] = position[0];
		bestMove[1] = position[1];
		
		if(currentScore != null && currentScore.get(0) == 0) {
			Random random = new Random();
			bestMove[0] = random.nextInt(10) + 5;
			bestMove[1] = random.nextInt(10) + 5;
		}
		
		return bestMove;
	}

	private boolean compareScore(List<Integer> currentScore, List<Integer> newScore) {
		if(currentScore.size() != newScore.size()) return true;
		for(int i=0; i<currentScore.size(); i++) {
			if(currentScore.get(i) > newScore.get(i)) return true;
			else if(currentScore.get(i) < newScore.get(i)) return false;
		}
		return true;
	}

	public List<Integer> scoreEvaluation(int x, int y) {
		List<Integer> valueList = new ArrayList<Integer>(8);

		if (mBoard[x][y] == 0) {
			valueList.add(test(x, y, "N"));
			valueList.add(test(x, y, "S"));
			valueList.add(test(x, y, "W"));
			valueList.add(test(x, y, "E"));
			valueList.add(test(x, y, "NW"));
			valueList.add(test(x, y, "SE"));
			valueList.add(test(x, y, "NE"));
			valueList.add(test(x, y, "SW"));
		}
		
		Collections.sort(valueList, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				if(arg0 > arg1) return -1;
				else if(arg0 < arg1) return 1;
				return 0;
			}
		});
		return valueList;
	}

	/**
	 * @param store
	 * @param x
	 * @param y
	 * @param ai
	 * @param valueN
	 * @param scoreOwn
	 * @param scoreOther
	 * @param player
	 * @return
	 */
	private int test(int x, int y, String direction) {
		int value1 = 0;
		int value2 = 0;
		int forwardInt = 1;
		int backwardInt = 1;
		boolean keepGo = false;
		
		for(int i=0; i<5; i++) {
			try {
				int[] forwardPosition = getPosition(direction, x, y, forwardInt);
				int[] backwardPosition = getPosition(getReverseDirection(direction), x, y, backwardInt);
				if(forwardPosition[0] < 0 || forwardPosition[1] < 0 || forwardPosition[0] > 18 || forwardPosition[1] > 18) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				}
				if(backwardPosition[0] < 0 || backwardPosition[1] < 0 || backwardPosition[0] > 18 || backwardPosition[1] > 18) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				}
				if (mBoard[forwardPosition[0]][forwardPosition[1]] == myStone) {
					forwardInt++;
					keepGo = true;
					value1 = scoreOwn[i];
				} else if(mBoard[forwardPosition[0]][forwardPosition[1]] == oppositeStone) {
					value1 -= 7;
					if(value1 < 0) value1 = 0;
					break;
				} else if(keepGo && mBoard[backwardPosition[0]][backwardPosition[1]] == myStone) {
					backwardInt++;
					value1 = scoreOwn[i];
				} else if(keepGo && mBoard[backwardPosition[0]][backwardPosition[1]] == oppositeStone) {
					value1 -= 7;
					if(value1 < 0) value1 = 0;
					break;
				} else {
					break;
				}
			} catch (Exception e) {
				continue;
			}
		}

		keepGo = false;
		forwardInt = 1;
		backwardInt = 1;
		for(int i=0; i<5; i++) {
			try {
				int[] forwardPosition = getPosition(direction, x, y, forwardInt);
				int[] backwardPosition = getPosition(getReverseDirection(direction), x, y, backwardInt);
				if(forwardPosition[0] < 0 || forwardPosition[1] < 0 || forwardPosition[0] > 18 || forwardPosition[1] > 18) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				}
				if(backwardPosition[0] < 0 || backwardPosition[1] < 0 || backwardPosition[0] > 18 || backwardPosition[1] > 18) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				}
				if (mBoard[forwardPosition[0]][forwardPosition[1]] == oppositeStone) {
					forwardInt++;
					keepGo = true;
					value2 = scoreOther[i];
				} else if (mBoard[forwardPosition[0]][forwardPosition[1]] == myStone) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				} else if(keepGo && mBoard[backwardPosition[0]][backwardPosition[1]] == oppositeStone) {
					backwardInt++;
					value2 = scoreOther[i];
				} else if(keepGo && mBoard[backwardPosition[0]][backwardPosition[1]] == myStone) {
					value2 -= 7;
					if(value2 < 0) value2 = 0;
					break;
				} else {
					break;
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		return Math.max(value1, value2);
	}

	private static String getReverseDirection(String direction) {
		switch (direction) {
			case "N":
				return "S";
			case "E":
				return "W";
			case "S":
				return "N";
			case "W":
				return "E";
			case "NW":
				return "SE";
			case "NE":
				return "SW";
			case "SW":
				return "NE";
			case "SE":
				return "NW";
			default :
				return null;
			}
	}
	private static int[] getPosition(String direction, int positionX, int positionY, int offset) {
		switch (direction) {
		case "N":
			positionY = positionY - offset;
			break;
		case "E":
			positionX = positionX + offset;
			break;
		case "S":
			positionY = positionY + offset;
			break;
		case "W":
			positionX = positionX - offset;
			break;
		case "NW":
			positionX = positionX - offset;
			positionY = positionY - offset;
			break;
		case "NE":
			positionX = positionX + offset;
			positionY = positionY - offset;
			break;
		case "SW":
			positionX = positionX - offset;
			positionY = positionY + offset;
			break;
		case "SE":
			positionX = positionX + offset;
			positionY = positionY + offset;
			break;
		default :
			break;
		}
		int[] result = {positionX, positionY};
		return result;
	}
}