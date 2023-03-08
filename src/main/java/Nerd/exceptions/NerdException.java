package Nerd.exceptions;

/**
 * A wrapper around the Exception class for the Nerd Chat bot.
 */
public class NerdException extends Exception {
    public NerdException(String message) {
        super(message);
    }
}