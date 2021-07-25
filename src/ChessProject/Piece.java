package ChessProject;

public class Piece {
	
	private String pieceType = "Empty";
	private String pieceDisplay = "None";
	protected int player;
	protected boolean isPromoted = false;
	
	public Piece(int player) {
		this.player = player;
	}

	public String getPieceType() {
		return pieceType;
	}

	public String getPieceDisplay() {
		return pieceDisplay;
	}

	public int getPlayer() {
		return player;
	}

	public boolean canMove(Square from, Square to, Board b) {
		return false;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public void setPieceDisplay(String newDisplay) {
		this.pieceDisplay = newDisplay;
	}

	public void setPieceType(String newType) {
		this.pieceType = newType;
	}

	public boolean isPromoted() {
		return isPromoted;
	}
	public void promote() {
		isPromoted = true;

	}

	public void demote() {
		isPromoted = false;

	}
}
