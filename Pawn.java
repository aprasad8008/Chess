public class Pawn extends Piece { 

   public Pawn (int row, int col, boolean isWhite) 
   {
      super(row, col, isWhite); 
   }
    
    //overriden method 
   public boolean isValid(Board board, int toRow, int toCol) {
      if(super.isValid(board, toRow, toCol) == false)
         return false;
      
      //check for the individual peices, can't move forward or backward
      if ((isWhite() && toRow >= getRow()) || (!isWhite() && toRow <= getRow()))
      {
         return false; 
      }
      //check for making sure to not go over two spaces 
      if ((isWhite() && toRow - getRow() < -1) || (!isWhite() && toRow - getRow() > 1))
      {
         return false;
      }
      
      return true;
         
      
   }
}