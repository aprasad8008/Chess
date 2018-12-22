public class Knight extends Piece{

   public Knight(int row, int col, boolean isWhite) {
      super(row, col, isWhite);
   }

   @Override
   public boolean isValid(Board board, int toRow, int toCol) {
      if(super.isValid(board, toRow, toCol) == false)
         return false;
      
      //check for the L-shape of the knight
      boolean good1 = Math.abs(toRow - getRow()) == 1 && Math.abs(toCol - getCol()) == 2;
      boolean good2 = Math.abs(toRow - getRow()) == 2 && Math.abs(toCol - getCol()) == 1;
      return good1 || good2; 
      
   }
}