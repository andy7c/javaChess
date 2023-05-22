package Chess;

public class Pawn extends Piece {
	/**
	 * Type contains an enum for which kind of Piece this is
	 */
	Type type;
	
	boolean canEnpassant = false;
	
	public Pawn(int y, int x, Color c) {
		super(y, x, c);
		type = Type.PAWN;
	}
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a Pawn, we want to make sure the user is trying to move it one piece forward.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = finalX - x;
		int yDiff = finalY - y;
		
		if(xDiff!=0 || Math.abs(yDiff)>2) {//checks if pawn is trying to move horizontally or MORE than 2 spots
			return false;
		}
		else if(c.equals(Color.BLACK)) {
			if(yDiff <= 0) { //if not moving forward 
				return false;
			}
			else {
				if(y!=1 && Math.abs(yDiff)==2) {//if pawn is NOT in starting position and tries to move two spots
					return false;
				}
				else {//STARTING position, allows pawn to move 1 or 2 spots forward
					return true;
				}
			}
		}
		else {//CHECKS WHITE PAWN
			if(yDiff>=0) { //checks if pawn is moving forward
				return false;
			}
			else {
				if(y!=6 && Math.abs(yDiff)==2) {//if pawn is NOT in starting position and tries to move two spots
					return false;
				}
				else {//STARTING position, allows pawn to move 1 or 2 spots forward
					return true;
				}
			}
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
		int yDiff = finalY - y;
		if(c.equals(Color.BLACK)) { //checks spots in front of black pawn
			for(int c = y+1; c <= finalY; c++) {
				if(board[c][x]!=null) {
					return false;
				}
			}
			if(Math.abs(yDiff)==2) {
				canEnpassant = true;
			}
			return true;
		}
		else { //checks spots in front of white pawn
			for(int c = y-1; c >= finalY; c--) {
				if(board[c][x]!=null) {
					return false;
				}
			}
			if(Math.abs(yDiff)==2) {
				canEnpassant = true;
			}
			return true;
		}
	}
	
	/**
	 * Returns bp for a black pawn
	 * or wp for a white pawn
	 * 
	 * @return returns "wp" or "bp"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bp";
		} else {
			return "wp";
		}
	}
}
