public class Board  
{
      
   public static final int SIZE = 8;
   private final Spot[][] spots = new Spot[SIZE][SIZE];
   
   public Board()
   {
      for (int row = 0; row < SIZE; row++){
         for (int col = 0; col < SIZE; col++){
         
            spots[row][col] = new Spot(row, col);
         }
      }
   }
  
   public Spot getSpot(int row, int col) {
      return spots[row][col];
   }
   
}