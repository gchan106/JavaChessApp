package ChessProject;
import javax.swing.JOptionPane;

public class Board {
	private Square[][] board = new Square[9][9];
	public Board() {
		String[] pieceTypeCArray = {"ChessPawn", "ChessKnight", "ChessKing", "ChessBishop", "ChessRook", "ChessQueen"};
		String[] pieceTypeSArray = {"ShogiPawn", "ShogiKnight", "ShogiKing", "ShogiBishop", "ShogiRook", "ShogiGold", "ShogiSilver","ShogiLance"};
		
		//Add Squares
		for(int row = 0; row < board.length; row++) {
			for(int column = 0; column < board[row].length; column++) {
				board[row][column] = new Square(row,column);
			}
		}
		
		//Add Pieces
		for(int i = 0; i < 9;i++) {
			board[1][i].setPiece(new ChessPawn(1));
			board[6][i].setPiece(new ShogiPawn(2));
		}
		board[0][0].setPiece(new ChessRook(1));
		board[0][8].setPiece(new ChessRook(1));
		board[8][0].setPiece(new ShogiLance(2));
		board[8][8].setPiece(new ShogiLance(2));
		board[0][4].setPiece(new ChessKing(1));
		board[8][4].setPiece(new ShogiKing(2));
		board[0][3].setPiece(new ChessQueen(1));
		board[0][5].setPiece(new ChessQueen(1));
		board[8][3].setPiece(new ShogiGold(2));
		board[8][5].setPiece(new ShogiGold(2));
		board[0][2].setPiece(new ChessBishop(1));
		board[0][6].setPiece(new ChessBishop(1));
		board[8][2].setPiece(new ShogiSilver(2));
		board[8][6].setPiece(new ShogiSilver(2));
		board[0][1].setPiece(new ChessKnight(1));
		board[0][7].setPiece(new ChessKnight(1));
		board[8][1].setPiece(new ShogiKnight(2));
		board[8][7].setPiece(new ShogiKnight(2));
		board[7][7].setPiece(new ShogiRook(2));
		board[7][1].setPiece(new ShogiBishop(2));
	}

	public void movePiece(Square from, Square to, int turn) throws Exception {
		Piece fromPiece = from.getPiece();
		// we check player int variable to check to the turn
		//make sure moving player is on correct turn, and is moving to a different square
		if(fromPiece.getPlayer() == turn && (from.getVertical() != to.getVertical() || from.getHorizontal() != to.getHorizontal())) {
			if(fromPiece.canMove(from, to, this)) {
				//promote piece if is the in enemy side of the board.
				try {
					if(to.getHorizontal() <= 1 && from.getPiece().getPlayer() == 2 || 
							to.getHorizontal() <= 1 && from.getPiece().getPlayer() == 2) {
						from.getPiece().promote();
					}

					if((to.getHorizontal() >= 8 && from.getPiece().getPlayer() == 1) || 
							to.getHorizontal() >= 8 && from.getPiece().getPlayer() == 1) {
						from.getPiece().promote();
					}

				} catch(Exception e) {

				}

				if(to.getPiece() != null) {
					if(to.getPiece().getPieceType().equals("CKing")|| to.getPiece().getPieceType().equals("SKing")) {
						JOptionPane.showMessageDialog(null, "Player " + from.getPiece().getPlayer() + " is the Winner!",
								"Game Over", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);
					}
	
				}

				from.setPiece(null);
				to.setPiece(null);
				to.setPiece(fromPiece);
			} 

			else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}

	public Square getPosition(int row, int column) {
		return board[row][column];
	}

	public String toString() {
		String returnText = "";
		for(int row = 0; row < board.length; row++) {
			for(int column = 0; column < board[row].length;column++) {
				if(board[row][column].getPiece() == null) {
					returnText += " ";
				} 
				else {
					returnText += " " + board[row][column].getPiece().getPieceDisplay() + " ";
				}
			}
			returnText += "\n";
		}
		return returnText;
	}
}
