package Chess;

public class Queen extends Piece {
	Type type;
	
	public Queen(int y, int x, Color c) {
		super(y, x, c);
		type = Type.QUEEN;
	}
	
	/**
	 * 
	 * This method checks if the piece is moving in the correct direction.
	 * For a Queen, we want to make sure the user is trying to move it diagonally, vertically, or horizontally.
	 * 
	 * @param finalX    the desired final X coordinate of the piece trying to move
	 * @param finalY    the desired final Y coordinate of the piece trying to move
	 * @return returns a boolean for whether or not the path is valid
	 */
	public boolean isValidPath(int finalX, int finalY) {
		int xDiff = Math.abs(finalX - x);
		int yDiff = Math.abs(finalY - y);
		if (xDiff  == yDiff) { //queen moves diagonally
			return true;
		} else if (xDiff == 0 && yDiff > 0) {
			return true;
		} else if (yDiff == 0 && xDiff > 0) {
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
		int yDiff = finalY - y;
		int xDiff = finalX - x;
		if(finalY==y && finalX==x) { //NOT moving at all
			return false;
		}
		
		else if(yDiff != 0 && xDiff == 0) { //moving VERTICALLY
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
		else if(xDiff!=0 && yDiff==0) { //moving HORIZONTALLY
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
		else {//moving DIAGONALLY
			int c,r;
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
	}
	
	/**
	 * Returns bQ for a black queen
	 * or wQ for a white queen
	 * 
	 * @return returns "wQ" or "bQ"
	 */
	public String toString() {
		if (c.equals(Color.BLACK)) {
			return "bQ";
		} else {
			return "wQ";
		}
	}
}
