package ChessProject;

public class ChessKnight extends Piece {
	
	public ChessKnight(int player) {
		super(player); 
		setPieceDisplay("Chess Knight");
		setPieceType("CKnight");
	}
	

	public boolean canMove(Square from, Square to, Board b) {

			if(player == 2) {
				//Check if moving down two squares
				  if( ( (    (from.getHorizontal() - to.getHorizontal() != -2 || Math.abs(from.getVertical() - to.getVertical()) != 1)
						&&   (from.getHorizontal() - to.getHorizontal() != -1 || Math.abs(from.getVertical() - to.getVertical()) != 2)
						&&   ((from.getHorizontal() - to.getHorizontal() != 2 || Math.abs(from.getVertical() - to.getVertical()) != 1)
						&&   ((from.getHorizontal() - to.getHorizontal() != 1 || Math.abs(from.getVertical() - to.getVertical()) != 2)) 
						)
						)
					  )
					){
					return false;
				}
				
				
			}
			if(player == 1) {
				//Check if moving down two squares
				  if(((	    (from.getHorizontal() - to.getHorizontal() != -2 || Math.abs(from.getVertical() - to.getVertical()) != 1)
						&&  (from.getHorizontal() - to.getHorizontal() != -1 || Math.abs(from.getVertical() - to.getVertical()) != 2)
						&& ((from.getHorizontal() - to.getHorizontal() != 2 || Math.abs(from.getVertical() - to.getVertical()) != 1)
						&& ((from.getHorizontal() - to.getHorizontal() != 1 || Math.abs(from.getVertical() - to.getVertical()) != 2)
					))))) {
					return false;
				}
					//Check if moving down two squares
				//else if(from.getHorizontal() - to.getHorizontal() != -1 || Math.abs(from.getVertical() - to.getVertical()) != 2) {
					//	return false;
					//}
			}

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

