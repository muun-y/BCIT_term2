/**
 * Assignment1d. 
 * The `Rectangle` class represents a rectangle shape that extends the `Shape`
 * class. It initializes a rectangle pattern with the specified width and height,
 * using '*' characters.
 * 
 * @author Mun Young Cho
 */
public class Rectangle extends Shape {
    /**
     * Constructs a Rectangle object with the specified width and height and
     * stores it in the pattern array.
     * 
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(int width, int height) {
        super(width, height, "*");

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[row].length; col++) {
                this.grid[row][col] = this.displayChar;
            }
        }
    }
}
