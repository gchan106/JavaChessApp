package ChessProject;

public class ChessRook extends Piece{
	
	private int directionVertical;
	private int directionHorizontal;
	
	public ChessRook(int player) {
		super(player);
		setPieceDisplay("Chess Rook");
		setPieceType("CRook");
	}
	
	public boolean canMove(Square current, Square next, Board b) {
		
		directionVertical = current.getVertical() - next.getVertical();
		directionHorizontal = current.getHorizontal() - next.getHorizontal();
		
		//Check if the rook is promoted
		//A promoted rook moves as either a king or a rook but only one of the two ways per turn.
		if(isPromoted) {
			//We check the absolute value of the direction values since we only want to know if they moved by
			// one square.
			if(Math.abs(directionHorizontal) <= 1 && Math.abs(directionVertical) <= 1) {	
				return true;
		}
	}
		//If the next square is occupied, check what player occupies the square
		if(next.getPiece()!=null)
		{
			if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
				return false; //If the player is friendly, the rook doesn't move to it.
			}
		}
		
		if(current.getVertical() != next.getVertical() && current.getHorizontal() != next.getHorizontal()) {
			//The rook can only go vertically up / down or horizontally left / right
			// in a single turn.
				return false;
		}
		
		//Check what direction the rook is moving.
		
			if(directionVertical > 0) {
				//Moving left
				for(int i = current.getVertical() - 1; i > next.getVertical(); i--) {
					//Look at each square in the rook's path.
					//If the rook is blocked by a piece, it can't move to that position.
					if(b.getPosition(current.getHorizontal(), i).getPiece() != null) {
						return false;
					}
				}
			}
			if(directionVertical < 0) {
				//Moving right
				for(int i = current.getVertical() + 1; i < next.getVertical(); i++) {
					//Look at each square in the rook's path.
					//If the rook is blocked by a piece, it can't move to that position.
					if(b.getPosition(current.getHorizontal(), i).getPiece() != null) {
						return false;
					}
				}
			}

			if(directionHorizontal > 0) {
				//Moving up
				for(int i = current.getHorizontal() - 1; i > next.getHorizontal(); i--) {
					//Look at each square in the rook's path.
					//If the rook is blocked by a piece, it can't move to that position.
					if(b.getPosition(i, current.getVertical()).getPiece() != null) {
						return false;
					}
				}
			}
			
			if(directionHorizontal < 0) {
				//Moving down
				for(int i = current.getHorizontal() + 1; i < next.getHorizontal(); i++) {
					//Look at each square in the rook's path.
					//If the rook is blocked by a piece, it can't move to that position.
					if(b.getPosition(i, current.getVertical()).getPiece() != null) {
						return false;
					}
				}
			}
		return true; // The rook is allowed to move to that position
	}
}