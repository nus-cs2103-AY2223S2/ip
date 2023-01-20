package Exceptions;

public class DukeMainExceptions extends RuntimeException{
    public DukeMainExceptions(String errorMsg) {
        super(errorMsg);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + super.getMessage();
    }
}
