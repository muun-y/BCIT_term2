/**
 * The `DisplayerFrame` class is responsible for displaying the shape within a
 * JFrame. It uses a GridBagLayout to arrange the shape's characters in the frame.
 * This code is provided by the instructor.
 */
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridBagConstraints;

public class DisplayerFrame extends JFrame {
    /**
     * Initializes the frame and arranges the characters of the provided `Shape` object
     * using a GridBagLayout.
     *
     * @param shape The `Shape` object to be displayed within the frame.
     */
    public void init(final Shape shape) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        for (int row = 0; row < shape.getHeight(); row++) {
            for (int col = 0; col < shape.getWidth(); col++) {
                c.gridx = col;
                c.gridy = row;
                add(new JButton(shape.getCharAt(row, col)), c);
            }
        }
    }
}
