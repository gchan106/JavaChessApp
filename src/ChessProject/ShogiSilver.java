package ChessProject;

public class ShogiSilver extends Piece{
	private int directionVertical, directionHorizontal;
	public ShogiSilver(int player) {
		super(player);
		setPieceDisplay("Silver General");
		setPieceType("Silver");
	}
	
	public boolean canMove(Square current, Square next, Board b) {
		directionVertical = current.getVertical() - next.getVertical();
		directionHorizontal = current.getHorizontal() - next.getHorizontal();
		//A promoted silver piece gains the same move properties of that of a gold piece.
		//Check to make sure that the Silver can move to the next square
		if(isPromoted) {					
			//Check if the piece is moving backwards
			//The Silver moves by one square distance.
			if(Math.abs(directionHorizontal) <= 1 && Math.abs(directionVertical) <= 1)
			//We check the absolute value of the direction values since we only want to know if they moved by
			// one square.
			{
				//Check which player is moving the piece backwards.
				//Make sure the piece cannot move backwards diagonally.
				if(player == 1) {
					//Player 1, check diagonal spots behind the piece
					//Is the Silver moving backwards for player 1?
					if(directionHorizontal == 1) {
						if(current.getVertical() != next.getVertical()) {
							//If the Silver is promoted, it moves the same as a gold piece.
							//The piece can move diagonally forward but not diagonally back.
							return false;
						}
					}					
				}
				else if(player == 2) {
						//Player 2, check diagonal spots behind the piece
						//Is the Silver moving backwards for player 2?
					if(directionHorizontal == -1) {
						if(current.getVertical() != next.getVertical()) {
							//Same as before, if the Silver is promoted, it has the moves of a gold piece.
							//Like the gold piece, the Silver cannot move backwards diagonally.
							return false;
						}
					}
				} 
				
				//If the position the Silver is moving to is empty
				if(next.getPiece() != null) {
					//Make sure the square is not occupied by a friendly unit for the player
					if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
						}
				} 
				return true; //Promoted Silver is allowed to move to this position
			} 
			return false; //Promoted Silver is not allowed to move to that position
		} //end promotion check
		
				//Silver movement
				//Make sure the piece cannot move backwards one square.
				//First, check if the Silver piece is moving by at most, one square.
				if(Math.abs(directionVertical) <=1 && Math.abs(directionHorizontal) <=1){
					//Check if the piece is moving backwards. A Silver piece can only move backwards diagonally.
					if(player == 1) {
						if(directionHorizontal == 1) {
							if(current.getVertical() == next.getVertical()) {
								return false;
							}
						}
					}
					else if(player == 2) {
						if(directionHorizontal == -1) {
							if(current.getVertical() == next.getVertical()) {
								return false;
							}
						}
					}
				//Now check to make sure if the Silver piece is moving left or right.
				//A Silver piece cannot move left or right.
				if(current.getHorizontal() == next.getHorizontal()) {
					return false;
				}
				
				//If the position the Silver is moving to is empty
				if(next.getPiece() != null) {
					//Make sure the square is not occupied by a friendly unit for the player
					if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
						}
				} 
				return true;
		}
	return false; // The Silver cannot move to that position
}
}