public class DukeException extends Exception{
    String message;
    DukeException(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}

