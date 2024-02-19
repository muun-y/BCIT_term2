public class Diamond extends Shape {
  /**
   * Constructs a Diamond object with the specified width and height. Throws a
   * BadWidthException if the width is even or negative. Note that it takes
   * parameters width and height but height uses width.
   * 
   * @param width
   * @param height
   * @throws BadWidthException
   */
  public Diamond(int width, int height) throws BadWidthException {
    super(width, width, "#");
    
    if (width % 2 == 0) {
      throw new BadWidthException();
    } else {
      int row;
      int col;
      for (row = 0; row <= width / 2; row++) {
        for (col = width / 2 - row; col <= width / 2 + row; col++) {
          this.grid[row][col] = this.displayChar;
        }
      }

      row = width / 2 + 1;

      for (col = width / 2 - 1; col >= 0; --col) {
        for (int k = width / 2 - col; k <= width / 2 + col; k++) {
          this.grid[row][k] = this.displayChar;
        }

        row++;
      }

    }
  }
}



