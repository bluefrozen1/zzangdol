package com.ksign.zzangdol.player.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ksign.zzangdol.player.Player;
import com.ksign.zzangdol.score.Score$2_3;

public class Ai$2_3 extends Player {

	Random random = new Random();
	public Ai$2_3(int mine, int opposite) {
		super(mine, opposite);
	}

	@Override
	public int[] getBestPosition(int x, int y) {
		int[] bestMove = new int[2];
		int[] position = new int[2];
		List<Integer> currentScore = new ArrayList<>(4);
		currentScore.add(0); currentScore.add(0); currentScore.add(0); currentScore.add(0);
		Map<int[], Integer> addScoreMap = getAddScoreMap();
		for (int w = 0; w < boardSize; w++) {
			for (int h = 0; h < boardSize; h++) {
				List<Integer> newScore = scoreEvaluation(w, h, addScoreMap);
				if(newScore != null && newScore.get(0) > 0) System.out.println("x : " + w + ", y : " + h + ", score : " + newScore);
				if(!compareScore(currentScore, newScore)) {
					currentScore = newScore;
					position[0] = w;
					position[1] = h;
				}
			}
		}
		bestMove[0] = position[0];
		bestMove[1] = position[1];

		if(currentScore.get(0) <= 1) {
			bestMove[0] = 9;
			bestMove[1] = 9;
		}

		return bestMove;
	}

	private boolean compareScore(List<Integer> currentScore, List<Integer> newScore) {
		if(newScore == null || currentScore.size() != newScore.size()) return true;
		for(int i=0; i<currentScore.size(); i++) {
			if(currentScore.get(i) > newScore.get(i)) return true;
			else if(currentScore.get(i) < newScore.get(i)) return false;
		}
		return random.nextBoolean();
	}

