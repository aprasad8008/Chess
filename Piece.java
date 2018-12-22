public abstract class Piece {

   private boolean available;
   private int row;
   private int col;
   private boolean isWhite;

   public Piece(int row, int col, boolean isWhite) {
     
      this.available = true;
      this.row = row;
      this.col = col;
      this.isWhite = isWhite;
   }


   public boolean isAvailable() {
      return available;
   }
   public void setAvailable(boolean available) {
      this.available = available;
   }
   public int getRow() {
      return row;
   }
   public void setRow(int row) {
      this.row = row;
   }
   public int getCol() {
      return col;
   }
   public void setCol(int col) {
      this.col = col;
   }
   public boolean isWhite()
   {
       return isWhite;
   }

   public boolean isValid(Board board, int toRow, int toCol){
      
      if(toRow == row && toCol == col)
      {
         return false; //cannot stay at the same position
      }
      //Exceeds the board
      if(toRow < 0 || toRow > 7 || toCol < 0 || toCol > 7)
      {
         return false;
      }
      return true;
   }
   
   public boolean isAttacking(Board board, int row, int col)
   {
      
      return isValid(board, row, col) && board.getSpot(row, col).isOccupied();
      
   }
}