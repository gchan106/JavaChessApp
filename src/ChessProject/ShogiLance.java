package ChessProject;

public class ShogiLance extends Piece{
	
	private int directionVertical, directionHorizontal;
	
	public ShogiLance(int player) {
		super(player);
		setPieceDisplay("Shogi Lance");
		setPieceType("SLance");
	}
	
	public boolean canMove(Square current, Square next, Board b) {
		
		directionVertical = current.getVertical() - next.getVertical();
		directionHorizontal = current.getHorizontal() - next.getHorizontal();
		
		//Check if the piece is promoted.
		//A promoted lance gains the move properties of a gold piece.
		if(isPromoted) {					
			//Check if the piece is moving backwards
			//We check the absolute value of the direction values since we only want to know if they moved by
			// one square.
			if(Math.abs(directionVertical) <= 1 && Math.abs(directionHorizontal) <= 1)
			{
				if(player == 1) {
					//Player 1, check diagonal spots behind the piece
					//Is the Lance moving backwards for player 1?
					if(directionHorizontal == 1) {
						if(current.getVertical() != next.getVertical()) {
							//If the Lance is promoted, it moves the same as a gold piece.
							//The piece can move diagonally forward but not diagonally back.
							return false;
						}
					}					
				}
				else if(player == 2) {
						//Player 2, check diagonal spots behind the piece
						//Is the Lance moving backwards for player 2?
					if(directionHorizontal == -1) {
						if(current.getVertical() != next.getVertical()) {
							//Same as before, if the Lance is promoted, it has the moves of a gold piece.
							//Like the gold piece, the Lance cannot move backwards diagonally.
							return false;
						}
					}
				} 
				//If the position the Lance is moving to is empty
				if(next.getPiece() != null) {
					//Make sure the square is not occupied by a friendly unit for the player
					//(This could happen at the start of the game)
					if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
						}
				} 
				return true; //Promoted Lance is allowed to move to this position
			} 
			return false; //Promoted Lance is not allowed to move to that position
		} //end promotion check
		
		//Lance movement
		//A Lance can only go forward in the same column. It can move over any amount of squares.
		if(current.getVertical() == next.getVertical()) {		
			
			if(player == 1) {
				//Check for other pieces while moving down.
				if(directionHorizontal < 0 && current.getVertical() == next.getVertical()) {
					for(int i = current.getHorizontal() + 1;i < next.getHorizontal(); i++) {
						if(b.getPosition(i, current.getVertical()).getPiece() != null) {
							return false;
						}
					}
				} 
				else {
					return false;
				}
			}
			
			if(player == 2) {
				//Check for other pieces while moving up.
				if(directionHorizontal > 0) {
					for(int i = current.getHorizontal() - 1; i > next.getHorizontal();i--) {
						if(b.getPosition(i, current.getVertical()).getPiece() != null) {
							return false;
						}
					}
				} 
				else {
					return false;
				}
			}
			
		//Make sure the next square the Lance is moving to is not occupied by a friendly unit.
		if(next.getPiece() != null) {
			if(current.getPiece().getPlayer() == next.getPiece().getPlayer())
			{
				return false;
			}
		}
			return true;
	}
		return false;
}	
}
