package Chess;
/**
 * 
 * This is the Knight class which is an implementation of the abstract Piece class.
 * 
 * @author Ahmed Ishaque
 * @author Andy Cheung
 *
 */
public class Knight extends Piece {
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
	public Knight(int y, int x, Color c) {
		super(y, x, c);
		type = Type.KNIGHT;
	}
	
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a knight, we want to make sure the user is trying to move in an L shape.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		if (xDiff == 2 && yDiff == 1) {
			return true;
		} else if (yDiff == 2 && xDiff == 1) {
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
		return true;
	}
	
	/**
	 * Returns bN for a black knight
	 * or wN for a white knight
	 * 
	 * @return returns "wN" or "bN"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bN";
		} else {
			return "wN";
		}
	}
}
