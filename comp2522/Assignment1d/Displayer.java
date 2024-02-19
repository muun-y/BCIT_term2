/**
 * Assignment1d.
 * The Displayer interface defines a contract for classes that can display shapes.
 * Classes implementing this interface should provide an implementation for the displayShape method,
 * which takes a Shape object as its parameter and displays it in some way.
 * 
 * @author Mun Young Cho
 */
public interface Displayer {
    /**
     * Displays the provided Shape
     * 
     * @param shape The Shape object to be displayed.
     */
    void displayShape(Shape shape);
}

