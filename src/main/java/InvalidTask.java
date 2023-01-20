public class InvalidTask extends Exception{
    String message;
    InvalidTask(String str) {
        message = str;
    }
    public String toString() {
        return message;
    }
}
