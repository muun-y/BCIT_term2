/**
 * COMP2522 LabA1c. 
 * The Diamond class represents a diamond-shaped geometric shape, which is a specific type of Shape.
 * It provides a method to display a diamond with a given width and height using "#" symbols.
 * The width must be an odd number to create a symmetric diamond.
 * @author Mun Young Cho (A01330048)
 */
public class Diamond extends Shape {

    /**
     * Constructs a new Diamond with the specified width and height.
     *
     * @param width  The width of the diamond.
     * @param height The height of the diamond (automatically set to the same value as the width).
     * @throws BadWidthException If the width is an even number, which would result in an asymmetric diamond.
     */
    public Diamond(int width, int height) throws BadWidthException {
        super(width, width, "DIAMOND");
        
        if (width % 2 == 0) {
            throw new BadWidthException("Diamond width must be odd.");
        }
    }

    /**
     * Displays the diamond by printing "#" symbols to represent its shape.
     * The width and height specified during construction determine the size of the diamond.
     */
    public void display() {
        int n = width / 2;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("#");
            }
            if (i < height - 1) {
                System.out.println();
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("#");
            }
            if (i < height - 1) {
                System.out.println();
            }
        }
    }
}
