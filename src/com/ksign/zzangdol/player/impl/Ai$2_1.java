package com.ksign.zzangdol.player.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ksign.zzangdol.player.Player;
import com.ksign.zzangdol.score.Score;

public class Ai$2_1 extends Player {

	public Ai$2_1(int mine, int opposite) {
		super(mine, opposite);
	}

	@Override
	public int[] getBestPosition(int x, int y) {
		int[] bestMove = new int[2];
		int[] position = new int[2];
		List<Integer> currentScore = new ArrayList<>(4);
		currentScore.add(0); currentScore.add(0); currentScore.add(0); currentScore.add(0);
		for (int w = 0; w < boardSize; w++) {
			for (int h = 0; h < boardSize; h++) {
				List<Integer> newScore = scoreEvaluation(w, h);
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
			Random random = new Random();
			bestMove[0] = random.nextInt(10) + 5;
			bestMove[1] = random.nextInt(10) + 5;
		}

		return bestMove;
	}

	private boolean compareScore(List<Integer> currentScore, List<Integer> newScore) {
		if(newScore == null || currentScore.size() != newScore.size()) return true;
		for(int i=0; i<currentScore.size(); i++) {
			if(currentScore.get(i) > newScore.get(i)) return true;
			else if(currentScore.get(i) < newScore.get(i)) return false;
		}
		return true;
	}

	public List<Integer> scoreEvaluation(int x, int y) {
		List<Integer> attackScoreList = new ArrayList<Integer>(4);
		List<Integer> defenceScoreList = new ArrayList<Integer>(4);

		if (mBoard[x][y] == empty) {
			attackScoreList.add(evaluationPosition(x, y, "N", true));
			attackScoreList.add(evaluationPosition(x, y, "E", true));
			attackScoreList.add(evaluationPosition(x, y, "NE", true));
			attackScoreList.add(evaluationPosition(x, y, "SE", true));

			defenceScoreList.add(evaluationPosition(x, y, "N", false));
			defenceScoreList.add(evaluationPosition(x, y, "E", false));
			defenceScoreList.add(evaluationPosition(x, y, "NE", false));
			defenceScoreList.add(evaluationPosition(x, y, "SE", false));
		} else {
			return null;
		}

		// 3x3, 4x3 체크
		boolean threeThree = false;
		for(int i=0; i<attackScoreList.size(); i++) {
			if(threeThree && (attackScoreList.get(i) >= 44)) {
				System.out.println("3x3 Attack. x : " + x + ", y : " + y + ", myScoreList : " + attackScoreList);
				attackScoreList.remove(i);
				attackScoreList.add(i, Score.THREE_THREE_ATTACK_SCORE);
				break;
			}
			if(attackScoreList.get(i) >= 44) {
				threeThree = true;
			}
		}

		threeThree = false;
		for(int i=0; i<defenceScoreList.size(); i++) {
			if(threeThree && (defenceScoreList.get(i) >= 40)) {
				System.out.println("3x3 Defence. x : " + x + ", y : " + y + ", oppositeScoreList : " + defenceScoreList);
				defenceScoreList.remove(i);
				defenceScoreList.add(i, Score.THREE_THREE_DEFENCE_SCORE);
				break;
			}
			if(defenceScoreList.get(i) >= 40) {
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

	private int evaluationPosition(int x, int y, String direction, boolean isAttack) {
		int score = 0;
		int[] position = null;
		StringBuilder sb = new StringBuilder();
		for(int i=5; i>=1; i--) {
			position = getOffsetPosition(getReverseDirection(direction), x, y, i);
			if(checkOutOfStore(position)) {
				if(isAttack) sb.append("x");
				else sb.append("/");
			} else {
				sb.append(getScoreMapString(mBoard[position[0]][position[1]], isAttack));
			}
		}
		sb.append(getScoreMapString(mBoard[x][y], isAttack));
		for(int i=1; i<=5; i++) {
			position = getOffsetPosition(direction, x, y, i);
			if(checkOutOfStore(position)) {
				if(isAttack) sb.append("x");
				else sb.append("/");
			} else sb.append(getScoreMapString(mBoard[position[0]][position[1]], isAttack));
		}

		int[] scoreArray = null;
		Map<String, int[]> scoreMap = isAttack ? Score.getScoreMap() : Score.getDefenceScoreMap();
		for(int i=0; i<5; i++) {
			scoreArray = scoreMap.get(sb.substring(0+i, 7+i));
			if(scoreArray != null) {
				if(scoreArray[4-i] > score) {
					score = scoreArray[4-i];
					System.out.println("x : " + x + ", y : " + y + ", i : " + i + ", scoreMap : " + sb.substring(0+i, 7+i) + ", score : " + score);
				}
			}
		}
		if(isAttack) score = score * 11;
		else score = score * 10;
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
}