public class Bishop extends Piece{

    public Bishop(int row, int col, boolean isWhite) {
        
        super(row, col, isWhite);
        
    }

    //overriden method
    public boolean isValid(Board board, int toRow, int toCol) {
        if(super.isValid(board, toRow, toCol) == false)
            return false;
        
        //diagonal moves
        return Math.abs(toRow - getRow()) == Math.abs(toCol - getCol());
            
    }

}