public class King extends Piece{

    public King(int row, int col, boolean isWhite) {
        
        super(row, col, isWhite);
        
    }

    @Override
    public boolean isValid(Board board, int toRow, int toCol) {
        if(!(super.isValid(board, toRow, toCol)))
            return false;
        
        //can't move greater than one
        if(Math.abs(getRow() - toRow) > 1 || Math.abs(getCol() - toCol) > 1)
        {
           return false;
        }
        //check whether king is being attacked
        for (int row = 0; row < Board.SIZE; row++)
        {
           for (int col = 0; col < Board.SIZE; col++)
           {
               Piece piece = board.getSpot(row, col).getPiece();
               if (piece != null && piece.isAttacking(board, toRow, toCol))
               {
                  return false; 
               }
           }
        }
        return true;
    }

}