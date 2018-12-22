import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessGUI extends JFrame implements MouseListener, MouseMotionListener
{
   
   //instance data from other chess classes
   private Board board = new Board();
   private Player whitePlayer = new Player(true);
   private Player blackPlayer = new Player(false);
   private Player currentPlayer = whitePlayer;
   private int fromRow = -1;
   private int fromCol = -1;
   private int toRow = -1;
   private int toCol = -1;
   private Point fromPoint;
   
   
   //instance data for GUI
   private JLayeredPane layeredPane;
   private JPanel chessBoard;
   private JLabel chessPiece;
   private int xAdjustment;
   private int yAdjustment;
   
   public ChessGUI(String title)
   {
      
      super(title);
      
      //setting up the board, with the speficified dimensions for the individual squares
      Dimension boardSize = new Dimension(800, 600);
   
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(boardSize);
      layeredPane.addMouseListener(this);
      layeredPane.addMouseMotionListener(this);
   
      chessBoard = new JPanel();
      layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
      chessBoard.setLayout( new GridLayout(Board.SIZE, Board.SIZE) );
      chessBoard.setPreferredSize( boardSize );
      chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
      
      //string used when adding pieces
      String[] topRow = {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
      int componentIndex = 0;
      
      for (int row = 0; row < Board.SIZE; row++)
      {
         for (int col = 0; col < Board.SIZE; col++)
         {
            //code alternating black and white boxes 
            boolean isWhite = ((row + col) % 2 == 0);
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
            square.setBackground(isWhite ? Color.WHITE : Color.BLACK );
            Player player = isWhite ? whitePlayer : blackPlayer; 
            String imageName = null;
            
            //code for adding pieces
            if (row == 0)
            {
               imageName = topRow[col] + "B.png";
            }
            else if (row == 1)
            {
               imageName = "PawnB.png";
            }
            else if (row == 6)
            {
               imageName = "PawnW.png";
            }
            else if (row == 7)
            {
               imageName = topRow[col] + "W.png";
            }
            
            if (imageName != null)
            {
               board.getSpot(row, col).occupySpot(player.getPieceAt(row, col));
               JLabel piece = new JLabel(new ImageIcon(imageName, imageName)); 
               square.add(piece);
            }
            
            componentIndex++;
         }
         
      }
   }
   
   //used when determining which player is playing
   private boolean isWhite(JLabel pieceLabel)
   {
      ImageIcon icon = (ImageIcon) pieceLabel.getIcon();
      String description = icon.getDescription();
      return description.contains("W.");
   }
   
   public void mousePressed(MouseEvent e)
   {
      chessPiece = null;
      Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
      if (c instanceof JPanel) 
         return;
      Point parentLocation = c.getParent().getLocation();
      xAdjustment = parentLocation.x - e.getX();
      yAdjustment = parentLocation.y - e.getY();
      chessPiece = (JLabel)c;
      
      //when moving a white piece or black piece twice in row 
      if ((isWhite(chessPiece) && currentPlayer == blackPlayer)
      || (!isWhite(chessPiece) && currentPlayer == whitePlayer))
      {
         chessPiece = null;
         return; 
      }
      
      fromRow = parentLocation.y / (chessBoard.getHeight() / Board.SIZE);
      fromCol = parentLocation.x / (chessBoard.getWidth() / Board.SIZE);
      System.out.println("from : " + fromRow + "," + fromCol);
      
      fromPoint = parentLocation;
      chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
      chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
      layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
   }
   
   public void mouseDragged(MouseEvent e) 
   {
      if (chessPiece == null) 
         return;
      chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
   }
     
   public void mouseReleased(MouseEvent e) 
   {
      if (chessPiece == null)
         return;
      
      Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
      Point parentLocation = c.getLocation();
      toRow = parentLocation.y / (chessBoard.getHeight() / Board.SIZE);
      toCol = parentLocation.x / (chessBoard.getWidth() / Board.SIZE);
      System.out.println("to : " + toRow + "," + toCol);
      Piece piece = currentPlayer.getPieceAt(fromRow, fromCol);
      boolean isValidMove = piece.isValid(board, toRow, toCol);
      System.out.println("" + isValidMove);
      if (isValidMove)
      {
         //cheching to make sure the correct player goes next 
         piece.setRow(toRow);
         piece.setCol(toCol);
         
         if (currentPlayer == whitePlayer)
         {
            currentPlayer = blackPlayer;
         }
         else 
         {
            currentPlayer = whitePlayer;
         }
         
         chessPiece.setVisible(false);
         //Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
         if (c instanceof JLabel){
            Container parent = c.getParent();
            parent.remove(0);
            parent.add( chessPiece );
         }
         else {
            Container parent = (Container)c;
            parent.add( chessPiece );
         }
         chessPiece.setVisible(true);
      
      }
      else 
      {
          chessPiece.setLocation(fromPoint);
          chessPiece = null;
          Toolkit.getDefaultToolkit().beep();
          
      }
      
      
   }
   
   public void mouseClicked(MouseEvent e) {
   
   }
   public void mouseMoved(MouseEvent e) {
   }
   public void mouseEntered(MouseEvent e){
   
   }
   public void mouseExited(MouseEvent e) {
   
   }
   public static void main(String[] args) {
      JFrame frame = new ChessGUI("Chess Board");
      frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
      frame.pack();
      frame.setResizable(true);
      frame.setLocationRelativeTo( null );
      frame.setVisible(true);
   }
}