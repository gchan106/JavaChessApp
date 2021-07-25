package ChessProject;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class ChessGame extends JFrame {
	JLabel Timer;
	private JLabel labelTime;
	JButton forfeitTurn;



	private static final long serialVersionUID = 6349187091886546866L; //Serial UID


	static JPanel chessBoardDisplay = new JPanel(new GridLayout(9,9));
	static JFrame boardFrame = new JFrame();
	static JButton[][] squaresJB = new JButton[9][9];
	static Board gameBoard = new Board();
	static JPanel bottomMenu = new JPanel(new GridLayout(1,3));
	
	//A variable that handles which Square the player currently has selected
	static Square lastClicked = null;

	//Handles the current turn
	protected static int turn = 1;
	protected static int timeRemaining=30;
	private TimerTurnChange timer;		

	public static void changeTurn() {
		if (turn == 1)
				turn = 2;
		else
			turn = 1;		
	}

 	public ChessGame() {
		labelTime = new JLabel("Timer");
		timer=new TimerTurnChange(labelTime);
		boardFrame.setLayout(new BorderLayout());
		forfeitTurn = new JButton("Forfeit Turn");
;
		forfeitTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a new game
				changeTurn();
			}
		});

		boardFrame.setSize(1300,1300);
		boardFrame.setTitle("Chess Game");
		boardFrame.add(chessBoardDisplay,BorderLayout.CENTER);
		bottomMenu.add(labelTime);
		bottomMenu.add(forfeitTurn);
		boardFrame.add(bottomMenu,BorderLayout.PAGE_END);
		boardFrame.setResizable(true);
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		if (timeRemaining < 1) {
			if (turn == 1) {
					turn = 2;
					timer.reset();
			}
			else {
				turn = 1;
				timer.reset();
			}
		}
		for(int row=0;row<9;row++) {
		for(int column=0;column<9;column++) {
				//Initialize and add to the button in the board array		
				squaresJB[row][column] = new JButton();	
				//outlines the squareJB;
				squaresJB[row][column].setBorder(new LineBorder(Color.BLACK));
				squaresJB[row][column].setBackground(Color.decode("#db9336"));
				//add a ActionListner to create a even that happens when a square is clicked
				squaresJB[row][column].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//For each square on the board
						for(int row = 0 ; row < 9 ; row++) {
						for(int column = 0;column < 9; column ++) {
								//Find the square J button in the 2D array that was just clicked
							if(e.getSource() == squaresJB[row][column ]) {
								int turnNumber = 0;
									//Check if the square contains a piece
								try {		// look at this position , get the player owner if no player then catch exception of having missing this player, turn expection at int = 0
										turnNumber = gameBoard.getPosition(row, column ).getPiece().getPlayer();} 
								catch(Exception genericException) {}
							//If it's the turn of the piece on the square
							//and the last clicked square is null
							if(lastClicked == null && turnNumber == turn) {
							//Does a piece exist
							if(gameBoard.getPosition(row, column ).getPiece() != null) {
							// if moving there and tnere contains no piece,
							//set the lastclicked variable to handle movement on
							//the next click
							lastClicked = gameBoard.getPosition(row, column );
							timer.start();
							
							//Get every square on the board and highlight possible moves
							for(int i = 0; i< 9 ;i++) {
							for(int j = 0; j< 9 ;j++) {
								if(gameBoard.getPosition(row, column ).getPiece().canMove(gameBoard.getPosition(row, column ),gameBoard.getPosition(i, j), gameBoard)) {
									squaresJB[i][j].setBackground(Color.decode("#db2336"));	
									timer.reset();
									}
								}
								}
								}
								} else {
									try {
										//If a piece is already selected, and a new square
										//was clicked, try to move the piece
										gameBoard.movePiece(lastClicked, gameBoard.getPosition(row, column ), turn);
										squaresJB[row][column ].setForeground(Color.BLACK);
										//clear lastclicked to allow the next move as clicking gets the square
										lastClicked = null;	
										changeTurn();
										updateBoard();
									} catch (Exception genericException) {											
										//If the move failed, clear lastclicked and allow
										//the player to try to move again
										genericException.printStackTrace(System.out);
										System.out.println("Invalid Move");
										lastClicked = null;
										updateBoard();
																}
											
								}
							}
						}
					}
				}
			}
		);
		//after creating a Jbutton add it the board
		chessBoardDisplay.add(squaresJB[row][column]);
		
	}
			
}

		updateBoard();
		boardFrame.setVisible(true);
		//Redraw the graphics to show the squares
		boardFrame.revalidate();
		boardFrame.repaint();
	}

//set icons for black
public static void DisplaySetterBlack(int row, int column, String fileLocation,String fileLocation2, String pieceType){
	if(gameBoard.getPosition(row , column).getPiece().getPieceType() == (pieceType)) {
	if(gameBoard.getPosition(row , column).getPiece().isPromoted() == (true)) {
			Icon Piece = new ImageIcon(fileLocation);
			squaresJB[row][column].setForeground(Color.BLACK);
			squaresJB[row][column].setIcon(Piece);
		} 
		else {
	Icon Pawn = new ImageIcon(fileLocation2);
	squaresJB[row][column].setForeground(Color.BLACK);
	squaresJB[row][column].setIcon(Pawn);
			}
		}
	}

