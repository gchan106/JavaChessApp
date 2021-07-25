package ChessProject;

public class ChessKing extends Piece{
	private int directionVertical, directionHorizontal;
	public ChessKing(int player) {
		super(player);
		setPieceDisplay("Chess King");
		setPieceType("CKing");
	}
	
	public boolean canMove(Square current, Square next, Board b) {
		//We check the absolute value of the direction values since we only want to know if they moved by
		// one square.
		directionVertical = Math.abs(current.getVertical() - next.getVertical());
		directionHorizontal = Math.abs(current.getHorizontal() - next.getHorizontal());
		
		if(next.getPiece() != null)
		{
			if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
				return false;
			}
		}
		//The King can move one square in any direction!
		//Check if the next position is one square away from the current.
		if(directionVertical <=1 && directionHorizontal <= 1) {
			return true;
		}
		return false; //If the check fails, the King can't move to that position
	}

//The King cannot be promoted!
public void promote() {
	;
}

public void demote() {
	;
}

}
