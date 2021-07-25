package ChessProject;
public class ShogiGold extends Piece{
	
	private int directionVertical, directionHorizontal;
	
	public ShogiGold(int player) {
		super(player);
		setPieceDisplay("Gold General");
		setPieceType("Gold");
	}
	
	public boolean canMove(Square current, Square next, Board b) {
		directionVertical = current.getVertical() - next.getVertical();
		directionHorizontal = current.getHorizontal() - next.getHorizontal();
		//Check to make sure that the gold piece can move to the next square				
		//Check if the piece is moving backwards
		//We check the absolute value of the direction values since we only want to know if they moved by
		// one square.
			if(Math.abs(directionVertical) <= 1 && Math.abs(directionHorizontal) <= 1)
					{
						if(player == 1) {
							
							//Player 1, check diagonal spots
							//Is the piece moving vertically up?
							if(directionHorizontal == 1) {
								if(current.getVertical() != next.getVertical()) {
									//The gold piece cannot move backwards diagonally.
									return false;
								}
							}					
						} //end player 1 check
						else if(player == 2) {
							//Player 2, check diagonal spots
								//Is the piece moving vertically down?
							if(directionHorizontal == -1) {
								//Is the Gold in the same column?
								if(current.getVertical() != next.getVertical()) {
									//the gold piece cannot move backwards diagonally.
									return false;
								}
							}
						} //end player 2 check
						
			if(next.getPiece() != null) {
				//Make sure the square is not occupied by a friendly unit for the player
				if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
						return false;
					}
				}
			return true; //The gold piece is allowed to move.
		}
			return false;
	}
//The gold piece cannot be promoted!
public void promote() {
	;
}

public void demote() {
	;
}
}