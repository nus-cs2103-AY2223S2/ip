package Duke.Exceptions;

public class InvalidArgumentException extends Exception{

    @Override
    public String toString() {
        return "No Arguments given after Duke Command";
    }
}
