/**
 * COMP2522 Lab A1c. 
 * The Shape class represents a basic geometric shape with width and height dimensions.
 * Subclasses can define specific shapes by extending this class.
 * @author Mun Young Cho (A01330048)
 */
public class Shape {
    protected int width;
    protected int height;
    protected ShapeType type;

    /**
     * Constructs a new Shape with the specified width, height, and shape type.
     *
     * @param width  The width dimension of the shape.
     * @param height The height dimension of the shape.
     * @param t      A string representing the type of shape.
     */
    protected Shape(final int width, final int height, final String t) {
        this.width = width;
        this.height = height;
        this.type = ShapeType.valueOf(t.toUpperCase());
    }

    /**
     * Display method for rendering the shape.
     * Subclasses should override this method to provide specific rendering logic.
     */
    public void display() {
        // Default implementation does nothing. Subclasses should override.
    }
}

