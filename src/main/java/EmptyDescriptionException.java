public class EmptyDescriptionException extends IllegalArgumentException{

    EmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + getMessage();
    }

}
