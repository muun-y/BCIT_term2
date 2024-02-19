/**
 * Assignment1d. 
 * The `Triangle` class is a subclass of `Shape` class and represents a Triangle
 * shape composed of '@' characters. It creates a triangle pattern with the
 * specified width and height calculated using (width / 2 + 1) and throws a
 * `BadWidthException` if the width is even or negative.
 * @author Mun Young Cho
 */
public class Triangle extends Shape {
    /**
     * Constructs a `Triangle` object with the specified width and height. Throws a
     * `BadWidthException` if the width is even or negative. Note that it takes
     * parameters `width` and `height`, but `height` is calculated using
     * `(width / 2 + 1)`.
     * 
     * @param width the width of the triangle (must be odd and positive)
     * @param height
     * @throws BadWidthException if the `width` is even or negative
     */
    public Triangle(int width, int height) throws BadWidthException {
        super(width, width / 2 + 1, "@");

        if (width % 2 == 0 || width < 0) {
            throw new BadWidthException();
        } else {
            for (int row = 0; row <= width / 2; row++) {
                for (int col = width / 2 - row; col <= width / 2 + row; col++) {
                    this.grid[row][col] = this.displayChar;
                }
            }
        }
    }
}
