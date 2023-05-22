package Chess;
/**
 * 
 * @author Ahmed Ishaque
 * @author Andy Cheung
 *
 */

abstract class Piece {
	public int x;
	public int y;
	public Color c;
	/**
	 * 
	 * This is the constructor for the Piece class
	 * 
	 * @param x    x position of the piece
	 * @param y    y position of the piece
	 * @param c    color of the piece
	 */
	public Piece(int y, int x, Color c) {
		this.y = y;
		this.x = x;
		this.c = c;
	}
	/**
	 * 
	 * This method checks if the piece is moving with its right rule.
	 * i.e. Bishop only moves diagonally, Rook vertically/horizontally, etc
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return boolean  if piece follows piece rule
	 */
	public abstract boolean isValidPath(int finalX, int finalY);
	/**
	 * 
	 * This method checks if the path of the piece is clear, and it can move.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @param board     the playing board is used to determine if there are empty squares
	 * @return boolean  if path for piece is clear
	 */
	public abstract boolean isClearPath(int finalX, int finalY, Piece[][] board);
	
	/**
	 * 
	 * toString method for pieces
	 * @return String color and type of Piece
	 */
	public abstract String toString();
}
