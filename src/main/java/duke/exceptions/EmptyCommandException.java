package duke.exceptions;

public class EmptyCommandException extends Exception{

    @Override
    public String toString() {
        return "Duke Command cannot be empty";
    }
}
