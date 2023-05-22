package Chess;

public class King extends Piece {
	/**
	 * Type contains an enum for which kind of Piece this is
	 */
	Type type;
	public boolean hasMoved;
	
	/**
	 * Constructor which makes use of the super constructor inside of Piece.java
	 * @param y  the starting y coordinate
	 * @param x  the starting x coordinate
	 * @param c  the color
	 */
	public King(int y, int x, Color c) {
		super(y, x, c);
		type = Type.KING;
	}
	
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a bishop, we want to make sure the user is trying to move only one box in any direction.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		if (xDiff > 1 || yDiff > 1) {
			return false;
		} else {
			return true;
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
	public boolean isClearPath(int finalX, int finalY,Piece[][] board) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		
		if (xDiff == 0 && yDiff == 0) { //not moving at all
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returns bK for a black king
	 * or wK for a white king
	 * 
	 * @return returns "wK" or "bK"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bK";
		} else {
			return "wK";
		}
	}
}
