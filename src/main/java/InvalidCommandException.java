public class InvalidCommandException extends DuduException{
    public InvalidCommandException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
