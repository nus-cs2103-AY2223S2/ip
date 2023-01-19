package Exceptions;

/**
 * The class represents the EmptyCommandException
 */
public class EmptyCommandException extends Exception {
    /**
     * The constructor for EmptyCommandException
     */
    public EmptyCommandException() {
        super(String.format("    OOPS!!! Stop making fun of me! Put something here as command now!\n"));
        //System.out.println("OOPS!!! Stop making fun of me! Put something here as command now!");
    }
}
