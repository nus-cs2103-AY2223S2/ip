package Exceptions;

public class EmptyCommandException extends Exception{

    @Override
    public String toString() {
        return "Command cannot be empty";
    }
}
