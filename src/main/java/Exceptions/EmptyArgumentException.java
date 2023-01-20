package Exceptions;

public class EmptyArgumentException extends Exception{

    @Override
    public String toString() {
        return "No Arguments given after Command";
    }
}