public static void DisplaySetterWhite(int row, int column, String fileLocation,String fileLocation2, String pieceType){
	if(gameBoard.getPosition(row , column).getPiece().getPieceType() == (pieceType)) {
	//Set piece color for player one
	//we could also set pieces icon here
		if(gameBoard.getPosition(row , column).getPiece().isPromoted() == (true)) {
			Icon Piece = new ImageIcon(fileLocation);
			squaresJB[row][column].setForeground(Color.BLACK);
			squaresJB[row][column].setIcon(Piece);
		} 
		else {
	Icon Pawn = new ImageIcon(fileLocation2);
	squaresJB[row][column].setForeground(Color.BLACK);
	squaresJB[row][column].setIcon(Pawn);
			}
		}
	}

	
	public static void updateBoard() {
		//For pieces in the 9x9 board
		for(int row = 0; row < 9; row ++) {
		for(int column = 0; column < 9; column++) {

			if(gameBoard.getPosition(row , column).getPiece() != null) {
					//apply checker pattern
					squaresJB[row ][column].setText(gameBoard.getPosition(row , column).getPiece().getPieceDisplay());
					if((row + column ) % 2 == 0)
					squaresJB[row][column].setBackground(Color.decode("#d96336"));
					else
					squaresJB[row][column].setBackground(Color.decode("#db7336"));
					//in this array check if there is a player if so check what piece type and display
					if(gameBoard.getPosition(row , column).getPiece().getPlayer() == 1) {
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiSilverGeneralPromote.png","Chess/Source Folder/images/black/ShogiSilverGeneral.png","Silver");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiGoldGeneral.png","Chess/Source Folder/images/black/ShogiGoldGeneral.png","Gold");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiPawnPromote.png","Chess/Source Folder/images/black/ShogiPawn.png","SPawn");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessPawn.png","Chess/Source Folder/images/black/ChessPawn.png","CPawn");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiBishopPromote.png","Chess/Source Folder/images/black/ShogiBishop.png","SBishop");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessBishop.png","Chess/Source Folder/images/black/ChessBishop.png","CBishop");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessKing.png","Chess/Source Folder/images/black/ChessKing.png","CKing");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiKing.png","Chess/Source Folder/images/black/ShogiKing.png","SKing");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessRook.png","Chess/Source Folder/images/black/ChessRook.png","CRook");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiRookPromote.png","Chess/Source Folder/images/black/ShogiRook.png","SRook");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessKnight.png","Chess/Source Folder/images/black/ChessKnight.png","CKnight");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiKnightPromote.png","Chess/Source Folder/images/black/ShogiKnight.png","SKnight");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ShogiLancePromote.png","Chess/Source Folder/images/black/ShogiLance.png","SLance");
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/black/ChessQueen.png","Chess/Source Folder/images/black/ChessQueen.png","CQueen");
	
					} else {
						DisplaySetterBlack(row,column,"Chess/Source Folder/images/white/ChessQueen.png","Chess/Source Folder/images/white/Queen.png","CQueen");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiSilverGeneralPromote.png","Chess/Source Folder/images/white/ShogiSilverGeneral.png","Silver");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiGoldGeneral.png","Chess/Source Folder/images/white/ShogiGoldGeneral.png","Gold");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiPawnPromote.png","Chess/Source Folder/images/white/ShogiPawn.png","SPawn");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ChessPawn.png","Chess/Source Folder/images/white/ChessPawn.png","CPawn");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiBishopPromote.png","Chess/Source Folder/images/white/ShogiBishop.png","SBishop");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ChessBishop.png","Chess/Source Folder/images/white/ChessBishop.png","CBishop");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ChessKing.png","Chess/Source Folder/images/white/ChessKing.png","CKing");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiKing.png","Chess/Source Folder/images/white/ShogiKing.png","SKing");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ChessRook.png","Chess/Source Folder/images/white/ChessRook.png","CRook");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiRookPromote.png","Chess/Source Folder/images/white/ShogiRook.png","SRook");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ChessKnight.png","Chess/Source Folder/images/white/ChessKnight.png","CKnight");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiKnightPromote.png","Chess/Source Folder/images/white/ShogiKnight.png","SKnight");
						DisplaySetterWhite(row,column,"Chess/Source Folder/images/white/ShogiLancePromote.png","Chess/Source Folder/images/white/ShogiLance.png","SLance");
					}
			
					} else {
						//If square is empty, this code will clear text & icon here
						// also repaints the empty squares with a grid
						if((row + column ) % 2 == 0)
							squaresJB[row][column].setBackground(Color.decode("#d96336"));
							else
							squaresJB[row][column].setBackground(Color.decode("#db7336"));
						squaresJB[row ][column].setText("");
						squaresJB[row ][column].setIcon(null);
				}
			}
		}
	}
	public static void main(String args[]) {
	
				new ChessGame();

}
}

