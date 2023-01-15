package Duke.Exceptions;

public class EmptyCommandException extends Exception {
    public EmptyCommandException() {
        super(String.format("☹ OOPS!!! Stop making fun of me! Put something here as command now!"));
    }
}
