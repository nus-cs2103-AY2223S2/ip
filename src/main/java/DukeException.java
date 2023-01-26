/**
 * File name: DukeException.java
 * @author: Jerome Neo
 * Description: DukeException inherits from Exception.
 */
public class DukeException extends Exception{
    /**
     * Constructor for exception. Takes in the message you want to include in this csutom exception.
     *
     * @param message
     */
    public DukeException(String message) {
        super(message);
    }
}