	public List<Integer> scoreEvaluation(int x, int y, Map<int[], Integer> addScoreMap) {
		List<Integer> attackScoreList = new ArrayList<Integer>(4);
		List<Integer> defenceScoreList = new ArrayList<Integer>(4);

		if (this.mBoard[x][y] == empty) {
			attackScoreList.add(evaluationPosition(x, y, "N", true, addScoreMap));
			attackScoreList.add(evaluationPosition(x, y, "E", true, addScoreMap));
			attackScoreList.add(evaluationPosition(x, y, "NE", true, addScoreMap));
			attackScoreList.add(evaluationPosition(x, y, "SE", true, addScoreMap));

			defenceScoreList.add(evaluationPosition(x, y, "N", false, addScoreMap));
			defenceScoreList.add(evaluationPosition(x, y, "E", false, addScoreMap));
			defenceScoreList.add(evaluationPosition(x, y, "NE", false, addScoreMap));
			defenceScoreList.add(evaluationPosition(x, y, "SE", false, addScoreMap));
		} else {
			return null;
		}

		// 3x3, 4x3 체크
		boolean threeThree = false;
		for(int i=0; i<attackScoreList.size(); i++) {
			if(threeThree && (attackScoreList.get(i) >= 40)) {
				System.out.println("3x3 Attack. x : " + x + ", y : " + y + ", myScoreList : " + attackScoreList);
				if(Score$2_3.THREE_THREE_ATTACK_SCORE > attackScoreList.get(i)) {
					attackScoreList.remove(i);
					attackScoreList.add(i, Score$2_3.THREE_THREE_ATTACK_SCORE);
				}
				break;
			}
			if(attackScoreList.get(i) >= 40) {
				threeThree = true;
			}
		}

		threeThree = false;
		for(int i=0; i<defenceScoreList.size(); i++) {
			if(threeThree && (defenceScoreList.get(i) >= 28)) {
				System.out.println("3x3 Defence. x : " + x + ", y : " + y + ", oppositeScoreList : " + defenceScoreList);
				if(Score$2_3.THREE_THREE_DEFENCE_SCORE > defenceScoreList.get(i)) {
					defenceScoreList.remove(i);
					defenceScoreList.add(i, Score$2_3.THREE_THREE_DEFENCE_SCORE);
				}
				break;
			}
			if(defenceScoreList.get(i) >= 28) {
				threeThree = true;
			}
		}

		for(int i=0; i<attackScoreList.size(); i++) {
			if(attackScoreList.get(i) < defenceScoreList.get(i)) {
				attackScoreList.remove(i);
				attackScoreList.add(i, defenceScoreList.get(i));
			}
		}

		Collections.sort(attackScoreList, new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				if(arg0 > arg1) return -1;
				else if(arg0 < arg1) return 1;
				return 0;
			}
		});
		return attackScoreList;
	}

	private String getScoreMapString(int stone, boolean isAttack) {
		if(stone == empty) return "-";
		if(isAttack) {
			if(stone == myStone) return "o";
			else if(stone == oppositeStone) return "x";
		} else {
			if(stone == myStone) return "x";
			else if(stone == oppositeStone) return "o";
		}
		return null;
	}

	private int evaluationPosition(int x, int y, String direction, boolean isAttack, Map<int[], Integer> addScoreMap) {
		int score = 0;
		int[] position = null;
		StringBuilder sb = new StringBuilder();
		for(int i=5; i>=1; i--) {
			position = getOffsetPosition(getReverseDirection(direction), x, y, i);
			if(checkOutOfStore(position)) {
				if(isAttack) sb.append("x");
				else sb.append("/");
			} else {
				sb.append(getScoreMapString(this.mBoard[position[0]][position[1]], isAttack));
			}
		}
		sb.append(getScoreMapString(this.mBoard[x][y], isAttack));
		for(int i=1; i<=5; i++) {
			position = getOffsetPosition(direction, x, y, i);
			if(checkOutOfStore(position)) {
				if(isAttack) sb.append("x");
				else sb.append("/");
			} else sb.append(getScoreMapString(this.mBoard[position[0]][position[1]], isAttack));
		}

		int[] scoreArray = null;
		Map<String, int[]> scoreMap = isAttack ? Score$2_3.getScoreMap() : Score$2_3.getDefenceScoreMap();
		for(int i=0; i<5; i++) {
			boolean isReverse = false;
			String scoreStr = sb.substring(0+i, 7+i);
			scoreArray = scoreMap.get(scoreStr);
			
			if(scoreArray == null) {
				scoreArray = scoreMap.get(reverseString(scoreStr));
				isReverse = true;
			}
			if(scoreArray != null) {
				int curScore = scoreArray[4-i];
				if(isReverse) curScore = scoreArray[i];
				if(addScoreMap != null) {
					for(Map.Entry<int[], Integer> entry : addScoreMap.entrySet()) {
						if(x == entry.getKey()[0] && y == entry.getKey()[1]) {
							curScore += entry.getValue();
							break;
						}
					}
				}
				if(curScore > score) {
					score = curScore;
					System.out.println("x : " + x + ", y : " + y + ", i : " + i + ", scoreMap : " + sb.substring(0+i, 7+i) + ", score : " + score + ", " + (isAttack ? "Attack" : "Defence"));
				}
			}
		}
		return score;
	}

	private boolean checkOutOfStore(int[] position) {
		if(position[0] < 0 || position[1] < 0 || position[0] > boardSize - 1 || position[1] > boardSize - 1) {
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

	private static int[] getOffsetPosition(String direction, int positionX, int positionY, int offset) {
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

	private static String reverseString(String s) {
	    return (new StringBuffer(s)).reverse().toString();
	  }

	private Map<int[], Integer> getAddScoreMap() {
		if(playStoneList.size() == 4 || playStoneList.size() == 5) {
			int[] firstMyStone = playStoneList.get(1);
			int[] secondMyStone = playStoneList.get(3);
			if(isBlack) {
				firstMyStone = playStoneList.get(0);
				secondMyStone = playStoneList.get(2);
			}
			// 내 돌 2개가 가로로 나열된 경우
			if(firstMyStone[1] == secondMyStone[1]
					&& (firstMyStone[0] - secondMyStone[0] == 1 || firstMyStone[0] - secondMyStone[0] == -1)) {
				int[] leftStone = firstMyStone;
				int[] rightStone = secondMyStone;
				if(firstMyStone[0] > secondMyStone[0]) {
					leftStone = secondMyStone;
					rightStone = firstMyStone;
				}
				Map<int[], Integer> addScoreMap = new HashMap<>();
				addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1] - 1}, 1);
				addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1] + 1}, 1);
				addScoreMap.put(new int[]{leftStone[0], leftStone[1] - 2}, 1);
				addScoreMap.put(new int[]{leftStone[0], leftStone[1] + 2}, 1);
				addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1] + 1}, 1);
				addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1] - 1}, 1);
				addScoreMap.put(new int[]{rightStone[0], rightStone[1] - 2}, 1);
				addScoreMap.put(new int[]{rightStone[0], rightStone[1] + 2}, 1);
				return addScoreMap;

			// 내 돌 2개가 새로로 나열된 경우
			} else if(firstMyStone[0] == secondMyStone[0]
					&& (firstMyStone[1] - secondMyStone[1] == 1 || firstMyStone[1] - secondMyStone[1] == -1)) {
				int[] topStone = firstMyStone;
				int[] bottomStone = secondMyStone;
				if(firstMyStone[1] > secondMyStone[1]) {
					topStone = secondMyStone;
					bottomStone = firstMyStone;
				}
				Map<int[], Integer> addScoreMap = new HashMap<>();
				addScoreMap.put(new int[]{topStone[0] - 1, topStone[1] - 1}, 1);
				addScoreMap.put(new int[]{topStone[0] + 1, topStone[1] + 1}, 1);
				addScoreMap.put(new int[]{topStone[0] - 2, topStone[1]}, 1);
				addScoreMap.put(new int[]{topStone[0] + 2, topStone[1]}, 1);
				addScoreMap.put(new int[]{bottomStone[0] - 2, bottomStone[1]}, 1);
				addScoreMap.put(new int[]{bottomStone[0] + 2, bottomStone[1]}, 1);
				addScoreMap.put(new int[]{bottomStone[0] - 1, bottomStone[1] + 1}, 1);
				addScoreMap.put(new int[]{bottomStone[0] + 1, bottomStone[1] + 1}, 1);
				return addScoreMap;

			// 내 돌 2개가 대각선으로 나열된 경우
			} else if((firstMyStone[0] == secondMyStone[0] - 1 && firstMyStone[1] == secondMyStone[1] - 1)
					|| (firstMyStone[0] == secondMyStone[0] - 1 && firstMyStone[1] == secondMyStone[1] + 1)
					|| (firstMyStone[0] == secondMyStone[0] + 1 && firstMyStone[1] == secondMyStone[1] - 1)
					|| (firstMyStone[0] == secondMyStone[0] + 1 && firstMyStone[1] == secondMyStone[1] + 1)) {
				int[] leftStone = firstMyStone;
				int[] rightStone = secondMyStone;
				if(firstMyStone[0] > secondMyStone[0]) {
					leftStone = secondMyStone;
					rightStone = firstMyStone;
				}
				Map<int[], Integer> addScoreMap = new HashMap<>();
				if(leftStone[1] < rightStone[1]) {
					addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1]}, 1);
					addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1] + 1}, 1);
					addScoreMap.put(new int[]{leftStone[0], leftStone[1] - 1}, 1);
					addScoreMap.put(new int[]{leftStone[0], leftStone[1] + 2}, 1);
					addScoreMap.put(new int[]{rightStone[0], rightStone[1] - 2}, 1);
					addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1] - 1}, 1);
					addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1]}, 1);
					addScoreMap.put(new int[]{rightStone[0], rightStone[1] + 1}, 1);
				} else {
					addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1] - 1}, 1);
					addScoreMap.put(new int[]{leftStone[0] - 1, leftStone[1]}, 1);
					addScoreMap.put(new int[]{leftStone[0], leftStone[1] - 2}, 1);
					addScoreMap.put(new int[]{leftStone[0], leftStone[1] + 1}, 1);
					addScoreMap.put(new int[]{rightStone[0], rightStone[1] - 1}, 1);
					addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1]}, 1);
					addScoreMap.put(new int[]{rightStone[0] + 1, rightStone[1] + 1}, 1);
					addScoreMap.put(new int[]{rightStone[0] + 0, rightStone[1] + 2}, 1);
				}
				return addScoreMap;
			}
		}
		return null;
	}
}