public class ToDoException extends Exception{
    String message;
    ToDoException(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}
