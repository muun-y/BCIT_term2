/**
 * Assignment1d. 
 * The `Shape` class is an abstract class representing a 2D shape composed of characters.
 * It provides a common structure for various shape types. 
 * 
 * This class contains information about the display character, width, height, and a grid
 * to represent the shape. It also includes methods to get the width, height, and character
 * at a specific position in the grid. Additionally, it has a method to display the shape
 * by printing it to the console.
 * 
 * @author Mun Young Cho
 */
public abstract class Shape {
    /** The character used to display the shape. */
    protected final String displayChar;
    
    /** The width of the shape. */
    protected final int width;
    
    /** The height of the shape. */
    protected final int height;
    
    /** The grid representing the shape. */
    protected final String[][] grid;

    /**
     * Get the width of the shape.
     *
     * @return The width of the shape.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the height of the shape.
     *
     * @return The height of the shape.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the character at a specific position in the shape's grid.
     *
     * @param row The row index.
     * @param col The column index.
     * @return The character at the specified position.
     */
    public String getCharAt(int row, int col) {
        return this.grid[row][col];
    }

    /**
     * Constructs a Shape object with the specified width, height, and type.
     * 
     * @param width The width of the shape.
     * @param height The height of the shape.
     * @param type The type of character used to display the shape.
     */
    protected Shape(int width, int height, String type) {
        this.width = width;
        this.height = height;
        this.grid = new String[height][width];
        this.displayChar = type;

        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[0].length; col++) {
                this.grid[row][col] = " ";
            }
        }
    }

    /**
     * Displays the pattern of the shape by iterating through the 'grid' array
     * and printing its characters.
     */
    public final void display() {
        for (int row = 0; row < this.grid.length; row++) {
            for (int col = 0; col < this.grid[0].length; col++) {
                System.out.print(this.grid[row][col]);
            }
            System.out.println();
        }
    }
}
