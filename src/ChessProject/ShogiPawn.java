package ChessProject;

public class ShogiPawn extends Piece	{
	
	private int directionVertical, directionHorizontal;
	public ShogiPawn(int player)	{
	
		super(player); //Invoke the constructor of the super class Piece
		setPieceDisplay("Shogi Pawn");
		setPieceType("SPawn");
	}

	public boolean canMove(Square current, Square next, Board b) {
		//Calculate the distance moved.
		directionVertical = current.getVertical() - next.getVertical();
		directionHorizontal = current.getHorizontal() - next.getHorizontal();
		
		//Check to make sure that the pawn can move to the next square.
		//If the pawn is promoted, it gains the move properties of a gold piece.
		if(isPromoted) {					
			//Check if the piece is moving backwards
			//The pawn moves by one square distance.
			if(Math.abs(directionHorizontal) <= 1 && Math.abs(directionVertical) <= 1)
			//We check the absolute value of the direction values since we only want to know if they moved by
			// one square.
			{
				//Check which player is moving the piece backwards.
				//Make sure the piece cannot move backwards diagonally.
				if(player == 1) {
					//Player 1, check diagonal spots behind the piece
					//Is the Pawn moving backwards for player 1?
					if(directionHorizontal == 1) {
						if(current.getVertical() != next.getVertical()) {
							//If the Pawn is promoted, it moves the same as a gold piece.
							//The piece can move diagonally forward but not diagonally back.
							return false;
						}
					}					
				}
				else if(player == 2) {
						//Player 2, check diagonal spots behind the piece
						//Is the Pawn moving backwards for player 2?
					if(directionHorizontal == -1) {
						if(current.getVertical() != next.getVertical()) {
							//Same as before, if the Pawn is promoted, it has the moves of a gold piece.
							//Like the gold piece, the Pawn cannot move backwards diagonally.
							return false;
						}
					}
				} 
				
				//If the position the pawn is moving to is empty
				if(next.getPiece() != null) {
					//Make sure the square is not occupied by a friendly unit for the player
					if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
						}
				} 
				return true; //Promoted Pawn is allowed to move to this position
			} 
			return false; //Promoted Pawn is not allowed to move to that position
		} //end promotion check
		
		//Pawn movement
		//Check if player 1 is moving downward or if player 2 is moving upward		
		if(player == 1 && directionHorizontal == -1 || 
				(player == 2 && directionHorizontal == 1)) {
			
			if(current.getVertical() == next.getVertical()) {
				//Check to make sure that the square in the column is not occupied
				if(next.getPiece() != null) {
					//Check if the square is occupied by a friendly unit for the player
					if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
					}
				}
				return true; //The pawn is allowed to move to that position
			}
		}

		return false; //If all checks fail, the pawn can't move to that position
	}
}
	




