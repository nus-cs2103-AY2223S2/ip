package Duke.Exceptions;

public class EmptyCommandException extends Exception{

    @Override
    public String toString() {
        return "Duke Command cannot be empty";
    }
}
