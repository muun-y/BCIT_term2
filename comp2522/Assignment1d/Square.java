/**
 * Assignment1d. 
 * The `Square` class is a subclass of `Rectangle` and represents a square shape.
 * It initializes a square with equal width and height.
 * 
 * @author Mun Young Cho
 */
public class Square extends Rectangle {
   /**
    * Constructs a Square object with the specified width. Both width and height
    * are set to the same value to create a square.
    * 
    * @param width the width of the square
    */
   public Square(int width, int height) {
      super(width, width);
   }
}
