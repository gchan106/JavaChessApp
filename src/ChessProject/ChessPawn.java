package ChessProject;

public class ChessPawn extends Piece{
	public ChessPawn(int player) {
		super(player);
		setPieceDisplay("Chess Pawn");
		setPieceType("CPawn");
	}

	public boolean canMove(Square from, Square to, Board b) {


		if (from.getHorizontal() == -1 || from.getHorizontal() == 1)
		{
			if(player == 1 && ((from.getHorizontal() - to.getHorizontal() == -1)|| 
					(from.getHorizontal() - to.getHorizontal() == -2)) ||
					player == 2 && ((from.getHorizontal() - to.getHorizontal() == 1)|| 
								(from.getHorizontal() - to.getHorizontal() == 2))
					) 	{
			if(from.getVertical() == to.getVertical()) {
						if(to.getPiece() != null) {
						if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
							return false;
						}
					}
						return true;
				}
			}
		//prevents jumping over friendly pieces
		if(to.getPiece() != null) {
			if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
				return false;
			}
		}

		return false;
		}
		
		if(player == 2) {
			//Check if moving up two squares
			if((from.getHorizontal() - to.getHorizontal() != 1 || Math.abs(from.getVertical() - to.getVertical() ) !=  0)) {
				if (to.getPiece().getPlayer()==1)
				return false;
			}
			//if(from.getHorizontal() - to.getHorizontal() == 1) {
			//	if(from.getVertical() == to.getVertical()) {
			//		if(to.getPiece() != null) {
			//			if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
			//				return false;
						}
			//if(to.getPiece() != null) {
			//	if(from.getPiece().getPlayer() != to.getPiece().getPlayer()) {
			//		return false;
				//}
			//}
			
			
		//}
		if(player == 1) {
			//Check if moving down two squares
			if((from.getHorizontal() - to.getHorizontal() != -1 || Math.abs(from.getVertical() - to.getVertical()) != 0)) {
					
					
				return false;
				//if(from.getHorizontal() - to.getHorizontal() == 1) {
				//	if(from.getVertical() == to.getVertical()) {
				//		if(to.getPiece() != null) {
				//			if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
				//				return false;
				
			}
		}
		//if exists a piece it is not null
		if(to.getPiece() != null) {
			if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
				return false;
			}
		}
		return true;
	}
		
	
	public void promote() {
		//This piece does not promote
	}
	public void demote() {
		//This piece does not demote
	}
}
