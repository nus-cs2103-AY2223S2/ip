package duke.exceptions;

public class InvalidCommandException extends Exception{

    @Override
    public String toString() {
        return "Invalid Duke Command";
    }
}