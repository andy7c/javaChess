package Chess;

/**
 * 
 * @author Ahmed Ishaque
 * @author Andy Cheung
 * 
 * This is the Rook class which is an implementation of the abstract Piece class
 *
 */
public class Rook extends Piece {
	/**
	 * Type contains an enum value specifying the kind of piece
	 */
	Type type;
	
	/**
	 * Identifies whether or not the piece has been moved from its starting position
	 */
	public boolean hasMoved;
	
	/**
	 * Constructor which makes use of the super constructor inside of Piece
	 * @param y    the starting Y coordinate of the piece
	 * @param x    the starting X coordinate of the piece
	 * @param c    the color of the piece
	 */
	public Rook(int y, int x, Color c) {
		super(y, x, c);
		type = Type.ROOK;
	}
	
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a Rook, we want to make sure the user is trying to move either horizontally or vertically.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		if (yDiff == 0 && xDiff > 0) {
			return true;
		} else if (xDiff == 0 && yDiff > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * This method checks that the path the piece wants to move in
	 * is not obstructed by any other pieces
	 * 
	 * @param finalX   the desired final x coordinate
	 * @param finalY   the desired final y coordinate
	 * @param board    the game board that is initialized in Chess.java
	 * @return returns a boolean for whether or not the path is clear
	 */
	public boolean isClearPath(int finalX, int finalY,Piece[][] board) {
		int xDiff = finalX - x;
		int yDiff = finalY - y;
		if(yDiff == 0 && xDiff == 0) { //NOT moving at all
			return false;
		}
		else if(yDiff!=0 && xDiff == 0) { //moving vertically
			if(yDiff > 0) { //moving DOWN
				for(int c = y+1; c < finalY;c++) {
					if(board[c][x]!=null) {
						return false;
					}
				}
				return true;
			}
			else {//moving UP
				for(int c = y-1; c > finalY;c--) {
					if(board[c][x]!=null) {
						return false;
					}
				}
				return true;
			}
		}
		else {//moving horizontally
			if(xDiff > 0) { //moving RIGHT
				for(int c = x+1; c < finalX;c++) {
					if(board[y][c]!=null) {
						return false;
					}
				}
				return true;
			}
			else {//moving LEFT
				for(int c = x-1; c > finalX;c--) {
					if(board[y][c]!=null) {
						return false;
					}
				}
				return true;
			}
		}
	}
	
	/**
	 * Returns bB for a black king
	 * or wB for a white King
	 * 
	 * @return returns "bR" or "wR"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bR";
		} else {
			return "wR";
		}
	}
}
