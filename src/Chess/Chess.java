package Chess;
/**
 * 
 * @author Ahmed Ishaque
 * @author Andy Cheung
 *
 */
import java.util.*;

public class Chess {
	
	static Piece[][] board = new Piece[8][8];
	/**
	 * 
	 * This method checks if the move entered is legal
	 * checks color of piece, Pawn normal and En Passant capture, isValidPath, and isClearPath
	 * @param p	        piece that is being moved
	 * @param x		    the desired final X coordinate of the piece trying to move
	 * @param y   		the desired final Y coordinate of the piece trying to move
	 * @param turn		which player's turn it is
	 * @param input		user's input of their move
	 * @return boolean  if move is legal
	 */
	public static boolean isMoveLegal(Piece p, int x, int y, String turn, String input) {
		
		if (p == null) {
			return false;
		}
		
		if (turn.equals("Black") && !p.c.equals(Color.BLACK)) {
			return false;
		}

		if (turn.equals("White") && !p.c.equals(Color.WHITE)) {
			return false;
		}
		
		if (p instanceof Pawn) {
			int xDiff = Math.abs(x - p.x);
			int yDiff = Math.abs(p.y - y);
			if (xDiff == 1 && yDiff == 1) { //normal diagonal capture
				if (board[y][x] != null && !board[y][x].c.equals(p.c)) {
					return true;
				}
				else if(board[y][x]==null) {
					if(p.c.equals(Color.BLACK)) { //black pawn en passant white 
						if(board[y-1][x]!=null && board[y-1][x] instanceof Pawn && !board[y-1][x].c.equals(p.c)) {
							Pawn abc = (Pawn) board[y-1][x];
							if(abc.canEnpassant==true) {
								board[y-1][x] = null;
								return true;
							}
						}
					}
					else {// WHITE pawn en passants black
						if(board[y+1][x]!=null && board[y+1][x] instanceof Pawn && !board[y+1][x].c.equals(p.c)) {
							Pawn abc = (Pawn) board[y+1][x];
							if(abc.canEnpassant==true) {
								board[y+1][x] = null;
								return true;
							}
						}
					}
				}
			}
		}

		if (!p.isClearPath(x, y, board)) {
			return false;
		}
		
		if (p instanceof King) {
			King k = (King) p;
			if (k.hasMoved != true) {
				if (p.c.equals(Color.BLACK)) {
					if (input.equals("e8 g8")) { // check black right rook
						if (board[0][7] instanceof Rook) {
							Rook r = (Rook) board[0][7];
							if (r.c.equals(Color.BLACK) && r.hasMoved != true) {
								// castle
								if (board[0][5] != null) {
									return false;
								}
								board[0][5] = r;
								board[0][7] = null;
								return true;
							}
							return false;
						}
					} else if (input.equals("e8 c8")) { // check black left rook
						if (board[0][0] instanceof Rook) {
							Rook r = (Rook) board[0][0];
							if (r.c.equals(Color.BLACK) && r.hasMoved != true) {
								// castle
								if (board[0][3] != null) {
									return false;
								}
								board[0][3] = r;
								board[0][0] = null;
								return true;
							}
							return false;
						}
					}
				} else {
					if (input.equals("e1 g1")) { // check white right rook
						if (board[7][7] instanceof Rook) {
							Rook r = (Rook) board[7][7];
							if (r.c.equals(Color.WHITE) && r.hasMoved != true) {
								// castle
								if (board[7][5] != null) {
									return false;
								}
								board[7][5] = r;
								board[7][7] = null;
								return true;
							}
							return false;
						}
					} else if (input.equals("e1 c1")) { // check white left rook
						if (board[7][0] instanceof Rook) {
							Rook r = (Rook) board[7][0];
							if (r.c.equals(Color.WHITE) && r.hasMoved != true) {
								// castle
								if (board[7][3] != null) {
									return false;
								}
								board[7][3] = r;
								board[7][0] = null;
								return true;
							}
							return false;
						}
					}
				}
			}
		}

		if (!p.isValidPath(x, y)) {
			return false;
		}

		return true;
	}
	/**
	 * 
	 * This method checks draws the playing board
	 */
	public static void Draw() {
		int bs = 1; // bs = black square counter
		int rank = 8; // used to list rank at end of every row
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				Piece curr = board[r][c];
				if (curr == null) { // prints black/white square
					if (bs % 2 == 0) {
						System.out.print("## ");
					} else {
						System.out.print("   ");
					}
				} else {
					System.out.print(curr.toString() + " "); // prints piece
				}
				bs++;
			}
			System.out.print(rank + "\n");
			rank--;
			bs--;
		}
		System.out.print(" a  b  c  d  e  f  g  h");
	}
	/**
	 * 
	 * This method converts the letters in user input for program's use
	 * i.e. converts a to 0 because it is the first column of the board
	 * 		b =1, c=2, etc
	 * @param xy        user's input
	 * @param index	    specific index of the user's input where the letter character is
	 * @return int      returns int value of the character from user input
	 */
	public static int convertX(String xy, int index) {
		if (xy.charAt(index) == 'a') {
			return 0;
		} else if (xy.charAt(index) == 'b') {
			return 1;
		} else if (xy.charAt(index) == 'c') {
			return 2;
		} else if (xy.charAt(index) == 'd') {
			return 3;
		} else if (xy.charAt(index) == 'e') {
			return 4;
		} else if (xy.charAt(index) == 'f') {
			return 5;
		} else if (xy.charAt(index) == 'g') {
			return 6;
		} else {
			return 7;
		}
	}
	/**
	 * 
	 * Main method of the program, where the chess game is ran
	 * @param args arguments to the program
	 */
	public static void main(String[] args) {
		// initialize our board
		//Piece[][] board = new Piece[8][8];

		// set all spots to null initially
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}

		// populate our board with black pieces
		board[0][0] = new Rook(0, 0, Color.BLACK);
		board[0][1] = new Knight(0, 1, Color.BLACK);
		board[0][2] = new Bishop(0, 2, Color.BLACK);
		board[0][3] = new Queen(0, 3, Color.BLACK);
		board[0][4] = new King(0, 4, Color.BLACK);
		board[0][5] = new Bishop(0, 5, Color.BLACK);
		board[0][6] = new Knight(0, 6, Color.BLACK);
		board[0][7] = new Rook(0, 7, Color.BLACK);
		for (int i = 0; i < 8; i++) {
			board[1][i] = new Pawn(1, i, Color.BLACK);
		}
		board[7][0] = new Rook(7, 0, Color.WHITE);
		board[7][1] = new Knight(7, 1, Color.WHITE);
		board[7][2] = new Bishop(7, 2, Color.WHITE);
		board[7][3] = new Queen(7, 3, Color.WHITE);
		board[7][4] = new King(7, 4, Color.WHITE);
		board[7][5] = new Bishop(7, 5, Color.WHITE);
		board[7][6] = new Knight(7, 6, Color.WHITE);
		board[7][7] = new Rook(7, 7, Color.WHITE);
		for (int i = 0; i < 8; i++) {
			board[6][i] = new Pawn(6, i, Color.WHITE);
		}

		boolean playing = true;

		Scanner sc = new Scanner(System.in);
		String input = "";
		int x1, y1, x2, y2;
		int player = 1;

		// player types in a move "e2 e7"
		// go to piece at e2 and check if move is valid
		// if not print error
		// if yes then set that piece to null and initialize new piece at the new
		// position
		while (playing) {
			Draw();
			String turn = "";
			if (player == 1) {
				turn = "White";
			} else {
				turn = "Black";
			}
			System.out.println("\n\n" + turn + "'s move: ");
			input = sc.nextLine();

			if (input.equals("resign")) {
				player *= -1;
				System.out.println(turn + " wins");
				playing = false;
				break;
			} else if (input.length() == 11) {
				input = sc.nextLine();
				break;
			}
			// else if for promotion

			x1 = convertX(input, 0);
			y1 = Math.abs(Character.getNumericValue(input.charAt(1)) - 8);

			Piece p = board[y1][x1];

			x2 = convertX(input, 3);
			y2 = Math.abs(Character.getNumericValue(input.charAt(4)) - 8);

			while (!isMoveLegal(p, x2, y2, turn, input)) {
				System.out.println("Illegal move, try again");
				System.out.println("\n\n" + turn + "'s move: ");
				input = sc.nextLine();

				x1 = convertX(input, 0);
				y1 = Math.abs(Character.getNumericValue(input.charAt(1)) - 8);

				p = board[y1][x1];

				x2 = convertX(input, 3);
				y2 = Math.abs(Character.getNumericValue(input.charAt(4)) - 8);
			}

			if (p instanceof Pawn) {
				if (p.c.equals(Color.BLACK)) {
					if (y2 == 7) {
						// promote black pawn
						if (input.length() > 5) {
							char c = input.charAt(6);
							if (c == 'N') {
								p = new Knight(x2, y2, Color.BLACK);
							} else if (c == 'Q') {
								p = new Queen(x2, y2, Color.BLACK);
							} else if (c == 'R') {
								p = new Rook(x2, y2, Color.BLACK);
							} else if (c == 'B') {
								p = new Bishop(x2, y2, Color.BLACK);
							}
						} else {
							p = new Queen(x2, y2, Color.BLACK);
						}
					}
				} else {
					if (y2 == 0) {
						// promote white pawn
						if (input.length() > 5) {
							char c = input.charAt(6);
							if (c == 'N') {
								p = new Knight(x2, y2, Color.WHITE);
							} else if (c == 'Q') {
								p = new Queen(x2, y2, Color.WHITE);
							} else if (c == 'R') {
								p = new Rook(x2, y2, Color.WHITE);
							} else if (c == 'B') {
								p = new Bishop(x2, y2, Color.WHITE);
							}
						} else {
							p = new Queen(x2, y2, Color.WHITE);
						}
					}
				}
			}

			if (p instanceof Rook) {
				Rook r = (Rook) p;
				r.hasMoved = true;
			}

			board[y1][x1] = null;
			p.x = x2;
			p.y = y2;

			board[y2][x2] = p;
			
			if (p instanceof King) {
				King k = (King) p;
				k.hasMoved = true;
			} else {
				King k = null;
				outerloop:
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++) {
						if (board[i][j] instanceof King && !p.c.equals(board[i][j].c)) {
							k = (King) board[i][j];
							break outerloop;
						}
					}
				}
				if (p.isValidPath(k.x, k.y) && p.isClearPath(k.x, k.y, board)) {
					System.out.print("Check");
					boolean top = true;
					boolean topRight = true;
					boolean right = true;
					boolean botRight = true;
					boolean bot = true;
					boolean botLeft = true;
					boolean left = true;
					boolean topLeft = true;
					if(k.y == 7) {//check vertically
						bot = false;
						botLeft = false;
						botRight = false;
					}
					if(k.y == 0) {
						top = false;
						topRight = false;
						topLeft = false;
					}
					if(k.x == 7) {//check horizontally
						right = false;
						topRight = false;
						botRight = false;
					}
					if(k.x == 0) {
						left = false;
						topLeft = false;
						botLeft = false;
					}
					
					int trueCount = 0;
					int count = 0;
					
					if (top) {
						int topx = k.x;
						int topy = k.y-1;
						trueCount++;
						if (board[topy][topx] != null && board[topy][topx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(topx, topy) && curr.isClearPath(topx, topy, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (topRight) {
						int toprightx = k.x+1;
						int toprighty = k.y-1;
						trueCount++;
						if (board[toprighty][toprightx] != null && board[toprighty][toprightx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(toprightx, toprighty) && curr.isClearPath(toprightx, toprighty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (topLeft) {
						int topleftx = k.x-1;
						int toplefty = k.y-1;
						trueCount++;
						if (board[toplefty][topleftx] != null && board[toplefty][topleftx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(topleftx, toplefty) && curr.isClearPath(topleftx, toplefty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (right) {
						int rightx = k.x+1;
						int righty = k.y;
						trueCount++;
						if (board[righty][rightx] != null && board[righty][rightx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(rightx, righty) && curr.isClearPath(rightx, righty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (left) {
						int leftx = k.x-1;
						int lefty = k.y;
						trueCount++;
						if (board[lefty][leftx] != null && board[lefty][leftx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(leftx, lefty) && curr.isClearPath(leftx, lefty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (bot) {
						int botx = k.x;
						int boty = k.y+1;
						trueCount++;
						if (board[boty][botx] != null && board[boty][botx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(botx, boty) && curr.isClearPath(botx, boty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
						
					}
					
					
					if (botLeft) {
						int botx = k.x-1;
						int boty = k.y+1;
						trueCount++;
						if (board[boty][botx] != null && board[boty][botx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(botx, boty) && curr.isClearPath(botx, boty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (botRight) {
						int botx = k.x+1;
						int boty = k.y+1;
						trueCount++;
						if (board[boty][botx] != null && board[boty][botx].c.equals(k.c)) {
							count++;
						} else {
							outerloop:
								for (int i = 0; i < board.length; i++) {
									for (int j = 0; j < board.length; j++) {
										if (board[i][j] != null && !board[i][j].c.equals(k.c)) {
											Piece curr = board[i][j];
											if (curr.isValidPath(botx, boty) && curr.isClearPath(botx, boty, board)) {
												count++;
												break outerloop;
											}
										}
									}
								}
						}
					}
					
					if (trueCount == count) {
						System.out.print("mate");
						player *= -1;
						System.out.println(turn + " wins");
						playing = false;
						break;
					}
					
				}
				
			}

			player *= -1;
			System.out.println();
			
		}
	}

}
