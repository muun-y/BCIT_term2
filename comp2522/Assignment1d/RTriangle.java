/**
 * The `RTriangle` class is a subclass of the `Shape` class and represents a Right Triangle
 * shape composed of '@' characters. It creates a right triangle pattern with the
 * specified width and height using width as the diagonal side length.
 * 
 * @author Mun Young Cho
 */
public class RTriangle extends Shape {
   /**
    * Constructs a Right Triangle object with the specified width and height. Note that it takes
    * parameters width and height, but height is set to the same value as width.
    * 
    * @param width The width (and height) of the right triangle.
    */
   public RTriangle(int width, int height) {
       super(width, width, "@");

       // Fill the grid with '@' characters to create the right triangle pattern
       for(int row = 0; row < width; row++) {
          for(int col = width - row - 1; col < width; col++) {
             this.grid[row][col] = this.displayChar;
          }
       }
   }
}
