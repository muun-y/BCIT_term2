/**
 * The `ConsoleDisplayer` class is an implementation of the `Displayer` interface
 * that displays a given `Shape` object on the console. It contains a constructor
 * to initialize the displayer.
 */
public class ConsoleDisplayer implements Displayer {

    /**
     * Constructs a `ConsoleDisplayer` object.
     */
    public ConsoleDisplayer() {
        
    }

    /**
     * Displays the provided `Shape` object on the console by iterating through its
     * grid and printing each character.
     *
     * @param shape The `Shape` object to be displayed.
     */
    @Override
    public void displayShape(Shape shape) {
        for (int row = 0; row < shape.getHeight(); row++) {
            for (int col = 0; col < shape.getWidth(); col++) {
                System.out.print(shape.getCharAt(row, col));
            }
            System.out.println();
        }
    }
}
