package roody.exceptions;

/**
 * Represents a custom exception for Roody chatbot.
 */
public class RoodyException extends Exception {
    public RoodyException(String s) {
        super("Oh no :( " + s + '\n');
    }
}
