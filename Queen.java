public class Queen extends Piece{

   public Queen(int row, int col, boolean isWhite) {
      super(row, col, isWhite);
   }

    //overriden method
   public boolean isValid(Board board, int toRow, int toCol) {
      if(super.isValid(board, toRow, toCol) == false)
         return false;
        //diagonal
      boolean good = Math.abs(toRow - getRow()) == Math.abs(toCol - getCol()) || toRow == getRow() || toCol == getCol();
        
      if (!good)
      {
         return false;
      }
        
      int deltaRow;
      
      if (toRow > getRow())
      {
         deltaRow = 1;
      }
      else if (toRow < getRow())
      {
         deltaRow = -1;
      }
      else 
      {
         deltaRow = 0;
      }

      int deltaCol; 
        
      if (toCol > getCol())
      {
         deltaCol = 1;
      }
      else if (toCol < getCol())
      {
         deltaCol = -1;
      }
      else 
      {
         deltaCol = 0;
      }
      
      int currentRow = getRow(); 
      int currentCol = getCol();
      
      while (currentRow != toRow || currentCol != toCol)
      {
         currentRow += deltaRow;
         currentCol += deltaCol;
         if (board.getSpot(currentRow, currentCol).isOccupied())
         {
            return false;
         }
      }
        
      return true;
   }

}