package com.ksign.zzangdol.player.impl;
import java.util.Scanner;

import com.ksign.zzangdol.player.Player;

public class Human extends Player {

	public Human() {
		super(1, 2);
	}

	public int[] getBestPosition(int x, int y) {
		int[] playMove = new int[2];
		Scanner myScan = new Scanner(System.in);

		System.out.printf("Select your move (0 to 18)\n");

		System.out.printf("x: ");
		playMove[0] = myScan.nextInt();
		System.out.printf("y: ");
		playMove[1] = myScan.nextInt();
		return playMove;
	}
}