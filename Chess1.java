package chess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chess1 extends JFrame implements MouseListener, MouseMotionListener
{
   private static final int SIZE = 8;
   
   private JLayeredPane layeredPane;
   private JPanel chessBoard;
   private JLabel chessPiece;
   private int xAdjustment;
   private int yAdjustment;
   
   public Chess1(String title)
   {
      super(title);
      Dimension boardSize = new Dimension(800, 600);
   
      layeredPane = new JLayeredPane();
      getContentPane().add(layeredPane);
      layeredPane.setPreferredSize(boardSize);
      layeredPane.addMouseListener(this);
      layeredPane.addMouseMotionListener(this);
   
      chessBoard = new JPanel();
      layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
      chessBoard.setLayout( new GridLayout(SIZE, SIZE) );
      chessBoard.setPreferredSize( boardSize );
      chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
      
      for (int row = 0; row < SIZE; row++) 
      {
         for (int col = 0; col < SIZE; col++)
         {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );
            square.setBackground( (row + col) % 2 == 0 ? Color.WHITE : Color.BLACK );        
         }
      }
      
      String[] topRow = {"Rook","Knight","Bishop","Queen","King","Bishop","Knight","Rook"};
      int componentIndex = 0;
      for (String name : topRow)
      {
         JLabel piece = new JLabel( new ImageIcon(name + "B.png") );
         JPanel panel = (JPanel)chessBoard.getComponent(componentIndex++);
         panel.add(piece);
      }
      
      for (int i = 0; i < SIZE; i++)
      {
         JLabel piece = new JLabel( new ImageIcon("PawnB.png") );
         JPanel panel = (JPanel)chessBoard.getComponent(componentIndex++);
         panel.add(piece);
      }
      
      componentIndex += SIZE*4;
      for (int i = 0; i < SIZE; i++)
      {
         JLabel piece = new JLabel( new ImageIcon("PawnW.png") );
         JPanel panel = (JPanel)chessBoard.getComponent(componentIndex++);
         panel.add(piece);
      }
      
      for (String name : topRow)
      {
         JLabel piece = new JLabel( new ImageIcon(name + "W.png") );
         JPanel panel = (JPanel)chessBoard.getComponent(componentIndex++);
         panel.add(piece);
      }
   
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
      chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
      chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
      layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
   }
   
   public void mouseDragged(MouseEvent me) {
      if (chessPiece == null) 
         return;
      chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
   }
     
   public void mouseReleased(MouseEvent e) {
      if(chessPiece == null) 
         return;
   
      chessPiece.setVisible(false);
      Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
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
   public void mouseClicked(MouseEvent e) {
   
   }
   public void mouseMoved(MouseEvent e) {
   }
   public void mouseEntered(MouseEvent e){
   
   }
   public void mouseExited(MouseEvent e) {
   
   }
   public static void main(String[] args) {
      JFrame frame = new Chess1("Chess Board");
      frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
      frame.pack();
      frame.setResizable(true);
      frame.setLocationRelativeTo( null );
      frame.setVisible(true);
   }
}