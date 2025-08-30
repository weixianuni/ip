package yappy.exception;

/**
 *
 */
public class YappyException extends Exception {

    /**
     * Constructs a new {@code YappyException} object with the corresponding error message.
     * This constructor calls the Exception superclass and initialises
     * the message field.
     *
     * @param message Error message to be printed out to user.
     */
    public YappyException(String message) {
        super(message);
    }
}