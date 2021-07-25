package ChessProject;

public class ShogiKnight extends Piece {
	
	public ShogiKnight(int player) {
		super(player); //Invoke the constructor of the super class Piece
		setPieceDisplay("Shogi Knight");
		setPieceType("SKnight");
	}
	

	public boolean canMove(Square from, Square to, Board b) {
			if(isPromoted) {
				//Gold movement code
				if((Math.abs(from.getHorizontal() - to.getHorizontal()) <= 1 && 
						(Math.abs(from.getVertical() - to.getVertical()) <= 1))) {
					if(player == 1) {
						//If Piece is moving backwards check for diagonal
						if(from.getHorizontal() - to.getHorizontal() == 1) {
							if(from.getVertical() != to.getVertical()) {
								return false;
							}
						}
					} else if(player == 2) {
						//If Piece is moving backwards check for diagonal
						if(from.getHorizontal() - to.getHorizontal() == -1) {
							if(from.getVertical() != to.getVertical()) {
								return false;
							}
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
			
			if(player == 2) {
				//Check if moving up two squares
				if(from.getHorizontal() - to.getHorizontal() != 2 || Math.abs(from.getVertical() - to.getVertical()) != 1) {
					return false;
				}
				
				
			}
			if(player == 1) {
				//Check if moving down two squares
				if(from.getHorizontal() - to.getHorizontal() != -2 || Math.abs(from.getVertical() - to.getVertical()) != 1) {
					return false;
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
}

