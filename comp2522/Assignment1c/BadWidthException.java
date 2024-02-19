/**
 * COMP2522 LabA1c. 
 * The BadWidthException is a custom exception class that is thrown when an invalid width is provided.
 * This exception is typically used in cases where a specific width value is expected to meet certain criteria.
 * @author Mun Young Cho (A01330048)
 */
public class BadWidthException extends Exception {

    /**
     * Constructs a new BadWidthException with the specified error message.
     *
     * @param message The error message describing the reason for the exception.
     */
    public BadWidthException(String message) {
        super(message);
    }
}

