package Chess;

/**
 * This is the Bishop class which is an implementation of the abstract piece class.
 * 
 * @author Ahmed Ishaque
 * @author Andy Cheung
 *
 */

public class Bishop extends Piece {
	/**
	 * Type contains an enum for which kind of Piece this is
	 */
	Type type;
	
	/**
	 * Constructor which makes use of the super constructor inside of Piece.java
	 * @param y  the starting y coordinate
	 * @param x  the starting x coordinate
	 * @param c  the color
	 */
	public Bishop(int y, int x, Color c) {
		super(y, x, c);
		type = Type.BISHOP;
	}
	
	
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a bishop, we want to make sure the user is trying to move it diagonally.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		if (xDiff == yDiff) {
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
	 * @param finalX
	 * @param finalY
	 * @param board
	 * @return returns a boolean for whether or not the path is clear
	 */
	public boolean isClearPath(int finalX, int finalY, Piece[][] board) {
		int xDiff = finalX - x;
		int yDiff = finalY - y;
		if(finalX==x && finalY == y) { //not moving at all
			return false;
		}
		int c, r;
		if (yDiff > 0) { //moving down
			c = y+1;
			if (xDiff > 0) { //moving to the right
				r = x+1;
				while (c < finalY && r < finalX) {
					if (board[c][r] != null) {
						return false;
					}
					c++;
					r++;
				}
				return true;
			} else { //moving to the left
				r = x-1;
				while (c < finalY && r > finalX) {
					if (board[c][r] != null) {
						return false;
					}
					c++;
					r--;
				}
				return true;
			}
		} else { //moving up
			c = y-1;
			if (xDiff > 0) { //moving to the right
				r = x+1;
				while (c > finalY && r < finalX) {
					if (board[c][r] != null) {
						return false;
					}
					c--;
					r++;
				}
				return true;
			} else { //moving to the left
				r = x-1;
				while (c > finalY && r > finalX) {
					if (board[c][r] != null) {
						return false;
					}
					c--;
					r--;
				}
				return true;
			}
		}
	}
	
	/**
	 * Returns bB for a black king
	 * or wB for a white King
	 * 
	 * @return returns "wB" or "bB"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bB";
		} else {
			return "wB";
		}
	}
}
