package ChessProject;

public class ChessQueen extends Piece{
	public ChessQueen(int player) {
		super(player);
		setPieceDisplay("Queen ");
		setPieceType("CQueen");
	}

	public boolean canMove(Square from, Square to, Board b) {

		
		if(Math.abs(from.getHorizontal() - to.getHorizontal()) == Math.abs(from.getVertical() - to.getVertical())) {
			//Check if moving left or right, up or down
			int directionColumn = to.getVertical() > from.getVertical() ? 1 : -1;
			int directionRow = to.getHorizontal() > from.getHorizontal() ? 1 : -1;
			for (int i=1;i<Math.abs(to.getVertical()-from.getVertical());i++) {
				if (b.getPosition(from.getHorizontal()+i*directionRow,from.getVertical()+i*directionColumn).getPiece() != null) {
					return false;
				}
			}

			if(to.getPiece() != null) {
				if(from.getPiece().getPlayer() == to.getPiece().getPlayer()) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public void promote() {
		//This piece does not promote
	}
	public void demote() {
		//This piece does not demote
	}
}
