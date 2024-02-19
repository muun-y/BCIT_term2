/**
 * COMP2522 LabA1c. 
 * The Triangle class represents a triangular shape, which is a specific type of Shape.
 * It provides a method to display a triangle with a given width and height using "@" symbols.
 * The width must be an odd number to create a symmetric triangle.
 * @author Mun Young Cho (A01330048)
 */
public class Triangle extends Shape {

    /**
     * Constructs a new Triangle with the specified width and height.
     *
     * @param width  The width of the triangle.
     * @param height The height of the triangle (automatically calculated as width / 2 + 1).
     * @throws BadWidthException If the width is an even number, which would result in an asymmetric triangle.
     */
    public Triangle(int width, int height) throws BadWidthException {
        super(width, width / 2 + 1, "TRIANGLE");
        
        if (width % 2 == 0) {
            throw new BadWidthException("Triangle width must be odd.");
        }
    }

    /**
     * Displays the triangle by printing "@" symbols to represent its shape.
     * The width and height specified during construction determine the size of the triangle.
     */
    @Override
    public void display() {
        int maxStars = 1;
        int numSpaces = width / 2;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < numSpaces; j++) {
                System.out.print(' ');
            }
            for (int j = 0; j < maxStars; j++) {
                System.out.print('@');
            }
            System.out.println();

            maxStars += 2;
            numSpaces--;
        }
    }
}




