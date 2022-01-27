package com.ksign.zzangdol.score;
import java.util.HashMap;
import java.util.Map;


public class Score$2_4 {
	private static Map<String, int[]> attackScoreMap = new HashMap<String, int[]>();
	private static Map<String, int[]> defenceScoreMap = new HashMap<String, int[]>();

	public static final int THREE_THREE_ATTACK_SCORE = 54;
//	public static final int THREE_FOUR_ATTACK_SCORE = 70;
	public static final int THREE_THREE_DEFENCE_SCORE = 53;
	
	public static Map<String, int[]> getScoreMap() {
		return attackScoreMap;
	}

	public static Map<String, int[]> getDefenceScoreMap() {
		return defenceScoreMap;
	}

	static {
//		attackScoreMap.put("-------", new int[]{0,0,0,0,0});
		attackScoreMap.put("-----o-", new int[]{0,10,30,30,0});
		attackScoreMap.put("----o--", new int[]{0,30,30,0,30});
		attackScoreMap.put("---o---", new int[]{30,30,0,30,30});
		attackScoreMap.put("--o----", new int[]{30,0,30,30,0});
		attackScoreMap.put("-o-----", new int[]{0,30,30,10,0});
		attackScoreMap.put("----oo-", new int[]{0,40,50,0,0});
		attackScoreMap.put("---o-o-", new int[]{30,40,0,50,0});
		attackScoreMap.put("---oo--", new int[]{40,50,0,0,50});
		attackScoreMap.put("--o--o-", new int[]{30,0,45,45,0});
		attackScoreMap.put("--o-o--", new int[]{40,0,50,0,40});
		attackScoreMap.put("--oo---", new int[]{50,0,0,50,40});
		attackScoreMap.put("-o---o-", new int[]{0,35,35,35,0});
		attackScoreMap.put("-o--o--", new int[]{0,45,45,0,30});
		attackScoreMap.put("-o-o---", new int[]{0,50,0,40,25});
		attackScoreMap.put("-oo----", new int[]{0,0,50,40,25});
		attackScoreMap.put("---ooo-", new int[]{60,70,0,0,0});
		attackScoreMap.put("--o-oo-", new int[]{50,0,70,0,0});
		attackScoreMap.put("--oo-o-", new int[]{50,0,0,70,0});
		attackScoreMap.put("--ooo--", new int[]{70,0,0,0,70});
		attackScoreMap.put("-o--oo-", new int[]{0,50,60,0,0});
		attackScoreMap.put("-o-o-o-", new int[]{0,60,0,60,0});
		attackScoreMap.put("-o-oo--", new int[]{0,70,0,0,60});
		attackScoreMap.put("-oo--o-", new int[]{0,0,60,50,0});
		attackScoreMap.put("-oo-o--", new int[]{0,0,70,0,50});
		attackScoreMap.put("-ooo---", new int[]{0,0,0,70,60});
		attackScoreMap.put("--oooo-", new int[]{100,0,0,0,0});
		attackScoreMap.put("-o-ooo-", new int[]{0,100,0,0,0});
		attackScoreMap.put("-oo-oo-", new int[]{0,0,100,0,0});
		attackScoreMap.put("-ooo-o-", new int[]{0,0,0,100,0});
		attackScoreMap.put("-oooo--", new int[]{0,0,0,0,100});
		
//		attackScoreMap.put("x------", new int[]{0,0,0,0,0});
		attackScoreMap.put("x----o-", new int[]{0,0,30,30,0});
		attackScoreMap.put("x---o--", new int[]{0,0,30,0,30});
		attackScoreMap.put("x--o---", new int[]{0,30,0,30,0});
		attackScoreMap.put("x-o----", new int[]{0,0,30,30,0});
		attackScoreMap.put("xo-----", new int[]{0,20,30,0,0});
		attackScoreMap.put("x---oo-", new int[]{0,40,50,0,0});
		attackScoreMap.put("x--o-o-", new int[]{0,40,0,50,0});
		attackScoreMap.put("x--oo--", new int[]{0,40,0,0,45});
		attackScoreMap.put("x-o--o-", new int[]{0,0,40,40,0});
		attackScoreMap.put("x-o-o--", new int[]{0,0,40,0,40});
		attackScoreMap.put("x-oo---", new int[]{0,0,0,40,40});
		attackScoreMap.put("xo---o-", new int[]{0,30,30,30,0});
		attackScoreMap.put("xo--o--", new int[]{0,20,25,0,30});
		attackScoreMap.put("xo-o---", new int[]{0,30,0,30,0});
		attackScoreMap.put("xoo----", new int[]{0,0,30,30,0});
		attackScoreMap.put("x--ooo-", new int[]{40,70,0,0,0});
		attackScoreMap.put("x-o-oo-", new int[]{40,0,70,0,0});
		attackScoreMap.put("x-oo-o-", new int[]{40,0,0,70,0});
		attackScoreMap.put("x-ooo--", new int[]{50,0,0,0,70});
		attackScoreMap.put("xo--oo-", new int[]{0,40,50,0,0});
		attackScoreMap.put("xo-o-o-", new int[]{0,40,0,45,0});
		attackScoreMap.put("xo-oo--", new int[]{0,50,0,0,50});
		attackScoreMap.put("xoo--o-", new int[]{0,0,40,40,0});
		attackScoreMap.put("xoo-o--", new int[]{0,0,50,0,40});
		attackScoreMap.put("xooo---", new int[]{0,0,0,50,40});
		attackScoreMap.put("x-oooo-", new int[]{100,0,0,0,0});
		attackScoreMap.put("xo-ooo-", new int[]{0,100,0,0,0});
		attackScoreMap.put("xoo-oo-", new int[]{0,0,100,0,0});
		attackScoreMap.put("xooo-o-", new int[]{0,0,0,100,0});
		attackScoreMap.put("xoooo--", new int[]{0,0,0,0,100});
		
//		attackScoreMap.put("x-----x", new int[]{0,0,0,0,0});
		attackScoreMap.put("x----ox", new int[]{0,0,0,0,0}); 
		attackScoreMap.put("x---o-x", new int[]{0,0,20,0,0});
		attackScoreMap.put("x--o--x", new int[]{0,20,0,20,0});
		attackScoreMap.put("x-o---x", new int[]{0,0,20,0,0});
		attackScoreMap.put("xo----x", new int[]{0,0,0,0,0});
		attackScoreMap.put("x---oox", new int[]{0,0,20,0,0});
		attackScoreMap.put("x--o-ox", new int[]{0,20,0,0,0});
		attackScoreMap.put("x--oo-x", new int[]{0,20,0,0,0});
		attackScoreMap.put("x-o--ox", new int[]{0,0,20,0,0});
		attackScoreMap.put("x-o-o-x", new int[]{0,0,20,0,0});
		attackScoreMap.put("x-oo--x", new int[]{0,0,0,20,0});
		attackScoreMap.put("xo---ox", new int[]{0,0,0,0,0});
		attackScoreMap.put("xo--o-x", new int[]{0,0,20,0,0});
		attackScoreMap.put("xo-o--x", new int[]{0,0,0,20,0});
		attackScoreMap.put("xoo---x", new int[]{0,0,20,0,0});
		attackScoreMap.put("x--ooox", new int[]{40,45,0,0,0});
		attackScoreMap.put("x-o-oox", new int[]{40,0,45,0,0});
		attackScoreMap.put("x-oo-ox", new int[]{40,0,0,45,0});
		attackScoreMap.put("x-ooo-x", new int[]{20,0,0,0,20});
		attackScoreMap.put("xo--oox", new int[]{0,40,40,0,0});
		attackScoreMap.put("xo-o-ox", new int[]{0,40,0,40,0});
		attackScoreMap.put("xo-oo-x", new int[]{0,45,0,0,40});
		attackScoreMap.put("xoo--ox", new int[]{0,0,45,40,0});
		attackScoreMap.put("xoo-o-x", new int[]{0,0,45,0,40});
		attackScoreMap.put("xooo--x", new int[]{0,0,0,45,40});
		attackScoreMap.put("x-oooox", new int[]{100,0,0,0,0});
		attackScoreMap.put("xo-ooox", new int[]{0,100,0,0,0});
		attackScoreMap.put("xoo-oox", new int[]{0,0,100,0,0});
		attackScoreMap.put("xooo-ox", new int[]{0,0,0,100,0});
		attackScoreMap.put("xoooo-x", new int[]{0,0,0,0,100});
		
//		defenceScoreMap.put("-------", new int[]{0,0,0,0,0});
		defenceScoreMap.put("-----o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("----o--", new int[]{0,0,10,0,10});
		defenceScoreMap.put("---o---", new int[]{0,10,0,10,0});
		defenceScoreMap.put("--o----", new int[]{10,0,10,0,0});
		defenceScoreMap.put("-o-----", new int[]{0,10,0,0,0});
		defenceScoreMap.put("----oo-", new int[]{0,25,30,0,0});
		defenceScoreMap.put("---o-o-", new int[]{0,30,0,30,0});
		defenceScoreMap.put("---oo--", new int[]{15,30,0,0,30});
		defenceScoreMap.put("--o--o-", new int[]{10,0,15,15,0});
		defenceScoreMap.put("--o-o--", new int[]{15,0,30,0,15});
		defenceScoreMap.put("--oo---", new int[]{30,0,0,30,15});
		defenceScoreMap.put("-o---o-", new int[]{0,15,10,15,0});
		defenceScoreMap.put("-o--o--", new int[]{0,25,25,0,10});
		defenceScoreMap.put("-o-o---", new int[]{0,30,0,30,0});
		defenceScoreMap.put("-oo----", new int[]{0,0,30,15,0});
		defenceScoreMap.put("---ooo-", new int[]{45,55,0,0,0});
		defenceScoreMap.put("--o-oo-", new int[]{45,0,55,0,0});
		defenceScoreMap.put("--oo-o-", new int[]{45,0,0,55,0});
		defenceScoreMap.put("--ooo--", new int[]{55,0,0,0,55});
		defenceScoreMap.put("-o-o-o-", new int[]{0,45,0,45,0});
		defenceScoreMap.put("-o-oo--", new int[]{0,55,0,0,45});
		defenceScoreMap.put("-o--oo-", new int[]{0,45,45,0,0});
		defenceScoreMap.put("-oo--o-", new int[]{0,0,45,45,0});
		defenceScoreMap.put("-oo-o--", new int[]{0,0,55,0,45});
		defenceScoreMap.put("-ooo---", new int[]{0,0,0,55,45});
		defenceScoreMap.put("--oooo-", new int[]{90,0,0,0,0});
		defenceScoreMap.put("-o-ooo-", new int[]{0,90,0,0,0});
		defenceScoreMap.put("-oo-oo-", new int[]{0,0,90,0,0});
		defenceScoreMap.put("-ooo-o-", new int[]{0,0,0,90,0});
		defenceScoreMap.put("-oooo--", new int[]{0,0,0,0,90});
		
//		defenceScoreMap.put("x------", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x----o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("x---o--", new int[]{0,0,0,0,10});
		defenceScoreMap.put("x--o---", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x-o----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("xo-----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x---oo-", new int[]{0,25,30,0,0});
		defenceScoreMap.put("x--o-o-", new int[]{0,25,0,25,0});
		defenceScoreMap.put("x--oo--", new int[]{0,30,0,0,30});
		defenceScoreMap.put("x-o--o-", new int[]{0,0,15,15,0});
		defenceScoreMap.put("x-o-o--", new int[]{25,0,15,0,15});
		defenceScoreMap.put("x-oo---", new int[]{30,0,0,10,0});
		defenceScoreMap.put("xo---o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("xo--o--", new int[]{0,0,10,0,10});
		defenceScoreMap.put("xo-o---", new int[]{0,10,0,10,0});
		defenceScoreMap.put("xoo----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--ooo-", new int[]{32,55,0,0,0});
		defenceScoreMap.put("x-o-oo-", new int[]{55,0,55,0,0});
		defenceScoreMap.put("x-oo-o-", new int[]{55,0,0,55,0});
		defenceScoreMap.put("x-ooo--", new int[]{55,0,0,0,55});
		defenceScoreMap.put("xo--oo-", new int[]{0,31,32,0,0});
		defenceScoreMap.put("xo-o-o-", new int[]{0,32,0,32,0});
		defenceScoreMap.put("xo-oo--", new int[]{0,32,0,0,32});
		defenceScoreMap.put("xoo--o-", new int[]{0,0,31,31,0});
		defenceScoreMap.put("xoo-o--", new int[]{0,0,32,0,32});
		defenceScoreMap.put("xooo---", new int[]{0,0,0,32,31});
		defenceScoreMap.put("x-oooo-", new int[]{90,0,0,0,0});
		defenceScoreMap.put("xo-ooo-", new int[]{0,90,0,0,0});
		defenceScoreMap.put("xoo-oo-", new int[]{0,0,90,0,0});
		defenceScoreMap.put("xooo-o-", new int[]{0,0,0,90,0});
		defenceScoreMap.put("xoooo--", new int[]{0,0,0,0,90});

//		defenceScoreMap.put("x-----x", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x----ox", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x---o-x", new int[]{0,0,0,0,12});
		defenceScoreMap.put("x--o--x", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x-o---x", new int[]{12,0,0,0,0});
		defenceScoreMap.put("xo----x", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x---oox", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--o-ox", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--oo-x", new int[]{0,0,0,0,12});
		defenceScoreMap.put("x-o--ox", new int[]{12,0,0,0,0});
		defenceScoreMap.put("x-o-o-x", new int[]{12,0,0,0,12});
		defenceScoreMap.put("x-oo--x", new int[]{12,0,0,10,0});
		defenceScoreMap.put("xo---ox", new int[]{0,0,0,0,0});
		defenceScoreMap.put("xo--o-x", new int[]{0,0,0,0,12});
		defenceScoreMap.put("xo-o--x", new int[]{0,0,0,0,0});
		defenceScoreMap.put("xo-o-ox", new int[]{0,15,0,15,0});
		defenceScoreMap.put("xoo---x", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--ooox", new int[]{37,37,0,0,0});
		defenceScoreMap.put("x-o-oox", new int[]{37,0,32,0,0});
		defenceScoreMap.put("x-oo-ox", new int[]{37,0,0,32,0});
		defenceScoreMap.put("x-ooo-x", new int[]{37,0,0,0,37});
		defenceScoreMap.put("xo--oox", new int[]{0,32,32,0,0});
		defenceScoreMap.put("xoo--ox", new int[]{0,0,32,32,0});
		defenceScoreMap.put("xo-oo-x", new int[]{0,32,0,0,37});
		defenceScoreMap.put("xoo-o-x", new int[]{0,0,32,0,37});
		defenceScoreMap.put("xooo--x", new int[]{0,0,0,32,37});
		defenceScoreMap.put("x-oooox", new int[]{90,0,0,0,0});
		defenceScoreMap.put("xo-ooox", new int[]{0,90,0,0,0});
		defenceScoreMap.put("xoo-oox", new int[]{0,0,90,0,0});
		defenceScoreMap.put("xooo-ox", new int[]{0,0,0,90,0});
		defenceScoreMap.put("xoooo-x", new int[]{0,0,0,0,90});

//		defenceScoreMap.put("/------", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/----o-", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/---o--", new int[]{0,0,0,0,10});
		defenceScoreMap.put("/--o---", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/-o----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/o-----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/---oo-", new int[]{0,0,10,0,0});
		defenceScoreMap.put("/--o-o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/--oo--", new int[]{0,0,0,0,10});
		defenceScoreMap.put("/-o--o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/-o-o--", new int[]{0,0,0,0,10});
		defenceScoreMap.put("/-oo---", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/o---o-", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/o--o--", new int[]{0,0,0,0,10});
		defenceScoreMap.put("/o-o---", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/oo----", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/--ooo-", new int[]{0,55,0,0,0});
		defenceScoreMap.put("/-o-oo-", new int[]{31,0,55,0,0});
		defenceScoreMap.put("/-oo-o-", new int[]{31,0,0,55,0});
		defenceScoreMap.put("/-ooo--", new int[]{0,0,0,0,55});
		defenceScoreMap.put("/o--oo-", new int[]{0,31,32,0,0});
		defenceScoreMap.put("/o-o-o-", new int[]{0,31,0,32,0});
		defenceScoreMap.put("/o-oo--", new int[]{0,31,0,0,32});
		defenceScoreMap.put("/oo--o-", new int[]{0,0,31,31,0});
		defenceScoreMap.put("/oo-o--", new int[]{0,0,31,0,32});
		defenceScoreMap.put("/ooo---", new int[]{0,0,0,31,32});
		defenceScoreMap.put("/-oooo-", new int[]{90,0,0,0,0});
		defenceScoreMap.put("/o-ooo-", new int[]{0,90,0,0,0});
		defenceScoreMap.put("/oo-oo-", new int[]{0,0,90,0,0});
		defenceScoreMap.put("/ooo-o-", new int[]{0,0,0,90,0});
		defenceScoreMap.put("/oooo--", new int[]{0,0,0,0,90});

//		defenceScoreMap.put("x-----/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x----o/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x---o-/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--o--/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x-o---/", new int[]{10,0,0,0,0});
		defenceScoreMap.put("xo----/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x---oo/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("x--o-o/", new int[]{10,10,0,0,0});
		defenceScoreMap.put("x--oo-/", new int[]{10,10,0,0,0});
		defenceScoreMap.put("x-o--o/", new int[]{10,0,0,0,0});
		defenceScoreMap.put("x-o-o-/", new int[]{10,0,0,0,0});
		defenceScoreMap.put("x-oo--/", new int[]{10,0,0,0,0});
		defenceScoreMap.put("xo---o/", new int[]{0,10,0,0,0});
		defenceScoreMap.put("xo--o-/", new int[]{0,10,0,0,0});
		defenceScoreMap.put("xo-o--/", new int[]{0,10,0,0,0});
		defenceScoreMap.put("xoo---/", new int[]{0,0,10,0,0});
		defenceScoreMap.put("x--ooo/", new int[]{32,31,0,0,0});
		defenceScoreMap.put("x-o-oo/", new int[]{32,0,31,0,0});
		defenceScoreMap.put("x-oo-o/", new int[]{32,0,0,31,0});
		defenceScoreMap.put("x-ooo-/", new int[]{37,0,0,0,0});
		defenceScoreMap.put("xo--oo/", new int[]{0,31,31,0,0});
		defenceScoreMap.put("xo-o-o/", new int[]{0,32,0,31,0});
		defenceScoreMap.put("xo-oo-/", new int[]{0,37,0,0,31});
		defenceScoreMap.put("xoo--o/", new int[]{0,0,32,31,0});
		defenceScoreMap.put("xoo-o-/", new int[]{0,0,37,0,31});
		defenceScoreMap.put("xooo--/", new int[]{0,0,0,37,0});
		defenceScoreMap.put("x-oooo/", new int[]{90,0,0,0,0});
		defenceScoreMap.put("xo-ooo/", new int[]{0,90,0,0,0});
		defenceScoreMap.put("xoo-oo/", new int[]{0,0,90,0,0});
		defenceScoreMap.put("xooo-o/", new int[]{0,0,0,90,0});
		defenceScoreMap.put("xoooo-/", new int[]{0,0,0,0,90});
		
//		defenceScoreMap.put("/-----/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/----o/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/---o-/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/--o--/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/-o---/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/o----/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/---oo/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/--o-o/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/--oo-/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/-o--o/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/-o-o-/", new int[]{0,0,10,0,0});
		defenceScoreMap.put("/-oo--/", new int[]{0,0,0,10,0});
		defenceScoreMap.put("/o---o/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/o--o-/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/o-o--/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/o-o-o/", new int[]{0,15,0,15,0});
		defenceScoreMap.put("/oo---/", new int[]{0,0,0,0,0});
		defenceScoreMap.put("/--ooo/", new int[]{31,31,0,0,0});
		defenceScoreMap.put("/-o-oo/", new int[]{31,0,31,0,0});
		defenceScoreMap.put("/-oo-o/", new int[]{31,0,0,31,0});
		defenceScoreMap.put("/-ooo-/", new int[]{31,0,0,0,31});
		defenceScoreMap.put("/o--oo/", new int[]{0,31,31,0,0});
		defenceScoreMap.put("/oo--o/", new int[]{0,0,31,31,0});
		defenceScoreMap.put("/o-oo-/", new int[]{0,31,0,0,31});
		defenceScoreMap.put("/oo-o-/", new int[]{0,0,31,0,31});
		defenceScoreMap.put("/ooo--/", new int[]{0,0,0,31,31});
		defenceScoreMap.put("/-oooo/", new int[]{90,0,0,0,0});
		defenceScoreMap.put("/o-ooo/", new int[]{0,90,0,0,0});
		defenceScoreMap.put("/oo-oo/", new int[]{0,0,90,0,0});
		defenceScoreMap.put("/ooo-o/", new int[]{0,0,0,90,0});
		defenceScoreMap.put("/oooo-/", new int[]{0,0,0,0,90});
	}
}