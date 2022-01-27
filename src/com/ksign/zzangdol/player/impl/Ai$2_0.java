package com.ksign.zzangdol.player.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ksign.zzangdol.player.Player;

public class Ai$2_0 extends Player {
	private static int[] scoreMine = { 23, 29, 37, 100, 100 };
	private static int[] scoreOpposite = { 10, 20, 30, 60, 60 };

	public Ai$2_0(int mine, int opposite) {
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
//					System.out.println("x : " + w + ", y : " + h + ", score : " + newScore);
				} else {
					if(newScore.size() == currentScore.size()) if(newScore.get(0) > 0) System.out.println("x : " + w + ", y : " + h + ", score : " + newScore);
					if(!compareScore(currentScore, newScore)) {
						currentScore = newScore;
						position[0] = w;
						position[1] = h;
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
		List<Integer> myScoreList = new ArrayList<Integer>(8);
		List<Integer> oppositeScoreList = new ArrayList<Integer>(8);
		Map<String, Object> positionScoreMap = null;

		if (mBoard[x][y] == empty) {
			positionScoreMap = evaluationPosition(x, y, "N", true, null); 
			myScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "S", true, (Map<String, String>)positionScoreMap.get("position"));
			myScoreList.add((int)positionScoreMap.get("score"));

			positionScoreMap = evaluationPosition(x, y, "N", false, null);
			oppositeScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "S", false, (Map<String, String>)positionScoreMap.get("position"));
			oppositeScoreList.add((int)positionScoreMap.get("score"));

			positionScoreMap = evaluationPosition(x, y, "W", true, null);
			myScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "E", true, (Map<String, String>)positionScoreMap.get("position"));
			myScoreList.add((int)positionScoreMap.get("score"));

			positionScoreMap = evaluationPosition(x, y, "W", false, null);
			oppositeScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "E", false, (Map<String, String>)positionScoreMap.get("position"));
			oppositeScoreList.add((int)positionScoreMap.get("score"));

			positionScoreMap = evaluationPosition(x, y, "NW", true, null);
			myScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "SE", true, (Map<String, String>)positionScoreMap.get("position"));
			myScoreList.add((int)positionScoreMap.get("score"));
			
			positionScoreMap = evaluationPosition(x, y, "NW", false, null);
			oppositeScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "SE", false, (Map<String, String>)positionScoreMap.get("position"));
			oppositeScoreList.add((int)positionScoreMap.get("score"));
			
			positionScoreMap = evaluationPosition(x, y, "NE", true, null);
			myScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "SW", true, (Map<String, String>)positionScoreMap.get("position"));
			myScoreList.add((int)positionScoreMap.get("score"));
			
			positionScoreMap = evaluationPosition(x, y, "NE", false, null);
			oppositeScoreList.add((int)positionScoreMap.get("score"));
			positionScoreMap = evaluationPosition(x, y, "SW", false, (Map<String, String>)positionScoreMap.get("position"));
			oppositeScoreList.add((int)positionScoreMap.get("score"));
		}

		// 3x3, 4x3 체크
		boolean threeThree = false;
		for(int i=0; i<oppositeScoreList.size(); i++) {
			if(threeThree && (oppositeScoreList.get(i) == 20 || oppositeScoreList.get(i) == 30)) {
				System.out.println("3x3.... x : " + x + ", y : " + y + ", oppositeScoreList : " + oppositeScoreList);
				oppositeScoreList.remove(i);
				oppositeScoreList.add(i, 49);
				break;
			}
			if(oppositeScoreList.get(i) == 20 || oppositeScoreList.get(i) == 30) {
				threeThree = true;
			}
		}

		for(int i=0; i<myScoreList.size(); i++) {
			if(myScoreList.get(i) < oppositeScoreList.get(i)) {
				myScoreList.remove(i);
				myScoreList.add(i, oppositeScoreList.get(i));
			}
		}
		
		Collections.sort(myScoreList, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				if(arg0 > arg1) return -1;
				else if(arg0 < arg1) return 1;
				return 0;
			}
		});
		return myScoreList;
	}

	private Map<String, Object> evaluationPosition(int x, int y, String direction, boolean myTurn, Map<String, String> reversePosition) {
		int score = 0;
		int forwardInt = 1;
		int backwardInt = 1;
		int myScoreIndex = 0;
		int oppositeScoreIndex = 0;
		int blockCount = 0;
		int emptyCount = 0;
		int backEmptyCount = 0;
		boolean goForward = true;
		Map<String, String> positionMap = new HashMap<String, String>();

		for(int i=0; i<5; i++) {
			try {
				if(goForward) {
					int[] forwardPosition = getPosition(direction, x, y, forwardInt);
					positionMap.put(forwardPosition[0] + "," + forwardPosition[1], null);
					if(checkOutOfStore(forwardPosition)) {
						blockCount++;
						goForward = false;
					}
					forwardInt++;
					int forwardStorePosition = mBoard[forwardPosition[0]][forwardPosition[1]];
					if (forwardStorePosition == empty) {
						emptyCount++;
						goForward = false;
					} else if (forwardStorePosition == (myTurn ? myStone : oppositeStone)) {
						score = (myTurn ? scoreMine[myScoreIndex++] : scoreOpposite[oppositeScoreIndex++]);
					} else if(forwardStorePosition == (myTurn ? oppositeStone : myStone)) {
						blockCount++;
						goForward = false;
					}
				} else {
					int[] backwardPosition = getPosition(getReverseDirection(direction), x, y, backwardInt);
					positionMap.put(backwardPosition[0] + "," + backwardPosition[1], null);
					if(checkOutOfStore(backwardPosition)) {
						blockCount++;
						break;
					}
					int backwardStorePosition = mBoard[backwardPosition[0]][backwardPosition[1]];
					backwardInt++;
					if (backwardStorePosition == empty) {
						emptyCount++;
						backEmptyCount++;
						if(backEmptyCount >= 2) positionMap.remove(backwardPosition[0] + "," + backwardPosition[1]);
						if(emptyCount >= 2) break;
					} else if(backwardStorePosition == (myTurn ? myStone : oppositeStone)) {
						score = (myTurn ? scoreMine[myScoreIndex++] : scoreOpposite[oppositeScoreIndex++]);
					} else if(backwardStorePosition == (myTurn ? oppositeStone : myStone)) {
						blockCount++;
						break;
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		if(blockCount == 1) score -= 7;
		else if(blockCount >= 2) score = 0;
		
		if(positionMap.equals(reversePosition)) score = 0;
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("score", score);
		resultMap.put("position", positionMap);
		return resultMap;
	}

	private boolean checkOutOfStore(int[] position) {
		if(position[0] < 0 || position[1] < 0 || position[0] > 18 || position[1] > 18) {
			return true;
		}
		return false;
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