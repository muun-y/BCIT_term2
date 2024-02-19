import javax.swing.JFrame;

/**
 * Assignment1d. 
 * The `SwingDisplayer` class is an implementation of the `Displayer` interface
 * that displays a shape using a Swing GUI window. It creates a `DisplayerFrame`
 * to display the shape.
 * @author Mun Young Cho
 */
public class SwingDisplayer implements Displayer {
    /**
     * Displays the specified shape in a Swing GUI window.
     *
     * @param shape the shape to display
     */
    public void displayShape(final Shape shape) {
        final DisplayerFrame frame;

        frame = new DisplayerFrame();
        frame.init(shape);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
