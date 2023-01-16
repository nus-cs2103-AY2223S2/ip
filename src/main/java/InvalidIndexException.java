public class InvalidIndexException extends IllegalArgumentException{
    InvalidIndexException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return "☹ OOPS!!! The index is out of bound";
    }

}
