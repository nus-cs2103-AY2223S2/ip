package Exceptions;

public class UnknownTaskException extends Exception {
    public UnknownTaskException(String s) {
        super("OH NO!! I DONT KNOW WHAT " + s.toUpperCase() + " MEANS!!");
    }
}