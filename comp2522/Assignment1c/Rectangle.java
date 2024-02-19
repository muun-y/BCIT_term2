/**
 * COMP2522 Lab A1c.
 * The Rectangle class represents a rectangular shape, which is a specific type of Shape.
 * It provides a method to display a rectangle of a given width and height using asterisk (*).
 * @author Mun Young Cho (A01330048)
 */
public class Rectangle extends Shape {

    /**
     * Constructs a new Rectangle with the specified width and height.
     *
     * @param width  The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(int width, int height) {
        super(width, height, "RECTANGLE");
    }

    /**
     * Displays the rectangle by printing asterisks (*) to represent its shape.
     * The width and height specified during construction determine the size of the rectangle.
     */
    public void display() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("*");
            }
            
                System.out.println();
            
        }
    }
}

