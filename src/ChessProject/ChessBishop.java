package ChessProject;
public class ChessBishop extends Piece {
	private int directionVertical, directionHorizontal;
	private int moveVertical, moveHorizontal;
	public ChessBishop(int player) {
		super(player);
		setPieceDisplay("Chess Bishop");
		setPieceType("CBishop");
	}

	public boolean canMove(Square current, Square next, Board b) {
		directionVertical = Math.abs(current.getVertical() - next.getVertical());
		directionHorizontal = Math.abs(current.getHorizontal() - next.getHorizontal());
		
		if(directionHorizontal == directionVertical) {
			//The bishop moves in four diagonal directions.
			//Check if the bishop is moving left or right and up or down.
			//The 1 and -1 values will be used when we check each position on the bishop's path.
			if(next.getVertical() > current.getVertical()){
				moveVertical = 1;
			}
			else {
				moveVertical = -1;
			}
			if(next.getHorizontal() > current.getHorizontal()) {
				moveHorizontal = 1;
			}
			else {
				moveHorizontal = -1;
			}
			
			for (int i = 1 ; i < Math.abs(next.getVertical() - current.getVertical()); i++) {
				//Check if there are pieces along the bishops path.
				//The moveHorizontal and moveVertical values are used to look at positions left or right / up or down.
				//Negative values will decrement the horizontal / vertical positions we look at.
				if (b.getPosition(current.getHorizontal() + i * moveHorizontal,
						current.getVertical() + i * moveVertical).getPiece() != null) {
					return false;
				}
			}
			if(next.getPiece() != null) {
				if(current.getPiece().getPlayer() == next.getPiece().getPlayer()) {
					return false;
					}
			}
			return true; // The bishop is allowed to move.
			}
		return false; //The bishop is not allowed to move.
	}

	public void promote() {
		//This piece does not promote
	}
	public void demote() {
		//This piece does not demote
	}
}

