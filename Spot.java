public class Spot {
    
    private int x;
    private int y;
    private Piece piece;

    public Spot(int x, int y) {
        
        this.x = x;
        this.y = y;
        piece = null;
    }
    
    public Piece getPiece()
    {
       return piece;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public void occupySpot(Piece piece){
        //When piece is already there, remove it and take its place 
        if(this.piece != null)
            this.piece.setAvailable(false);
        //replacing the old piece 
        this.piece = piece;
    }

    public boolean isOccupied() {
        
        return piece != null;
            
    }

    public Piece releaseSpot() {
        
        Piece releasedPiece = piece;
        piece = null;
        return releasedPiece;
    }

}