package com.ksign.zzangdol.launcher;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.ksign.zzangdol.player.Player;
import com.ksign.zzangdol.player.impl.Ai$2_4;

/**
 * 
 * @author sungeun
 *
 */
public class App {
	private static final byte YOUR_NAME = 1;
	private static final byte YOUR_TURN = 2;
	private static final byte GAME_OVER = 3;
	
	private static final int EMPTY = 0;
	private static final int MINE = 1;
	private static final int OPPOSITE = 2;
	
	private static final int BOARD_SIZE = 19;
	
	private class Stone {
		int x = 0;
		int y = 0;
		
		Stone(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private int[][] mBoard = null;
	
	/**
	 * 
	 */
	public App() {
		int x = 0;
		int y = 0;
		
		mBoard = new int[BOARD_SIZE][BOARD_SIZE];
		
		for (x = 0 ; x < BOARD_SIZE ; x++) {
			for (y = 0 ; y < BOARD_SIZE ; y++) {
				mBoard[x][y] = EMPTY;
			}
		}
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 * @throws Exception
	 */
	private Stone play(int x, int y, Player ai) throws Exception {
		if ((x >= 0) && (x < BOARD_SIZE) && (y >= 0) && (y < BOARD_SIZE)) {
			mBoard[x][y] = OPPOSITE;
		}

		int[] stone = ai.play(x, y);
		if (mBoard[stone[0]][stone[1]] == EMPTY) {
			mBoard[stone[0]][stone[1]] = MINE;
		}
		
		return new Stone(stone[0], stone[1]);
	}
	
	/**
	 * 
	 * @param name
	 * @param addr
	 * @param port
	 * @throws Exception
	 */
	public void startGame(String name, String addr, int port) throws Exception {
		Socket socket = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		byte code = 0;
		byte[] b = null;
		int x = 0;
		int y = 0;
		byte winner = 0;
		Stone stone = null;
		
		try {
			//connect to server
			socket = new Socket(addr, port);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			Player player = new Ai$2_4(MINE, OPPOSITE);
			while (true) {
				code = dis.readByte();
				switch (code) {
				case YOUR_NAME:
					System.out.println("YOUR_NAME");
					
					b = name.getBytes("UTF-8");
					dos.writeInt(b.length);
					dos.write(b);
					break;
				case YOUR_TURN:
					System.out.println("YOUR_TURN");
					
					x = dis.readByte();
					y = dis.readByte();
					System.out.println(String.format("    opposite (%02d, %02d)", x, y));

					stone = play(x, y, player);
					System.out.println(String.format("    mine     (%02d, %02d)", stone.x, stone.y));
					
					dos.writeByte(stone.x);
					dos.writeByte(stone.y);
					break;
				case GAME_OVER:
					System.out.println("GAME_OVER");
					
					winner = dis.readByte();
					if (winner == 1) {
						System.out.println("WINNER!!!");
					} else {
						System.out.println("looser.........");
					}
					return;
				default:
					throw new Exception(String.format("unknown code(%d)", code));
				}
			} //end while
		} finally {
			if (dos != null) {
				dos.close();
			}

			if (dis != null) {
				dis.close();
			}

			if (socket != null) {
				socket.close();
			}
		}
		
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int i = 0;
		String name = null;
		String addr = null;
		int port = 0;
		
		if (args.length != 3) {
			System.out.println("omok-client name addr port");
			System.out.println();
			return;
		}
		
		try {
			i = 0;
			name = args[i++];
			addr = args[i++];
			port = Integer.parseInt(args[i++]);
			
			new App().startGame(name, addr, port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
