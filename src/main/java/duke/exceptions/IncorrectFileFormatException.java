package duke.exceptions;

public class IncorrectFileFormatException extends Exception {

    @Override
    public String toString() {
        return "Wrong format in tasks.txt File, other than T/D/E";
    }
}
