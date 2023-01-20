public class todoException extends Exception{
    String message;
    todoException(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}
